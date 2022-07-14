FROM maven:3.6.0-jdk-11

COPY src /home/SeleniumTestFramework/src

COPY pom.xml /home/SeleniumTestFramework

COPY testNG.xml /home/SeleniumTestFramework
ENV HUB_HOST=selenium-hub
RUN mvn -f /home/SeleniumTestFramework/pom.xml clean test -DskipTests=true