
public interface BodyFatRange {
	String[] outputArray = {
			"You are extremely unhealthy. Eat something",
            "You are in the essential zone. Be very careful",
            "Great job. You are within the athlete range",
            "Nice job. You are within the fitness range",
            "You are within the average range",
            "You are in the obese range"};
	String outputRange(double bodyFat);
}
