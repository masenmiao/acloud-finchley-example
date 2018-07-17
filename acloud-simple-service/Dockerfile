FROM java:8-jre-alpine
MAINTAINER masen <masen.miao@gmail.com>

RUN mkdir /app
WORKDIR /app

COPY acloud-simple-service.jar /app
#VOLUME /app
EXPOSE 8601

#ENTRYPOINT ["java","-Xms256m -Xmx256m -Djava.security.egd=file:/dev/./urandom","-jar","/app/acloud-simple-service.jar"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/acloud-simple-service.jar" ]