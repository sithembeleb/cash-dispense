FROM java:8-jdk-alpine


COPY "./target/cash-dispense-0.0.1-SNAPSHOT.jar" ./tmp

WORKDIR ./tmp

RUN sh -c 'cash-dispense-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","cash-dispense-0.0.1-SNAPSHOT.jar"]