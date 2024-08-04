FROM maven:latest

ENV LANGUAGE='en_US:en'

COPY . /deployments/
WORKDIR /deployments
RUN mvn compile


EXPOSE 8080

CMD [ "mvn" , "quarkus:dev" ]

