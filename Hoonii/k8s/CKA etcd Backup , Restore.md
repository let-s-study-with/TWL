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