package util;

import java.nio.file.Paths;

public class IoUtils {
    public static String getResourcePath(String filePath) {
        return Paths.get("").toAbsolutePath().toString().concat("/src/main/resource/").concat(filePath);
    }
}
