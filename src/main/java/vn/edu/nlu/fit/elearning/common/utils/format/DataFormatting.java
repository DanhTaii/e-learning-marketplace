package vn.edu.nlu.fit.elearning.common.utils.format;

import java.text.NumberFormat;
import java.util.Locale;

public class DataFormatting {
    public static String formatAndConvert(double priceInVND) {
        Locale df = Locale.getDefault(Locale.Category.FORMAT);
        NumberFormat nf = NumberFormat.getCurrencyInstance(df);
        double finalPrice = priceInVND;
        String country = df.getCountry();
        if ("US".equals(country)) {
            finalPrice = priceInVND / 25000.0;
        }
        return nf.format(finalPrice);
    }

    public static String formatDuration(double durationHours) {
        int hours = (int) durationHours;
        int minutes = (int) ((durationHours - hours) * 60);
        if (hours == 0) {
            return minutes + "p";
        } else if (minutes == 0) {
            return hours + "h ";
        } else {
            return hours + "h " + minutes + "p";
        }
    }
}
