FROM bladex/alpine-java:openjdk17_cn_slim

MAINTAINER bladejava@qq.com

RUN mkdir -p /blade/knowledge-llm

WORKDIR /blade/knowledge-llm

EXPOSE 8106

ADD ./target/blade-knowledge-llm.jar ./app.jar

ENTRYPOINT ["java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=test"]
