sealed abstract class GradeCategory permits GradeCategory.Outstanding,
                                             GradeCategory.Excellent,
                                             GradeCategory.Good,
                                             GradeCategory.Average,
                                             GradeCategory.Poor {
 
    abstract String label();
    abstract String emoji();
 
    static final class Outstanding extends GradeCategory {
        @Override public String label() { return "Outstanding"; }
        @Override public String emoji() { return "🏆"; }
    }
    static final class Excellent extends GradeCategory {
        @Override public String label() { return "Excellent"; }
        @Override public String emoji() { return "⭐"; }
    }
    static final class Good extends GradeCategory {
        @Override public String label() { return "Good"; }
        @Override public String emoji() { return "👍"; }
    }
    static final class Average extends GradeCategory {
        @Override public String label() { return "Average"; }
        @Override public String emoji() { return "📘"; }
    }
    static final class Poor extends GradeCategory {
        @Override public String label() { return "Poor"; }
        @Override public String emoji() { return "⚠️"; }
    }
}