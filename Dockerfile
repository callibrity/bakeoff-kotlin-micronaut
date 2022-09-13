FROM azul/zulu-openjdk:11.0.10

RUN set -x mkdir -p /app
COPY ./build/libs/bakeoff-0.1-all.jar /app/
EXPOSE 8080
WORKDIR /app
CMD ["java", "-jar", "bakeoff-0.1-all.jar"]