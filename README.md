# Rocket Challenge

## The Challenge 🧑‍💻

In this challenge you are going to build a service (or multiple) which consumes messages
from a number of entities – i.e. a set of _rockets_ – and make the state of these
available through a REST API. We imagine this API to be used by something like a dashboard.

As a minimum we expect endpoints which can:

1. Return the current state of a given rocket (type, speed, mission, etc.)
2. Return a list of all the rockets in the system; preferably with some kind of sorting.


## Installation

### Docker

#### Build the image

```sh
foo@bar:~$ docker build . -t lunar-project
```

#### Run the container

```sh
foo@bar:~$ docker run --name lunar -p 8080:8080 lunar-project:latest
```

#### Verify server is up and running using Httpie

```sh
foo@bar:~$ http :8080/api/rockets
```

#### Verify server is up and running using a browser

Go to [Swagger](http://localhost:8080/swagger)

#### Start rocket (Not part of this repo) - Only after verifying that the server is up and running.

```sh
foo@bar:~$ ./rockets launch "http://localhost:8080/messages" --message-delay=500ms --concurrency-level=1
```

#### Test
```sh
foo@bar:~$ docker exec -it lunar mvn test
```
### Maven

#### Start the application

```sh
foo@bar:~$ mvn quarkus:dev
```

#### Verify server is up and running using Httpie

```sh
foo@bar:~$ http :8080/api/rockets
```

#### Verify server is up and running using a browser

Go to [Swagger](http://localhost:8080/swagger)

#### Start rocket (Not part of this repo) - Only after verifying that the server is up and running.

```sh
foo@bar:~$ ./rockets launch "http://localhost:8080/messages" --message-delay=500ms --concurrency-level=1
```

#### Test
```sh
foo@bar:~$ mvn test
```

## Swagger

API documentation can be found if the server is running at: [Swagger](http://localhost:8080/swagger)

## Considerations/shortcuts

### Limitations - In memory database.

This service does not use any database; all data is stored in an in-memory list. Naturally, this imposes scaling limitations due to limited RAM. To improve scaling capabilities, I recommend the following:

1. Relational databases like MySQL, PostgreSQL, and SQLite are highly effective for structured data storage and are a good choice when we need to materialize a view.

2. NoSQL databases such as MongoDB, Cassandra, and Redis are designed for flexibility, scalability, and high performance. They can handle large volumes of unstructured or semi-structured data, are a good fit for storing events (like in this example).


#### Event handling

Events are managed using an event buffer, which verifies events for duplicates and discards delayed events. The application does not have a continuous flush job; instead, this is triggered by requests. When a counter reaches a specified threshold, the flush is activated, allowing users to view the rockets that have been created or updated by the event stream.

#### Test cases

It would be an illusion  to belive that I have created a test suite that (in real life) suffice enough.

#### Missings

##### Static analysis - &cross;

##### Double check for spelling errors etc. - &cross;

##### Performance test - &cross;

##### Persistence Database - &cross;

Sorry, but I was out of time. :)

##### Happy with the challenge - :heavy_check_mark:
