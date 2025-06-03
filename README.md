# EventTracker REST API

 # Overview
  
  The goal of the project was to create a Java entity class POJO that modeled a database table. Then configure a Spring Boot app to publish a REST API of the model. A physical exerice   workout database table was created, annotated using Spring REST annotations then CRUD operations executed on it using Spring Data JPA. The REST API send and receive was executed using JSON.


### Endpoints

| HTTP Verb | URI             | Request Body | Response Body | Status |
|-----------|-----------------|--------------|---------------|---------|
| GET       | `/api/workouts`    |              | List of workouts | 200   |
| GET       | `/api/workous/17` |              | Single workout   | 200 or 404 |
| POST      | `/api/workouts`    | JSON of new workout       | JSON of created workout | 201 or 400 |
| PUT       | `/api/workouts/17` | JSON for updating workouts | JSON of updated workouts | 200, 404, or 400 |
| DELETE    | `/api/workouts/17` |              | | 204, 404, or 400 |
