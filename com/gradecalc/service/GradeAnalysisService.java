package com.gradecalc.service;

import com.gradecalc.model.Student;

import java.util.*;
import java.util.stream.Collectors;

// ─────────────────────────────────────────────────────────────────────────────
// Java 8 — STREAM API (filter, map, collect, reduce)
// Responsibility : All analytical / aggregation operations on student lists.
//                 Pure business logic — no I/O, no data sourcing.
// OOP Principle  : Single Responsibility, Separation of Concerns,
//                  High Cohesion (all stream ops are co-located here)
// ─────────────────────────────────────────────────────────────────────────────
public final class GradeAnalysisService {

    private GradeAnalysisService() {
        throw new UnsupportedOperationException("Utility class");
    }

    // ── filter + map + collect ────────────────────────────────────────────────

    /**
     * Returns sorted names of students who are passing.
     * Demonstrates: filter (Predicate), map (method reference), collect.
     */
    public static List<String> getPassingStudentNames(List<Student> students) {
        return students.stream()
                       .filter(GradePredicates.IS_PASSING)      // Predicate — filter
                       .map(Student::name)                       // method reference — map
                       .sorted()
                       .collect(Collectors.toList());            // collect
    }

    /**
     * Returns formatted names of distinction students.
     * Demonstrates: filter + map + Collectors.joining.
     */
    public static String getDistinctionStudents(List<Student> students) {
        return students.stream()
                       .filter(GradePredicates.IS_DISTINCTION)
                       .map(s -> s.name() + " (" + String.format("%.1f", s.average()) + ")")
                       .collect(Collectors.joining(", "));
    }

    // ── reduce ────────────────────────────────────────────────────────────────

    /**
     * Sums all marks across every student.
     * Demonstrates: flatMap (flatten nested lists) + reduce (method reference).
     */
    public static double totalMarksSum(List<Student> students) {
        return students.stream()
                       .flatMap(s -> s.marks().stream())
                       .reduce(0.0, Double::sum);               // method reference — reduce
    }

    /**
     * Computes the class-wide grand average via reduce.
     */
    public static double grandAverage(List<Student> students) {
        return students.stream()
                       .mapToDouble(Student::average)
                       .reduce(0.0, Double::sum) / students.size();
    }

    // ── groupingBy ────────────────────────────────────────────────────────────

    /**
     * Groups students by their letter grade.
     * Demonstrates: Collectors.groupingBy + Collectors.mapping.
     */
    public static Map<String, List<String>> groupByGrade(List<Student> students) {
        return students.stream()
                       .collect(Collectors.groupingBy(
                           GradePredicates.TO_GRADE_LETTER::apply,   // method reference
                           Collectors.mapping(Student::name, Collectors.toList())
                       ));
    }

    // ── statistics ────────────────────────────────────────────────────────────

    /**
     * Returns full summary statistics (count, min, max, avg, sum).
     * Demonstrates: mapToDouble + summaryStatistics.
     */
    public static DoubleSummaryStatistics classStatistics(List<Student> students) {
        return students.stream()
                       .mapToDouble(Student::average)
                       .summaryStatistics();
    }

    // ── partitioningBy ────────────────────────────────────────────────────────

    /**
     * Partitions students into pass (true) and fail (false) buckets.
     * Demonstrates: Collectors.partitioningBy.
     */
    public static Map<Boolean, List<String>> partitionByPass(List<Student> students) {
        return students.stream()
                       .collect(Collectors.partitioningBy(
                           GradePredicates.IS_PASSING,
                           Collectors.mapping(Student::name, Collectors.toList())
                       ));
    }

    // ── at-risk / high performers ─────────────────────────────────────────────

    /** Returns names of failing / at-risk students (Predicate.negate). */
    public static List<String> getAtRiskStudents(List<Student> students) {
        return students.stream()
                       .filter(GradePredicates.IS_AT_RISK)
                       .map(Student::name)
                       .collect(Collectors.toList());
    }

    /** Returns names of high-performing students (Predicate.and). */
    public static List<String> getHighPerformers(List<Student> students) {
        return students.stream()
                       .filter(GradePredicates.IS_HIGH_PERFORMER)
                       .map(Student::name)
                       .collect(Collectors.toList());
    }

    // ── Optional ─────────────────────────────────────────────────────────────

    /** Safely finds the top student by average using Optional. */
    public static Optional<Student> findTopStudent(List<Student> students) {
        return students.stream()
                       .max(Comparator.comparingDouble(Student::average));
    }

    /**
     * Returns the top student's name safely.
     * Demonstrates: Optional chaining (map + filter + orElse), Java 11 isBlank().
     */
    public static String topStudentName(List<Student> students) {
        return findTopStudent(students)
                .map(Student::name)
                .filter(name -> !name.isBlank())   // Java 11 — String::isBlank
                .orElse("No students found");
    }
}
