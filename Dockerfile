FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/x-ray-spring-boot-app.jar x-ray-spring-boot-app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /x-ray-spring-boot-app.jar" ]
