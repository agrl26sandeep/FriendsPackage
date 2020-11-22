package com.solution.utility;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 *  Class maintains the file related operations
 * @author Sandeep Agrawal
 */
public class FileUtility {

    /**
     * Get the absolute path of the file
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String getAbsolutePath(String filePath) throws IOException {
        return getFile(filePath).getAbsolutePath();
    }

    public static File getFile(String filePath) throws IOException {
        File file = new File(filePath).getCanonicalFile();
        return file;
    }

    /**
     *  Get the extension of given file.
     * @param filename
     * @return
     */
    public static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
