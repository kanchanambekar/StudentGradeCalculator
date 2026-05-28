package src.com.gradecalc.utils;

import src.com.gradecalc.model.GradeResult;
import src.com.gradecalc.model.Student;
import src.com.gradecalc.service.GradeCategoryService;
import src.com.gradecalc.service.GradePredicates;

import java.util.List;
import java.util.Map;
import java.util.DoubleSummaryStatistics;
import java.util.function.Consumer;

// ─────────────────────────────────────────────────────────────────────────────
// Java 8 — CONSUMER<T>
// Responsibility : All console output / report rendering. Zero business logic.
// OOP Principle  : Single Responsibility (presentation only),
//                  Separation of Concerns (no computation here)
// ─────────────────────────────────────────────────────────────────────────────
public final class ReportPrinter {

    private ReportPrinter() {
        throw new UnsupportedOperationException("Utility class");
    }

    // ── Consumer<Student> — prints one student's full report card ─────────────

    /**
     * Consumer<T> — accepts a Student, produces formatted output, returns void.
     * Uses: var (Java 11), Text Block (Java 15), GradeResult (Java 17 sealed).
     */
    public static final Consumer<Student> PRINT_STUDENT_REPORT = student -> {
        var avg      = student.average();                         // Java 11 — var
        var grade    = GradePredicates.TO_GRADE_LETTER.apply(student);
        var result   = GradeResult.evaluate(avg);
        var category = GradeCategoryService.classify(avg);

        // Java 15 — TEXT BLOCK (multi-line string literal)
        String report = """
                ┌──────────────────────────────────────────┐
                │  Student : %-30s│
                │  ID      : %-30d│
                │  Average : %-30.2f│
                │  Grade   : %-30s│
                │  Status  : %-30s│
                │  Category: %s %-27s│
                └──────────────────────────────────────────┘
                """.formatted(
                        student.name(), student.id(),
                        avg, grade,
                        result.getClass().getSimpleName(),
                        category.emoji(), category.label()
                );
        System.out.println(report);
    };

    // ── Section headers ───────────────────────────────────────────────────────

    public static void printBanner() {
        System.out.println("""
                ╔══════════════════════════════════════════════════╗
                ║        STUDENT GRADE CALCULATOR                  ║
                ║   Java 8 • 11 • 17 • 21 Features Showcase        ║
                ╚══════════════════════════════════════════════════╝
                """);
    }

    public static void printSection(int number, String title) {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("  SECTION " + number + " — " + title);
        System.out.println("═══════════════════════════════════════");
    }

    // ── Specific section printers ─────────────────────────────────────────────

    public static void printStreamResults(List<String> passingNames,
                                          String distinctionLine) {
        System.out.println("✅ Passing students  : " + passingNames);
        System.out.println("🏅 Distinctions      : " + distinctionLine);
    }

    public static void printReduceResults(double totalSum, double grandAvg) {
        System.out.printf("Σ Total marks across all students: %.1f%n", totalSum);
        System.out.printf("  Class grand average            : %.2f%n", grandAvg);
    }

    public static void printGroupedGrades(Map<String, List<String>> grouped) {
        grouped.entrySet().stream()
               .sorted(Map.Entry.comparingByKey())
               .forEach(e -> System.out.println("  Grade " + e.getKey() + " → " + e.getValue()));
    }

    public static void printStatistics(DoubleSummaryStatistics stats) {
        System.out.printf("%nClass Statistics:%n");
        System.out.printf("  Count : %d%n",   stats.getCount());
        System.out.printf("  Min   : %.1f%n", stats.getMin());
        System.out.printf("  Max   : %.1f%n", stats.getMax());
        System.out.printf("  Avg   : %.2f%n", stats.getAverage());
        System.out.printf("  Sum   : %.1f%n", stats.getSum());
    }

    public static void printPartition(Map<Boolean, List<String>> partitioned) {
        System.out.println("Pass  : " + partitioned.get(true));
        System.out.println("Fail  : " + partitioned.get(false));
    }

    public static void printPredicateResults(List<String> atRisk,
                                             List<String> highPerformers) {
        System.out.println("⚠️  At-risk students      : " + atRisk);
        System.out.println("🌟 High performers        : " + highPerformers);
    }

    public static void printFeatureSummary() {
        System.out.println("""
                \n╔══════════════════════════════════════════════════╗
                ║              FEATURES DEMONSTRATED               ║
                ╠══════════════════════════════════════════════════╣
                ║  Java 8  │ Lambda, Stream, Optional,             ║
                ║          │ Functional Interfaces, Method Refs,   ║
                ║          │ Default & Static Interface Methods     ║
                ╠══════════════════════════════════════════════════╣
                ║  Java 11 │ var, String::strip/isBlank/lines/     ║
                ║          │ repeat, Optional::ifPresentOrElse     ║
                ╠══════════════════════════════════════════════════╣
                ║  Java 17 │ Sealed Classes & Interfaces,          ║
                ║          │ Pattern Matching for instanceof        ║
                ╠══════════════════════════════════════════════════╣
                ║  Java 21 │ Records, Text Blocks,                 ║
                ║          │ Pattern Matching Switch,              ║
                ║          │ Enhanced NullPointerException         ║
                ╚══════════════════════════════════════════════════╝
                """);
    }
}
