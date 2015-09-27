import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonFactory {
	Connection con = null;
	PreparedStatement statement = null;
	private String sex, goalWeight, goalBodyFat;
	private double currentWeight, currentBodyFat, goalWeightNew, goalBodyFatNew;
	private int sexNewUser;
	private List<String[]> resultArray = new ArrayList<>();
	
	public Person getPerson(String username) {
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
			
			System.out.println("Enter current weight");
			currentWeight = keyboard.nextDouble();
			
			System.out.println("Enter current body fat percentage");
			currentBodyFat = keyboard.nextDouble();

			return new PersonExists(username, sex, currentWeight, currentBodyFat, goalWeight, goalBodyFat);
		} else {
			System.out.println("No record of username " + username + ". Would you like to add it to the database?");
			char saved = keyboard.next().toLowerCase().charAt(0);
			if (saved == 'y'){
				Person human = createPerson(username);
				check.createUserEntry(human);
				human.setDatabaseSave(saved);
				return human;
			} else {
				Person human = createPerson(username);
				human.setDatabaseSave(saved);
				return human;
			}
		} 
	}
	public Person createPerson(String username){
		Scanner keyboard = new Scanner(System.in);

		if (username == "0"){
			System.out.println("Please enter your username:");
			username = keyboard.next();
		}
		
		CheckUser check = new CheckUser();
		List availableUserName = check.getUser(username);
		
		if (availableUserName.size() == 0){
			return getNewUserStats(username);
		} else {
			//this causes it to break
			System.out.println("Sorry, that username is taken. Try another one.");
			//createPerson("0");
			return null;
		}
		
	}
	public Person getNewUserStats(String username) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your sex");
		char sex = keyboard.next().toLowerCase().charAt(0);
		if (sex == 'm'){
			sexNewUser = 1;
		} else {
			sexNewUser = 0;
		}
		
		System.out.println("Enter your current weight");
		currentWeight = keyboard.nextDouble();
		
		System.out.println("Enter your current body fat percentage");
		currentBodyFat = keyboard.nextDouble();
		
		System.out.println("Enter your goal weight");
		goalWeightNew = keyboard.nextDouble();
		
		System.out.println("Enter your goal body fat percentage");
		goalBodyFatNew = keyboard.nextDouble();
		
		//System.out.println(username + " "+ sexNewUser  + " "+ currentWeight  + " "+ currentBodyFat  + " "+ goalWeightNew  + " "+ goalBodyFatNew);
		return new PersonNew(username, sexNewUser, currentWeight, currentBodyFat, goalWeightNew, goalBodyFatNew);
	}

}
