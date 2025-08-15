FROM openjdk:17-jre-slim

# 设置工作目录
WORKDIR /app

# 复制jar文件
COPY target/blade-knowledge-demo.jar app.jar

# 暴露端口
EXPOSE 8100

# 设置JVM参数
ENV JAVA_OPTS="-Xms512m -Xmx1024m -Djava.security.egd=file:/dev/./urandom"

# 启动命令
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
