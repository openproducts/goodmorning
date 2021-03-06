package models.actions;

public abstract class Action {
	private int volume;
	private String description;
	private String beginTime;
	private String endTime;

	public Action(int volume, String description, String beginTime, String endTime) {
		this.setVolume(volume);
		this.setBeginTime(beginTime);
		this.setDescription(description);
		this.setBeginTime(beginTime);
		this.setEndTime(endTime);
	}
	
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
}
