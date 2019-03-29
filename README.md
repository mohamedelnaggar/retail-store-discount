# retail-store-discount

This microservice implements a basic retail discount service.

## Getting started

After cloning the repository :

```bash
    git clone git@github.com:mohamedelnaggar/retail-store-discount.git
    cd retail-store-discount
```

to quickly get started and initialize the environment, use the project runner command :

run the database creator script

```bash
    sh docs/recreate-db.sh
```

put your mysql username and password in application.yml file

```bash
    mvn spring-boot:run
```

which will run the development environment and install its dependencies (including a postgres docker container), and immediately give you access to [the api](http://localhost:7000/documentation/swagger-ui.html) in development mode .


## Running the service tests  

running the tests (all tests: unit tests and integration tests):

```bash
    mvn clean test
```

## Api Documentation  

A swagger api documentation is avilable through visiting the documentation url at :

    {host:port}/documentation/swagger-ui.html 
    

## The Handled scenarios
The input is json like 
{
	"userId": 20,
	"amount": 200
}

and the discount will be calculated based on user type, start date, and amount
