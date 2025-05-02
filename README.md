# Labseq Sequence Calculator

## 📖 Description

Labseq Sequence Calculator is a web application that calculates the value of a custom sequence at a given index. It features a backend API built with Quarkus and a frontend developed with Angular.js, deployed using Docker. This project was developed as a technical test for Multivision.ltda.

## 🛠️ How to Use

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/tiagoAry15/Multivision-Challenge.git
    cd Multivision-Challenge
    ```

2. **Run with Docker Compose**:
    Ensure Docker and Docker Compose are installed, then execute:
    ```bash
    docker-compose up
    ```

3. **Access the Web Application**:
- Open your browser or API client and navigate to `http://localhost:4200`.

![Web page](webpage.png)

Enter a non-negative integer to calculate the value at that index in the Labseq sequence.


4. **Access the API**:
    - Open your browser or API client and navigate to `http://localhost:8080`.
    - OpenAPI documentation is available at `http://localhost:8080/swagger-ui`.

5. **Invoke the API**:
    Use the `/labseq/{n}` endpoint to calculate the `labseq` value for a given integer positive number `n`.

## 🧰 Technologies Used

- **Quarkus**: Java framework for building high-performance applications.
- **OpenAPI (Swagger)**: For API documentation.
- **Angular.js**: Frontend for interacting with the API.
- **Docker & Docker Compose**: Simplified containerized deployment.

## Assumptions About the Challenge

### 1. Return Type
During my tests, I noticed that when I used large values for `n`, some results were returning negative numbers, which is incorrect since the entire algorithm is based on summing positive numbers. That’s why I chose to use `BigInteger` instead of `int` or `long`. The challenge involves testing the algorithm with very large values of `n`, and `BigInteger` is the appropriate choice as it supports arbitrarily large numbers without the risk of overflow, which can occur with primitive types like `int` or `long`. After that I chose to convert the numeric value to String



| Type        | Bit Size | Minimum Value             | Maximum Value              |
|-------------|----------|---------------------------|----------------------------|
| `int`       | 32-bit   | -2,147,483,648            | 2,147,483,647              |
| `long`      | 64-bit   | -9,223,372,036,854,775,808 | 9,223,372,036,854,775,807 |
| `BigInteger`| N/A      | No fixed limit            | No fixed limit             |

> `BigInteger` is not limited by a fixed bit size; it can grow as large as the available memory allows.

### 2. Redis Cache vs In-Memory Cache
I chose **in-memory caching** over Redis due to its performance. Acessing memory is significantly faster than communicating with Redis over the network. No serialization or network latency is involved. 
The current project is lightweight, runs on a single node, and does not require distributed caching or persistence between restarts. In-memory caching keeps the architecture simple and fast.
**Trade-off**:  
If the application restarts, all in-memory cache is lost. However, for this scope, I believe it’s an acceptable limitation.

### 3. Iterative vs Recursive Approach
Using plain recursion without caching leads to exponential time complexity (O(2ⁿ)), resulting in very slow performance and a high risk of stack overflow or out-of-memory errors with large inputs.  
By switching to an **iterative approach** and caching computed values, the application's performance improved by ~90%, and the occurrence of expected errors significantly decreased.

### 4. Usage of Concurrent Hash Map as Cache
`ConcurrentHashMap` was a better choice for caching due to its built-in thread-safety. It allows multiple threads to safely access and modify the cache simultaneously without requiring external synchronization.  
Additionally, it is optimized for high concurrency, offering excellent read and write performance even under heavy load.





