# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /build
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime (muy liviano)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
COPY --from=builder /build/target/*.jar app.jar
USER appuser
EXPOSE 8091
ENV JAVA_OPTS="-XX:InitialRAMPercentage=75.0 -XX:MaxRAMPercentage=75.0"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]