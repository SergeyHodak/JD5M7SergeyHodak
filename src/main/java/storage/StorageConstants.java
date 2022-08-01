package storage;

import java.io.File;

public class StorageConstants {
    public static final String CONNECTION_URL; // = "jdbc:h2:/spacetrain";
    static {
        String homeDirectory = System.getenv().get("HOME"); // отримання шляху до домашньої директорії (аналог в виндовс USERPROFILE)
        // (якщо ця зміння зараєстрована в переменних среди ОС під цим ім'ям)
        // mkdirs() - створює ієрархію папокякщо їх немає
        String dbFolderPath = homeDirectory + "/.shortlink";
        new File(dbFolderPath).mkdirs();
        CONNECTION_URL = "jdbc:h2:" + dbFolderPath + "/db";
    }
}