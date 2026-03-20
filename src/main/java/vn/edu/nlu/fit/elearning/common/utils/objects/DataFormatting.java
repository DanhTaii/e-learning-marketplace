package vn.edu.nlu.fit.elearning.common.utils.objects;

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
}
