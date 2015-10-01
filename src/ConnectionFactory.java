import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	/*private static final String DB_URL = "jdbc:mysql://localhost/bodyfat";
	private static final String USER = "root";
	private static final String PASS = "";*/
	//private static final String DB_URL = "jdbc:mysql://192.168.3.100:3306/bodyfat"; //raspberry - ethernet
	private static final String DB_URL = "jdbc:mysql://192.168.3.115:3306/bodyfat"; //raspberry - wireless
	private static final String USER = "tucker";
	private static final String PASS = "tanner22";
	
	private ConnectionFactory() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static Connection createConnection(){
		Connection connection = null;
		try {
		//	System.out.println("Connecting to Body Fat Database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			
			//System.out.println("Connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: Unable to connect");
			//e.printStackTrace();
		}
		return connection;
		
	}
	public static Connection getConnection() {
		return ConnectionFactory.createConnection();
	}
}
