
# Film API
Film API is a RESTful API containing a datastore and an interface allowing users to store and manipulate movies, characters and franchises. The application constructed in Spring Web is comprised of database created through Hibernate. The data consists of movie information such as the title, director, genres, poster picture and trailer link. A movie can have multiple characters playing in the movie as well as potentially being a part of a franchise. A character has a name as well as an alias, picture and gender. Characters can play in several movies. Franchises has a name, a description and has a collection of multiple movies.

![Alt text](erDiagram.png)  
Picture 1. ER Diagram of the database created with Hibernate


## Installation

### Prerequisites

- Java 17 or later version and any supporting IDE, suggestively VSCode or IntelliJ
- A relational database management system (RDBMS) supporting SQL, suggestively PostgreSQL
- Maven version 3.8.1 or later


### Configuration

To clone this application run the following command in your terminal:
```bash
git clone https://github.com/98emre/Film-API
```


#### Dependencies in your application.properties file

Make sure all you have all of the following dependencies in your pom.xml file and use the latest version:
- spring-boot-starter
- spring-boot-starter-data-jpa
- spring-boot-starter-actuator
- spring-boot-starter-web
- spring-boot-starter-test
- springdoc-openapi-starter-webmvc-ui
- postgresql (make sure to change this dependency in case you use another relational database management system)
- lombok
- mapstruct
- mapstruct-processor


As well as verifying that your maven plugin version exists and is not later than your installed maven version.


In the application.properties file, to configure to your database, set your environment variables alternatively set them explicitly:
```
spring.datasource.url=${POSTGRES_URL}
spring.datasource.username=${POSTGRES_USERNAME}
spring.datasource.password=${POSTGRES_PASSWORD}
```


When mapping and seeding the database and running the application for the first time, make sure this property is set to create:
```
spring.jpa.hibernate.ddl-auto=create
```

When mapping and seeding is completed set the property to update:
```
spring.jpa.hibernate.ddl-auto=update
```


## Usage
The application will run on port 8080 by default. 
If another port would be desired, this can be set in the application.properties file.


### API Endpoints
| HTTP Verbs | Endpoints | Action |
| --- | --- | --- |
| POST | /api/movies/add | To create a new movie |
| POST | /api/characters/add | To create a new character |
| POST | /api/franchises/add | To create a new franchise |
| GET | /api/movies | To retrieve all movies on the platform |
| GET | /api/characters | To retrieve all characters on the platform |
| GET | /api/franchises | To retrieve all franchises on the platform |
| GET | /api/movies/:movieId | To retrieve details of a single movie |
| GET | /api/characters/:characterId | To retrieve details of a single character |
| GET | /api/franchises/:franchiseId | To retrieve details of a single franchise |
| GET | /api/movies/:movieId/characters | To retrieve all characters of a single movie |
| GET | /api/franchises/:franchiseId/movies | To retrieve all movies of a single franchise |
| GET | /api/franchises/:franchiseId/movies/characters | To retrieve all characters of all the movies of a single franchise | 
| PATCH | /api/movies/update/:movieId | To edit the details of a single movie |
| PATCH | /api/characters/update/:characterId | To edit the details of a single character |
| PATCH | /api/franchises/update/:franchiseId | To edit the details of a single franchise |
| DELETE | /api/movies/delete/:movieId | To delete a single movie |
| DELETE | /api/characters/delete/:characterId | To delete a single character |
| DELETE | /api/franchises/delete/:franchiseId | To delete a single franchise |


## Authors

@levenfalk  
@98emre


## License
This project is available for use under the [MIT License](https://choosealicense.com/licenses/mit/) 