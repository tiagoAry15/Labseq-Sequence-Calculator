# Labseq Sequence Calculator

## 📖 Description

The Labseq Sequence Calculator is a RESTful web service built with the **Quarkus Java framework**. It computes and returns values from the `labseq` sequence, offering high performance and scalability. The project also includes OpenAPI documentation and supports containerized execution using **Docker Compose**.

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

![Web page](image.png)

Enter a non-negative integer to calculate the value at that index in the Labseq sequence.


4. **Access the API**:
    - Open your browser or API client and navigate to `http://localhost:8080`.
    - OpenAPI documentation is available at `http://localhost:8080/swagger-ui`.

5. **Invoke the API**:
    Use the `/labseq/{n}` endpoint to calculate the `labseq` value for a given integer positive number `n`.

## 🧰 Technologies Used

- **Quarkus**: Java framework for building high-performance applications.
- **OpenAPI (Swagger)**: For API documentation.
- **Redis**: Caching layer to optimize calculations.
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

### 2. Why I Went Iterative Instead of Recursive

Doing everything with plain recursion and no caching blows up the call tree to O(2ⁿ), making the code slow and causing constant stack overflows. Swapping in an **iterative** approach—where I keep results in an array—made the algorithm nearly 90% faster and completely nixed those stack overflow issues.

