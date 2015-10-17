
public class PersonNew extends Person {

	public PersonNew(String username, String password, int sex, double currentWeight, double currentBodyFat, double goalWeightNew, double goalBodyFatNew) {
		super(username, password, sex, currentWeight, currentBodyFat, goalWeightNew, goalBodyFatNew);
		if (sex == 0){
			personSex = new FemaleRange();
		} else {
			personSex = new MaleRange();
		}
	}

	
}
