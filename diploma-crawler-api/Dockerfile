FROM bellsoft/liberica-openjdk-alpine:11
LABEL maintainer="motozov.a.v@gmail.com"

EXPOSE 8080/tcp

ARG APP_JAR_FILE
ENV JAVA_OPTS="-Xmx4096m -Xss512k -XX:MetaspaceSize=128m"
ENV JAVA_OPTS_ADD=""

RUN mkdir -p /apps

COPY ${APP_JAR_FILE} /apps/diploma-crawler-api.jar

WORKDIR /

COPY ./entrypoint.sh /apps/entrypoint.sh
RUN chmod +x /apps/entrypoint.sh
CMD ["/apps/entrypoint.sh"]
