package models.news;




import java.util.List;

public class Feed{
   	private String author;
   	private String description;
   	private List<Entries> entries;
   	private String feedUrl;
   	private String link;
   	private String title;
   	private String type;

 	public String getAuthor(){
		return this.author;
	}
	public void setAuthor(String author){
		this.author = author;
	}
 	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
 	public List<Entries> getEntries(){
		return this.entries;
	}
	public void setEntries(List<Entries> entries){
		this.entries = entries;
	}
 	public String getFeedUrl(){
		return this.feedUrl;
	}
	public void setFeedUrl(String feedUrl){
		this.feedUrl = feedUrl;
	}
 	public String getLink(){
		return this.link;
	}
	public void setLink(String link){
		this.link = link;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
