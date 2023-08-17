package in.fssa.carecentral.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {
	public static Connection getConnection() {
		String url;
        String userName;
        String passWord;
        Connection con;

        if (System.getenv("CI") != null) {
            url = System.getenv("DATABASE_HOSTNAME");
            userName = System.getenv("DATABASE_USERNAME");
            passWord = System.getenv("DATABASE_PASSWORD");
        } else {
            Dotenv env = Dotenv.load();
            url = env.get("DATABASE_HOSTNAME");
            userName = env.get("DATABASE_USERNAME");
            passWord = env.get("DATABASE_PASSWORD");
        }
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,passWord);

		}
		catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return con;
	}
	public static void close(Connection connection , PreparedStatement  ps) {
		try {
			if(ps !=null) {
				ps.close();
			}
			if(connection!= null) {
				connection.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection connection , PreparedStatement  ps , ResultSet  rs) {
		try {
			if(rs !=null) {
				rs.close();
			}
			if(ps !=null) {
				ps.close();
			}
			if(connection!= null) {
				connection.close();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
