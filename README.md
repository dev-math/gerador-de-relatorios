# Report generator in JAVA.
Refactored Java "Report Generator" project using Strategy & Decorator patterns. Customizable sorting, filtering & formatting for product listings. Utilizes Java collections for improved data handling. New features include descending sorting, price range filtering, substring-based filtering, text color decorators, and CSV data import.

## Usage
Follow the steps below for running it.

In the project folder, compile with:
```bash
javac -d out -cp ./:lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar:./lib/jsoup-1.16.1.jar ./**/*.java
```
Execute with:
```bash
java -cp ./:lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar:./lib/jsoup-1.16.1.jar::./out src.Main
```