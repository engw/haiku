haiku
=======

```
sbt docker:publishLocal
docker run -p 8080:8080 -d haiku:1.0
curl -X POST -d '{"verse":"古池や蛙飛び込む水の音"}'  localhost:8080/v1/haiku
```
