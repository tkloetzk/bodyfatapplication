import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseUtil {
	public static void close(Connection connection){
		if (connection != null) {
			try {
				connection.close();
				//System.out.println("Closed connection");				
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	public static void close (Statement statement){
		if (statement != null){
			try {
				statement.close();
				//System.out.println("Closed statement");
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet resultSet){
		if (resultSet != null){
			try {
				resultSet.close();
				//System.out.println("Closed resultSet");
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
