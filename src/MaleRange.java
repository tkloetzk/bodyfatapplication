public class MaleRange implements BodyFatRange {
	String output;
	@Override
	public String outputRange(double bodyFat) {
		if (bodyFat < 2) {
			output = outputArray[0];
		}
		if (bodyFat >= 2 && bodyFat < 6){
			output = outputArray[1];
		}
		if (bodyFat >= 6 && bodyFat < 14){
			output = outputArray[2];
		}
		if (bodyFat >= 14 && bodyFat < 18){
			output = outputArray[3];
		}
		if (bodyFat >= 18 && bodyFat < 25){
			output = outputArray[4];
		}
		if (bodyFat >= 25){
			output = outputArray[5];
		}
		System.out.println(output);
		return "Range for male";
	}
	
}