package com.gradecalc.model;

// ─────────────────────────────────────────────────────────────────────────────
// Java 17 — SEALED INTERFACE
// Responsibility : Represents the outcome of a grade evaluation.
//                 Only Pass, Fail, Distinction are permitted subtypes.
// OOP Principle  : Abstraction + Open/Closed (closed for modification,
//                  open via permitted subtypes only)
// ─────────────────────────────────────────────────────────────────────────────
public sealed interface GradeResult
        permits GradeResult.Pass, GradeResult.Fail, GradeResult.Distinction {

    // Java 8 — DEFAULT METHOD (interface behaviour without breaking implementors)
    default String summary() {
        return "Grade Result: " + this.getClass().getSimpleName();
    }

    // Java 8 — STATIC FACTORY METHOD (encapsulates creation logic)
    static GradeResult evaluate(double average) {
        if (average >= 85) return new Distinction(average);
        else if (average >= 50) return new Pass(average);
        else return new Fail(average);
    }

    // Java 16 — RECORDS as permitted subtypes (immutable value objects)
    record Pass(double average)        implements GradeResult {}
    record Fail(double average)        implements GradeResult {}
    record Distinction(double average) implements GradeResult {}
}
