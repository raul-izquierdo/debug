# Debug Library

![License](https://img.shields.io/github/license/raul-izquierdo/debug)
![Release](https://img.shields.io/github/v/release/raul-izquierdo/debug)
![Platform](https://img.shields.io/badge/platform-windows%20%7C%20linux%20%7C%20macos-blue)
![Java](https://img.shields.io/badge/language-java-blue)
![Maven](https://img.shields.io/badge/build-maven-blue)
![JUnit](https://img.shields.io/badge/tested%20with-junit-blue)
![Conventional Commits](https://img.shields.io/badge/conventional%20commits-1.0.0-yellow)
![Keep a Changelog](https://img.shields.io/badge/keep%20a%20changelog-yes-brightgreen)

## Main purpose

A Java library designed to simplify the implementation of validations in code, especially for checking arguments in public and private methods.

## Using this Library

This library provides static methods for validating arguments and conditions. The main method is `requireNonNullArgs`, which checks that all provided arguments are non-null with a single call and, if any argument is invalid, throws an `IllegalArgumentException` indicating the position of the invalid argument.

```java
void myMethod(Object object, String text, List<String> words, List<Integer> numbers) {
    requireNonNullArgs(object, text, words, numbers);

    // method logic here...
}
```

If it is a private method and you prefer to fail with an assertion instead of throwing an `IllegalArgumentException`, you can use `assertNonNullArgs` to validate the arguments with `assert`.
```java
private void myPrivateMethod(Object object, String text, List<String> words, List<Integer> numbers) {
    assertNonNullArgs(object, text, words, numbers);

    // method logic here...
}
```

This will be the main usage. However, additional methods are provided to perform further validations on each argument.

```java
void myMethod(Object object, String text, List<String> words, List<Integer> numbers) {
    requireNonNullArgs(object, text, words, numbers);

    // Specific validations for each argument
    requireNonBlank("text", text);
    requireAllNonBlank("words", words);
    requireAllNonNull("numbers", numbers);

    // Proceed with method logic...
}
```

## Content of the Library

This library consists of three classes:
- `Preconditions`: for validating preconditions, that is, arguments of public methods.
- `Assertions`: methods that simplify working with assertions.
- `CommonChecks`: common validations to use with either of the two previous classes. In fact, most methods in `Preconditions` and `Assertions` delegate to the methods in `CommonChecks` to perform specific validations.

## Installation

For now, simply copy and paste the three classes into your project (yes, it's a bit crude). When I have time, I will upload the library to Maven Central so you can add it as a dependency.

<!-- Add the library as a dependency in your Maven project: -->

<!--
```xml
<dependency>
  <groupId>es.uniovi.raul</groupId>
  <artifactId>checks</artifactId>
  <version>latest</version>
</dependency>
``` -->

Or download the JAR from the [releases page](https://github.com/raul-izquierdo/debug/releases/latest).



## Contributing

If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

## License

See `LICENSE`.
Copyright (c) 2026 Raul Izquierdo Castanedo
