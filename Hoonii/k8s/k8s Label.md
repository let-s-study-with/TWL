
# 0. 출처
[TTABAE-LEARN 유튭](https://www.youtube.com/watch?v=yf5syEBAg2Y&list=PLApuRlvrZKohaBHvXAOhUD-RxD0uQ3z0c&index=30)
[k8s docs](https://kubernetes.io/docs/)


# 1. Label
- Node, Pod, Deployment 등 리소스에 하나 혹은 여러 라벨 지정 가능
- 리소스의 특징을 분류하고, Selector 를 사용하여 선택하도록 구성


- 예제
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: label-demo
  labels:
    environment: production
    app: nginx
spec:
  containers:
  - name: nginx
    image: nginx:1.14.2
    ports:
    - containerPort: 80
```
1) 위처럼 "metadata: labels:" 위치에 Label 을 기재하여 사용 가능


- Command 를 통한 Label 활용
```shell
kubectl get pods -l environment=production,tier=frontend

kubectl get pods -l 'environment in (production),tier in (frontend)'
```
1) kubectl get 뿐만 아니라 delete 등 다양하게 활용 가능


- 이 외에 StatefulSet 등 Controller 에서 Label Selector 를 통한 Replication 동작


# 2. Node Label
- Worker Node 의 물리/논리적 환경이 다른 경우가 다반사
- Node 에 Label 을 통해 환경을 지정하고 특정 조건에 충족하는 노드에서 Pod 가 동작하도록 구성


- 예시
```Shell
kubectl label node <node-name> disk=ssd
```


- Label 활용 ( 여러 Key 의 Label OR 조건 시 ) 
``` yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
spec:
  containers:
    - name: my-container
      image: my-image
  nodeSelector:
    matchExpressions:
      - key: key1
        operator: In
        values:
          - value1
      - key: key2
        operator: NotIn
        values:
          - value2
```

- Label 활용 ( 여러 Key 의 Label AND 조건 시 ) 
``` yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
spec:
  containers:
    - name: my-container
      image: my-image
  nodeSelector:
    nodeSelectorTerms:
      - matchExpressions:
          - key: key1
            operator: In
            values:
              - value1
      - matchExpressions:
          - key: key2
            operator: In
            values:
              - value2
```


# 3. Annotation
- Rolling Update 시 History 기록 가능 ( --record 방식과 상응 )
- Pod 동작 시 상태 혹은 자세한 정보를 확인하기위해 yaml 이나 container 를 살펴보지 않아도 간단하게 Annotation 정보를 통해 확인 가능
- Custom 하게 Pod 에 설정 가능


- 예시
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: annotations-demo
  annotations:
    imageregistry: "https://hub.docker.com/",
    key1 : "value1",
    key2 : "value2"
spec:
  containers:
  - name: nginx
    image: nginx:1.14.2
    ports:
    - containerPort: 80
```


- 동작 확인
```shell
kubectl describe annotations-demo

<출력 ...>
Annotations: ~~~ (내가 설정한 값)
<출력 ...>
```


# 4. Label 을 활용한 Canary Deployment
- 기존 버전 유지된 채로 일부 신규 버전을 Canary 로 배포
- 이 후 일부 특정 사용자 트래픽을 Canary 버전으로 사용하여 이상 여부 확인
- 2 가지 버전이 동시 동작하여 테스트하여 A/B 테스팅과 유사


- 예시
1) 기존 버전
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mainui-stable
sepc:
  replicas: 2
  selector:
    matchLabels:
      app: mainui
      version: stable
```

2) Canary 버전
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mainui-canary
sepc:
  replicas: 1
  selector:
    matchLabels:
      app: mainui
      version: canary
```

3) 두 버전 모두 동작을 위한 Service
```yaml
apiVersion: v1
kind: Service
metadata:
  name: mainui-svc
spec:
  selector:
    app: mainui
```

- "app: mainui" 라벨이 두 버전의 Deployment 모두 속하므로 Service 는 두 버전 Pod 내 Container 에 Load Balancing 