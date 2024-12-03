# Group Microservice

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

## About the project
The `Group Microservice` is a application focused in organizing users from the [User Microservice](https://github.com/peluargo/user-ms) in groups and asserting roles to each member.

![image](https://github.com/user-attachments/assets/04e92dd2-24ae-42e8-8c36-2750eabdf9f9)

### Communication
The Group Microservice communicates with the User Microservice by making HTTP requests using [Feign Client](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html). Since both of the microservices are also discovery clients, the [Gateway](https://github.com/peluargo/gateway) facilitates the communication between them.

![peluargo-app-communication-between-groupms-and-userms drawio](https://github.com/user-attachments/assets/3336a2f4-37bf-4afd-afc7-765c40c3e9db)

##

### Features

 - [ ] https://github.com/peluargo/group-ms/issues/1
 - [ ] https://github.com/peluargo/group-ms/issues/2
