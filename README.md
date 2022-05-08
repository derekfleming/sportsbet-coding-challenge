sportsbet-coding-challenge

# Getting started

## Running tests

- Run `mvn test` to execute tests

## Running locally

- Compile
  - `mvn compile`
- Execute jar
  - `java -jar /target/sportsbet-challenge.jar`
- See all available endpoints
  - `http://localhost:8080/swagger-ui.html`

## General Notes

### Assumptions

- ID of the transaction required in the output is the same that is provided in the input
- Valid ages are 0-150
- Storing transactions or queries is not a requirement

### Limitations

- Extensive error handling has not been implemented
  - Exceptions will be handled by Spring's default implementation
