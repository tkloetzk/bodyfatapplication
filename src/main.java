import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.SQLException;
import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		Person human;
		PersonFactory personFactory = new PersonFactory();
		
		Scanner keyboard = new Scanner(System.in);
		// connection
		Connection con = ConnectionFactory.createConnection();
		if (con != null){
			personFactory.begin();

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Not connected.");
			System.out.println("Please enter your name");
			human = personFactory.getNewUserStats(keyboard.next(), true);
			PersonFactory.outputMuscleAndFat(human);
			PersonFactory.seeProgress(human);
		}
	}
}
