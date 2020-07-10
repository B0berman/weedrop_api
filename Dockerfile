FROM tomcat:latest

MAINTAINER h_walbecq

COPY ./target/mideval-api.war /usr/local/tomcat/webapps/