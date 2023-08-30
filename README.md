# GitHub Scrapper Project

## Description
The GitHub Scrapper Project is designed to fetch and display information about non-fork repositories and their branches for a specific GitHub user.

## Features
- Fetches a list of non-fork repositories for a GitHub user.
- Retrieves branch information for each repository.
- Provides a user-friendly response with repository names, owners, and branch details.

## Installation
1. Clone the repository to your local machine.

```
git clone https://github.com/brajankw/githubscrapper.git
```

2. Navigate to the project directory.

```
cd githubscrapper
```

3. Build and run the project using your preferred Java development environment.

## Usage
1. Launch the application and make a GET request to the appropriate endpoint.
2. Provide the username of the GitHub user whose repositories you want to retrieve.
3. The application will fetch and display a list of non-fork repositories and their branch details.

## Technologies Used
- Java 17
- Spring Framework
- Spring WebFlux
- Lombok
- Jackson JSON library
- WebClient for making API requests

## Endpoints
- `GET /repositories/{username}`
  Fetches non-fork repositories and their branch information for the given GitHub username.

## Error Handling

The GitHub Scrapper Project includes a robust error handling mechanism to provide meaningful responses to different scenarios. Here's how errors are handled:

### NotFoundException
- If a GitHub user does not exist or endpoint returns 404, a `NotFoundException` is thrown.
- The API responds with an HTTP 404 status code and an error message indicating the issue.

### HeaderNotValidException
- If the request header's `Accept` value is not set to `application/json`, a `HeaderNotValidException` is thrown.
- The API responds with an HTTP 406 status code and an error message indicating the issue.