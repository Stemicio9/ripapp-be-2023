FROM maven:3.9.1 as maven
LABEL COMPANY="Twentyfive25"
LABEL MAINTAINER="io@io.io"
LABEL APPLICATION="RippApp Dashboard"

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package


FROM tomcat:8.5-jdk15-openjdk-oracle
ARG TOMCAT_FILE_PATH=/docker


#Data & Config - Persistent Mount Point
ENV APP_DATA_FOLDER=/var/lib/RipApp
ENV SAMPLE_APP_CONFIG=${APP_DATA_FOLDER}/config/
ENV CATALINA_OPTS="-Xms1024m -Xmx4096m -XX:MetaspaceSize=512m -	XX:MaxMetaspaceSize=512m -Xss512k"


#Move over the War file from previous build step
WORKDIR /usr/local/tomcat/webapps/
COPY --from=maven /Users/twentyfive/IdeaProjects/ripapp-be-2023/target/ripapp.war /usr/local/tomcat/webapps/api.war

COPY ${TOMCAT_FILE_PATH}/* ${CATALINA_HOME}/conf/

WORKDIR $APP_DATA_FOLDER

EXPOSE 80
ENTRYPOINT ["catalina.sh", "run"]