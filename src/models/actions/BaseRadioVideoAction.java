package models.actions;


public abstract class BaseRadioVideoAction extends Action {
    private String url;  
    private String name;
	
	public BaseRadioVideoAction(int volume, String description,
			String beginTime, String endTime, String url, String name) {
		super(volume, description, beginTime, endTime);
		this.setUrl(url);
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
     
}
