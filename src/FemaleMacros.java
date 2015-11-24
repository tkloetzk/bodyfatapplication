
public class FemaleMacros implements Macros {

	private double gramsOfProtein, gramsOfCarbs, gramsOfFat, calories;
	
	
	@Override
	public void cutting(double currentBodyWeight) {
		setGramsOfProtein(currentBodyWeight * 1.2);
		setGramsOfCarbs(currentBodyWeight);
		setGramsOfFat(currentBodyWeight * 0.2);
		printOutMacros(currentBodyWeight);
	}

	private void printOutMacros(double currentBodyWeight) {
		setCalories(currentBodyWeight);
		System.out.println("\n-------------------------------------------");
		System.out.printf("Your total daily calories is %d ", getCalories());	
		System.out.printf("\nYour daily grams of protein should be %d grams", getGramsOfProtein());
		System.out.printf("\nYour daily grams of carbs should be %d grams", getGramsOfCarbs());
		System.out.printf("\nYour daily grams of fat should be %d grams", getGramsOfFat());
		System.out.println("\n-------------------------------------------\n");	
	}

	@Override
	public void bulking(double currentBodyWeight) {
		System.out.println("Coming soon...");
		// TODO Auto-generated method stub

	}

	@Override
	public String maintaining() {
		return "Coming soon...";
		// TODO Auto-generated method stub

	}

	public int getGramsOfProtein() {
		return (int)Math.round(gramsOfProtein);
	}

	public void setGramsOfProtein(double gramsOfProtein) {
		this.gramsOfProtein = gramsOfProtein;
	}

	public int getGramsOfCarbs() {
		return (int)Math.round(gramsOfCarbs);
	}

	public void setGramsOfCarbs(double gramsOfCarbs) {
		this.gramsOfCarbs = gramsOfCarbs;
	}

	public int getGramsOfFat() {
		return (int)Math.round(gramsOfFat);
	}

	public void setGramsOfFat(double gramsOfFat) {
		this.gramsOfFat = gramsOfFat;
	}

	public int getCalories() {
		return (int)Math.round(calories);
	}

	public void setCalories(double currentBodyWeight) {
		calories = (getGramsOfCarbs() * 4) + 
				(getGramsOfProtein() * 4) +
				(getGramsOfFat() * 9);
		if (calories < currentBodyWeight * 10){
			calories = currentBodyWeight * 10;
		}
	}

}
