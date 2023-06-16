package dbms;

public class Skill {
	private int mID;
	private String skillName;

	public Skill(int mID, String skillName) {
		// TODO Auto-generated constructor stub
		this.mID = mID;
		this.skillName = skillName;
	}

	public int getmID() {
		return mID;
	}

	public void setmID(int mID) {
		this.mID = mID;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

}
