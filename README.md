# Async Statement API Development Assignment
An API for making a asynchronous statement generation ,allowing user to sends request and nofities when statement generated successfully .

-Existing System 
  1. Makes a blocking call to the Core Banking system to retrieve statement data (in JSON format).
  2. Sends this data to a templating engine to generate the PDF.
  3. Returns the PDF in response to the user.
 However, this approach forces the user to wait for a response. Upstream capacity issues and long transaction histories increase the
 likelihood of timeout errors.
<img width="362" alt="image" src="https://github.com/user-attachments/assets/b8bcdcd2-dc09-4812-a79e-a7b625173e89">

-Proposed Solution
 Refactor the system to employ an asynchronous architecture, which will enhance user experience by eliminating blocking calls and reducing
 errors.
 Process Flow:
 1. Upon request submission, the user receives a response that the request is accepted.
 2. The backend asynchronously processes the request, invokes the Core Banking system recursively to gather all relevant data, and
 generates the statement.
 3. The system notifies the customer once the statement is ready, providing a download link.
 <img width="398" alt="image" src="https://github.com/user-attachments/assets/6c351ca7-d0b5-4400-b00c-67f6ee14d81e">



## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)

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

4.How to configure your gmail account for testing API locally :

   - Go to your gmail account
   - Click Manage your google Account.
   - ![image](https://github.com/user-attachments/assets/6cc930b1-ff8b-4291-83f8-7683a080b7e9)
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

