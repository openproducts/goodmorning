package classes;

public class SongInfo {
	private String artist;
	private String title;
	private String path;


	public SongInfo(String title, String artist, String path) {
		this.artist = artist;
		this.title = title;
		this.path = path;
		
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}
