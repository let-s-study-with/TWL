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



