# temporal-insurance-parent

Quarkus Temporal Insurance is a comprehensive solution for managing insurance policies, claims, and customer
interactions using Temporal workflows. This project provides a robust framework for building scalable and maintainable
insurance applications with a focus on reliability and performance.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

# Overview

The following workflow is orchestrated across multiple services:

1. **Policy Management**: Handles the creation, modification, and deletion of insurance policies.
2. **Claims Processing**: Manages the lifecycle of insurance claims, including submission, review, and resolution.
3. **Customer Management**: Manages customer information, including contact details and policyholder information.
4. **Notification Service**: Sends notifications to customers regarding policy updates, claim status, and other relevant
   information.
5. **Payment Service**: Handles payment processing for policy premiums and claim payouts.

If anything in this process fails, the workflow will retry the failed step up to 3 times before a "compensating
transaction" is executed to revert the previous steps. This ensures that the system remains consistent and reliable,
even in the face of failures.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/temporal-insurance-parent-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

