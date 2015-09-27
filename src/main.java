import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		Person human;
		char answer;
		char saved = 'y';
		CheckUser check = new CheckUser();
		PersonFactory personFactory = new PersonFactory();
		
		Scanner keyboard = new Scanner(System.in);
		Scanner keyboardSaveUser = new Scanner(System.in);
		// connection
		Connection con = ConnectionFactory.createConnection();
		if (con != null){
			System.out.print("Enter your username (or press zero to create one): ");	
			if (keyboard.hasNextInt()){
				human = personFactory.createPerson("0");
				System.out.println("Would you like to save yourself into the database?");
				saved = keyboardSaveUser.next().toLowerCase().charAt(0);
				human.setDatabaseSave(saved);
			//	System.out.println(human.getDatabaseSave());
				if (human.getDatabaseSave() == 'y'){
					check.createUserEntry(human);
				}
			} else {
				human = personFactory.getPerson(keyboard.nextLine());
			}			

			outputMuscleAndFat(human);
			
			if (human.getDatabaseSave() != 'n'){
				Scanner keyboardSaveEntry = new Scanner(System.in);
				System.out.println("Would you like to save this as an entry? [y/n]");
				answer = keyboardSaveEntry.next().toLowerCase().charAt(0);
				
				if (answer == 'y'){
					check.submitEntry(human);
				}
				
				seeProgress(human);
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Not connected.");
			System.out.println("Please enter your name");
			human = personFactory.getNewUserStats(keyboard.next());
			outputMuscleAndFat(human);
			seeProgress(human);
		}
	}

	private static void seeProgress(Person human) {
		char answer;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Would you like to see your progress? [y/n]");
		answer = keyboard.next().toLowerCase().charAt(0);
		
		if (answer == 'y'){
			human.getProgress();
		}
	}

	private static void outputMuscleAndFat(Person human) {
		System.out.println("Your muscle is "+human.getCalculatedBodyMuscle());
		System.out.println("Your body fat is " + human.getCalculatedBodyFat());
	}
}
