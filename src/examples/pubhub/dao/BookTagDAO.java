package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.BookTag;

public interface BookTagDAO {
	public List<BookTag> getAllBookTags();
	public List<BookTag> getBookTagByISBN(String isbn);
	public BookTag getBookTagById(String id);
	
	public boolean addBookTag(BookTag booktag);
	public boolean editBookTag(BookTag booktag);
	public boolean deleteBookTagById(BookTag tag);
	public boolean deleteBookTagById(String id);
	public List<BookTag> getBookTagByInput(String tagInput);
}
