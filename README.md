# OAuth2 Client, Authorization Server, and Resource Server

## Overview

This project includes three distinct modules:

1. **OAuth2 Client**: Handles OAuth2 authentication and authorization for client applications.
2. **OAuth2 Authorization Server**: Manages the authorization process and token issuance.
3. **OAuth2 Resource Server**: Secures APIs by validating access tokens provided by clients.

Each module is implemented using Spring Security, with separate configurations and functionalities.

## Modules

### 1. OAuth2 Client

**Purpose**: This module provides the functionality for clients to authenticate using OAuth2 and obtain access tokens.

**Features**:
- OAuth2 login flow
- Token management
- Token storage and retrieval

**Configuration**:
- Update `application.yml` or `application.properties` with the OAuth2 provider details.

**Dependencies**:
- Spring Boot Starter Security
- Spring Security OAuth2 Client

### 2. OAuth2 Authorization Server

**Purpose**: This module serves as the authorization server, responsible for issuing OAuth2 tokens and managing client credentials.

**Features**:
- Token issuance
- Client registration
- Token revocation

**Configuration**:
- Configure client details, token settings, and security properties in `application.yml` or `application.properties`.

**Dependencies**:
- Spring Boot Starter Security
- Spring Security OAuth2 Authorization Server

### 3. OAuth2 Resource Server

**Purpose**: This module secures APIs by validating the access tokens issued by the authorization server.

**Features**:
- Token validation
- Access control
- Secure API endpoints

**Configuration**:
- Set up resource server properties and token validation settings in `application.yml` or `application.properties`.

**Dependencies**:
- Spring Boot Starter Security
- Spring Security OAuth2 Resource Server

## Getting Started

### Prerequisites

- Java 17
- Gradle (or Gradle Wrapper included in the project)
- MySQL (for Authorization Server)

### Running the Application

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/PranshuBarar/OAuth2-Client_Auth_Resource_server_setup.git
    cd OAuth2-Client_Auth_Resource_server_setup
    ```

2. **Build the Project**: (Go inside each individual module and run ./gradlew build)

    ```bash
    ./gradlew build
    ```

3. **Run the Modules**:

    - **OAuth2 Client**:

        ```bash
        cd clientv4
        ./gradlew bootRun
        ```

    - **OAuth2 Authorization Server**:

        ```bash
        cd resourceserverv4
        ./gradlew bootRun
        ```

    - **OAuth2 Resource Server**:

        ```bash
        cd authorizationserverv4
        ./gradlew bootRun
        ```

### Configuration

Update `application.properties` files in each module to set up the necessary configuration parameters:

- **OAuth2 Client**: Configure OAuth2 provider details and client settings.
- **OAuth2 Authorization Server**: Set up client details, token settings, and security parameters.
- **OAuth2 Resource Server**: Configure token validation and API security settings.

### Troubleshooting

- **Common Issues**: Ensure all modules are properly configured and running on their respective ports.
- **Logs**: Check the application logs for detailed error messages.

## Contact

For any questions or support, please contact:

- **Name**: Pranshu Barar
- **Email**: pranshubarar1851996@gmail.com
- **GitHub**: [My GitHub Profile](https://github.com/PranshuBarar)

