package src.com.gradecalc.utils;

// ─────────────────────────────────────────────────────────────────────────────
// Java 11 — NEW STRING METHODS
// Responsibility : Isolated demonstration of Java 11 String API additions.
// OOP Principle  : Single Responsibility (demo only, no business logic),
//                  Encapsulation (utility class, no public state)
// ─────────────────────────────────────────────────────────────────────────────
public final class Java11StringDemo {

    private Java11StringDemo() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void run() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("  Java 11 — New String Methods Demo");
        System.out.println("═══════════════════════════════════════");

        var raw = "  Alice   ";               // Java 11 — var (local type inference)

        // strip() — Unicode-aware whitespace removal (superior to trim())
        System.out.println("strip()         : '" + raw.strip()         + "'");
        System.out.println("stripLeading()  : '" + raw.stripLeading()  + "'");
        System.out.println("stripTrailing() : '" + raw.stripTrailing() + "'");

        // isBlank() — true if empty or contains only whitespace
        System.out.println("isBlank()       : " + "   ".isBlank());

        // lines() — returns a Stream<String> split by line terminators
        var multiline = "Math: 90\nScience: 85\nEnglish: 78";
        System.out.println("lines() stream  :");
        multiline.lines()
                 .map(line -> "  » " + line)
                 .forEach(System.out::println);   // method reference

        // repeat(n) — returns string repeated n times
        System.out.println("repeat('─', 20): " + "─".repeat(20));
    }
}
