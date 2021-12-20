package by.tsarenkov.shop.util;

import by.tsarenkov.shop.service.ServiceException;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Properties;
import java.util.Random;

public class PhotoLoader {

    private static final String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public PhotoLoader() {
    }

    public static String loadPhoto(InputStream in, String path, String name)
            throws ServiceException {
        String fileName = null;
        try {
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            fileName = generateFileName();
            File targetFile = new File(path + fileName);
            if(!targetFile.exists()){
                targetFile.createNewFile();
            }

            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            in.close();
            outStream.close();
        } catch (IOException e) {
            throw new ServiceException(e);
        }
        return fileName;
    }
    private static String generateFileName() {

        Random rnd = new Random();
        final StringBuilder code = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            code.append(SYMBOLS.charAt(rnd.nextInt(SYMBOLS.length())));
        }
        return code + ".png";
    }
}
