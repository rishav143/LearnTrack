# Learn Track Management System

A simple console-based Java application to manage Courses, Students, and Enrollments. It demonstrates clean separation of concerns via entities and services, basic validation, and a menu-driven UI.

## Features
- Create, update, list, and remove courses
- Register students and update their details
- Enroll students into courses and view enrollments

## Requirements
- Java 17 or later
- A terminal/shell

## Compile

From the project root:

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
```

## Run

```bash
java -cp out main.com.learntrack.studentmanagement.ui.Main
```

If using an IDE (e.g., IntelliJ IDEA), set the project SDK to Java 17+, mark `src` as a Source Root, and run `Main`.

