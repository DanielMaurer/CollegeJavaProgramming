// This class will contain the information of a user. Users will make up a group
public class User {
	// data members
	private String name;
	private int id;
	private String phoneNum;
	private boolean safe;
	
	// getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public boolean isSafe() {
		return safe;
	}
	public void setSafe(boolean safe) {
		this.safe = safe;
	}
	
	// constructor
	public User(String name, int id, String phoneNum, boolean safe) {
		super();
		this.name = name;
		this.id = id;
		this.phoneNum = phoneNum;
		this.safe = safe;
	}
	
	// Functions
	
	
}
