# Report generator in JAVA.

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