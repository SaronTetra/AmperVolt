# Amper Volt
## Overview
Bank simulator with NFC card acting as debit cards


## Description
This app is a Bank simulator. Backend is written in Kotlin with Exposed library to communicate with MySQL database. It uses REST API on level 2 of maturity level to communicate with it. Frontend is in JS. Users can create accounts and cards and use them to make transactions to other users. NFC cards are read using WebNFC.

Frontend source code can be viewed here: 

## Tools
1. IntelliJ IDEA Ultimate
2. DataGrip 2018.3.4
3. Postman
4. VPS with Debian
6. Android device with NFC and Chrome
7. Docker

## Example Endpoints
GET `/users` - gets a list of all users  
POST `/users` - creates new user  
GET `/users/{login}` - gets info about user *login*  
GET `/users/{login}/{account}/transactions` - gets all transactions of user *login* on account *account*  
PUT `/users/{login}/{account}/transactions` - does a transaction  
DELETE `/users/{login}/{account}/transactions` - deletes all transactions  

## How to run WebNFC
* Make sure you're using Chrome for Android 
* Go to chrome://flags 
* Search for NFC
* Enable WebNFC
* Search for Experimental Web Platform features 
* Restart Chrome 

## How to compile and run
To run AmperVolt you will need to install Docker, Oracle JDK11 and Maven

* go to root folder of project
* run docker container with MySQL using command `sudo docker-compose -f docker-compose.yml up`
* create jar file with command `mvn package`
* `cd av-services/target`
* run jar `java -jar filename.jar`

## Attributions
[KotlinLang](https://kotlinlang.org/docs/tutorials/spring-boot-restful.html)  
[DigitalOcean tutorials](https://www.digitalocean.com/community/tutorials/)  
[Baeldung](https://www.baeldung.com/)

## Credits
[Mateusz Ostrowski](https://github.com/matostr98) &
[Maciej Stosik](https://github.com/SaronTetra)
