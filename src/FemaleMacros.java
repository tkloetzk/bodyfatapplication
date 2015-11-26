import java.util.Scanner;


public class FemaleMacros implements Macros {

	private double gramsOfProtein, gramsOfCarbs, gramsOfFat, calories;
	private String answer;
	Scanner keyboard = new Scanner(System.in);
	
	@Override
	public void cutting(double currentBodyWeight) {
		setGramsOfProtein(currentBodyWeight * 1.2);
		setGramsOfCarbs(currentBodyWeight);
		setGramsOfFat(currentBodyWeight * 0.2);
		setCalories(currentBodyWeight);
		printOutMacros();
	}

	@Override
	public void bulking(double currentBodyWeight) {
		System.out.println("\nIf you have a sedentary job and workout 2-3 times per week, enter 1.\n"
				+ "If you have an active job and workout 2-3 times per week, or have a sedentary job but workout 4-6 times per week, enter 2.\n"
				+ "If you have an active job and workout 4+ times per week, enter 3: ");
		answer = keyboard.next();
		
		if (answer.equals("1") || answer.equals("2") || answer.equals("3")){
			switch (answer){
			case "1":
				calories = currentBodyWeight * 16;
				break;
			case "2":
				calories = currentBodyWeight * 17;
				break;
			case "3":
				calories = currentBodyWeight * 18;
				break;
			}
			setGramsOfProtein(currentBodyWeight * 1.2);
			setGramsOfFat(currentBodyWeight * 0.45);
			int carbs = ((int) (calories - ((getGramsOfProtein() * 4) + (getGramsOfFat() * 9))))/4;
			setGramsOfCarbs(carbs);
			printOutMacros();
			
		} else {
			System.out.println("\nThat wasn't a valid answer. Try again.");
			bulking(currentBodyWeight);
		}

	}

	@Override
	public void maintaining(double currentBodyWeight) {
		calories = currentBodyWeight * 16;
		setGramsOfCarbs((getCalories() * 0.5) / 4);
		setGramsOfProtein((getCalories() * 0.3) / 4);
		setGramsOfFat((getCalories() * 0.2) / 9);
		printOutMacros();
		System.out.println("**This is just a starting point.\nYou may have to adjust your calories depending on how active you are.");
	}
	
	public void printOutMacros() {
		System.out.println("\n------------------------------------------------");
		System.out.printf("Your total daily calories is %d ", getCalories());	
		System.out.printf("\nYour daily grams of protein should be %d grams", getGramsOfProtein());
		System.out.printf("\nYour daily grams of carbs should be %d grams", getGramsOfCarbs());
		System.out.printf("\nYour daily grams of fat should be %d grams", getGramsOfFat());
		System.out.println("\n------------------------------------------------\n");	
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
