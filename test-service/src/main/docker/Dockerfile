FROM java
MAINTAINER fanT
ARG JAR_FILE
ARG BUILD_DIR
ADD ${JAR_FILE} app.jar
CMD ["java", "-version"]
ENTRYPOINT ["java", "-jar", "/app.jar"]