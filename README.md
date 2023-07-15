## Restaurant Management Service API

This is an API for managing restaurant operations, such as creating food items, placing orders, and managing user accounts. It provides endpoints for various functionalities and uses several frameworks and languages.

### Table of Contents
- [Frameworks and Languages Used](#frameworks-and-languages-used)
- [Data Flow](#data-flow)
- [Data Structures](#data-structures)
- [Project Summary](#project-summary)
- [Getting Started](#getting-started)
- [Testing Endpoints](#testing-endpoints)
- [License](#license)

### Frameworks and Languages Used
- Java
- Spring Boot
- Maven
- Dependencies:
  - Spring Web
  - Lombok
  - Spring Data JPA
  - Validation
  - WebMVC UI(Swagger UI)
  - MySQL Driver
  - JavaX Mail Api

### Data Flow

#### Controller
- `FoodItemController`:
  - `createFoodItem`: Creates a new food item if the user has admin privileges.
  - `getAllFoodItems`: Retrieves a list of all food items.

- `OrderController`:
  - `createOrder`: Creates a new order if the user has normal user privileges.
  - `getAllOrders`: Retrieves a list of all orders.
  - `updateOrderStatus`: Updates the status of an order if the user has admin privileges.
  - `cancelOrder`: Cancels an order if the user has admin or normal user privileges.

- `UserController`:
  - `signUp`: Registers a new user.
  - `signIn`: Authenticates a user and generates an authentication token.
  - `getAllUsers`: Retrieves a list of all users.
  - `signOutUser`: Signs out a user by deleting their authentication token.

#### Services
- `FoodItemService`:
  - `createFoodItem`: Validates and saves a new food item to the repository.
  - `getAllFoodItems`: Retrieves all food items from the repository.
  - `authenticateAdmin`: Authenticates if a user has admin privileges.

- `OrderService`:
  - `createOrder`: Validates and saves a new order to the repository.
  - `getAllOrders`: Retrieves all orders from the repository.
  - `authenticateUser`: Authenticates if a user has normal user privileges.
  - `getOrderById`: Retrieves an order by its ID from the repository.
  - `authenticateAdmin`: Authenticates if a user has admin privileges.
  - `updateOrderStatus`: Updates the status of an order in the repository.
  - `cancelOrder`: Cancels an order in the repository.

- `UserService`:
  - `signUp`: Validates and registers a new user to the repository.
  - `signIn`: Authenticates a user's credentials and generates an authentication token.
  - `getAllUsers`: Retrieves all users from the repository.
  - `signOutUser`: Signs out a user by deleting their authentication token.

#### Repository
- `AuthRepo`: Repository for managing authentication tokens.
- `FoodItemRepo`: Repository for managing food items.
- `OrderRepo`: Repository for managing orders.
- `UserRepo`: Repository for managing users.

#### Database Design
The API uses a MySQL database for storing data. The database design includes tables for users, food items, orders, and authentication tokens.

### Data Structures Used
- `FoodItem`: Represents a food item with attributes such as ID, title, description, price, and creation timestamp.
- `Order`: Represents an order with attributes such as ID, food item, user, quantity, status, and creation timestamp.
- `User`: Represents a user with attributes such as ID, username, password, email, and role.
- `AuthenticationToken`: Represents an authentication token with attributes such as ID, token value, creation timestamp, and associated user.

### Project Summary
The Restaurant Management Service API provides endpoints for creating food items, placing orders, managing user accounts, and updating order status. It includes authentication and authorization mechanisms to ensure secure access to the API endpoints. The API uses a MySQL database to store and retrieve data related to food items, orders, users, and authentication tokens.

### Getting Started
- To get started with the API, you need to set up a development environment with Java and MySQL.
- Clone the project repository [Restaurant Management Service API](https://github.com/ayaan097/Restaurant-Management-Service-Api.git)
- Import it into your preferred IDE. Update the MySQL database configuration in the `application.properties` file with your database credentials.
- Run the application, and you can then access the API endpoints using the provided base URL.

### Testing Endpoints

You can test the API endpoints using tools like Postman or by accessing the Swagger UI documentation at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html). The Swagger UI provides a user-friendly interface to interact with the API and test different endpoints.

### FoodItem Endpoints

- **Create a Food Item**
  - Method: POST
  - URL: `/fooditem?authToken={authToken}`
  - Request Body:
    ```json
    {
      "title": "Burger",
      "description": "Delicious burger with juicy patty",
      "price": 9.99,
      "dummyImageUrl": "https://example.com/burger.jpg"
    }
    ```
  - Response: "Food item created successfully."

- **Get All Food Items**
  - Method: GET
  - URL: `/fooditem/fooditems`
  - Response: Array of Food Item objects

### Order Endpoints

- **Create an Order**
  - Method: POST
  - URL: `/orders?authToken={authToken}`
  - Request Body:
    ```json
    {
      "foodItem": {
        "id": 1,
        "title": "Burger",
        "description": "Delicious burger with juicy patty",
        "price": 9.99,
        "createdAt": "2023-07-15T10:30:00",
        "dummyImageUrl": "https://example.com/burger.jpg"
      },
      "user": {
        "id": 1,
        "username": "john_doe",
        "userEmail": "john@example.com",
        "role": "NORMAL_USER"
      },
      "quantity": 2,
      "status": "CREATED",
      "createdAt": "2023-07-15T11:00:00"
    }
    ```
  - Response: "Order created successfully."

- **Get All Orders**
  - Method: GET
  - URL: `/orders`
  - Response: Array of Order objects

- **Update Order Status**
  - Method: POST
  - URL: `/orders/updateOrderStatus/{orderId}?authToken={authToken}&newStatus={newStatus}`
  - Path Variables:
    - `orderId`: ID of the order to update
  - Request Parameters:
    - `newStatus`: New status of the order (CREATED, DISPATCHED, DELIVERED)
  - Response: "Order Status Updated!!!"

- **Cancel an Order**
  - Method: DELETE
  - URL: `/orders/cancelOrder/{orderId}?authToken={authToken}`
  - Path Variables:
    - `orderId`: ID of the order to cancel
  - Response: "Order is canceled."

### User Endpoints

- **Register a User**
  - Method: POST
  - URL: `/users/signup`
  - Request Body:
    ```json
    {
      "username": "john_doe",
      "password": "password123",
      "userEmail": "john@example.com",
      "role": "NORMAL_USER"
    }
    ```
  - Response: SignUpOutput object

- **Authenticate User**
  - Method: POST
  - URL: `/users/signin`
  - Request Body:
    ```json
    {
      "email": "john@example.com",
      "password": "password123"
    }
    ```
  - Response: "Sign in successful."

- **Get All Users**
  - Method: GET
  - URL: `/users/all`
  - Response: Array of User objects

- **Sign Out User**
  - Method: DELETE
  - URL: `/users/signOut?email={email}&token={token}`
  - Request Parameters:
    - `email`: Email of the user to sign out
    - `token`: Authentication token of the user
  - Response: "User Signed out Successfully"

Please note that you need to replace `{authToken}`, `{orderId}`, `{newStatus}`, `{email}`, and `{token}` with the actual values when testing the endpoints.


### License
This project is Not licensed 
