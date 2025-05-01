# Labseq Sequence Calculator

## 📖 Description

The Labseq Sequence Calculator is a RESTful web service built with the **Quarkus Java framework**. It computes and returns values from the `labseq` sequence, offering high performance and scalability. The project also includes OpenAPI documentation and supports containerized execution using **Docker Compose**.

## 🛠️ How to Use

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/your-repo/labseq-sequence-calculator.git
    cd labseq-sequence-calculator
    ```

2. **Run with Docker Compose**:
    Ensure Docker and Docker Compose are installed, then execute:
    ```bash
    docker-compose up
    ```

3. **Access the API**:
    - Open your browser or API client and navigate to `http://localhost:8080`.
    - OpenAPI documentation is available at `http://localhost:8080/swagger-ui`.

4. **Invoke the API**:
    Use the `/labseq/{n}` endpoint to calculate the `labseq` value for a given `n`.

## 🧰 Technologies Used

- **Quarkus**: Java framework for building high-performance applications.
- **OpenAPI (Swagger)**: For API documentation.
- **Redis**: Caching layer to optimize calculations.
- **Angular.js**: Frontend for interacting with the API.
- **Docker & Docker Compose**: Simplified containerized deployment.