# EmailService

## Overview

EmailService is a Spring Boot application designed to handle email notifications. It listens to Kafka topics for email events and sends emails using the JavaMail API. The service supports sending simple HTML emails.

## Features

- Listens to Kafka topics for email events
- Sends HTML emails using JavaMail API
- Configurable SMTP settings

## Prerequisites

- Java 8 or higher
- Apache Kafka
- Spring Boot
- Maven

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/dhanushdh01/EmailService.git
    ```
2. Navigate to the project directory:
    ```bash
    cd EmailService
    ```
3. Update the email credentials in `SendEmailService` class:
    ```java
    return new PasswordAuthentication("your_email@example.com", "your_email_password");
    ```
4. Build the project using Maven:
    ```bash
    mvn clean install
    ```

## Configuration

Configure the Kafka listener and SMTP settings in `application.properties`:

```properties
# Kafka configuration
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=emailService
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

# SMTP configuration (in code)
mail.smtp.host=smtp.gmail.com
mail.smtp.port=587
mail.smtp.auth=true
mail.smtp.starttls.enable=true
