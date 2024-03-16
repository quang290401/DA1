package com.mycompany.da1.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class XFile {
    public static void save(File src) {
        File dst = new File("Images", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ImageIcon read(String fileName) {
        File path = new File("Images", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    public static String getPath(String fileName) {
        File path = new File("Images", fileName);
        return path.getAbsolutePath();
    }
}
