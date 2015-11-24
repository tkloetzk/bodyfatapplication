import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PersonFactory {
	private Person human;
	Connection con = null;
	PreparedStatement statement = null;
	private char answer;
	private char saved = 'y';;
	private double currentBodyFat, currentWeight, goalWeightNew, goalBodyFatNew;
	private int sexNewUser, i = 0;
	private Object[] resultArray;
	
	CheckUser check = new CheckUser();
	Scanner keyboardSaveUser = new Scanner(System.in);
	Scanner keyboard = new Scanner(System.in);
	
	public void begin(){
		System.out.print("Enter your username (or press zero to create one): ");
		questions();		
	}
	
	public void questions() {
		if (keyboard.hasNextInt()){
			human = createPerson("0", false);
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
			//get username, check if in mysql
			//if in mysql, ask for password
			//if not in mysql, ask user if they want to be created
			human = getPerson(keyboard.nextLine());
			outputMuscleAndFat(human);
		}			
		
		if (human.getDatabaseSave() != 'n'){
			Scanner keyboardSaveEntry = new Scanner(System.in);
			System.out.print("\nWould you like to save this as an entry? ");
			answer = keyboardSaveEntry.next().toLowerCase().charAt(0);
			
			if (answer == 'y'){
				if (human.getSex() == 0){
					System.out.println("If you're on your period, you can skip this entry when viewing your progress. Enter yes or no:");
					human.setPeriod(keyboardSaveEntry.next().toLowerCase().charAt(0));
				} else {
					human.setPeriod('n');
				}
				check.submitEntry(human);
			}
			seeProgress(human);
		}
		getMacros(human);
	}
	private void getMacros(Person human) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Would you like to get an update on your nutritional goals? ");
		if (keyboard.next().toLowerCase().charAt(0) == 'y') {
			System.out.print("Are you losing weight, gaining muscle, or maintaining? ");
			char diet = keyboard.next().toLowerCase().charAt(0);
			if (diet == 'l' || diet == 'g' || diet == 'm'){
				human.seeMacros(diet);
			} else {
				System.out.println("I'm sorry. I didn't get that. Ask again.");
			}
		}
	}

	public static void outputMuscleAndFat(Person human) {
		System.out.println("-------------------------------------------");
		System.out.println("\nYour muscle is "+ human.getCalculatedBodyMuscle());
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
		char sexChar = 0;
		CheckUser check = new CheckUser();
		resultArray = check.username(username);
		
		if (resultArray[0] != null){

			if (resultArray[1] != null){
				passwordChecker();
			}

            currentWeight = getCurrentBodyWeight();
			
			currentBodyFat = getCurrentBodyFat(sexChar);
			
			System.out.println("\n-------------------------------------------");
			return new PersonExists(username, (String)resultArray[1], (String)resultArray[2], currentWeight, currentBodyFat, (String)resultArray[3], (String)resultArray[4]);
		} else {
			System.out.print("No record of username " + username + ". Would you like to add it to the database? ");
			char saved = keyboard.next().toLowerCase().charAt(0);
			if (saved == 'y'){
				Person human = createPerson(username, false);
				check.createUserEntry(human);
				human.setDatabaseSave(saved);
				return human;
			} else {
				Person human = createPerson(username, true);
				human.setDatabaseSave('n');
				seeProgress(human);
				return human;
			}
		} 
	}

	private boolean passwordChecker() {
		Scanner keyboard = new Scanner(System.in);
		String userPass = (String) resultArray[1];
		System.out.print("Please enter your password: ");
		String password = keyboard.nextLine();

		if (!userPass.equalsIgnoreCase(password)){
			System.out.println("Invalid password. Try again.");
			passwordChecker();
		} else {
			System.out.println();
			return true;
		}
		return false;
	}

	private double getCurrentBodyWeight() {
		Scanner keyboard = new Scanner(System.in);
		double currentWeight = 0;
		while (true) {
			System.out.print("Enter current weight: ");
			String input = keyboard.next();
		    try {
		        currentWeight = Double.parseDouble(input);
		        if (currentWeight < 80 || currentWeight > 299){
		        	System.out.println("Did you enter your body weight (" + currentWeight + " lbs) correctly?");
		    		char answer = keyboard.next().toLowerCase().charAt(0);
		    		if (answer != 'y'){
		    			getCurrentBodyWeight();
		    		}
		        }
		        break;
		    } catch (NumberFormatException ne) {
		        System.out.println(input + " is not a valid number, try again.");
		    }
		}
		return currentWeight;
	}
	private double getCurrentBodyFat(char sex) {
		Scanner keyboard = new Scanner(System.in);
		double currentBodyFat = 0;
		while (true) {
			System.out.print("Enter current body fat percentage: ");
			String input = keyboard.next();
		    try {
		        currentBodyFat = Double.parseDouble(input);
		        if (sex == 'f' && (currentBodyFat < 11 || currentBodyFat > 35) ||
		        		sex == 'm' && (currentBodyFat < 4 || currentBodyFat > 27)){
		        	System.out.println("Did you enter your body fat percentage (" + currentBodyFat + "%) correctly?");
		        	char answer = keyboard.next().toLowerCase().charAt(0);
		    		if (answer != 'y'){
		    			getCurrentBodyFat(sex);
		    		}
		        }
		        break;
		    } catch (NumberFormatException ne) {
		        System.out.println(input + " is not a valid number, try again.");
		    }
		}
		return currentBodyFat;
	}
	private Person createPerson(String username, boolean skip){
		Scanner keyboard = new Scanner(System.in);
		if (username == "0"){
			System.out.print("Please enter your username: ");
			username = keyboard.next();
		}
		
		CheckUser check = new CheckUser();
		Object[] availableUserName = check.username(username);
		
		if (availableUserName[0] == null){
			return getNewUserStats(username, skip);
		} else {
			System.out.println("\nSorry, that username is taken. Try another one.\n");
			questions();
			return human;
		}
		
	}
	public Person getNewUserStats(String username, boolean skip) {
		Scanner keyboard = new Scanner(System.in);
		String password = createPassword(skip);

		System.out.print("Enter your sex: ");
		char sex = keyboard.next().toLowerCase().charAt(0);
		if (sex == 'm'){
			sexNewUser = 1;
		} else {
			sexNewUser = 0;
		}
		
        double currentWeight = getCurrentBodyWeight();
		
        currentBodyFat = getCurrentBodyFat(sex);
		
		System.out.print("Enter your goal weight: ");
		goalWeightNew = keyboard.nextDouble();
		
		System.out.print("Enter your goal body fat percentage: ");
		goalBodyFatNew = keyboard.nextDouble();
		
		return new PersonNew(username, password, sexNewUser, currentWeight, currentBodyFat, goalWeightNew, goalBodyFatNew);
	}

	private String createPassword(boolean skip) {
		Scanner keyboard = new Scanner(System.in);
		String password = null;
		if (skip != true){
			System.out.print("Please enter a password (or press enter to skip): ");
			password = keyboard.nextLine();

			if (password == null || password.isEmpty()){
				password = null;
			}
		}
		return password;
	}

}
