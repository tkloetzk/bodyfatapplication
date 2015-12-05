
public class PersonExists extends Person {
	public PersonExists(String usersName, String password, String usersSex, double currentWeight, double currentBodyFat, String goalWeight, String goalBodyFat) {
		super(usersName, password, Integer.parseInt(usersSex), currentWeight, currentBodyFat, Double.parseDouble(goalWeight), Double.parseDouble(goalBodyFat));
		
	//	System.out.println("Person exists: " + usersName);

		if (usersSex.equals("0")){
			personSex = new FemaleRange();
		} else {
			personSex = new MaleRange();
		}
		personSex.outputRange(super.getCurrentBodyFat());
		
	}
}
