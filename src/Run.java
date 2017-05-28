import java.io.*;
import java.util.HashMap;

/**
 * NestedSetModel - 2017. 5. 25..
 */
public class Run {
	private String categroyFilePath;
	private String categoryCountFilePath;
	private String outputFilePath;
	private HashMap<String,Node> categoryHashMap = new HashMap<>();

	public Run(String categroyFilePath, String categoryCountFilePath, String outputFilePath) throws IOException {
		this.categroyFilePath = categroyFilePath;
		this.categoryCountFilePath = categoryCountFilePath;
		this.outputFilePath = outputFilePath;
		makeNode();
	}

	public void makeNode() throws IOException{
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(categroyFilePath))) {
			while ((line = br.readLine()) != null){
				Node n = new Node(line.split(","));
				categoryHashMap.put(n.getCategoryId(),n);
			}
		}
	}

	public String findSelfCategoryName(String categoryId){
		if (categoryId.equals("none")){
			return "-";
		}
		return categoryHashMap.get(categoryId).getCategoryName();
	}

	public String findParent(String categoryId){
		if (categoryId.equals("none"))
			return "-";
		Node findNode = categoryHashMap.get(categoryId);
		Node parentNode = null;
		for(Node n : categoryHashMap.values()){
			if (findNode.getLeftValue() > n.getLeftValue() && findNode.getRightValue()< n.getRightValue() && !n.getCategoryId().equals("1") && !n.getCategoryId().equals("194")){
				parentNode = n;
			}
		}

		if(parentNode == null)
			return findNode.getCategoryName();
		return parentNode.getCategoryName();
	}

	public Boolean isChildNode(String categoryId){
		if (findSelfCategoryName(categoryId) != findParent(categoryId)){
			return true;
		} else {
			return false;
		}
	}

	public void fileWirteFunction(String category1, String category2, String categoryCount) throws IOException{
		String output = null;
		if (isChildNode(category1) && isChildNode(category2)){
			output = findParent(category1)+">"+findSelfCategoryName(category1)+","+findParent(category2)+">"+findSelfCategoryName(category2)+","+categoryCount+"\r\n";
		} else if(!isChildNode(category1) && isChildNode(category2)){
			output = findSelfCategoryName(category1)+","+findParent(category2)+">"+findSelfCategoryName(category2)+","+categoryCount+"\r\n";
		} else if(isChildNode(category1) && !isChildNode(category2)){
			output = findParent(category1)+">"+findSelfCategoryName(category1)+","+findSelfCategoryName(category2)+","+categoryCount+"\r\n";
		} else if(!isChildNode(category1) && !isChildNode(category2)){
			output = findSelfCategoryName(category1)+","+findSelfCategoryName(category2)+","+categoryCount+"\r\n";
		}

		try (BufferedWriter wr = new BufferedWriter(new FileWriter(outputFilePath,true))){
			wr.write(output);
			wr.flush();
		}
	}

	public void makeOutput() throws IOException{
		String output = "카테고리1,카테고리,count\r\n";
		try (BufferedWriter wr = new BufferedWriter(new FileWriter(outputFilePath))){
			wr.write(output);
			wr.flush();
		}

		String lines;
		try (BufferedReader br = new BufferedReader(new FileReader(categoryCountFilePath))){
			while ((lines = br.readLine()) != null){
				String[] splits = lines.split("\t");
				String category1 = splits[0].split(",")[0];
				String category2 = splits[0].split(",")[1];
				String categoryCount = splits[1];
				fileWirteFunction(category1, category2, categoryCount);
			}
		}
	}
}
