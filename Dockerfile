FROM tomcat:latest

MAINTAINER h_walbecq

COPY ./target/weedrop-api.war /usr/local/tomcat/webapps/