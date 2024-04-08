FROM openjdk:11

COPY build/libs/MarketPlace-0.0.1-SNAPSHOT.jar /MarketPlace-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/MarketPlace-0.0.1-SNAPSHOT.jar"]
