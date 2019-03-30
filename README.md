# Retail Store Discount Service

This microservice implements a basic retail discount service.

## Getting started

After cloning the repository :

```bash
    git clone git@github.com:mohamedelnaggar/retail-store-discount.git
    cd retail-store-discount
```

Install mysql database, and run the database creator script

```bash
    sh docs/recreate-db.sh
```

put your mysql username and password in application.yml file

Then run

```bash
    mvn spring-boot:run
```

## Running the service tests  

running the tests (all tests: unit tests and integration tests):

```bash
    mvn clean test
```
    

## The Handled Scenarios

* The users api (get all users)

    [Get request]
    http://localhost:8080/api/users


* The calculate discount api

    [POST request]
    http://localhost:8080/api/retail-store/calculate-discount
    
        The input is json like
        {
            "userId": 1,
            "amount": 200
        }
    
    
        The output is
        {
            "user": {
                "id": 1,
                "name": "Mohamed",
                "userType": "EMPLOYEE",
                "startDate": "2019-03-25"
            },
            "originalAmount": 200,
            "discountAmount": 60,
            "netPayableAmount": 140
        }

The discount will be calculated based on user type, start date, and amount
