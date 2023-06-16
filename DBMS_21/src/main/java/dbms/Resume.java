package dbms;

public class Resume {
	private int resumeID;
	private String resumeName;
	private String rContent;
	
	public Resume() {
	}
	
	public Resume(int resumeID, String resumeName, String rContent) {
		this.resumeID = resumeID;
		this.resumeName = resumeName;
		this.rContent = rContent;
	}
	
	public int getResumeID() {
		return resumeID;
	}

	public void setResumeID(int resumeID) {
		this.resumeID = resumeID;
	}

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
}
