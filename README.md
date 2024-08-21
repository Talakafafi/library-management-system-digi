 ### **Library Management System Overview**
 
This system manages various aspects of a library, including users, books, and loans. It provides a range of API endpoints to perform different features related to these entities.

### **User Management**

1. **Add a User**
   - **POST**: [http://localhost:8080/users]
   - **Description**: Creates a new user in the system. You have to insert user details such as name, email, and password in the request body.

2. **Get User by ID**
   - **GET**: [http://localhost:8080/users/{id}]
   - **Description**: Fetches user information associated with the ID.

3. **Get User by Email**
   - **GET**: [http://localhost:8080/users/email/{email}]
   - **Description**: Fetches user information associated with the email. 

4. **Update User Information**
   - **PUT**: [http://localhost:8080/users/{id}]
   - **Description**: Updates the details of an existing user. You have to insert updated user information in the request body. 

5. **Delete a User**
   - **DELETE**: [http://localhost:8080/users/{id}]
   - **Description**: Deletes user from the system by their ID. 

6. **Get All Users**
   - **GET**: [http://localhost:8080/users]
   - **Description**: Fetches a list of all users in the system.

### **Book Management**

1. **Add a New Book**
   - **POST**: [http://localhost:8080/books]
   - **Description**: Adds a new book to the library. You have to insert book details such as title, description, and authorId in the request body.

2. **Get Book by ID**
   - **GET**: [http://localhost:8080/books/{id}]
   - **Description**: Fetches information about a book by its unique ID.

3. **Filter Books**
   - **GET**: [http://localhost:8080/books/filter]
   - **Description**: Fetches a list of books based on specified filtering criteria such as title, author, publisher, category, and ISBN.

4. **Search for Books**
   - **GET**: [http://localhost:8080/books/search]
   - **Description**: Searches for books based on various search criteria including title, author, publisher, and category.

### **Loan Management**

1. **Borrow a Book**
   - **POST**: [http://localhost:8080/loans/borrow]
   - **Description**: Allows a user to borrow a book from the library. 

2. **Return Borrowed Book**
   - **POST**: [http://localhost:8080/loans/return]
   - **Description**: Allows a user to return a previously borrowed book.

3. **Get User Loan History**
   - **GET**: [http://localhost:8080/loans/user-history/{userId}]
   - **Description**: Fetches the loan history for a specific user.


