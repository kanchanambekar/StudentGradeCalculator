# StudentGradeCalculator
StudentGradeCalculatorApp is a multi-file Java 21 project that calculates and analyses student grades using a wide range of modern Java language features. It is structured into clean, well-separated packages following industry-standard OOP design principles.
The application takes a list of students with their marks, evaluates their averages, classifies them into grade categories, detects pass/fail/distinction status, and generates detailed reports — all while showcasing lambda expressions, sealed types, records, pattern matching, streams, and more.

# Project Structure
com.gradecalc/
│
├── app/
│   └── StudentGradeCalculatorApp.java       ← Entry point — orchestration only│
├── model/
│   ├── Student.java                          ← Record: immutable student data carrier
│   └── GradeResult.java                     ← Sealed interface: Pass / Fail / Distinction│
├── enums/
│   └── GradeCategory.java                   ← Sealed abstract class: grade labels & emoji│
├── service/
│   ├── GradePredicates.java                 ← Predicate<T> & Function<T,R> business rules
│   ├── StudentRepository.java               ← Supplier<T>: data access / provisioning
│   ├── GradeAnalysisService.java            ← Stream API: filter, map, collect, reduce
│   └── GradeCategoryService.java            ← Pattern matching: instanceof & switch│
└── utils/
    ├── ReportPrinter.java                   ← Consumer<T>: all console output & formatting
    ├── Java11StringDemo.java                ← Java 11 String method demonstrations
    └── EnhancedNpeDemo.java                 ← Java 21 Enhanced NullPointerException demo

# How to Run
Prerequisites

JDK 21 or higher
No external dependencies — pure Java standard library

Compile and Run (One-liner)
javac --enable-preview --release 21 com/gradecalc/**/*.java com/gradecalc/app/*.java && \
java --enable-preview -cp . com.gradecalc.app.StudentGradeCalculatorApp
