package util;

/**
 *
 * @author PC
 */
public class StringUtil {
    public static int parseInt(String number, int defaultValue) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    public static double parseDouble(String number, double defaultValue) {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
