package src.com.gradecalc.service;

import com.gradecalc.model.Student;

import java.util.List;
import java.util.function.Supplier;

// ─────────────────────────────────────────────────────────────────────────────
// Java 8 — SUPPLIER<T>
// Responsibility : Provides sample student data. Acts as the data-access /
//                 repository layer. Isolates data sourcing from business logic.
// OOP Principle  : Single Responsibility (data provisioning only),
//                  Dependency Inversion (consumers depend on the Supplier
//                  abstraction, not a concrete data source)
// ─────────────────────────────────────────────────────────────────────────────
public final class StudentRepository {

    private StudentRepository() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Supplier<List<Student>> — lazily provides sample data.
     * The last entry (null name) intentionally triggers an NPE in the
     * Student compact constructor to demonstrate Enhanced NPE (Java 21).
     */
    public static final Supplier<List<Student>> SAMPLE_DATA_SUPPLIER = () -> List.of(
        new Student("Alice",   101, List.of(92.0, 88.0, 95.0, 91.0, 89.0)),
        new Student("Bob",     102, List.of(45.0, 55.0, 48.0, 52.0, 43.0)),
        new Student("Charlie", 103, List.of(78.0, 82.0, 75.0, 80.0, 77.0)),
        new Student("Diana",   104, List.of(98.0, 96.0, 99.0, 97.0, 95.0)),
        new Student("Eve",     105, List.of(30.0, 35.0, 28.0, 40.0, 38.0)),
        new Student("Frank",   106, List.of(65.0, 70.0, 68.0, 72.0, 66.0)),
        new Student("Grace",   107, List.of(85.0, 88.0, 84.0, 87.0, 90.0)),
        new Student(null,      108, List.of(70.0))   // ← intentional NPE demo
    );

    /**
     * Clean dataset without the null entry — used after NPE is demonstrated.
     */
    public static final Supplier<List<Student>> CLEAN_DATA_SUPPLIER = () -> List.of(
        new Student("Alice",   101, List.of(92.0, 88.0, 95.0, 91.0, 89.0)),
        new Student("Bob",     102, List.of(45.0, 55.0, 48.0, 52.0, 43.0)),
        new Student("Charlie", 103, List.of(78.0, 82.0, 75.0, 80.0, 77.0)),
        new Student("Diana",   104, List.of(98.0, 96.0, 99.0, 97.0, 95.0)),
        new Student("Eve",     105, List.of(30.0, 35.0, 28.0, 40.0, 38.0)),
        new Student("Frank",   106, List.of(65.0, 70.0, 68.0, 72.0, 66.0)),
        new Student("Grace",   107, List.of(85.0, 88.0, 84.0, 87.0, 90.0))
    );
}
