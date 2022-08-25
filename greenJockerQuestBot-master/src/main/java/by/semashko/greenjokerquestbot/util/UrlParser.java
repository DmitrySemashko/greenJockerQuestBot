package by.semashko.greenjokerquestbot.util;

public class UrlParser {

    private static final String DOMAIN_ZONE = "en.cx/";
    private static final String PROTOCOL = "https://";
    private static final String PATH = "/GameDetails.aspx?gid=";

    public static String getDomain(String url) throws Exception {
        if (isValidUrl(url)){
            return url.substring(0,nthIndexOf(url,"/",3));
        }else {
            throw new Exception();
        }
    }

    private static boolean isValidUrl(String url){
        return url.startsWith(PROTOCOL)&&url.contains(DOMAIN_ZONE)&&url.contains(PATH);
    }

    private static int nthIndexOf(String string, String token, int index) {
        int j = 0;
        for (int i = 0; i < index; i++) {
            j = string.indexOf(token, j + 1);
            if (j == -1) break;
        }
        return j;
    }
}
