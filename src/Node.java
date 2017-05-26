/**
 * NestedSetModel - 2017. 5. 25..
 */
public class Node {

	private String categoryId;
	private String categoryName;
	private int leftValue;
	private int rightValue;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getLeftValue() {
		return leftValue;
	}

	public void setLeftValue(int leftValue) {
		this.leftValue = leftValue;
	}

	public int getRightValue() {
		return rightValue;
	}

	public void setRightValue(int rightValue) {
		this.rightValue = rightValue;
	}

	public Node(String[] node) {
		this.categoryId = node[0];
		this.categoryName = node[1];
		this.leftValue = Integer.parseInt(node[2]);
		this.rightValue = Integer.parseInt(node[3]);
	}

	@Override
	public String toString() {
		return "Node{" +
				"categoryId='" + categoryId + '\'' +
				", categoryName='" + categoryName + '\'' +
				", leftValue=" + leftValue +
				", rightValue=" + rightValue +
				'}';
	}
}