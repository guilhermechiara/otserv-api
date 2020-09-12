## Welcome to OTServAPI [![Build Status](https://travis-ci.org/guilhermechiara/otserv-api.svg?branch=master)](https://travis-ci.org/guilhermechiara/otserv-api)

Hi! The purpose of this project is to provide a simple and robust API for [TheForgottenServer](https://github.com/otland/forgottenserver), so you can easily create integrations and your own website.  It should work fine with the last version of TFS and might need some adjustments to work properly on older versions, as database can change between versions.

### Starting

 1. You can run this project through docker-compose.

	    docker-compose up

 2. Docker will automatically start a mariadb instance, an adminer instance and the apis.
 3. The database is saved inside **database** folder (it's a volume pointing to the container)
 4. If you want to run any scripts to customize your database, you can put .sql files inside the **schema** folder, as mariadb will run the scripts in alphabetical order.
 5. Remember that if you have any custom field, you might need to update the source files so the APIs can understand.
 
 
You can visit the API at `http://localhost:8080`

You can get to Adminer at `http://localhost:8081`

You can access the database using `localhost:3306@root`
