[Practice Test](https://kodekloud.com/topic/practice-test-pods-2/)

1.  Q1  
	How many pods in default namespace.     

- Console  
```shell
controlplane ~ ➜  kubectl get pods

No resources found in default namespace.  
```

 - Answer  
    0    

2. Q2  
	Create a new pod with the nginx image.     

-  Console  
```shell
controlplane ~ ➜  kubectl run nginx --image=nginx

pod/nginx created
```

-  Answer  
    Success  

3.  Q3  
	How many pods are created now?
	
	Note: We have created a few more pods. So please check again.

-  Console
```shell
controlplane ~ ➜  kubectl get pods

NAME            READY   STATUS    RESTARTS   AGE

nginx           1/1     Running   0          119s

newpods-vg8kp   1/1     Running   0          33s

newpods-n7q24   1/1     Running   0          33s

newpods-4nbxt   1/1     Running   0          33s
```

-  Answer  
    4  

4. Q4
	What is the image used to create the new pods?
	
	You must look at one of the new pods in detail to figure this out.  

-  Console  
```shell
controlplane ~ ➜  kubectl describe pod newpods-vg8kp

Name:             newpods-vg8kp

Namespace:        default

Priority:         0

Service Account:  default

Node:             controlplane/172.25.0.78

Start Time:       Fri, 02 Jun 2023 08:05:13 +0000

Labels:           tier=busybox

Annotations:      <none>

Status:           Running

IP:               10.42.0.12

IPs:

  IP:           10.42.0.12

Controlled By:  ReplicaSet/newpods

Containers:

  busybox:

    Container ID:  containerd://89000de15623b49c1667ba6f89319ab146e2beadee8c307883d31af119835410

    Image:         busybox  
```


-  Answer      
    BUSYBOX  

1. Q5  
    Which nodes are these pods placed on?

You must look at all the pods in detail to figure this out.  
 

1. Console  
     

controlplane ~ ➜  kubectl get pods -o wide

NAME            READY   STATUS    RESTARTS   AGE     IP           NODE           NOMINATED NODE   READINESS GATES

nginx           1/1     Running   0          7m40s   10.42.0.9    controlplane   <none>           <none>

newpods-vg8kp   1/1     Running   0          6m14s   10.42.0.12   controlplane   <none>           <none>

newpods-n7q24   1/1     Running   0          6m14s   10.42.0.11   controlplane   <none>           <none>

newpods-4nbxt   1/1     Running   0          6m14s   10.42.0.10   controlplane   <none>           <none>  
 

1. Answer  
      
    CONTROLPLANE  
      
     

1. Q6  
     

How many containers are part of the pod webapp?

Note: We just created a new POD. Ignore the state of the POD for now.

1. Console  
      
     

controlplane ~ ➜  kubectl describe pod webapp

Name:             webapp

Namespace:        default

Priority:         0

Service Account:  default

Node:             controlplane/172.25.0.78

Start Time:       Fri, 02 Jun 2023 08:12:18 +0000

Labels:           <none>

Annotations:      <none>

Status:           Pending

IP:               10.42.0.13

IPs:

  IP:  10.42.0.13

Containers:

  nginx:

    Container ID:   containerd://f1cec98a8aa5e9b2882f76d65cb112ae38f22c6421c672c6313b4bdb4be381b8

    Image:          nginx

    Image ID:       docker.io/library/nginx@sha256:af296b188c7b7df99ba960ca614439c99cb7cf252ed7bbc23e90cfda59092305

    Port:           <none>

    Host Port:      <none>

    State:          Running

      Started:      Fri, 02 Jun 2023 08:12:20 +0000

    Ready:          True

    Restart Count:  0

    Environment:    <none>

    Mounts:

      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-7vlrc (ro)

  agentx:

    Container ID:  

    Image:          agentx

    Image ID:      

    Port:           <none>

    Host Port:      <none>

    State:          Waiting

      Reason:       ImagePullBackOff

    Ready:          False

    Restart Count:  0

    Environment:    <none>

    Mounts:

      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-7vlrc (ro)

Conditions:  
 

1. Answer  
      
    2  
      
     

1. Q7  
     

What images are used in the new webapp pod?

You must look at all the pods in detail to figure this out.

1. Console  
      
    위와 동일  
     
2. Answer  
      
    nginx & agentx  
      
     

1. Q8  
     

What is the state of the container agentx in the pod webapp?

Wait for it to finish the ContainerCreating state  
 

1. Console  
      
    위와 동일  
     
2. Answer  
      
    Error or Waiting  
      
     

1. Q9  
     

Why do you think the container agentx in pod webapp is in error?

Try to figure it out from the events section of the pod.  
 

1. Console  
      
    kubectl describe pod webapp  
      
    <중략>  
      
     

  Warning  Failed     2m59s (x4 over 4m4s)  kubelet            Error: ImagePullBackOff

  Normal   Pulling    2m44s (x4 over 4m6s)  kubelet            Pulling image "agentx"

  Warning  Failed     2m44s (x4 over 4m5s)  kubelet            Failed to pull image "agentx": rpc error: code = Unknown desc = failed to pull and unpack image "docker.io/library/agentx:latest": failed to resolve reference "docker.io/library/agentx:latest": pull access denied, repository does not exist or may require authorization: server message: insufficient_scope: authorization failed

  Warning  Failed     2m44s (x4 over 4m5s)  kubelet            Error: ErrImagePull

  Normal   BackOff    2m29s (x5 over 4m4s)  kubelet            Back-off pulling image "agentx"

1. Answer  
      
    A Docker image with this name doesn't exist on Docker Hub  
      
     

1. Q10  
      
    What does the READY column in the output of the kubectl get pods command indicate?  
     

1. Console  
     

controlplane ~ ➜  kubectl get pods

NAME            READY   STATUS             RESTARTS   AGE

nginx           1/1     Running            0          16m

newpods-vg8kp   1/1     Running            0          14m

newpods-n7q24   1/1     Running            0          14m

newpods-4nbxt   1/1     Running            0          14m

webapp          1/2     ImagePullBackOff   0          7m31s

1. Answer  
      
    Running Containers in Pod/ Total Containers in Pods  
      
     

1. Q11  
     

Delete the webapp Pod.

Once deleted, wait for the pod to fully terminate.  
 

1. Console  
      
    controlplane ~ ➜  kubectl delete pod webapp

pod "webapp" deleted  
 

1. Answer  
      
    Success  
      
     

1. Q12  
     

Create a new pod with the name redis and with the image redis123.

Use a pod-definition YAML file. And yes the image name is wrong!  
 

1. Console  
     

controlplane ~ ➜  ls

sample.yaml

controlplane ~ ➜  kubectl run redis --image redis123 --dry-run -o yaml > sample.yaml

W0602 08:24:24.926331   12069 helpers.go:663] --dry-run is deprecated and can be replaced with --dry-run=client.

controlplane ~ ➜  cat sample.yaml

apiVersion: v1

kind: Pod

metadata:

  creationTimestamp: null

  labels:

    run: redis

  name: redis

spec:

  containers:

  - image: redis123

    name: redis

    resources: {}

  dnsPolicy: ClusterFirst

  restartPolicy: Always

status: {}

controlplane ~ ➜  kubectl run -f sample.yaml

Flag --filename has been deprecated, because it is not used by this command. It will be removed in version 1.29.

error: required flag(s) "image" not set

controlplane ~ ✖ kubectl run pod -f sample.yaml

Flag --filename has been deprecated, because it is not used by this command. It will be removed in version 1.29.

error: required flag(s) "image" not set

controlplane ~ ✖ kubectl create -f sample.yaml

pod/redis created  
 

1. Answer  
      
    TRUE  
      
     

1. Q13  
     

Now change the image on this pod to redis.

Once done, the pod should be in a running state.  
 

1. Console  
      
     

controlplane ~ ➜  kubectl run redis --image redis --dry-run -o yaml > sample.yaml

W0602 08:26:40.258734   12988 helpers.go:663] --dry-run is deprecated and can be replaced with --dry-run=client.

controlplane ~ ➜  cat sample.yaml

apiVersion: v1

kind: Pod

metadata:

  creationTimestamp: null

  labels:

    run: redis

  name: redis

spec:

  containers:

  - image: redis

    name: redis

    resources: {}

  dnsPolicy: ClusterFirst

  restartPolicy: Always

status: {}

controlplane ~ ➜  kubectl apply -f sample.yaml

Warning: resource pods/redis is missing the kubectl.kubernetes.io/last-applied-configuration annotation which is required by kubectl apply. kubectl apply should only be used on resources created declaratively by either kubectl create --save-config or kubectl apply. The missing annotation will be patched automatically.

pod/redis configured

controlplane ~ ➜  kubectl get pods

NAME            READY   STATUS    RESTARTS        AGE

nginx           1/1     Running   0               23m

newpods-4nbxt   1/1     Running   1 (5m12s ago)   21m

newpods-vg8kp   1/1     Running   1 (5m12s ago)   21m

newpods-n7q24   1/1     Running   1 (5m12s ago)   21m

redis           1/1     Running   0               2m5s  
 

1. Answer  
      
    TRUE