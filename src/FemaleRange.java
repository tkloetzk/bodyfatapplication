
public class FemaleRange implements BodyFatRange {
	String output;
	@Override
	public String outputRange(double bodyFat) {
		if (bodyFat < 10) {
			output = outputArray[0];
		}
		if (bodyFat >= 10 && bodyFat < 14){
			output = outputArray[1];
		}
		if (bodyFat >= 14 && bodyFat < 21){
			output = outputArray[2];
		}
		if (bodyFat >= 21 && bodyFat < 25){
			output = outputArray[3];
		}
		if (bodyFat >= 25 && bodyFat < 32){
			output = outputArray[4];
		}
		if (bodyFat >= 32){
			output = outputArray[5];
		}
		System.out.println(output);
		return null;
	}
	
}
