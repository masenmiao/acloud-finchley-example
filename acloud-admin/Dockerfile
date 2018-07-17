FROM java:8-jre-alpine
WORKDIR /app

COPY acloud-admin.jar /app
EXPOSE 8505
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/acloud-admin.jar"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/acloud-admin.jar" ]