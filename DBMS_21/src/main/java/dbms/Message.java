package dbms;

public class Message {
	
	private int sID;
	private int rID;
	private String content;
	public Message(int sID, int rID, String content) {
		super();
		this.sID = sID;
		this.rID = rID;
		this.content = content;
	}
	public int getsID() {
		return sID;
	}
	public void setsID(int sID) {
		this.sID = sID;
	}
	public int getrID() {
		return rID;
	}
	public void setrID(int rID) {
		this.rID = rID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
