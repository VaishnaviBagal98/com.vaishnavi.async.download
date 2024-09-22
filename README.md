# Async Statement API Development Assignment
An API for making a asynchronous statement generation ,allowing user to sends request and nofities when statement generated successfully .

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)
- [Contributing](#contributing)
- [Acknowledgments](#acknowledgments)

## Features

- It has mocked the core banking service.
- Download the statement and notifies the user through email.
- Statement pages generates asynchronously.
- Reduce the errors and system timeout issues.
- Performed the paginations.
- RESTful API with JSON responses.

## Technologies Used

- **Java**: 22
- **Spring Boot**: 3.3.3
- **Spring Data JPA**: 3.3.3
- **Hibernate**: 8.0.1
- **Maven**: 3.3.3

## Installation
1.Clone the repository:
  https://github.com/VaishnaviBagal98/com.vaishnavi.async.download.git
  
2.Install the dependencies using:  **mvn install** command

3.Set-up the local database:
  server.port=9197
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driver-class-name=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=password

4. How to configure your gmail account for testing API locally :
   - Go to your gmail account
   - ![image](https://github.com/user-attachments/assets/6cc930b1-ff8b-4291-83f8-7683a080b7e9)
   - click Manage your google Account.
   - Search App Password.
   - Put your gmail password
   - You will receive randome password eg: alaf lhuu nliz xekl save it somewhere and put in your Application.properties file
   - eg:
     spring.mail.username=vaishnavibagal1998@gmail.com
     spring.mail.password=alaf lhuu nliz xekl

## Usage
  Open your web browser and go to http://localhost:9197 
  
## Testing
  - Run tests using Maven: mvn test 

