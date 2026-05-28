package com.gradecalc.service;

import com.gradecalc.model.Student;

import java.util.function.Function;
import java.util.function.Predicate;

// ─────────────────────────────────────────────────────────────────────────────
// Java 8 — FUNCTIONAL INTERFACES (Predicate, Function)
// Responsibility : Central registry of reusable Predicates and Functions
//                 that define grade-related business rules.
// OOP Principle  : Single Responsibility (only rule definitions, no I/O),
//                  Open/Closed (add new predicates without touching consumers)
// ─────────────────────────────────────────────────────────────────────────────
public final class GradePredicates {

    // Utility class — prevent instantiation
    private GradePredicates() {
        throw new UnsupportedOperationException("Utility class");
    }

    // ── Predicates ────────────────────────────────────────────────────────────

    /** True when a student's average is >= 50 (passing threshold). */
    public static final Predicate<Student> IS_PASSING =
            student -> student.average() >= 50;

    /** True when a student's average is >= 85 (distinction threshold). */
    public static final Predicate<Student> IS_DISTINCTION =
            student -> student.average() >= 85;

    /** True when a student is failing (negation of IS_PASSING). */
    public static final Predicate<Student> IS_AT_RISK =
            IS_PASSING.negate();

    /** True when a student both passes AND achieves distinction. */
    public static final Predicate<Student> IS_HIGH_PERFORMER =
            IS_PASSING.and(IS_DISTINCTION);

    // ── Functions ─────────────────────────────────────────────────────────────

    /**
     * Maps a Student to its letter grade (A+, A, B, C, D, F).
     * Function<T, R> — transforms Student → String.
     */
    public static final Function<Student, String> TO_GRADE_LETTER = student -> {
        double avg = student.average();
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "F";
    };
}
