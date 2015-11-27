import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckUser {
	private Connection connection;
	private Statement statement;
	ResultSet rs = null;
	private String query;
	PreparedStatement stmt = null;
	private String[] columnHeaders = {"username", "password", "is_Male", "goalWeight", "goalBodyFat"};
	
	public CheckUser(){}
	
	private void getConnected() throws SQLException {
		connection = ConnectionFactory.getConnection();
		statement = connection.createStatement();
	}
	private void closeConnection(ResultSet rs) {
		DatabaseUtil.close(rs);
		DatabaseUtil.close(statement);
		DatabaseUtil.close(connection);
	}
	
	public void submitEntry(Person user){
		System.out.print("... ");
		try {
			getConnected();
			query = "INSERT INTO entries ("
					+ "bodyFatPercentageEntry, bodyWeightEntry, "
					+ "fat, muscle, timestamp, username, period"
					+ ") VALUES (?, ?, ?, ?, NOW(), ?, ?);";	
			
			stmt = connection.prepareStatement(query);
			stmt.setDouble(1, user.getCurrentBodyFat());
			stmt.setDouble(2, user.getCurrentBodyWeight());
			stmt.setDouble(3, user.getCalculatedBodyFat());
			stmt.setDouble(4, user.getCalculatedBodyMuscle());
			stmt.setString(5, user.getUsername());
			stmt.setString(6, String.valueOf(user.getPeriod()));
			stmt.executeUpdate();
			System.out.print("Entry successfully submitted.\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error submitting entry.");
			e.printStackTrace();
		}
		finally {
			closeConnection(rs);
		}
	}
	
	public Object[] username(String name) {
		query = "SELECT * FROM users WHERE username = '" + name + "';";
		Object[] resultsArray = new Object[5];

		try {
			getConnected();
			rs = statement.executeQuery(query);
			
			while (rs.next()){
				for (int i = 0; i < columnHeaders.length; i++){
					resultsArray[i] = rs.getString(columnHeaders[i]);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			closeConnection(rs);
		}

		return resultsArray;
	}
	
	public void getGoals(String username) {
		query = "SELECT goalWeight, goalBodyFat FROM users WHERE username = '" + username + "';";
		try {
			getConnected();
			rs = statement.executeQuery(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(rs);
		}
		
	}
	public void createUserEntry(Person user){
		try {
			getConnected();
			//System.out.println("goal weight " + user.getGoalWeight());
			query = "INSERT INTO users ("
					+ "goalBodyFat, goalWeight, "
					+ "is_Male, username"
					+ ") VALUES (?, ?, ?, ?);";	
			
			stmt = connection.prepareStatement(query);
			stmt.setDouble(1, user.getGoalBodyFat());
			stmt.setDouble(2, user.getGoalWeight());
			stmt.setDouble(3, user.getSex());
			stmt.setString(4, user.getUsername());
			stmt.executeUpdate();
			System.out.println("User sucessfully created.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			closeConnection(rs);
		}
	}
	public void getHistory() {
		
	}
}
