package src.com.gradecalc.enums;

// ─────────────────────────────────────────────────────────────────────────────
// Java 17 — SEALED ABSTRACT CLASS
// Responsibility : Classifies a numeric average into a named grade category
//                 with a label and emoji.
// OOP Principle  : Abstraction (abstract class defines the contract),
//                  Inheritance (each category extends GradeCategory),
//                  Polymorphism (label()/emoji() resolved at runtime),
//                  Encapsulation (final subclasses, no external extension)
// ─────────────────────────────────────────────────────────────────────────────
public sealed abstract class GradeCategory
        permits GradeCategory.Outstanding,
                GradeCategory.Excellent,
                GradeCategory.Good,
                GradeCategory.Average,
                GradeCategory.Poor {

    // Abstract contract — subclasses must provide these
    public abstract String label();
    public abstract String emoji();

    // ── Permitted Subclasses ──────────────────────────────────────────────────

    public static final class Outstanding extends GradeCategory {
        @Override public String label() { return "Outstanding"; }
        @Override public String emoji() { return "🏆"; }
    }

    public static final class Excellent extends GradeCategory {
        @Override public String label() { return "Excellent"; }
        @Override public String emoji() { return "⭐"; }
    }

    public static final class Good extends GradeCategory {
        @Override public String label() { return "Good"; }
        @Override public String emoji() { return "👍"; }
    }

    public static final class Average extends GradeCategory {
        @Override public String label() { return "Average"; }
        @Override public String emoji() { return "📘"; }
    }

    public static final class Poor extends GradeCategory {
        @Override public String label() { return "Poor"; }
        @Override public String emoji() { return "⚠️"; }
    }
}
