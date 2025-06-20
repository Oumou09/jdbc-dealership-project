JDBC-Dealership Project

## Description of the Project

This Java console application is designed to manage a car dealership’s inventory and sales operations. Its primary purpose is to help dealership staff efficiently track vehicles, maintain inventory records, and process sales contracts.

Intended for dealership managers and sales personnel, the application provides core functionalities such as adding and removing vehicles from inventory, recording sales contracts with customer and vehicle details, and ensuring data integrity through validation and relational constraints.

By automating inventory management and sales tracking, the application aims to streamline dealership workflows, reduce manual errors, and provide a reliable system for monitoring vehicle availability and sales history



## User Stories

- As a user, I want to add new vehicles to the inventory so I can keep the system updated with the latest available cars for sale.
- As a user, I want to record sales contracts quickly and accurately so I can ensure every sale is properly tracked.
### Prerequisites

- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK is installed and configured in IntelliJ.

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Find the main class with the `public static void main(String[] args)` method.
5. Right-click on the file and select run 'Main.java()'' to start the application.

## Technologies Used

- Java: 17
- MySQL workbench

## Future Work

Outline potential future enhancements or functionalities you might consider adding:

- Adding functionality to create, update, and view customer profiles. 
- Link customers to sales contracts for full transaction history.


##  Reflection: Interesting Code I Worked On

Inserting Sales Contracts with Auto-Generated Keys implemented a method to insert new sales contracts into the database using PreparedStatement.RETURN_GENERATED_KEYS. 

This allowed me to retrieve the auto-generated contract ID after insertion and link it back to the Java object. It taught me how to manage database connections, safely insert data, and retrieve generated primary keys — a technique commonly used in real-world applications.

Managing Foreign Key Constraints and Error Handling while inserting or deleting records, I encountered several SQL exceptions such as:

SQLIntegrityConstraintViolationException – when trying to insert a contract for a VIN that didn’t exist in the vehicles table.

MysqlDataTruncation – when inserting a VIN longer than the allowed limit.

These situations pushed me to improve my understanding of relational database constraints, data validation, and robust error handling to maintain data integrity.

## Resources

- Potato Sensei
- https://github.com/RayMaroun/yearup-spring-section-10-2025/tree/master
- [Effective Java](https://www.example.com)

## Team Members

- **Oumou Diallo** - Developer.

## Thanks

- Thank you to Potato sensei, Raymond and my peers for all their continuous support and guidance.
 