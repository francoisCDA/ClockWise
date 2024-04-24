FROM maven as builder 

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM openjdk

WORKDIR /app

COPY --from=builder /app/target/cw_api-0.0.1-SNAPSHOT.jar .

EXPOSE 8000

ENTRYPOINT [ "java","-jar","cw_api-0.0.1-SNAPSHOT.jar"]
