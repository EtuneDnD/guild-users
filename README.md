# Guild Users Microservice
![example workflow](https://github.com/EtuneDnD/guild-users/actions/workflows/build.yml/badge.svg)
## Responsability
The Guild Users Microservice is in charge of the user management this includes all operations related to users like
*create*, *delete*, *modify*...

Not only that the Guild User Microservice is also in charge of managing all the ban system and profile of each user.

**Players** will be able to post a description in their profile so other players can know each other better before 
jumping into a new session. 

This will work as an icebreaker for introverted people. 

They can also add info only visible
to Dungeon Masters in order to create a safe and comfortable place for all the members involved in the session.

Dungeon Master will be able to receive feedback from the Players they played with, in order to get better with their DM 
skills. 

At the same time Dungeon Masters will be able to propose Strikes to ban punish players if they didn't behave
properly during a session. They can also add DM notes to Players only visible by other DMs.

## Project Structure
The decided architecture for this project is Hexagonal Architecture, all the microservices will follow this architecture.

>Right now project folders are not Domain orientated, this may change in the future.

It's very important to keep all code inside the **Infrastructure** module protected by the ***Internal*** visibility
clause to prevent calls between different port implementations (`Driven Adapters / Driving Adapters`)

Each different implementation of a port is encapsulated in a different module:
* **firebase**: for user management
* **flyway**: for DB migrations 
* **jooq**: for persistence
* **rabbitmq**: for messaging
* **rest**: for API exposure

Keeping all these different implementations protected by the **internal** clause will save us from breaking the Hexagonal
Architecture.

## Running the project
Currently, we use docker to create containers to satisfy dependencies as postgres and rabbitmq.

But in the future when more microservices join the project, we will use minikube for local development.

To start the project:
1. Make sure you have installed **Docker**
2. CD into the root project folder (`path_to_the_project/dev/guild-project/guild-users`)
3. Execute ./gradlew deployLocalEnv

>WARNING: This is the legacy way 

~~To start the project:~~
1. ~~Make sure you have Docker installed in your machine~~
2. ~~Make sure you have Java 17 SDK installed in your machine~~
3. ~~**Access with a terminal to the _docker_ folder and run `docker-compose up -d`**~~
4. ~~With IntelliJ run the project going to the **_boot module_** and run the `UserApplication.kt` file~~

>Note: If you want to test it is not going to work because you will need a credentials file for Firebase not posted in
> the repo for obvious reasons, but you can create your own Firebase account and add the credentials file
> to the FireBase module in the next directory -> `src/main/resources/service-account.json`
