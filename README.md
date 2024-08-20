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

- Java 11 or higher
- Gradle 6.0+ (or Gradle Wrapper included in the project)
- PostgreSQL or another relational database (for Authorization Server)

### Running the Application

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/yourusername/your-repo.git
    cd your-repo
    ```

2. **Build the Project**:

    ```bash
    ./gradlew build
    ```

3. **Run the Modules**:

    - **OAuth2 Client**:

        ```bash
        cd oauth2-client
        ./gradlew bootRun
        ```

    - **OAuth2 Authorization Server**:

        ```bash
        cd oauth2-authorization-server
        ./gradlew bootRun
        ```

    - **OAuth2 Resource Server**:

        ```bash
        cd oauth2-resource-server
        ./gradlew bootRun
        ```

### Configuration

Update the `application.yml` or `application.properties` files in each module to set up the necessary configuration parameters:

- **OAuth2 Client**: Configure OAuth2 provider details and client settings.
- **OAuth2 Authorization Server**: Set up client details, token settings, and security parameters.
- **OAuth2 Resource Server**: Configure token validation and API security settings.

### API Documentation

- **OAuth2 Client**: [API Documentation Link]
- **OAuth2 Authorization Server**: [API Documentation Link]
- **OAuth2 Resource Server**: [API Documentation Link]

### Troubleshooting

- **Common Issues**: Ensure all modules are properly configured and running on their respective ports.
- **Logs**: Check the application logs for detailed error messages.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request with your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or support, please contact:

- **Name**: Your Name
- **Email**: your.email@example.com
- **GitHub**: [Your GitHub Profile](https://github.com/yourusername)

