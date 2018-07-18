package util;

import java.nio.file.Paths;

public class IoUtils {

    public static String getResourcePath(String fileName) {
        String resAbsolutePath = Paths.get("").toAbsolutePath().toString();
        String filePath = resAbsolutePath.concat("/src/main/resources/").concat(fileName);
        return filePath;
    }
}
