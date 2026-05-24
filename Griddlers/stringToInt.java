
public class stringToInt {
    public static int convert(String st) {
        int sum = 0;
        int i = 0;
        while (i < st.length()) {
            char c = st.charAt(i);
            int b = st.length() - i - 1;
            if (c == '1') {
                sum = (int)((double)sum + 1.0 * Math.pow(10.0, b));
            } else if (c == '2') {
                sum = (int)((double)sum + 2.0 * Math.pow(10.0, b));
            } else if (c == '3') {
                sum = (int)((double)sum + 3.0 * Math.pow(10.0, b));
            } else if (c == '4') {
                sum = (int)((double)sum + 4.0 * Math.pow(10.0, b));
            } else if (c == '5') {
                sum = (int)((double)sum + 5.0 * Math.pow(10.0, b));
            } else if (c == '6') {
                sum = (int)((double)sum + 6.0 * Math.pow(10.0, b));
            } else if (c == '7') {
                sum = (int)((double)sum + 7.0 * Math.pow(10.0, b));
            } else if (c == '8') {
                sum = (int)((double)sum + 8.0 * Math.pow(10.0, b));
            } else if (c == '9') {
                sum = (int)((double)sum + 9.0 * Math.pow(10.0, b));
            }
            ++i;
        }
        return sum;
    }
}

