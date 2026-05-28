package com.gradecalc.model;

import java.util.List;
import java.util.Objects;

// ─────────────────────────────────────────────────────────────────────────────
// Java 16 — RECORD
// Responsibility : Immutable data carrier for a student entity.
//                 Auto-generates constructor, accessors, equals, hashCode,
//                 toString.
// OOP Principle  : Encapsulation (fields are private final by default in
//                  records), Single Responsibility (holds student data only)
// ─────────────────────────────────────────────────────────────────────────────
public record Student(String name, int id, List<Double> marks) {

    // Compact canonical constructor — guards against invalid state (fail-fast)
    public Student {
        Objects.requireNonNull(name,  "Student name must not be null");
        Objects.requireNonNull(marks, "Marks list must not be null");
        if (marks.isEmpty())
            throw new IllegalArgumentException("Marks list is empty for: " + name);
        marks = List.copyOf(marks); // defensive copy — immutability
    }

    // Derived behaviour that belongs with the data
    public double average() {
        return marks.stream()
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);
    }
}
