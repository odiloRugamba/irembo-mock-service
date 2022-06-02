FROM adoptopenjdk/openjdk14
#RUN apk add --no-cache tzdata
ENV TZ=Africa/Kigali
EXPOSE 8494
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} irembopay-mock-service.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=kubernetes","/irembopay-mock-service.jar"]
