# IMDplay
API developed to attend a card game based in movie ratting with IMDb (Internet Movie Database).

> :warning: This is a MVP, so the code may have some bugs. If you found something wrong or to improve, please feel free to contribute!

## About
### Stack
The fallow stack was used to develop this project:

* `Java`: 17
* `Groovy`: 4.0
* `Spring Boot` (Spring Data JPA and Spring Web): 3.0.0
* `MySQL`: 8
* `Docker`
* `Spock Framework`: 2.3
* `Lombok`
* `Jacoco`

### Architecture
This project was developed based in Hexagonal Architecture, specifically the [Netflix Hexagonal Architecture](https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749).
The main principle used in this project is the decoupling, making use of Port Adapter design pattern.

## Usage
Until this moment, the project don't have any cloud environment. So, to run and test, it's necessary to build and launch the project using Docker.

### Setup
This project requires a Database to run. So, the first thing is launch a MySQL Database.
Run the fallowing command to create a MySQL8 container with `docker-compose.yml`:

```bash
docker-compose up
```

> :warning: You should run this command into the project folder!

Now, it's time to build and launch the project using the `Dockerfile`:
```bash
docker build -t imdplay .
```

```bash
docker run -p 8080:8080 imdplay
```

### API
> If you are running this project local, consider the '[host]' as localhost.
#### Registry User
POST `[host]`/v1/register
```json
{
  "fullName": "Test User",
  "username": "TestUser1",
  "email": "test_user@test.com",
  "password": "1234"
}
```

#### Get User
GET `[host]`/v1/register (Authentication Required)

#### Login
POST `[host]`/v1/login
```json
{
  "userName": "TestUser14",
  "password": "1234"
}
```

#### Start Match
POST `[host]`/v1/match (Authentication Required)

#### Respond Round
POST `[host]`/v1/match/{ID}?response={A,B} (Authentication Required)

#### Get Match
GET `[host]`/v1/match/{ID} (Authentication Required)

#### List Matches
GET `[host]`/v1/match (Authentication Required)

#### Finish Match
PATCH `[host]`/v1/match/{ID} (Authentication Required)

#### Get Ranking
GET `[host]`/v1/ranking (Authentication Required)

> :warning: All requests annotated with `(Authentication Required)` must be authenticated using Basic Auth (based in user/password).

### OpenAPI
To get more details, check the OpenAPI documentation:
`[host]`/v3/api-docs