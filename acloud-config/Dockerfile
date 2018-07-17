FROM java:8-jre-alpine

MAINTAINER masen <masen.miao@gmail.com>

RUN mkdir /app
WORKDIR /app
COPY acloud-config.jar /app
#VOLUME /app
EXPOSE 8502

#"--spring.profiles.active=${SPRING_PROFILES_ACTIVE}",
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/acloud-config.jar"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/acloud-config.jar" ]