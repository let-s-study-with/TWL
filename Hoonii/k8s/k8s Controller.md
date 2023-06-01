
# 0. 출처
1. [TTABAE-LEARN 유튭](https://www.youtube.com/watch?v=5X3t6VJH2vQ&list=PLApuRlvrZKohaBHvXAOhUD-RxD0uQ3z0c&index=17)
2. [k8s docs](https://kubernetes.io/docs/)

# 1. Controller
## 1-1. Replication Controller
- Pod 의 개수 보장

- 예시
```yaml
apiVersion: v1
kind: ReplicationController
metadata:
  name: nginx
spec:
  replicas: 3
  selector:
    app: nginx
  template:
    metadata:
      name: nginx
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx
        ports:
        - containerPort: 80
```


## 1-2. ReplicaSet
- Pod 의 개수 보장 , RC 보다 Label Selector 를 상세하게 사용

- 예시
```yaml
apiVersion: apps/v1
kind: ReplicaSet
metadata:
  name: frontend
  labels:
    app: guestbook
    tier: frontend
spec:
  # modify replicas according to your case
  replicas: 3
  selector:
    matchLabels:
      tier: frontend
  template:
    metadata:
      labels:
        tier: frontend
    spec:
      containers:
      - name: php-redis
        image: gcr.io/google_samples/gb-frontend:v3
```

## 1-3. Deployment
- ReplicaSet 을 자동으로 사용하면서 Rolling Update 또한 지원

- 예시
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
  labels:
    app: nginx
spec:
  replicas: 3
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.14.2
        ports:
        - containerPort: 80
```

## 1-4. DaemonSet
- Worker Node 에 Pod 하나씩 배포, Rolling Update 도 지원

- 예시
```yaml
apiVersion: apps/v1
kind: DaemonSet
metadata:
  name: fluentd-elasticsearch
  namespace: kube-system
  labels:
    k8s-app: fluentd-logging
spec:
  selector:
    matchLabels:
      name: fluentd-elasticsearch
  template:
    metadata:
      labels:
        name: fluentd-elasticsearch
    spec:
      tolerations:
      # these tolerations are to have the daemonset runnable on control plane nodes
      # remove them if your control plane nodes should not run pods
      - key: node-role.kubernetes.io/control-plane
        operator: Exists
        effect: NoSchedule
      - key: node-role.kubernetes.io/master
        operator: Exists
        effect: NoSchedule
      containers:
      - name: fluentd-elasticsearch
        image: quay.io/fluentd_elasticsearch/fluentd:v2.5.2
        resources:
          limits:
            memory: 200Mi
          requests:
            cpu: 100m
            memory: 200Mi
        volumeMounts:
        - name: varlog
          mountPath: /var/log
      terminationGracePeriodSeconds: 30
      volumes:
      - name: varlog
        hostPath:
          path: /var/log
```


## 1-5. StatefulSet
- Pod 의 상태 ( 이름 , 볼륨 스토리지 ) 를 보존 기능 포함
- 상황에 맞게 Deployment 와 StatefulSet 을 나눠 사용 필요

- 예시
```yaml
apiVersion: v1
kind: Service
metadata:
  name: nginx
  labels:
    app: nginx
spec:
  ports:
  - port: 80
    name: web
  clusterIP: None
  selector:
    app: nginx
---
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: web
spec:
  serviceName: "nginx"
  replicas: 2
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: registry.k8s.io/nginx-slim:0.8
        ports:
        - containerPort: 80
          name: web
        volumeMounts:
        - name: www
          mountPath: /usr/share/nginx/html
  volumeClaimTemplates:
  - metadata:
      name: www
    spec:
      accessModes: [ "ReadWriteOnce" ]
      resources:
        requests:
          storage: 1Gi
```


## 1-6. Job
- Batch 처리가 필요한 경우 사용

```yaml
apiVersion: batch/v1
kind: Job
metadata:
  name: pi
spec:
  template:
    spec:
      containers:
      - name: pi
        image: perl:5.34.0
        command: ["perl",  "-Mbignum=bpi", "-wle", "print bpi(2000)"]
      restartPolicy: Never
  backoffLimit: 4
```


## 1-7. CronJob
- Job 을 스케줄링
- Linux / Unix 의 Cron 과 유사

```yaml
apiVersion: batch/v1
kind: CronJob
metadata:
  name: hello
spec:
  schedule: "* * * * *"
  jobTemplate:
    spec:
      template:
        spec:
          containers:
          - name: hello
            image: busybox:1.28
            imagePullPolicy: IfNotPresent
            command:
            - /bin/sh
            - -c
            - date; echo Hello from the Kubernetes cluster
          restartPolicy: OnFailure
```





# 2. Controller 동작 원리
- 사전 필요 개념
	- k8s Master Node 에는 k8s cluster 관리를 위한 여러 요소가 내장되어 동작 중
		- api-server
		- etcd
		- controller manager
		- scheduler
			- 참고 [k8s docs](https://kubernetes.io/ko/docs/concepts/overview/components/)
- 동작 순서
	1) kubectl 을 통해 api 서버는 관련 Controller 생성 요청 수신
	2) api 서버는 scheduler 를 통해 Pod 를 동작시킬 Worker Node 를 선택받음
		1) scheduler 는 etcd 의 Worker Node 정보를 통해 선택
	3) 이를 수신한 api server 는 각 Worker Node 의 kubectl 로 Pod 실행 요청
	4) api server 는 controller manager 에 pod 수량 유지 명령
	5) controller manager 얘가 관리
	- 참고 [블로그](https://kimjingo.tistory.com/188)


각 Controller 별 상세 동작, 내용 및 활용 방안은 개인 노트에 정리
