package models.news;


import java.util.List;

public class Entries{
   	private String author;
   	private List<String> categories;
   	private String content;
   	private String contentSnippet;
   	private String link;
   	private String publishedDate;
   	private String title;

 	public String getAuthor(){
		return this.author;
	}
	public void setAuthor(String author){
		this.author = author;
	}
 	public List<String> getCategories(){
		return this.categories;
	}
	public void setCategories(List<String> categories){
		this.categories = categories;
	}
 	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content = content;
	}
 	public String getContentSnippet(){
		return this.contentSnippet;
	}
	public void setContentSnippet(String contentSnippet){
		this.contentSnippet = contentSnippet;
	}
 	public String getLink(){
		return this.link;
	}
	public void setLink(String link){
		this.link = link;
	}
 	public String getPublishedDate(){
		return this.publishedDate;
	}
	public void setPublishedDate(String publishedDate){
		this.publishedDate = publishedDate;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
