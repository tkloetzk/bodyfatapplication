import java.text.DecimalFormat;
import java.lang.Math;


public abstract class Person  {
	private String username, gainOrLose;
	private double currentBodyWeight, currentBodyFat, calculatedBodyFat, calculatedBodyMuscle, calculatedGoalBodyFat, calculatedGoalBodyMuscle,
	goalWeight, goalBodyFat;
	private int sex;
	public BodyFatRange personSex;
	DecimalFormat df = new DecimalFormat("#.##"); 
	private char saved;
	
	public Person(String username, int sex, double currentWeight, double currentBodyFat2, double goalWeight, double goalBodyFat) {
		setUsername(username);
		setSex(sex);
		setCurrentBodyWeight(currentWeight);
		setCurrentBodyFat(currentBodyFat2);
		setGoalWeight(goalWeight);
		setGoalBodyFat(goalBodyFat);
		setCalculatedBodyMuscle();
		setCalculatedBodyFat();
		setCalculatedGoalBodyFat();
		setCalculatedGoalBodyMuscle();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex){
		this.sex = sex;
	}
	public double getCurrentBodyWeight() {
		String bodyWeightString = df.format(currentBodyWeight);
		return Double.parseDouble(bodyWeightString);
	}

	public void setCurrentBodyWeight(double currentBodyWeight) {
		this.currentBodyWeight = currentBodyWeight;
	}

	public double getCurrentBodyFat() {
		String bodyFatString = df.format(currentBodyFat);
		return Double.parseDouble(bodyFatString);
	}

	public void setCurrentBodyFat(double currentBodyFat) {
		this.currentBodyFat = currentBodyFat;
	}
	public double getGoalWeight() {
		String bodyWeightString = df.format(goalWeight);
		return Double.parseDouble(bodyWeightString);
	}

	public void setGoalWeight(double goalWeight) {
		this.goalWeight = goalWeight;
	}

	public double getGoalBodyFat() {
		String bodyFatString = df.format(goalBodyFat);
		return Double.parseDouble(bodyFatString);
	}

	public void setGoalBodyFat(double goalBodyFat) {
		this.goalBodyFat = goalBodyFat;
	}

	public double getCalculatedBodyFat() {
		String bodyFatString = df.format(calculatedBodyFat);
		return Double.parseDouble(bodyFatString);
	}

	public void setCalculatedBodyFat() {
		this.calculatedBodyFat = getCurrentBodyWeight() * (getCurrentBodyFat() / 100);
	}
	
	public double getCalculatedBodyMuscle() {
		String bodyMuscleString = df.format(calculatedBodyMuscle);
		return Double.parseDouble(bodyMuscleString);
	}
	
	public void setCalculatedBodyMuscle() {
		this.calculatedBodyMuscle = getCurrentBodyWeight() * ((100 - getCurrentBodyFat())/100);
	}
	
	public double getCalculatedGoalBodyFat() {
		String bodyFatString = df.format(calculatedGoalBodyFat);
		return Double.parseDouble(bodyFatString);
	}
	
	public void setCalculatedGoalBodyFat() {
		this.calculatedGoalBodyFat = getGoalWeight() * (getGoalBodyFat() / 100);
	}
	
	public double getCalculatedGoalBodyMuscle() {
		String bodyMuscleString = df.format(calculatedGoalBodyMuscle);
		return Double.parseDouble(bodyMuscleString);
	}
	public void setCalculatedGoalBodyMuscle() {
		this.calculatedGoalBodyMuscle = getGoalWeight() * ((100 - getGoalBodyFat())/100);
		
	}
	public void getProgress() {
		double bodyFatLeft = getCalculatedGoalBodyFat() - getCalculatedBodyFat();
		double absoluteBodyFat = Math.abs(bodyFatLeft);
		String absoluteBodyWeightString = df.format(getGoalWeight() - getCurrentBodyWeight());
		double absoluteBodyWeight = Math.abs(Double.parseDouble(absoluteBodyWeightString));
		double absoluteBodyMuscle = Math.abs(getCalculatedGoalBodyMuscle() - getCalculatedBodyMuscle());

		System.out.println("\n--------------------------------------------------------------------------------");
		System.out.println("Current weight: " + getCurrentBodyWeight());
		System.out.println("Your goal weight is " + getGoalWeight() + "\n");
		
		if (getCurrentBodyWeight() > getGoalWeight()) {
			gainOrLose = "to lose";
		} else if (getCurrentBodyWeight() < getGoalWeight()) {
			gainOrLose = "to gain";
		} else {
			//System.out.println("else");
		}

		System.out.println("You have " + absoluteBodyWeight  + " total pounds left " + gainOrLose);

		System.out.println("With " + (df.format(absoluteBodyMuscle)) + " pounds of muscle to gain to reach your goal muscle weight of " + getCalculatedGoalBodyMuscle() + " lbs.");

		if (getCurrentBodyFat() > getGoalBodyFat()){
			gainOrLose = "to lose";
		} else {
			gainOrLose = "to gain";
		}
		System.out.println("And " + df.format(absoluteBodyFat) + " pounds "+ gainOrLose + " to reach your goal fat weight of " + getCalculatedGoalBodyFat() + " lbs.");

		System.out.print("--------------------------------------------------------------------------------");	
	}
	public String getRange() {
		return personSex.outputRange(getCurrentBodyFat());
	}

	public char getDatabaseSave() {
		return saved;
	}

	public void setDatabaseSave(char saved) {
		this.saved = saved;
	}

}
