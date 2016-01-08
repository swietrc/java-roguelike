package Utils;

public class Toolbox {
    public static boolean isInteger(String s) {
        boolean res = false;
        try {
            Integer.parseInt(s);
            res = true;
        } catch(NumberFormatException e) {
        }
        return res;
    }
}
