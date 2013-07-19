package pageobjects;

class Utils {
    public static String getFilenameFromClasspath(String relativePath) {
        return Utils.class.getClassLoader().getResource(relativePath).getFile();
    }
}
