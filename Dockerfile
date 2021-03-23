FROM openjdk:8
COPY ./target/sample-1.0.0-jar-with-dependencies.jar /usr/src/myapp/app.jar
WORKDIR /usr/src/myapp
ENTRYPOINT ["java", "-jar","app.jar"]
CMD [""]