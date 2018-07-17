FROM java:8-jre-alpine
WORKDIR /app

COPY acloud-turbine.jar /app
EXPOSE 8506
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/acloud-turbine.jar.jar"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/acloud-turbine.jar" ]