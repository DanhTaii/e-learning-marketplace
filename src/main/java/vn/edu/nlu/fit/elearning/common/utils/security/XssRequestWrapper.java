package vn.edu.nlu.fit.elearning.common.utils.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class XssRequestWrapper extends HttpServletRequestWrapper {
    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private String cleanXss(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        // Safelist.none() sẽ xóa mọi thẻ HTML (<script>, <b>, <i>...)
        // Trả về văn bản thuần túy (Plain Text).
        return Jsoup.clean(value, Safelist.none());
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return cleanXss(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return cleanXss(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);

        if (values == null) {
            return null;
        }

        String[] cleanValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            cleanValues[i] = cleanXss(values[i]);
        }
        return cleanValues;
    }
}
