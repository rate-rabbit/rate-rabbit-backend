FROM openjdk:14-alpine
COPY build/libs/raterabbit-*-all.jar raterabbit.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "raterabbit.jar"]