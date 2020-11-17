package examples.pubhub.model;


public class BookTag {
	private String id;
	private String tagName;
	private String isbn13;
	
	public BookTag(String id, String tag_name, String isbn13) {
		this.id = id;
		this.tagName = tag_name;
		this.isbn13 = isbn13;
	}
	
	// Default constructor
	public BookTag() {
		this.id = null;
		this.tagName = null;
		this.isbn13 = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tag_name) {
		this.tagName = tag_name;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	
	
}
