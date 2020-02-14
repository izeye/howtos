# How To Deploy Spring Boot Buildpacks Image To minikube

## Steps

```
$ minikube start --insecure-registry "10.0.0.0/24"
...
$ minikube addons enable registry
...
$
```

```
$ docker run --rm -it --network=host alpine ash -c "apk add socat && socat TCP-LISTEN:5000,reuseaddr,fork TCP:$(minikube ip):5000"
...
```

```
$ ./gradlew clean bootBuildImage
...
$
```

```
$ docker tag docker.io/library/test:0.0.1-SNAPSHOT localhost:5000/test
$ docker push localhost:5000/test
```

```
kubectl create deployment test --image=localhost:5000/test
kubectl expose deployment/test --type="NodePort" --port 8080
```

```
export NODE_PORT=$(kubectl get services/test -o go-template='{{(index .spec.ports 0).nodePort}}')
curl $(minikube ip):$NODE_PORT/actuator/health
```

## References:

* https://minikube.sigs.k8s.io/docs/tasks/registry/insecure/
* https://kubernetes.io/docs/tutorials/
