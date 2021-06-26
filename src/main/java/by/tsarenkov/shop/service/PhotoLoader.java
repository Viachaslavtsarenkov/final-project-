package by.tsarenkov.shop.service;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Random;

public class PhotoLoader {

    private static final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String IMAGE_FOLDER_PATH = "../../src/main/webapp/";

    public PhotoLoader() {
    }

    public static String loadPhoto(InputStream in, String path) {
        String fileName = null;
        try {
            byte[] buffer = new byte[in.available()];

            in.read(buffer);
            fileName = "img/" + generateFileName() + ".png";
            File targetFile = new File(path + IMAGE_FOLDER_PATH + fileName);
            if(!targetFile.exists()){
                targetFile.createNewFile();
            }

            System.out.println(targetFile.getAbsolutePath());
            OutputStream outStream = new FileOutputStream(targetFile);

            outStream.write(buffer);
            in.close();
            outStream.close();
        } catch (IOException e) {

            //todo log
        }
        return fileName;
    }

    private static String generateFileName() {

        Random rnd = new Random();
        final StringBuilder code = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            code.append(SYMBOLS.charAt(rnd.nextInt(SYMBOLS.length())));
        }
        return code.toString();
    }
}
