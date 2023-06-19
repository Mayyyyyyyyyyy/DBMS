package dbms;

public class Job {
    private String jobName;
    private String jContent;
	private int jID;
	private int cID;


    public Job() {
    }
    
    public Job(int jID, String jobName, String jContent, int cID) {
    	this.jID = jID; 
        this.jobName = jobName;
        this.jContent = jContent;
        this.cID = cID;
    }

    public int getjID() {
		return jID;
	}

	public void setjID(int jID) {
		this.jID = jID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getjContent() {
		return jContent;
	}

	public void setjContent(String jContent) {
		this.jContent = jContent;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}
}
