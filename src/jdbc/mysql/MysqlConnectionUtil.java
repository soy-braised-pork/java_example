package jdbc.mysql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnectionUtil {

    private static MysqlConnectionUtil connectionUtil = new MysqlConnectionUtil();

    public static Connection connection;

    private MysqlConnectionUtil() {

    }

    public static MysqlConnectionUtil getInstance() {
        if (connectionUtil == null) {
            synchronized (MysqlConnectionUtil.class) {
                if (connectionUtil == null) {
                    connectionUtil = new MysqlConnectionUtil();
                }
            }
        }
        return connectionUtil;
    }


    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        //从配置文件里把相应的配置读出来
        File config =new File("src/mysql.properties");
        FileInputStream fis=new FileInputStream(config);
        Properties properties=new Properties();
        properties.load(fis);
        String jdbcDriver;
        //主机
        String jdbcUrl;
        //数据库用户名
        String userName;
        String passWord;
        jdbcDriver=properties.getProperty("DRIVER");
        jdbcUrl=properties.getProperty("URL");
        userName= properties.getProperty("USERNAME");
        passWord=properties.getProperty("PASSWORD");
        Class.forName(jdbcDriver);
        connection= DriverManager.getConnection(jdbcUrl,userName,passWord);
        return connection;
    }
}
