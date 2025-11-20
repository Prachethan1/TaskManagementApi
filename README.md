
# Task Management API

* Technology Stack: Java, Spring Boot, Spring Data JPA, H2 Database
* Focus Areas: RESTful APIs, Database Relationships (1:1, 1:N, N:M), JPA Annotations

A Spring Boot RESTful API for a simple task management system that manages users, tasks, comments, and tags with support for filtering tasks by tags.

Getting Started
Prerequisites

* Java 17+

* Maven 3.x

* H2 Database

## Running the Application

Clone the repository:
```bash
   git clone https://github.com/Prachethan1/TaskManagementApi
   cd TaskManagementApi
```

Build and run the project:
```bash
   mvn clean install
   mvn spring-boot:run
```


### Database Schema 
#### Entities and Relationships

#### User 
id, name, email

One-to-Many with Task (@OneToMany)

One-to-Many with Comment

#### Task

id, title, description, status, createdAt, updatedAt

Many-to-One with User (@ManyToOne)

One-to-Many with Comment

Many-to-Many with Tag (@ManyToMany)


#### Comment

id, text, createdAt

Many-to-One with User and Task

#### Tag

id, name

Many-to-Many with Task

#### API will be available at: http://localhost:8080/api

### Users
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | /api/users | Create a new user |
| GET    | /api/users | Get all users |
| GET    | /api/users/{userId}/tasks | Get all tasks for a specific user |

### Tasks
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | /api/tasks | Create a new task |
| GET    | /api/tasks | Get all tasks (added filter by status or tag) |
| GET    | /api/tasks/{id} | Get a task by ID |
| PUT    | /api/tasks/{id} | Update a task |
| DELETE | /api/tasks/{id} | Delete a task |

### Comments
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | /api/tasks/{taskId}/comments | Add a comment to a task |
| GET    | /api/tasks/{taskId}/comments | Get all comments for a task |

### Tags
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST   | /api/tags | Create a new tag |
| GET    | /api/tags | Get all tags |
| POST   | /api/tasks/{taskId}/tags/{tagId} | Assign a tag to a task |
| DELETE | /api/tasks/{taskId}/tags/{tagId} | Remove a tag from a task |

* Postman Link for all apis : https://web.postman.co/workspace/My-Workspace~39a2f4bb-03ba-4102-8f3f-840c169bdc37/collection/30375219-236492ff-7af4-45d3-98bc-6c949b982f54?action=share&source=copy-link&creator=30375219


## API ScreenShots
createUser
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/createUser.PNG?raw=true)
getAllUsers
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/getAllUsers.PNG?raw=true)
getUserTasks
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/getUserTasks.PNG?raw=true)
createTask
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/createTask.PNG?raw=true)
getTasksByFilter
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/getTasksByFilter.PNG?raw=true)
getTaskById
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/getTaskById.PNG?raw=true)
updateTaskById
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/updateTaskById.PNG?raw=true)
deleteTaskById
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/deleteTaskById.PNG?raw=true)
addComment
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/addComment.PNG?raw=true)
getTaskComments
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/getTaskComments.PNG?raw=true)
createTag
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/createTag.PNG?raw=true)
getTags
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/getTags.PNG?raw=true)
assignTagtoTask
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/assignTagtoTask.PNG?raw=true)
deleteTaskTag
![update](https://github.com/Prachethan1/TaskManagementApi/blob/main/screenshots/deleteTaskTag.PNG?raw=true)




