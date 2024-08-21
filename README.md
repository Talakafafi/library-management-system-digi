 Library Management System Overview
This system manages various aspects of a library, including users, books, and loans. It provides a range of API endpoints to perform different features related to these entities.

### **User Management**

1. **Add a User**
   - **POST**: [http://localhost:8080/users](http://localhost:8080/users)
   - **Description**: Creates a new user in the system. You need to provide user details such as name, email, and password in the request body.

2. **Get User by ID**
   - **GET**: [http://localhost:8080/users/{id}](http://localhost:8080/users/{id})
   - **Description**: Retrieves user information by their unique ID. Replace `{id}` with the actual user ID.

3. **Get User by Email**
   - **GET**: [http://localhost:8080/users/email/{email}](http://localhost:8080/users/email/{email})
   - **Description**: Fetches user details based on their email address. Replace `{email}` with the actual email address.

4. **Update User Information**
   - **PUT**: [http://localhost:8080/users/{id}](http://localhost:8080/users/{id})
   - **Description**: Updates the details of an existing user. You need to provide updated user information in the request body. Replace `{id}` with the actual user ID.

5. **Delete a User**
   - **DELETE**: [http://localhost:8080/users/{id}](http://localhost:8080/users/{id})
   - **Description**: Deletes a user from the system by their ID. Replace `{id}` with the actual user ID.

6. **Get All Users**
   - **GET**: [http://localhost:8080/users](http://localhost:8080/users)
   - **Description**: Retrieves a list of all users in the system.

### **Book Management**

1. **Add a New Book**
   - **POST**: [http://localhost:8080/books](http://localhost:8080/books)
   - **Description**: Adds a new book to the library. Provide book details such as title, author, publisher, category, ISBN, and availability in the request body.

2. **Get Book by ID**
   - **GET**: [http://localhost:8080/books/{id}](http://localhost:8080/books/{id})
   - **Description**: Fetches information about a book by its unique ID. Replace `{id}` with the actual book ID.

3. **Filter Books**
   - **GET**: [http://localhost:8080/books/filter](http://localhost:8080/books/filter)
   - **Description**: Retrieves a list of books based on specified filtering criteria such as title, author, publisher, category, and ISBN.

4. **Search for Books**
   - **GET**: [http://localhost:8080/books/search](http://localhost:8080/books/search)
   - **Description**: Searches for books based on various search criteria including title, author, publisher, and category.

### **Loan Management**

1. **Borrow a Book**
   - **POST**: [http://localhost:8080/loans/borrow](http://localhost:8080/loans/borrow)
   - **Description**: Allows a user to borrow a book from the library. Provide the user ID and book ID as query parameters.

2. **Return Borrowed Book**
   - **POST**: [http://localhost:8080/loans/return](http://localhost:8080/loans/return)
   - **Description**: Allows a user to return a previously borrowed book. Provide the user ID and book ID as query parameters.

3. **Get User Loan History**
   - **GET**: [http://localhost:8080/loans/user-history/{userId}](http://localhost:8080/loans/user-history/{userId})
   - **Description**: Retrieves the loan history for a specific user. Replace `{userId}` with the actual user ID.

---

This system supports essential library operations through these RESTful API endpoints, enabling you to manage users, books, and loan transactions efficiently.
