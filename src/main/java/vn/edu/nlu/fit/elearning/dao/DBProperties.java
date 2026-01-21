package vn.edu.nlu.fit.elearning.dao;

import java.io.IOException;
import java.util.Properties;

public class DBProperties {
    private static Properties prop = new Properties();

    static {
        try (var check = DBProperties.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (check != null) {
                prop.load(check);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hàm bổ trợ để ưu tiên lấy biến môi trường
    private static String getVal(String key, String envKey) {
        String envVal = System.getenv(envKey);
        if (envVal != null && !envVal.isEmpty()) {
            return envVal;
        }
        return prop.getProperty(key);
    }

//    public static String getDbHost() {
//        return prop.get("db.host").toString();
//    }
//
//    public static String getDbPort() {
//        return prop.get("db.port").toString();
//    }
//
//    public static String getUsername() {
//        return prop.get("db.username").toString();
//    }
//
//    public static String getPassword() {
//        return prop.get("db.password").toString();
//    }
//
//    public static String getDboptions() {
//        return prop.get("db.options").toString();
//    }
//
//    public static String getDbName() {
//        return prop.get("db.dbName").toString();
//    }

    public static String getDbHost() { return getVal("db.host", "DB_HOST"); }
    public static String getDbPort() { return getVal("db.port", "DB_PORT"); }
    public static String getUsername() { return getVal("db.username", "DB_USER"); }
    public static String getPassword() { return getVal("db.password", "DB_PASS"); }
    public static String getDbName() { return getVal("db.dbName", "DB_NAME"); }
    public static String getDboptions() { return getVal("db.options", "DB_OPTIONS"); }

}
