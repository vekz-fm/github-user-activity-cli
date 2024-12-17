# GitHub User Activity CLI

This project is a simple command-line interface (CLI) built in Java 21 that fetches and displays the recent activity of a GitHub user. It utilizes the GitHub API to retrieve data and presents it in a user-friendly format within the terminal. This project is a great exercise for practicing skills such as working with APIs, handling JSON data, and building CLI applications.

It is a solution for the Task Tracker challenge from [roadmap.sh](https://roadmap.sh/projects/github-user-activity).

## Features

*   Fetches recent GitHub activity for a specified user.
*   Displays activity in the terminal.
*   Simple command-line interface.

## Requirements

*   Java 21 or later.
*   Maven.
*   Internet connection (to access the GitHub API).

## Installation and Usage

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/vekz-fm/github-user-activity-cli.git
    ```

2.  **Navigate to the project directory:**

    ```bash
    cd github-user-activity-cli
    ```

3.  **Build the project using Maven:**

    ```bash
    mvn clean package
    ```

    This command will compile the Java code and create an executable JAR file in the `target` directory. The JAR will be named similar to `github-user-activity-cli-1.0.jar`.

4.  **Run the application:**

    ```bash
    java -jar target/github-user-activity-cli-1.0.jar
    ```
    
5.  **Using the CLI:**

    Once the application is running, you can use the following commands:

    *   **Display GitHub activity:**

        ```
        github-activity <username>
        ```

        Replace `<username>` with the GitHub username you want to check. For example: `github-activity google`.

    *   **Exit the application:**

        ```
        exit
        ```

## Example
```bash
java -jar target/github-activity-1.0.jar
github-activity octocat
```
This would fetch and display the recent activity of the GitHub user "octocat".
