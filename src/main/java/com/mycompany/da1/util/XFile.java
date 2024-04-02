package com.mycompany.da1.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;

public class XFile extends FileFilter {

    private final String extension;
    private final String description;
    
    public XFile() {
        this.extension = ".txt";
        this.description = "";
    }

    public XFile(String extension, String description) {
        this.extension = extension;
        this.description = description;
    }

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

    public List<String> getListFile() {
        File folder = new File("Images");
        List<String> lstFileName = new ArrayList<>();
        // Kiểm tra xem có phải là thư mục không
        if (folder.isDirectory()) {
            // Lấy danh sách tệp trong thư mục
            File[] files = folder.listFiles();

            // Lặp qua mỗi tệp và in ra tên của từng tệp
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && (file.getName().endsWith(".png")) || file.getName().endsWith(".jpg")) {
                        lstFileName.add(file.getName());
                    }
                }
            } else {
                System.out.println("Thư mục rỗng");
            }
        } else {
            System.out.println("Đường dẫn không phải là một thư mục");
        }
        return lstFileName;
    }
    
    
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        return f.getName().endsWith(extension);
    }

    @Override
    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }
}
