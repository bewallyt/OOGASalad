import java.util.*;

public class Main {
	public static void main(String[] args){
		Set myFeatures = new HashSet<Feature>();
		myFeatures.add(new GridObjectCreation());
		AuthoringView view = new AuthoringView(myFeatures);
	}
}