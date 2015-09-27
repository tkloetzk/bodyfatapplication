
public class PersonNew extends Person {

	public PersonNew(String username, int sex, double currentWeight, double currentBodyFat, double goalWeightNew, double goalBodyFatNew) {
		super(username, sex, currentWeight, currentBodyFat, goalWeightNew, goalBodyFatNew);
		if (sex == 0){
			personSex = new FemaleRange();
		} else {
			personSex = new MaleRange();
		}
	}

	
}
