package notepad;


public class TextStats {

    
    public static String caretInfo(String text, int caretPos) {
        int line = 1, col = 1;
        int limit = Math.min(caretPos, text.length());
        for (int i = 0; i < limit; i++) {
            if (text.charAt(i) == '\n') { line++; col = 1; }
            else                        { col++;            }
        }
        return "Ln " + line + ", Col " + col;
    }

  
    public static String charCount(String text) {
        int n = text.length();
        return n + " char" + (n != 1 ? "s" : "");
    }


    public static String wordCount(String text) {
        if (text == null || text.isBlank()) return "0 words";
        long words = java.util.Arrays.stream(text.trim().split("\\s+")).filter(w -> !w.isEmpty()).count();
        return words + " word" + (words != 1 ? "s" : "");
    }
}
