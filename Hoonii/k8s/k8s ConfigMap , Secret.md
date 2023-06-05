# 0. 출처
1. [TTABAE-LEARN 유튭](https://www.youtube.com/watch?v=xyGTvkKzrB4&list=PLApuRlvrZKohaBHvXAOhUD-RxD0uQ3z0c&index=34)
2. [k8s docs](https://kubernetes.io/docs/)

# 1. ConfigMap
- 컨테이너 구성 정보를 한 곳에 모아서 관리

- 사용 이유
  - 각 컨테이너 구성정보를 개별적으로 설정해 관리하는 경우 컨테이너의 수량이 기하급수적으로 증가하는 경우 감당이 매우 힘듬

## 1-1. 선언형 ConfigMap
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: game-demo
data:
  # 속성과 비슷한 키; 각 키는 간단한 값으로 매핑됨
  player_initial_lives: "3"
  ui_properties_file_name: "user-interface.properties"

  # 파일과 비슷한 키
  game.properties: |
    enemy.types=aliens,monsters
    player.maximum-lives=5    
  user-interface.properties: |
    color.good=purple
    color.bad=yellow
    allow.textmode=true   
```

- 단, ConfigMap 의 데이터는 많은 양을 보유하도록 설계되지 않아 Value 크기는 1MiB 를 초과할 수 없다.
  - 때문에 해당 크기보다 커야 하는 경우, ConfigMap 을 Volume 으로 Mount 하는 방식으로 사용해야 한다.

## 1-2. 명령형 ConfigMap
```shell
kubectl create configmap test-map --from-literal=test_value=2 --from-file=config.dir/
```

## 1-3. Pod 적용
- ConfigMap 내 일부 데이터만 적용
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: configmap-demo-pod
spec:
  containers:
    - name: demo
      image: alpine
      command: ["sleep", "3600"]
      env:
        # 환경 변수 정의
        - name: PLAYER_INITIAL_LIVES # 참고로 여기서는 컨피그맵의 키 이름과
                                     # 대소문자가 다르다.
          valueFrom:
            configMapKeyRef:
              name: game-demo           # 이 값의 컨피그맵.
              key: player_initial_lives # 가져올 키.
        - name: UI_PROPERTIES_FILE_NAME
          valueFrom:
            configMapKeyRef:
              name: game-demo
              key: ui_properties_file_name
```

- ConfigMap 전체 적용
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: configmap-demo-pod
spec:
  containers:
    - name: demo
      image: alpine
      command: ["sleep", "3600"]
      envFrom:
      - configMapRef:
        name: game-demo
```

- Volue Mount 적용
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: configmap-demo-pod
spec:
  containers:
    - name: demo
      image: alpine
      command: ["sleep", "3600"]
      volumeMounts:
      - name: config
        mountPath: "/config"
        readOnly: true
  volumes:
    # 파드 레벨에서 볼륨을 설정한 다음, 해당 파드 내의 컨테이너에 마운트한다.
    - name: config
      configMap:
        # 마운트하려는 컨피그맵의 이름을 제공한다.
        name: game-demo
        # 컨피그맵에서 파일로 생성할 키 배열
        items:
        - key: "game.properties"
          path: "game.properties"
        - key: "user-interface.properties"
          path: "user-interface.properties"
```


# 2. Secret

- 사용 방법은 ConfigMap 과 매우 유사하다
- 단지 Value 저장 시 Base64 Encoding 이 되지만, Pod 에 Data 전달 시 Decoding 되어 전달된다.