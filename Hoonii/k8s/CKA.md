# 0. 출처
1. [FastCampus](https://fastcampus.co.kr/dev_online_kubemsa)
2. [k8s docs](https://kubernetes.io/docs/)

# 1. etcd
- key-value 형태의 데이터 스토리지
- k8s cluster 의 정보를 저장해서 관리
- 모든 etcd 데이터는 별도의 파일로 보관 ( /var/lib/etcd )

- etcd 는 master node 에서 동작하는 static pod 다. ( control plane component )
  - static pod 는 특정 경로 밑에 yaml 파일을 저장하면 자동으로 동작하는 pod

## 1-1. etcd backup & restore
- etcd 는 k8s cluster 내의 모든 정보를 저장하고 있어 특정 시점의 데이터를 snapshot 으로 저장하여 복구 가능
- etcd 혹은 mater node 장애 상황을 대비해 snapshot 을 백업하는건 필수

```shell
ETCDCTL_API=3 etcdctl --endpoints=https://127.0.0.1:2379 \
  --cacert=<trusted-ca-file> --cert=<cert-file> --key=<key-file> \
  snapshot save <backup-file-location>
```

```shell
ETCDCTL_API=3 etcdctl snapshot restore --data-dir <data-dir-location> snapshotdb
```

- etcd 는 별도의 etcdctl 을 통해 etcd backup / restore api 에 접근 가능
- etcd 는 static pod 이기에 새로운 경로에 restore 이후 yaml 을 수정하면 자동으로 pod 가 재기동


# 2. k8s cluster upgrade
- [k8s docs](https://kubernetes.io/ko/docs/tasks/administer-cluster/kubeadm/kubeadm-upgrade/)
- k8s package
  - kubeadm : 클러스터를 부트스트랩하는 명령
  - kubelet : Pod 와 Controller 시작과 같은 작업 수행 컴포넌트
  - kubectl : 클러스터와 통신하기 위한 command line 유틸리티
  - 업그레이드 진행 시 위 패키지 구성요소를 모두 업그레이드 필요

# 2-1. Node Upgrade
- kubeadm upgrade apply 시 , control plane 컴포넌트 즉, static pod 들이 업그레이드 됨
---
- Master Node Drain 시
  - control plane 컴포넌트도 결국 static 이지만 pod 이기에 동작이 중지
  - drain 이후 uncorden 시 static pod 들은 자동 시작
---
- Node Drain
  - 업그레이드 시 Node 를 Drain 상태로 만들고 완료 시 해지한다 
  - 드레인 상태 시 
    - 동작중인 파드를 재 스케줄링하여 다른 노드로 이동
    - 노드를 비활성화 하여 스케줄링된 pod 가 할당되지 않도록 동작
    - 클러스터 이벤트로 드레인 상태 및 파드 이동에 대한 정보를 추적하고 모니터링 가능
---
- 업그레이드는 k8s docs 에 맞춰 진행하면 됨

# 3. rbac 인증 ( 역할 기반 인증 )
[k8s docs rbac](https://kubernetes.io/docs/reference/access-authn-authz/rbac/)
[k8s docs cert & csr](https://kubernetes.io/docs/reference/access-authn-authz/certificate-signing-requests/#normal-user)
- k8s api server 에서 인증은 2가지 형태로 진행

1. rbac ( roll-based access control )
	1. 인증 단위
		1. User ( Linux 가 아닌 k8s 계정 )
		2. Service Account ( 각 Pod 엔 SA 가 할당되어 인증 시 사용 가능 )
2. abac ( attribute-based access control )

# 3-1. rbac
1. 동작 과정
	1. 요청 수신
		1. kubectl 로 실행한 명령은 항상 master node 의 api 서버로 전달
		   
	2. Authentication ( 인증 )
		1. 요청자가 User / SA 를 전달하면 이 정보가 정상인지 허용 / 거부 판단
		   
	3. Authorization ( 인가 )
		1. 인증된 User / SA 에 할당하는 범위 제어
		   
	4. Admission Control
		1. 해당 동작이 k8s 동작에 영향을 주거나 지정된 동작 범위를 벗어나는지 판단
		   
	5. 승인
---
- Authorization 관련
  - Role
	  - 어떤 API 에 대한 권한 허용인지 명시
	  - ex, Pod - get , create 가능 + Deployment - get , create , delete 가능 등
  - Role Binding
	  - Role + User or SA 연결

- 위 Role / Role Binding 은 한 Namespace 단위로 동작하고, Cluster 전체 단위로 사용은 Cluster Role 과 Cluster Role Binding 이 사용된다.


# 4. Pod

[k8s docs pod env](https://kubernetes.io/ko/docs/tasks/inject-data-application/environment-variable-expose-pod-information/)  
[k8s docs pod command line](https://kubernetes.io/ko/docs/tasks/inject-data-application/define-command-argument-container/)  
[k8s docs pod logging architecture](https://kubernetes.io/ko/docs/concepts/cluster-administration/logging/)

## 4-1. Static Pod
- static pod 는 컨테이너로 실행되지 않고 노드 자체 프로세스로 실행
	- 때문에 동작 노드의 docker 프로세스로 확인이 불가능
	- 그래서 docker ps 가 아닌 pstree 로 static pod 확인 가능
- static pod 는 해당 노드의 kubelet 데몬에 의해 동작되고 관리
	- 하지만 kubelet 프로세스의 미동작 여부와는 관계없음
	- kubelet 프로세스가 죽는다고 동작중인 static pod 프로세스가 죽진 않음
		- 새로운 static pod 생성은 안될듯

## 4-2. sidecar-container ( 중요 )
- 둘 이상의 컨테이너가 서로 종속되어 실행되는 pod 환경
	- 보통 공용 directory 에 여러 container log 를 저장하고 분석하거나 monitoring 솔루션에서 이를 활용 하여 사용하도록 구성

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: eshop-cart-app
spec:
  containers:
  - name: eshop-cart
    image: busybox:1.28
    args:
    - /bin/sh
    - -c
    - >
      i=0;
      while true;
      do
        echo "$i: $(date)" >> /var/log/cart-app.log;
        i=$((i+1));
        sleep 1;
      done      
    volumeMounts:
    - name: cart-log
      mountPath: /var/log
  - name: cart-app-log-1
    image: busybox:1.28
    args: [/bin/sh, -c, 'tail -n+1 -f /var/log/cart-app.log']
    volumeMounts:
    - name: varlog
      mountPath: /var/log
  volumes:
  - name: varlog
    emptyDir: {}
```

- eshop-cart container 는 log 를 공용 volume 에 생성
- cart-log container 는 해당 log 를 표준출력으로 내보내서 kubectl log [pod 이름] -c cart.log 로 확인 가능하도록 동작
- 중요한건 두 컨테이너가 동일 volume 을 mount 하여 공유해서 사용하여 서로의 종속성을 가져간다는 것

# 5. Deployment

[k8s docs deployment](https://kubernetes.io/ko/docs/concepts/workloads/controllers/deployment/)

- ReplicaSet 에서 생성한 Pod 이름은 "[RS 이름]-[랜덤 Hash]"
	- 때문에 Pod 에 문제가 생겨 새롭게 생성하면 랜덤 hash 로 인해 이름이 바뀜

- Pod 목록 이름만 저장하라는 문제
	- kubectl get pods | grep eshop | awk -F' ' '{print $1}' | grep -v NAME
		- awk -F' ' 이건 공백 기준으로 필드를 구분 -> 구분된 필드의 $1 첫 번째 필드를 출력 -> grep -v 는 grep 의 반대로 해당 문구는 뺴고 출력

- Rolling Update 시 ReplicaSet 은 신규 ReplicaSet 으로 대체된다
	- 즉 기존 Pod 이름 앞에 RS 명 ( RS Hash ) 과 Pod Hash 값이 바뀐다


# 6. Node 관련

## 6-1. Node 관리
- Node 스케줄링 중단 및 허용

1. Node Drain VS Node Cordon
	1. Node Drain ( 노드 비우기 )
		1. 해당 노드에 더 이상 Pod 가 스케줄링 되지 않고 , 기존 동작중인 Pod 는 재 스케줄링하여 중단 없이 다른 Node 로 이동 시킴
		2. 노드를 유지보수 , 업그레이드 , 확장 , 비상상황 등에 사용   
	2. Node Cordon
		1. 해당 노드에 더 이상 Pod 가 스케줄링 되지 않고 , 기존 동작중인 Pod 는 그대로 유지
		2. 기존 동작중인 Pod 들에 Resource 를 보장하기 위해 사용

두 가지 모두 해제는 kubectl uncordon [node 이름]

## 6-2. Node 정보

[k8s docs taint & tolerance](https://kubernetes.io/ko/docs/concepts/scheduling-eviction/taint-and-toleration/)

- Node 엔 taint 지정 가능
	- 기본적으로 Master Node 엔 "NoSchedule" effect 의 Taint 가 설정되어있다. ( key : effect 구조 )
		- kubectl describe nodes (노드 명) | grep -i taint
			- 확인 명령어
	- taint 구조
		- key=value:effect
- Pod 엔 tolerance 지정 가능

- 때문에 Master Node 엔 일반적으로 Pod 가 배포되지 않는다.
- Pod 에 NoSchedule effect 의 tolerance 를 지정하면 master node 에도 해당 Pod 가 스케줄링되도록 구성할 수 있다.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: nginx
  labels:
    env: test
spec:
  containers:
  - name: nginx
    image: nginx
    imagePullPolicy: IfNotPresent
  tolerations:
  - key: "node-role.kubernetes.io/master"
    operator: "Exists"
    effect: "NoSchedule"
```

- 해당 키가 일치하는 노드와 키가 없는 노드에도 할당되도록 스케줄링한다.
	- 즉 , Master & Worker Node 모두 스케줄링


# 7. Service

- 하나의 서비스는 안정성과 확장성을 고려하여 여러개의 Pod 로 분산 배포된다. ( Deployment - RS )
- 분산 배포된 Pod 는 각각의 IP 가 동적으로 할당되기에 단일 진입점이 요구된다.
- 이를 위해 k8s 의 Service 를 사용한다.

## 7-1. Service 동작 원리

- Serivce 관련 kubectl 요청은 api-server 로 전달된다.
- Headless 가 아닌 Service 의 경우 단일진입점인 ClusterIP 가 할당되는데 해당 ClusterIP 를 통해 LoadBalacing 이 가능하도록 각 노드의 Kubelet 에게 명령이 전달된다.
	- 정확히 iptables 를 통해 nat 동작이 가능하도록 명령이 전달
- 해당 요청을 수신한 kubelet 은 iptablels 생성 권한이 있는 kube-proxy 에게 요청을 전달한다.
- kube-proxy 는 iptables 구분을 추가하고 관리하여 LB 가 가능하도록 구성한다.
	- 실제 노드에서 iptables -t nat -L 혹은 iptables-save 를 통해 상세 동작을 확인할 수 있다

## 7-2. Service ClusterIP Type

[k8s docs service clusterIP](https://kubernetes.io/ko/docs/concepts/services-networking/service/#%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%A0%95%EC%9D%98](https://kubernetes.io/ko/docs/concepts/services-networking/service/#%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%A0%95%EC%9D%98)

- 단일진입점만 생성하는 개념
- Service Type 생략 시 자동으로 ClusterIP Type 으로 동작
- ClusterIP 미지정 시 자동으로 10.96.0.0/12 범위 내에서 부여

1. 생성 방법
	1. CLI
		1. kubectl expose deployment nginx-dep --type=ClusterIP --port=80 --target-port=80
	2. Yaml 
```yaml
apiVersion: v1
kind: Service
metadata:
  creationTimestamp: null
  labels:
    app: nginx-dep
  name: nginx-dep
spec:
  ports:
  - port: 80
    protocol: TCP
    targetPort: 80
  selector:
    app: nginx-dep
  type: ClusterIP
status:
  loadBalancer: {}
```


## 7-3. Service NodePort Type

[k8s docs service nodeport](https://kubernetes.io/ko/docs/concepts/services-networking/service/#type-nodeport)

- ClusterIP 동작 + 각 노드마다 하나의 포트를 지정하여 Load Balacing 수행
- NodePort 를 지정하지 않으면 30000 ~ 32767 범위 내에서 랜덤 선택
- clusterip 와 같이 nodeport 도 결국 iptables 로 동작하여 전체 Pod endpoint 목록 중 Load Balancing

```yaml
apiVersion: v1
kind: Service
metadata:
  name: my-service
spec:
  type: NodePort
  selector:
    app.kubernetes.io/name: MyApp
  ports:
      # 기본적으로 그리고 편의상 `targetPort` 는 `port` 필드와 동일한 값으로 설정된다.
    - port: 80
      targetPort: 80
      # 선택적 필드
      # 기본적으로 그리고 편의상 쿠버네티스 컨트롤 플레인은 포트 범위에서 할당한다(기본값: 30000-32767)
      nodePort: 30007
```


# 8. Network Policy

[k8s docs network policy](https://kubernetes.io/ko/docs/concepts/services-networking/network-policies/)

- Pod 로 들어오고 (ingress) , 나가는 (egress) 트래픽을 제어할 수 있는 Whitelist 방식의 Stateful 방화벽 역할
- 단 , Flannel CNI 를 사용하는 Cluster 의 경우 Control 을 지원하지 않아 Network Policy 를 사용할 수 없다, Flannel 을 사용하는 경우 Canal 과 같이 사용해야 한다.
	- [관련 문서](https://banzaicloud.com/docs/pipeline/security/network-policy/network-plugins/)

트래픽을 4가지 타입으로 분류하여 허용 정책을 수행한다.
- ipBlock
	- CIDR IP 대역으로, 특정 IP 대역에서만 트래픽이 들어오도록 지정
- podSelector
	- Pod 에 label 을 지정하여, 특정 label 로 동작하는 Pod 들만 트래픽이 허용
- namespaceSelector
	- Namespace 에 label 을 지정하여, 특정 label 로 동작하는 namespace 에서만 트래픽 허용
- Protocol & Port
	- 특정 Protocol 혹은 Port 로 설정된 트래픽만 허용되는 포트 정의

- 예시
```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: test-network-policy
  namespace: default
spec:
  podSelector:
    matchLabels:
      role: db
  policyTypes:
    - Ingress
    - Egress
  ingress:
    - from:
        - ipBlock:
            cidr: 172.17.0.0/16
            except:
              - 172.17.1.0/24
        - namespaceSelector:
            matchLabels:
              project: myproject
        - podSelector:
            matchLabels:
              role: frontend
      ports:
        - protocol: TCP
          port: 6379
  egress:
    - to:
        - ipBlock:
            cidr: 10.0.0.0/24
      ports:
        - protocol: TCP
          port: 5978
```


# 9. Ingress

[k8s docs ingress](https://kubernetes.io/ko/docs/concepts/services-networking/ingress/)

- 클러스터에 접근하는 트래픽을 대상으로 L7 로드밸런싱을 수행 ( ALB : Application Load Balacer )
- Http 의 경우 URL 을 바탕으로 Service 에 Load Balacing 할 수 있다
- Ingress 의 다양한 기능
	- SSL 인증서 처리
	- Virtual Hosting 을 지정 가능

- 예시
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: ingress-wildcard-host
spec:
  rules:
  - host: "foo.bar.com"
    http:
      paths:
      - pathType: Prefix
        path: "/bar"
        backend:
          service:
            name: service1
            port:
              number: 80
  - host: "*.foo.com"
    http:
      paths:
      - pathType: Prefix
        path: "/foo"
        backend:
          service:
            name: service2
            port:
              number: 80
```


# 10. kube-dns 

[k8s docs dns servcie](https://kubernetes.io/ko/docs/concepts/services-networking/dns-pod-service/)

- 쿠버네티스 클러스터에서 동작하는 DNS
- 모든 Service 는 DNS 이름이 할당된다.
	- [svc-name].[namespace-name].svc.cluser.local
- 클러스터 내 Pod 의 /etc/resolv.conf 에는 kube-dns 가 nameserver 로 등록되어 동작한다.
	- 기본 search 정보 ( 쿼리 시 fqdn 을 안쓰고 생략 가능한 domain )
		- [namespace-name].svc.cluster.local , svc.cluster.local , cluster.local
- pod 이름이나 service 이름으로 DNS 쿼리로 접근 가능
	- [svc-name].[namespace-name].svc.cluser.local
	- [pod-id].[namespace-name].pod.cluster.local


# 11. Volume mount - emptyDir

[k8s docs empty dir](https://kubernetes.io/ko/docs/concepts/storage/volumes/#emptydir)

- Pod 생성 시 빈 디렉토리를 생성하여 사용, Pod 내에서만 데이터를 유지하는 단일성 스토리지의 경우 최적  
	- 단, Pod 제거 시 emptyDir 제거되며 Pod 간 Data 가 상이하게 동작하므로 상황에 맞게 고려 필요

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: test-pd
spec:
  containers:
  - image: registry.k8s.io/test-webserver
    name: test-container
    volumeMounts:
    - mountPath: /cache
      name: cache-volume
  volumes:
  - name: cache-volume
    emptyDir: {}
```


# 12. Volume mount - hostPath

[k8s docs hostPath](https://kubernetes.io/ko/docs/concepts/storage/volumes/#hostpath)

- emptyDir 과 달리 노드 내 특정 경로 디렉토리를 mount 하여 사용  
	- 노드 내에서 Pod 간 데이터 공유가 가능하지만, 여러 노드에 동일한 목적의 Pod 가 분산된 경우 다른 데이터를 사용할 수 있어 사용 환경 고려 필요


- 예시
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: test-pd
spec:
  containers:
  - image: registry.k8s.io/test-webserver
    name: test-container
    volumeMounts:
    - mountPath: /test-pd
      name: test-volume
  volumes:
  - name: test-volume
    hostPath:
      # 호스트의 디렉터리 위치
      path: /data
      # 이 필드는 선택 사항이다
      type: Directory
```


# 13. Storage Class

[k8s docs storage class](https://kubernetes.io/ko/docs/concepts/storage/storage-classes/)

- AWS , Azure 같은 public cloud 의 경우 스토리지를 유지하는것 자체가 비용이 발생
	- 때문에 public cloud k8s 서비스의 경우 storage class 를 미리 정의해 둔 것이 있음
	- 이를 활용하여 pvc 에서 storage class 를 명시하여 특정 목적에 맞는 volume 을 생성하도록 요청함
	- 또한 reclaim 정책을 통해 해당 pvc 를 더 이상 사용하지 않는 경우 해당 볼륨을 제거하도록 하여 최적의 비용으로 유지 가능
- 위 public cloud 의 경우가 아니더라도 pv 의 storage class 를 명시하여 pvc 에서 목적에 맞게 구분하여 storage 를 사용하도록 효율적으로 운영할 수 있음

```yaml
apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: standard
provisioner: kubernetes.io/aws-ebs
parameters:
  type: gp2
reclaimPolicy: Retain
allowVolumeExpansion: true
mountOptions:
  - debug
volumeBindingMode: Immediate
```

# 14. Persistent Volume

[k8s docs pv](https://kubernetes.io/ko/docs/concepts/storage/persistent-volumes/)

- 기존 emptyDir 이나 HostPath 는 Pod 가 여러 노드에 분산된 경우 고려해야할 사항이 많아진다.
- 때문에 영구적인 Volume 개념으로 NFS , iSCSI , FC 등으로 외부 스토리지를 PV 로 등록하고 이를 사용하는 방식이 분산 배포 환경에서 적합할 수 있다.

- 예시
```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: pv0003
spec:
  capacity:
    storage: 5Gi
  volumeMode: Filesystem
  accessModes:
    - ReadWriteOnce
  persistentVolumeReclaimPolicy: Recycle
  storageClassName: slow
  mountOptions:
    - hard
    - nfsvers=4.1
  nfs:
    path: /tmp
    server: 172.17.0.2
```


# 15. Persistent Volume Claim

[k8s docs pvc](https://kubernetes.io/ko/docs/concepts/storage/persistent-volumes/#%ED%8D%BC%EC%8B%9C%EC%8A%A4%ED%84%B4%ED%8A%B8%EB%B3%BC%EB%A5%A8-%EC%98%88%EC%95%BD)

- 사용하고자 하는 PV 스펙을 작성하여 실제 Pod 에서 사용하기 위해 PVC 를 사용
- PVC 를 설정하면 PV 가 할당되고 PV 가 할당된 PVC 를 Pod 에 Volume 및 VolumeMount 로 연결하여 사용하는 개념

- 예시
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: myclaim
spec:
  accessModes:
    - ReadWriteOnce
  volumeMode: Filesystem
  resources:
    requests:
      storage: 8Gi
  storageClassName: slow
  selector:
    matchLabels:
      release: "stable"
    matchExpressions:
      - {key: environment, operator: In, values: [dev]}
```

