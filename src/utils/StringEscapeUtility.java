package utils;

public class StringEscapeUtility {
    public static String escape(String text) {
        text = text.replace("&", "&amp;");
        text = text.replace("<", "&lt;");
        text = text.replace(">", "&gt;");
        text = text.replace("\"", "&quot;");
        text = text.replace("'", "&#039;");
        text = text.replace("\n", "<br>");
        return text;
    }
}
