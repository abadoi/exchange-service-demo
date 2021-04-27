# exchange-service-demo

# Prerequisites
- Maven 3.6+
- Spring Boot 2+
- Java 11
- MySQL 8+
- liquibase

# Build Project

- `docker-compose up --build`

# Run Project

- `docker-compose up`: Create and Start Docker Instances;
- `docker ps`: Check docker instances
- `http://localhost:8080/`: The endpoint
  - `/fetch` - to fetch data from XML: https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml
  - '/date' - get all currency rates from date
      - param: `date`
      - result: non-paginated list of currencies
  - `/currency` - get a specific currency rate
      - param: `currency`
      - param: `date`
      - result: a single currency
  - `/currency/all` - get all time currency rate for a specific currency sorted by date
      - param: `currency`
      - result: paginated list of a currency rates
      

# Rebuild the Project after changes
- `docker-compose stop`: Stop all Instances from docker-compose.yml file;
- `docker rm exchange-service-demo`: Delete the App Docker Instance;
- `Build & Run Project`;

# Dockers

### Docker Images
- `db`: Image with MySQL 8;
- `app`: Based on an image with Java JDK 11(`adoptopenjdk/openjdk11:alpine-jre`), which is build in a separate image with Maven(`maven:3.6-jdk-11-slim`);

### Docker Instances
- `exchange-service-demo`: Running the `exchange-0.0.1-SNAPSHOT` image. There are a service with Netty Server running the JAR file at port 8080;

### Docker Commands
- `docker ps`: show all Docker Instances running;
- `docker ps -a`: show all Docker Instances;
- `docker images`: show all Images;
- `docker-compose stop`: Stop all Instances from docker-compose.yml file;
