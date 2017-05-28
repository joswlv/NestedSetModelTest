import java.io.IOException;

/**
 * NestedSetModel - 2017. 5. 25..
 */
public class main {
	public static void main(String[] args) throws IOException {
		String categoryFilePath = args[0];
		String categoryCountFilePath = args[1];
		String outputFilePath = args[2];
		Run r = new Run(categoryFilePath, categoryCountFilePath, outputFilePath);

		r.makeOutput();
	}
}
