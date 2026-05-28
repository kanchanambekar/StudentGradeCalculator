package src.com.gradecalc.utils;

import com.gradecalc.model.Student;

import java.util.List;
import java.util.Optional;

// ─────────────────────────────────────────────────────────────────────────────
// Java 21 — ENHANCED NullPointerException (JEP 358, enabled by default)
// Responsibility : Demonstrates how Java 21 NPEs carry precise messages
//                 naming exactly which variable was null, and how Optional
//                 prevents NPEs entirely through safe chaining.
// OOP Principle  : Single Responsibility (NPE demo only),
//                  Encapsulation (no public mutable state)
// ─────────────────────────────────────────────────────────────────────────────
public final class EnhancedNpeDemo {

    private EnhancedNpeDemo() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void run() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("  Java 21 — Enhanced NullPointerException");
        System.out.println("═══════════════════════════════════════");

        // ── Demo 1: Enhanced NPE message ─────────────────────────────────────
        // The Student compact constructor calls Objects.requireNonNull(name, …).
        // Java 21 JVM pinpoints exactly which field was null in the message.
        try {
            new Student(null, 999, List.of(80.0));
        } catch (NullPointerException e) {
            System.out.println("✔ NPE caught (enhanced message): " + e.getMessage());
        }

        // ── Demo 2: Optional prevents NPE entirely ────────────────────────────
        // Optional.empty() + chained map/orElse = zero NullPointerException risk.
        Optional<Student> maybeStudent = Optional.empty();
        String name = maybeStudent
                .map(Student::name)                             // safe — skipped if empty
                .orElse("⚠️ No student present — Optional prevented NPE");
        System.out.println(name);

        // ── Demo 3: Optional.ifPresentOrElse (Java 9+) ───────────────────────
        maybeStudent.ifPresentOrElse(
            s  -> System.out.println("Found: " + s.name()),
            () -> System.out.println("ℹ️  ifPresentOrElse: no student in Optional")
        );
    }
}
