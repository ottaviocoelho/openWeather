package utils;

public class IconTurner {

    public static String turnToDay(String icon) {
        char[] iconChars = icon.toCharArray();
        iconChars[2] = 'd';
        return new String(iconChars);
    }

    public static String turnToNight(String icon) {
        char[] iconChars = icon.toCharArray();
        iconChars[2] = 'n';
        return new String(iconChars);
    }

}
