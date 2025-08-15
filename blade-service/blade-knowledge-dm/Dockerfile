FROM bladex/alpine-java:openjdk17_cn_slim

MAINTAINER bladejava@qq.com

RUN mkdir -p /blade/knowledge-dm

WORKDIR /blade/knowledge-dm

EXPOSE 8109

ADD ./target/blade-knowledge-dm.jar ./app.jar

ENTRYPOINT ["java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=test"]
