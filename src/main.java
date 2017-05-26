import java.io.IOException;

/**
 * NestedSetModel - 2017. 5. 25..
 */
public class main {
	public static void main(String[] args) throws IOException {
		String filePath = args[0];
		Run r = new Run(filePath);

		System.out.println(r.findParent("12").toString());
	}
}
