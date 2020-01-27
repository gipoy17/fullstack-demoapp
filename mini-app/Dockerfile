FROM baseimage

ARG JAR_FILE
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-javaagent:/tmp/jmx_prometheus_javaagent-0.3.1.jar=5556:config.yaml","-jar","/app.jar"]
