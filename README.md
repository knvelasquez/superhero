
# Super Hero Api Rest

API Rest that allow to manage superheroes, principals tools, Spring Boot 3.0, Spring security, java 17, docker, H2 database

## Run Locally

Clone the project

```bash
  git clone https://github.com/knvelasquez/superhero.git
```

Start the server

```bash
    docker compose up --build
```


## Running Tests

To run tests, run the following command

#### the previous command is responsible for executing all test as the main step before starting the server

#### Note: if you still want to run all test manually you can use

```bash
    mvn clean test -P dev -X
```




## API Reference
We use **Spring Security** to secure our apis.

Since version 3 of **Spring Security** the **@PreAuthorize** annotation is available which is newer and much more flexible than **@Secured**,
example syntax:
```
  hasRole('ROLE_CREATE') OR hasRole('ROLE_ADMIN')
```
Each API is **protected** with this annotation and cannot be accessed without first sending a **JWT** inside the header

**@PreAuthorize** performs an authorization filter that validates the existence of a JSON Web Tokens within the request header.
#### Create a Jwt

```http
  POST /jwt
```

| Body| Type     | Description                |
| :-------- | :------- | :------------------------- |
| idUser | `Integer` | **Required.** id of user authenticated |


**Note**: the *idUser* represent an id of a user who already did login with **user/pass**
from another **authentication service**(*not part of this scope*) and from that result we obtain the **idUser** to get all privileges.

**Privileges** are stored in our database in memory in which we can make all the queries we want.

## Users available to create a JWT
These are users who have already done previous authentication in an external **login service** and which can be used to create the new jwt token with specific privilege.
```
idUser=1020 ('ROLE_ADMIN')
idUser=1025 ('ROLE_CONSULT')
idUser=1035 ('ROLE_CREATE')
idUser=1045 ('ROLE_UPDATE')
idUser=1055 ('ROLE_DELETE')
idUser=1065 ('ROLE_CONSULT', 'ROLE_CREATE')
idUser=1075 ('ROLE_CONSULT','ROLE_CREATE','ROLE_UPDATE')
idUser=1085 ('ROLE_CONSULT','ROLE_CREATE','ROLE_UPDATE','ROLE_DELETE')
```

## Schema privileges
ROLE_ADMIN = Get access to all system resources

ROLE_CONSULT = Allows to consult Super Heroes

ROLE_CREATE = Allows to create a Super Hero

ROLE_UPDATE = Allows to update a Super Hero

ROLE_DELETE = Allows to delete a Super Hero

## Schema JWT
Each JWT lasts 24min.

```json

{
  "authorities": [
    "ROLE_ADMIN","ROLE_CONSULT","ROLE_..." // is the list of privileges
  ], 
  "Company": "Super Heroes Fintech, S.A", //is the company name
  "UserName": 1020, // is the user_name of autenticated user
  "iat": 1677530075, // is the creation time 
  "exp": 1677531515 // is the expiration time (24 min)
}

```

for more information
https://jwt.io/

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


## Usage/Examples

```javascript
import Component from 'my-project'

function App() {
  return <Component />
}
```


## Swagger Documentation

We use swagger to document the rest apis

you can access here
[http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui/index.html)


## Screenshots/Demo
# Swagger UI

you can access here http://localhost:8080/swagger-ui

![Swagger Secure Api](https://i.imgur.com/GsEPDKO.png)

![Swagger padlock](https://i.imgur.com/j3SZy8y.png)

![Swagger jwt](https://i.imgur.com/goTJNZH.png)

![jwt oficial site](https://i.imgur.com/sn2om5W.png)

for more information https://jwt.io/

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



# Tabla de contenido

- [Sección 1](#sección-1)
- [Sección 2](#sección-2)
- [Enlace a READMEk2.md](Readme2.md)

## Sección 1

## Sección 1

Contenido de la sección 1...

## Sección 2

Contenido de la sección 2...
