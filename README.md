# Tic Tac Toe
[![Build Status](https://travis-ci.org/hkgumbs/tictactoe-java.svg?branch=master)](https://travis-ci.org/hkgumbs/tictactoe-java)

Playable, unbeatable tic tac toe game.

## Requires
- Java 8
- Gradle
- jUnit 4.12

## Instructions
- Play game:

    ```
    gradle jar
    java -jar build/libs/tictactoe-java.jar [--size <number>] [--padding <number>] [--humans|--minimax]
    ```

- See jUnit test report:

    ```
    gradle test
    open build/reports/tests/index.html
    ```

- See test jacoco test coverage:

    ```
    gradle jacocoTestReport
    open build/reports/jacoco/test/html/index.html
    ```

