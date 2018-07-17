FROM java:8-jre-alpine
WORKDIR /app

COPY acloud-gateway.jar /app
EXPOSE 8503
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/acloud-gateway.jar"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/acloud-gateway.jar" ]