package by.semashko.greenjokerquestbot.util;

import by.semashko.greenjokerquestbot.exception.InvalidUrlException;

public class UrlParser {

    private static final String DOMAIN_ZONE = "en.cx/";
    private static final String PROTOCOL = "https://";
    private static final String PATH = "/GameDetails.aspx?gid=";

    public static String getDomain(String url) {
        if (isValidUrl(url)) {
            return url.substring(0, nthIndexOf(url) + 1);
        } else {
            throw new InvalidUrlException("Не верный URL адрес");
        }
    }

    public static int getIdGame(String url) {
        if (isValidUrl(url)) {
            return Integer.parseInt(url.substring(url.lastIndexOf("=") + 1));
        }
        return -1;
    }

    private static boolean isValidUrl(String url) {
        return url.startsWith(PROTOCOL) && url.contains(DOMAIN_ZONE) && url.contains(PATH);
    }

    private static int nthIndexOf(String string) {
        int j = 0;
        for (int i = 0; i < 3; i++) {
            j = string.indexOf("/", j + 1);
            if (j == -1) break;
        }
        return j;
    }
}
