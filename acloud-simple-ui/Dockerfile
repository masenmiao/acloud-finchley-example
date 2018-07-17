FROM java:8-jre-alpine
#USER root

#ADD  ./ /tmp/build/
#RUN mkdir /app
WORKDIR /app

COPY acloud-simple-ui.jar /app

VOLUME /tmp
EXPOSE 8701
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/acloud-web-simple.jar"]
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/acloud-simple-ui.jar" ]