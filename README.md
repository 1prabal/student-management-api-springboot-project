# Student Management REST API

Spring Boot REST API project for managing students. Built with Java 21, Spring Boot, Spring Data JPA, Validation and MySQL.

## Requirements
- IntelliJ IDEA
- JDK 21
- MySQL Server 8+

## Open in IntelliJ
1. Open this repository folder in IntelliJ IDEA.
2. Set Project SDK to Java 21.
3. Wait for Maven dependencies to download.

## Configure MySQL
Edit `src/main/resources/application.properties` and replace `YOUR_MYSQL_PASSWORD` with your MySQL password. The JDBC URL creates `student_db` automatically if it does not exist. Hibernate creates/updates the tables when the application starts.

## Run
Run `StudentManagementApiApplication.java`. The server starts at `http://localhost:8080`.

## REST endpoints
| Method | URL | Purpose |
|---|---|---|
| POST | `/api/students` | Add a student |
| GET | `/api/students` | Retrieve all students |
| GET | `/api/students/{id}` | Search by ID |
| PUT | `/api/students/{id}` | Update student details, scores and skills |
| DELETE | `/api/students/{id}` | Delete a student |
| GET | `/api/students/merit-list` | Merit list, highest average first |

### Example POST body
```json
{
  "name": "Aarav Sharma",
  "email": "aarav@example.com",
  "courseName": "B.Tech AIML",
  "programmingScore": 92,
  "mathematicsScore": 88,
  "technicalSkills": ["Java", "Spring Boot", "MySQL"]
}
```

Use Postman with `http://localhost:8080/api/students` and Body → raw → JSON.

## Average and performance category
Average = `(programmingScore + mathematicsScore) / 2`.
- 85 or above: Excellent
- 70–84.99: Good
- 50–69.99: Average
- Below 50: Needs Improvement

These category cut-offs are sensible defaults because the assignment did not specify all ranges. Scores are validated from 0 to 100, email addresses must be unique, and technical skills are stored in a separate `student_skills` table. Delete returns HTTP 204; missing IDs return HTTP 404; duplicate emails return HTTP 409.
