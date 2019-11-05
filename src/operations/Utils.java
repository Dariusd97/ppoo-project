package operations;


import exceptions.NegativeNumberException;

public class Utils {

    private Utils() {
    }

    public static int toInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                int number = Integer.parseInt(str);
                if(number < 0){
                    throw new NegativeNumberException();
                }
                return number;
            } catch (NumberFormatException | NegativeNumberException ex) {
                return defaultValue;
            }
        }
    }

    public static double toDouble(String str, double defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                double number = Double.parseDouble(str);
                if(number < 0){
                    throw new NegativeNumberException();
                }
                return number;
            } catch (NumberFormatException | NegativeNumberException ex) {
                return defaultValue;
            }
        }
    }
}
