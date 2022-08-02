package storage;

import java.io.File;

public class StorageConstants {
//    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/jd5m7";
//    public static final String DB_USERNAME = "root";
//    public static final String DB_PASSWORD = "12345";
    public static final String CONNECTION_URL;

    static {
        String homeDirectory = System.getenv().get("HOME");

        String dbFolderPath = homeDirectory + "/.shortlink";
        new File(dbFolderPath).mkdirs();

        CONNECTION_URL = "jdbc:h2:" + dbFolderPath + "/db";
    }
}