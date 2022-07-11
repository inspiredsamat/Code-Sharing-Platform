# Code Sharing Platform

## Table of Contents

- [About](#about)
- [Contributing](../CONTRIBUTING.md)

## About <a name = "about"></a>

There is a lot of collaboration in programming: many projects require sharing your code with other developers. Using open web resources such as Pastebin is not always an option. In this project, I created my own secure version of a code-sharing platform.

## Usage <a name = "usage"></a>

### POST http://localhost:8080/api/code/new

<h4>Receives the new code snippet and saves it in database.</h4>

### GET http://localhost:8080/code/new

<h4>Generates HTML page to post code snippet.</h4>
If code snippet you are going to share is secret, you can give restricted number of views or time in seconds.
When one of them equals to "0", code snippet will be deleted from the database. 
You can look at the secret code snippet only with it's id.

### GET http://localhost:8080/code/id

<h4>Returns the code snippet with given id and generates HTML page. It is only way of looking at secret code snippet.</h4>

### GET http://localhost:8080/api/code/id

<h4>Returns the code snippet with given id. It is only way of looking at secret code snippet.</h4>

### GET http://localhost:8080/code/latest

<h4>Returns the list of 10 (actually Math.min(10, size_of_not_secret_code_snippets)) latest non-secret code snippets and generates HTML page.</h4>

### GET http://localhost:8080/api/code/latest

<h4>Returns the list of 10 (actually Math.min(10, size_of_not_secret_code_snippets)) latest non-secret code snippets.</h4>
