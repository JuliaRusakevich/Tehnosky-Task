#RESTful account-service (сервис для перевода денег между пользователями)

Выполняемые сервисом операции:

- пополнение счета пользователя c учестом того, что одновременно могут выполняться сразу две операции одновременно.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Requirements](#requirements)
3. [Usage](#usage)
4. [Features](#features)
5. [File Structure](#file-structure)
6. [Configuration](#configuration)
7. [Running the Application](#running-the-application)
8. [Running the tests](#running-the-tests)

## Project Overview

Сервис предназначен для управления пользователями, их счетами и осуществляемыми транзакциями (с последующим расширением
функционала сервиса).

## Requirements

1. **Java 19**

2. **Gradle**: В проекте используется Gradle для автоматизации сборки, Gradle [here](https://gradle.org/install/).

3. **Docker**: Docker для поднятия PostgreSQL, Liquibase.

## Usage

Сервис поддерживает функцию перевода денег между пользователями.

## Features

### Core Features

1. **CRUD операции для базовых сущностей**
2. **Аутентификация пользователя**:  SpringSecurity Basic Auth, аутентифицированные пользователи могут осуществлять
   операции на сервисе
3. **Перевод денег**: Перевод денег осуществляется только между зарегистрированными аутентифицированными пользователями
   по счетам в одной валюте. У одного пользователя могут быть несколько счетов в разных валютах.
4. **Unit Testing**

## Endpoints description

- `POST /api/v1/users/login`
- `GET /api/v1/users/me`

### POST

### Input

#### 1. Логинация пользователя

- Endpoint: `POST /api/v1/users/login`
- Example Request:
  ```http
  POST /api/v1/users/login
  Content-Type: application/json

  {
    "username": "ivanov@gmail.com",
    "password": "123"
  }

Successful (Status 200);

- JSON Response Example:
  ```json
    {
    "id": 1,
    "fullName": "Ivanov I.I.",
    "mail": "ivanov@gmail.com",
    "documentType": "PASSPORT",
    "documentNumber": "MP1111111"
    }
 
Unsuccessful (Status 401)

#### 2. Получение информации о пользователе после входа в систему

- Endpoint: `GET /api/v1/users/me`

### Output

CustomUserDetails (User Information)

- JSON Response Example:
  ```json
    {
    "id": 1,
    "mail": "ivanov@gmail.com",
    "enabled": true,
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "authorities": null,
    "username": "ivanov@gmail.com"
    }

## Transaction Endpoint

### Endpoint URL

The endpoint URL is constructed as follows:

- `GET /api/v1/transaction/account/{accountId}`
- `GET api/v1/account/{accountId}/transaction/{transactionId}`
- `POST /api/v1/transaction`

### GET

### Input

#### 1. Получение всех транзакций по номеру счета

- Endpoint: `GET /api/v1/transaction/account/{accountId}`
- Example URL: `GET /api/v1/transaction/account/1`
- Description: Получает информацию о всех транзакциях, предоставляя идентификатор номера счета.

### Output

#### TransactionInfo

- JSON Response Example:
  ```json
    {  
        "transactionType": "TRANSFER",
        "sumInCurrencyAccount": -10,
        "date": "2023-11-08 16:28:10"
    },
    {
        "transactionType": "TRANSFER",
        "sumInCurrencyAccount": -100,
        "date": "2023-11-08 16:28:19"
    }

Счет не найден - 404 NOT FOUND

- JSON Response Example:
  ```json
    {
    "message": "Account not found!"
    }

#### 2. Получение транзакции по номеру id счета / id транзакции

- Endpoint: `GET api/v1/account/{accountId}/transaction/{transactionId}`
- Example URL: `/api/v1/account/1/transaction/3`

### Output

#### TransactionInfo

- JSON Response Example:
  ```json
    {
    "transactionType": "TRANSFER",
    "sumInCurrencyAccount": -100,
    "date": "2023-11-08 16:28:19"
    }

Счет не найден - 404 NOT FOUND

- JSON Response Example:
  ```json
    {
    "message": "Account not found!"
    }

Транзакция не найдена - 404 NOT FOUND

- JSON Response Example:
  ```json
    {
    "message": "Transaction not found!"
    }

### POST

#### Транзакция

- Endpoint: `POST /api/v1/transaction`
- Example Request:
  ```http
    POST /api/v1/transaction
    Content-Type: application/json
    
    {
    "type": "TRANSFER",
    "currency": "EUR",
    "sum": 25,
    "senderAccountId": 3,
    "receiverAccountId": 1
    }

### Output

Недостаточно средств на счету - 400 BAD REQUEST

- JSON Response Example:
  ```json
    {
    "message": "Not enough money!"
    }

Счета отправителя / получателя не найдены - 404 NOT FOUND

- JSON Response Example:
  ```json
    {
    "message": "Account not found!"
    }

Перевод междуразными валютами - 400 BAD REQUEST

- JSON Response Example:
  ```json
    {
    "message": "Transfer is possible in one currency!"
    }

## File Structure

- **src/main/java**
- **src/test/java**
- **src/main/resources/db/changelog**: Liquibase DB migrations

## Configuration

- Конфигурация проекта `src/main/resources/application.yml`

## Running the Application

To run the application:

1. Clone the repository: `git clone <repository_url>`

2. Configure PostgreSQL settings in `application.yml`.

3. Build the project: `./gradlew build`

4. Run the application: `./gradlew run`

## Running the tests

```bash
./gradlew test
