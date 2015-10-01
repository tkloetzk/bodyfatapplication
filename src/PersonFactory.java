import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonFactory {
	private Person human;
	Connection con = null;
	PreparedStatement statement = null;
	private char answer;
	private char saved = 'y';
	private String sex, goalWeight, goalBodyFat;
	private double currentWeight, currentBodyFat, goalWeightNew, goalBodyFatNew;
	private int sexNewUser;
	private int i = 0;
	private List<String[]> resultArray = new ArrayList<>();
	
	CheckUser check = new CheckUser();
	Scanner keyboardSaveUser = new Scanner(System.in);
	Scanner keyboard = new Scanner(System.in);
	
	public void begin(){
		System.out.print("Enter your username (or press zero to create one): ");
		questions();		
	}
	
	public void questions() {
		if (keyboard.hasNextInt()){
			human = createPerson("0");
			i++;
			if (i <= 2){
				System.out.println("\nWould you like to save yourself into the database?");
				saved = keyboardSaveUser.next().toLowerCase().charAt(0);
				human.setDatabaseSave(saved);
				if (human.getDatabaseSave() == 'y'){
					check.createUserEntry(human);
					outputMuscleAndFat(human);
				} else {
					outputMuscleAndFat(human);
					seeProgress(human);
				}
				i++;
			}
		} else {
			human = getPerson(keyboard.nextLine());
			outputMuscleAndFat(human);
		}			
		
		if (human.getDatabaseSave() != 'n'){
			Scanner keyboardSaveEntry = new Scanner(System.in);
			System.out.print("\nWould you like to save this as an entry? ");
			answer = keyboardSaveEntry.next().toLowerCase().charAt(0);
			
			if (answer == 'y'){
				check.submitEntry(human);
			}
			seeProgress(human);
		}
	}
	public static void outputMuscleAndFat(Person human) {
		System.out.println("-------------------------------------------");
		System.out.println("\nYour muscle is "+human.getCalculatedBodyMuscle());
		System.out.println("Your body fat is " + human.getCalculatedBodyFat() + "\n");
		System.out.println("-------------------------------------------");
	}
	public static void seeProgress(Person human) {
		char answer;
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Would you like to see your progress? ");
		answer = keyboard.next().toLowerCase().charAt(0);
		
		if (answer == 'y'){
			human.getProgress();
		} 
	}
	
	private Person getPerson(String username) {
		Scanner keyboard = new Scanner(System.in);

		CheckUser check = new CheckUser();
		resultArray = check.getUser(username);
		int count = resultArray.size();
		if (count == 1){
			for (String[] row: resultArray){
				sex = row[3];
				goalWeight = row[4];
				goalBodyFat = row[5];
			}
			
			System.out.print("Enter current weight: ");
			currentWeight = keyboard.nextDouble();
			
			System.out.print("Enter current body fat percentage: ");
			currentBodyFat = keyboard.nextDouble();
			
			System.out.println("\n-------------------------------------------");
			return new PersonExists(username, sex, currentWeight, currentBodyFat, goalWeight, goalBodyFat);
		} else {
			System.out.print("No record of username " + username + ". Would you like to add it to the database? ");
			char saved = keyboard.next().toLowerCase().charAt(0);
			if (saved == 'y'){
				Person human = createPerson(username);
				check.createUserEntry(human);
				human.setDatabaseSave(saved);
				return human;
			} else {
				Person human = createPerson(username);
				human.setDatabaseSave('n');
				seeProgress(human);
				return human;
			}
		} 
	}
	private Person createPerson(String username){
		Scanner keyboard = new Scanner(System.in);

		if (username == "0"){
			System.out.print("Please enter your username: ");
			username = keyboard.next();
		}
		
		CheckUser check = new CheckUser();
		List availableUserName = check.getUser(username);
		
		if (availableUserName.size() == 0){
			return getNewUserStats(username);
		} else {
			System.out.println("\nSorry, that username is taken. Try another one.\n");
			questions();
			return human;
		}
		
	}
	public Person getNewUserStats(String username) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter your sex: ");
		char sex = keyboard.next().toLowerCase().charAt(0);
		if (sex == 'm'){
			sexNewUser = 1;
		} else {
			sexNewUser = 0;
		}
		
		System.out.print("Enter your current weight: ");
		currentWeight = keyboard.nextDouble();
		
		System.out.print("Enter your current body fat percentage: ");
		currentBodyFat = keyboard.nextDouble();
		
		System.out.print("Enter your goal weight: ");
		goalWeightNew = keyboard.nextDouble();
		
		System.out.print("Enter your goal body fat percentage: ");
		goalBodyFatNew = keyboard.nextDouble();
		
		return new PersonNew(username, sexNewUser, currentWeight, currentBodyFat, goalWeightNew, goalBodyFatNew);
	}

}
