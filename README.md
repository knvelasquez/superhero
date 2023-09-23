
# Super Hero Api Rest

API Rest that allow to manage superheroes, principals tools, Spring Boot 3.0, Spring security, java 17, docker, H2 database

# Tabla de contenido
- [Analysis and design (Espanish version)](Readme2.md)
- [use-with-docker-container](#use-with-docker-container)
  - [Run SuperHero Image from docker hub in a Docker Container](#run-superhero-image-from-docker-hub-in-a-docker-container)
  - [Run JwtAUth Image from docker hub in a Docker Container](#run-jwtaUth-image-from-docker-hub-in-a-Docker-container)
- [Source code Installation](#source-code-installation)
  - [Navigate to the project directory](#navigate-to-the-project-directory)
  - [Resolve internal dependencies](#resolve-internal-dependencies)
  - [Build the container](#build-the-container)
  - [Start the container](#start-the-container) 
  - [It can also do everything in a single line](#it-can-also-do-everything-in-a-single-line)
  - [Running Tests](#running-tests)
- [API Reference](#api-reference)
- [Swagger Documentation](#swagger-documentation)



    
## Use with Docker Container
### (not need to download source code)

If you prefer not to download and compile the source code directly, you can use Docker to easily run the microservice. 
We have hosted Docker images of the **[Superhero Microservice](https://hub.docker.com/layers/knvelasquez/superhero/v2.0/images/sha256-b167d39c5e66b46842c50ccc3d9f9c05b9db0ceb14feeb23a054c06f9a1b2ca4?context=repo)** on **Docker Hub** to simplify this process.

## Run SuperHero Image from docker hub in a Docker Container
- Make sure you have **Docker** installed on your machine. You can download Docker from 
 [Docker's Official Website](https://www.docker.com/get-started/)
- Run the following command to download the **Superhero** image from docker hub and start it, replacing <PORT> with the port you want to use to access the microservice:
```bash
 docker run -p 8080:8080 knvelasquez/superhero:v2.0
```
- The microservice will be up and running on the specified port. You can access it through your browser([SuperHeroSwagger](http://localhost:8080/swagger-ui/index.html)) or through API requests.

## Run JwtAUth Image from docker hub in a Docker Container
- Run the following command to download the **Jwtauth** image from docker hub and start it, replacing <PORT> with the port you want to use to access the microservice:
```bash
 docker run -p 8082:8082 knvelasquez/jwtauth:v1.3
```
-  The microservice will be up and running on the specified port. You can access it through your browser([JwtAuthSwagger](http://localhost:8082/swagger-ui/index.html)) or through API requests.


## Source code Installation

Clone the repository to your local machine

```bash
  git clone https://github.com/knvelasquez/superhero.git
```
## Navigate to the project directory

```bash
cd superhero/
```

## Resolve internal dependencies
We need to incorporate this **ApiLibrarys**

- Clone and confiure this:
  - **[FilterLibrary v1.0.0](https://github.com/knvelasquez/FilterLibrary)**
  - **[JwtLibrary v1.0.0](https://github.com/knvelasquez/JwtLibrary)**
  - Compile and install them in the local maven repository
```bash
    mvn clean install
```

## Build the container
```bash
    docker compose build
```

## Start the container

```bashd
    docker compose up
```

**Note**: **SPRING_BOOT_PORT** variable must be set if you want to set specific port to access
```bash
  SPRING_BOOT_PORT=8181 docker compose up
```

## It can also do everything in a single line
```bash
  SPRING_BOOT_PORT=8181 docker compose up --build
```

## Running Tests

To run tests, run the following command

#### the previous command is responsible for executing all test as the main step before starting the server

#### Note: if you still want to run all test manually you can use

```bash
    mvn clean test -P dev -X
```

## API Reference

#### Get all super heroes

```http
  GET /superhero
```

| Header| Type     | Description                |
| :-------- | :------- | :------------------------- |
| Authorization | Bearer Token | **Required.** JWT with ROLE_ADMIN or ROLE_CONSULT privilege |


#### Get super heroe by unique id

```http
  GET /superhero/${id}
```

| Header| Type     | Description                |
| :-------- | :------- | :------------------------- |
| Authorization | Bearer Token | **Required.** JWT with ROLE_ADMIN or ROLE_CONSULT privilege |

| Path Parameter| Type     | Description                |
| :-------- | :------- | :------------------------- |
| id | `Integer` | **Required.** id of the super hero to consult |

#### Get super heroe by contain name

```http
  GET /superhero/contain/${name}
```

| Header| Type     | Description                |
| :-------- | :------- | :------------------------- |
| Authorization | Bearer Token | **Required.** JWT with ROLE_ADMIN or ROLE_CONSULT privilege |

| Path Parameter| Type     | Description                |
| :-------- | :------- | :------------------------- |
| name | `String` | **Required.** name of the super heroes to consult |

#### Create a super heroe

```http
  POST /superhero
```

| Header| Type     | Description                |
| :-------- | :------- | :------------------------- |
| Authorization | Bearer Token | **Required.** JWT with ROLE_ADMIN or ROLE_CREATE privilege |

| Body Parameter| Type     | Description                |
| :-------- | :------- | :------------------------- |
| name | `String` | **Required.** name of the super heroe to create |

#### Update a super heroe

```http
  PUT /superhero
```

| Header| Type     | Description                |
| :-------- | :------- | :------------------------- |
| Authorization | Bearer Token | **Required.** JWT with ROLE_ADMIN or ROLE_UPDATE privilege |

| Body Parameter| Type     | Description                |
| :-------- | :------- | :------------------------- |
| id | `Integer` | **Required.** id of the super heroe to update |
| name | `String` | **Required.** name of the super heroe to update |

#### Delete a super heroe

```http
  DELETE /superhero/${{id}}
```

| Header| Type     | Description                |
| :-------- | :------- | :------------------------- |
| Authorization | Bearer Token | **Required.** JWT with ROLE_ADMIN or ROLE_DELETE privilege |

| Body Parameter| Type     | Description                |
| :-------- | :------- | :------------------------- |
| id | `Integer` | **Required.** id of the super heroe to delete |


## Swagger Documentation
We use **Open Api 3.0 Swagger** to document the microservice you can access here:
 - **[SwaggerSuperHero:8080](http://localhost:8080/swagger-ui/index.html)**
 - **[SwaggerJwthAuth:8082](http://localhost:8082/swagger-ui/index.html)**


![Swagger Secure Api](https://i.imgur.com/GsEPDKO.png)

![Swagger padlock](https://i.imgur.com/j3SZy8y.png)


# prefer to use postman collection

```bash

./SuperHeroCollection.postman_collection.json

```
![postman collection](https://i.imgur.com/mBI46Tm.png)

![postman collection2](https://i.imgur.com/2vdIsVh.png)


## Author

- [Kevin Velásquez](https://github.com/knvelasquez)


## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/knvelasquez/)