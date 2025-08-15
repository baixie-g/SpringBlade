FROM bladex/alpine-java:openjdk17_cn_slim

MAINTAINER bladejava@qq.com

RUN mkdir -p /blade/knowledge-kmc

WORKDIR /blade/knowledge-kmc

EXPOSE 8108

ADD ./target/blade-knowledge-kmc.jar ./app.jar

ENTRYPOINT ["java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=test"]
