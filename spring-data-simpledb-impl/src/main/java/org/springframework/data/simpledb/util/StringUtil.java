package org.springframework.data.simpledb.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {

    private StringUtil() {
        //utility class
    }

    public static String[] splitCamelCaseString(String str) {
        return str.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
    }

    public static String combineLowerCase(String[] values, String separator) {
        StringBuilder buffer = new StringBuilder("");
        for (String value : values) {
            buffer.append(value.toLowerCase());
            buffer.append(separator);
        }

        String str = buffer.toString();

        //remove last separator
        return str.substring(0, str.length() - 1);
    }

    public static String toLowerFirstChar(String source) {
        if (source == null) {
            return null;
        }

        if (source.length() == 1) {
            return source.toLowerCase();
        } else {
            String rest = source.substring(1);
            String start = String.valueOf(source.charAt(0));
            return start.toLowerCase() + rest;
        }
    }

    public static List<String> splitStringByDelim(String actual, String deliminator) {
        return Arrays.asList(actual.split(deliminator));
    }

    public static String[] toStringArray(Object[] values) {
        String[] queryParams = new String[values.length];
        for (int i = 0; i < queryParams.length; i++) {
            if (values[i] instanceof String) {
                queryParams[i] = (String) values[i];
            }
        }
        return queryParams;
    }

    public static List<String> getAttributesInQuerry(String query){
        final Pattern betweenSelectAndFrom = Pattern.compile("select(?:\\s+)(.+?)(?:\\s+from)", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = betweenSelectAndFrom.matcher(query);
        if (matcher.find()) {
            String attribute = matcher.group(1);
            //attribute.replaceAll(" ", "");
            return attribute.contains(",") ? Arrays.asList(attribute.split(",")) : Arrays.asList(attribute);
        }
        return new ArrayList<String>();
    }
}
