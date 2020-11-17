package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/SearchTag")
public class SearchTagServlet extends HttpServlet{

	// Get letters => search for alike tag_name, if it is more than 1, display it in the header, else, go to viewBookDetail?isbn=
	// request.getHeader("Referer") to go back 1 page
	
	// For multiple, requestDispatcher them and send them to the previous page, use href to link them to other page
	
	// If no result: redirect them to previous page and save message in session 'No result found'
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess= false;
		
		String tagInput = request.getParameter("search_tag");
		BookTagDAO tagdao = DAOUtilities.getBookTagDAO();
		List<BookTag> tags = tagdao.getBookTagByInput(tagInput);
				
		if (tags.size()>0) {
			isSuccess = true;
		}else {
			isSuccess = false;
		}
		
		BookDAO dao = DAOUtilities.getBookDAO();
		List<Book> bookList = dao.getAllBooks();
		
		request.getSession().setAttribute("books", bookList);;
		
		if(isSuccess){
            request.getSession().setAttribute("headerMessage", "Book Tag for key word '"+ tagInput + "' successfully found");
            request.getSession().setAttribute("messageClass", "text-success font-11");
            if(tags.size()==1) {
            	request.getSession().setAttribute("headerMessage", "Here is the book for tag " + tags.get(0).getTagName() + " with key word '"+ tagInput +"'");
				response.sendRedirect(request.getContextPath() + "/ViewBookDetails?isbn13=" + tags.get(0).getIsbn13());
			}else {
				request.setAttribute("tags", tags);
				request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);
	
			}
        } if(!isSuccess) {
            request.getSession().setAttribute("headerMessage", "Book Tag not found");
            request.getSession().setAttribute("messageClass", "text-warning font-11");
            response.sendRedirect(request.getHeader("Referer"));
        }
	}
}
