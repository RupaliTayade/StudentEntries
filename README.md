**_studentEntries_**

This is a Spring Boot project that allows you to manage Students entries. You can create student list by assigning teachers. First you add a few teachers list. 
You can edit teachers information and add profile photo too. 
Add students and then you can update student by their profile as well as select teacher for students.  


The project uses Spring Boot, Spring Data JPA, and Thymeleaf. The project is using an H2 embedded MySQL database, but you can connect it to a local database through the application.properties file. 
Just unmark and add your information. 

About patterns used:
1. Builder Design Pattern (Creating object)
2. Facade Design Pattern (Database connection through front end)
3. Singleton (one to one class, same instance)
4. Controller
5. Adapter (mappers, transferring dto to entity and back)
6. Interface
