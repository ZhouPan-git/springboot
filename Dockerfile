# 添加java8镜像来源
FROM java:8

#添加参数
ARG JAR_FILE

#添加springboot包，JAR_FILE 参数是从Docker Maven 插件中指定的构建参数
ADD target/${JAR_FILE} app.jar

#执行启动命令，-Djava.security.egd=file:/dev/./urandom优化随机数生成策略
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]