package models.actions;


public abstract class BaseRadioVideoAction extends Action {
    private String url;  
	
	public BaseRadioVideoAction(int volume, int type, String description,
			String beginTime, String endTime, String url) {
		super(volume, type, description, beginTime, endTime);
		this.setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
     
}
