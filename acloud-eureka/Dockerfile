FROM java:8-jre-alpine

MAINTAINER masen <masen.miao@gmail.com>

RUN mkdir /app
WORKDIR /app
COPY acloud-eureka.jar /app
#VOLUME /app
EXPOSE 8501
#端口需要通过run, compose替换，因为需要集群启动多台（可能在一台虚拟机上，端口不能一样）
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/acloud-eureka.jar"]
CMD [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/acloud-eureka.jar" ]