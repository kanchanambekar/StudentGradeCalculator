package com.gradecalc.service;

import com.gradecalc.enums.GradeCategory;
import com.gradecalc.model.GradeResult;
import com.gradecalc.model.Student;

// ─────────────────────────────────────────────────────────────────────────────
// Java 17 / 21 — PATTERN MATCHING (instanceof + switch)
// Responsibility : Classifies grades into categories and describes results
//                 using exhaustive pattern matching on sealed types.
// OOP Principle  : Single Responsibility, Polymorphism (sealed type dispatch),
//                  Open/Closed (new subtypes = compiler forces new case)
// ─────────────────────────────────────────────────────────────────────────────
public final class GradeCategoryService {

    private GradeCategoryService() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Maps a numeric average to a GradeCategory subtype.
     * Demonstrates: factory method + sealed class instantiation.
     */
    public static GradeCategory classify(double avg) {
        if (avg >= 95) return new GradeCategory.Outstanding();
        if (avg >= 85) return new GradeCategory.Excellent();
        if (avg >= 70) return new GradeCategory.Good();
        if (avg >= 50) return new GradeCategory.Average();
        return new GradeCategory.Poor();
    }

    /**
     * Describes a GradeResult using Java 21 pattern-matching switch.
     * Exhaustive — compiler enforces all permitted subtypes are handled.
     */
    public static String describeResult(GradeResult result) {
        return switch (result) {
            case GradeResult.Distinction d ->
                    "🏅 Distinction with avg " + String.format("%.1f", d.average());
            case GradeResult.Pass p ->
                    "✅ Pass with avg "        + String.format("%.1f", p.average());
            case GradeResult.Fail f ->
                    "❌ Fail with avg "        + String.format("%.1f", f.average());
        };
    }

    /**
     * Describes any Object using Java 16+ pattern matching for instanceof.
     * Includes a guarded pattern (&&) for extra condition on matched type.
     */
    public static String describeObject(Object obj) {
        if (obj instanceof Student s) {                         // pattern matching instanceof
            return "Student record: " + s.name()
                   + " | avg: " + String.format("%.1f", s.average());
        } else if (obj instanceof String str && !str.isBlank()) {  // guarded pattern
            return "Non-blank String: " + str.strip();
        } else if (obj instanceof Integer i) {
            return "Integer value: " + i;
        }
        return "Unknown object: " + obj;
    }
}
