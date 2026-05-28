package com.gradecalc.app;

import com.gradecalc.model.GradeResult;
import com.gradecalc.model.Student;
import com.gradecalc.service.GradeAnalysisService;
import com.gradecalc.service.GradeCategoryService;
import com.gradecalc.service.StudentRepository;
import com.gradecalc.util.EnhancedNpeDemo;
import com.gradecalc.util.Java11StringDemo;
import com.gradecalc.util.ReportPrinter;

import java.util.List;

// ─────────────────────────────────────────────────────────────────────────────
// APPLICATION ENTRY POINT
// Responsibility : Orchestrates all sections in sequence. Contains ONLY
//                 coordination logic — no business rules, no I/O formatting.
// OOP Principle  : Single Responsibility (orchestration only),
//                  Dependency Inversion (depends on service abstractions,
//                  not implementations directly)
// ─────────────────────────────────────────────────────────────────────────────
public class StudentGradeCalculatorApp {

    public static void main(String[] args) {

        // ── Banner ────────────────────────────────────────────────────────────
        ReportPrinter.printBanner();

        // ── Load student data via Supplier<T> (Java 8) ────────────────────────
        // The SAMPLE_DATA_SUPPLIER contains a null-name student to demo NPE.
        // We catch it and fall back to the clean dataset.
        List<Student> students;
        try {
            students = StudentRepository.SAMPLE_DATA_SUPPLIER.get();
        } catch (NullPointerException e) {
            System.out.println("⚠️  Skipping malformed student: " + e.getMessage());
            students = StudentRepository.CLEAN_DATA_SUPPLIER.get();
        }

        // ── SECTION 1: Consumer<T> — individual report cards ──────────────────
        ReportPrinter.printSection(1, "Individual Student Reports (Consumer<T>)");
        students.forEach(ReportPrinter.PRINT_STUDENT_REPORT); // method reference

        // ── SECTION 2: Stream — filter + map + collect ────────────────────────
        ReportPrinter.printSection(2, "Stream: filter / map / collect");
        var passingNames      = GradeAnalysisService.getPassingStudentNames(students);
        var distinctionLine   = GradeAnalysisService.getDistinctionStudents(students);
        ReportPrinter.printStreamResults(passingNames, distinctionLine);

        // ── SECTION 3: Stream — reduce ────────────────────────────────────────
        ReportPrinter.printSection(3, "Stream: reduce");
        var totalSum  = GradeAnalysisService.totalMarksSum(students);
        var grandAvg  = GradeAnalysisService.grandAverage(students);
        ReportPrinter.printReduceResults(totalSum, grandAvg);

        // ── SECTION 4: groupingBy + statistics ────────────────────────────────
        ReportPrinter.printSection(4, "Grouped by Grade Letter + Statistics");
        var grouped = GradeAnalysisService.groupByGrade(students);
        ReportPrinter.printGroupedGrades(grouped);
        var stats = GradeAnalysisService.classStatistics(students);
        ReportPrinter.printStatistics(stats);

        // ── SECTION 5: Optional — avoid NPE ──────────────────────────────────
        ReportPrinter.printSection(5, "Optional (avoid NPE)");
        System.out.println("🥇 Top student: " + GradeAnalysisService.topStudentName(students));
        GradeAnalysisService.findTopStudent(students).ifPresentOrElse(
            s  -> System.out.println("   Detail: " + GradeCategoryService.describeObject(s)),
            () -> System.out.println("   No students in list")
        );

        // ── SECTION 6: Sealed types + Pattern Matching switch (Java 21) ───────
        ReportPrinter.printSection(6, "Sealed Types + Pattern Matching Switch");
        students.stream()
                .map(s -> GradeResult.evaluate(s.average()))    // static interface method
                .map(GradeCategoryService::describeResult)       // method reference
                .forEach(System.out::println);

        // ── SECTION 7: Java 11 String methods ────────────────────────────────
        Java11StringDemo.run();

        // ── SECTION 8: Enhanced NullPointerException (Java 21) ───────────────
        EnhancedNpeDemo.run();

        // ── SECTION 9: Predicate composition (and / negate / or) ─────────────
        ReportPrinter.printSection(9, "var + Predicate Composition (and / negate)");
        var atRisk       = GradeAnalysisService.getAtRiskStudents(students);
        var highPerformers = GradeAnalysisService.getHighPerformers(students);
        ReportPrinter.printPredicateResults(atRisk, highPerformers);

        // ── SECTION 10: Collectors.partitioningBy ─────────────────────────────
        ReportPrinter.printSection(10, "Collectors.partitioningBy");
        var partitioned = GradeAnalysisService.partitionByPass(students);
        ReportPrinter.printPartition(partitioned);

        // ── Feature Summary ───────────────────────────────────────────────────
        ReportPrinter.printFeatureSummary();
    }
}
