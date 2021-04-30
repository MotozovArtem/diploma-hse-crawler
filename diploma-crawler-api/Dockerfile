FROM bellsoft/liberica-openjdk-alpine:11
LABEL maintainer="motozov.a.v@gmail.com"

EXPOSE 8080/tcp

ARG APP_JAR_FILE
ARG JCP_ZIP_FILE
ARG FONTS_FILE
ENV JAVA_OPTS="-Xmx4096m -Xss512k -XX:MetaspaceSize=128m"
ENV JAVA_OPTS_ADD=""

RUN mkdir -p /apps

COPY ${APP_JAR_FILE} /apps/asutd.jar

COPY ${JCP_ZIP_FILE} /apps/jcp.zip
RUN mkdir -p /opt
RUN unzip /apps/jcp.zip -d /opt/
RUN rm /apps/jcp.zip

RUN chmod +x /opt/jcp-2.0.40035/*.sh
WORKDIR $JAVA_HOME
WORKDIR /opt/jcp-2.0.40035/
RUN ./setup_console.sh $JAVA_HOME -force -en -install -jre $JAVA_HOME -jcp -jcryptop -cades
RUN cp ./dependencies/bcpkix-jdk15on-1.50.jar $JAVA_HOME/jre/lib/ext/
RUN cp ./dependencies/bcprov-jdk15on-1.50.jar $JAVA_HOME/jre/lib/ext/
RUN cp ./dependencies/xmlsec-1.5.0.jar $JAVA_HOME/jre/lib/ext/
RUN cp ./dependencies/commons-logging-1.1.1.jar $JAVA_HOME/jre/lib/ext/

WORKDIR /

COPY ./entrypoint.sh /apps/entrypoint.sh
RUN chmod +x /apps/entrypoint.sh
CMD ["/apps/entrypoint.sh"]
