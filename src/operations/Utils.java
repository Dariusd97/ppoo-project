package operations;


public class Utils {
    public static int toInt(String str) {
        return toInt(str, -1);
    }

    public static int toInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException ex) {
                return defaultValue;
            }
        }
    }

    public static double toDouble(String str) {
        return toDouble(str, -1.0);
    }

    public static double toDouble(String str, double defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException ex) {
                return defaultValue;
            }
        }
    }
}
