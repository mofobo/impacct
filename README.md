# ImpAcct

## Description
ImpaAcct is SpringBoot-Thymeleaf based Web App developed by Mohammed Fonseca Boukhalat for the MAS-RAD FRM course 2021.

The product is called "ImpAcct", it is a contraction of "Improve your Accounting". The objective of the product is to help the user in the management of his expenses. This is done by tracking his expenses and income and presenting various reports on the distribution of his expenses and income. The user will thus be in possession of all the data necessary to better manage his budgets.

## Prerequisites
- docker
- docker-compose

## Make It Run

Clone or download the repository locally to your machine.

From the project directory root path, execute:
1. ./gradlew clean build
2. docker-compose build
3. docker-compose up

#### Credentials

- admin  
    username: pablo.escobar@grr.la  
    password: plataOplomo

- user  
    username: jean-marie.lepen@grr.la  
    password: pointDEdétail
    
## Features
- create/read/edit/delete transactions
- create/read/edit/delete categories
- show transactions reports by year and month
## Backlog
You can find the product backlog [here](-).
## Tech
### Library
ImpAcct use a number of open source projects to work properly:
* Language: [Kotlin]((https://kotlinlang.org/))
* Authentication: [OKTA](https://developer.okta.com/)
* Layout: [Thymeleaf](https://www.thymeleaf.org/)
### Tools
Some tools that I used for the development of the application:
* [IntelliJ IDEA](https://www.jetbrains.com/fr-fr/idea/) - IDE for Java application development (by JetBrains)
* [GitHub](https://github.com) - Software source code hosting and version control.
* [dillinger](https://dillinger.io/) - dillinger is an online Markdown editor
* [trello](https://trello.com/) - trello is a web-based Kanban-style list-making application

## License
MIT : **Free Software, Hell Yeah!**