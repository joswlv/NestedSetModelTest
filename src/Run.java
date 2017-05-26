import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * NestedSetModel - 2017. 5. 25..
 */
public class Run {
	private String filePath;
	private HashMap<String,Node> node = new HashMap<>();

	public Run(String filePath) throws IOException {
		this.filePath = filePath;
		makeNode();
	}

	public void makeNode() throws IOException{
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			while ((line=br.readLine())!=null){
				Node n = new Node(line.split(","));
				node.put(n.getCategoryId(),n);
			}
		}
	}

	public Node findParent(String categoryId){
		Node findNode = node.get(categoryId);
		Node parentNode = null;
		for(Node n : node.values()){
			if (findNode.getLeftValue() > n.getLeftValue() && findNode.getRightValue()< n.getRightValue() && !n.getCategoryId().equals("1") && !n.getCategoryId().equals("194")){
				parentNode = n;
			}
		}

		if(parentNode == null)
			return findNode;
		return parentNode;
	}
}
