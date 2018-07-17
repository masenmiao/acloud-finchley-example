FROM gradle:3.5-jdk-alpine

#RUN cd /
RUN pwd
#RUN ls -a
RUN whoami
USER root
RUN whoami

RUN echo $PATH
#ENV GRADLE_USER_HOME=~/tmp/.gradle
#RUN chmod 777 /tmp/build
#RUN mkdir -p 777 tmp/build

#RUN mkdir -p /tmp/build/.gradle
#ENV GRADLE_USER_HOME=/home/gradle/.gradle

#ADD build.gradle /tmp/build/
#ADD settings.gradle /tmp/build/
#ADD acloud* /tmp/build/
ADD  ./ /tmp/build/


#COPY .gradle /tmp/build/.gradle
#RUN cd /tmp/build && gradle build
#ADD acloud-service-simple /tmp/build/acloud-service-simple



#ADD config /tmp/build/

#ENV GRADLE_HOME /home/gradle
#ENV GRADLE_USER_HOME /cache

#ENV PATH $PATH:$GRADLE_HOME/bin
#VOLUME $GRADLE_USER_HOME

RUN mkdir /app
#WORKDIR /app

RUN cd /tmp/build && gradle :acloud-eureka-server:clean assemble -info \
        #拷贝编译结果到指定目录
        && mv acloud-eureka-server/build/libs/*.jar /app/acloud-eureka-server.jar \
        #清理编译痕迹
        && rm -rf /tmp/build
VOLUME /tmp
EXPOSE 8501
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/acloud-eureka-server.jar"]
