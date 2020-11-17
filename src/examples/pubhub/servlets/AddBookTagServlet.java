package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/AddBookTag")

public class AddBookTagServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess= false;
        
        String isbn13 = request.getParameter("isbn13");
        BookTag tag = new BookTag();

        tag.setIsbn13(isbn13);
        tag.setTagName(request.getParameter("tag_name"));
        tag.setId(request.getParameter("id"));

        BookTagDAO tagdao = DAOUtilities.getBookTagDAO();
        isSuccess = tagdao.addBookTag(tag);

        if(isSuccess){
        	
            request.getSession().setAttribute("message", "Book Tag successfully published");
            request.getSession().setAttribute("messageClass", "alert-success");
            response.sendRedirect(request.getContextPath() + "/ViewBookDetails?isbn13=" + isbn13);
        } else {
            request.getSession().setAttribute("message", "There was a problem publishing the tag");
            request.getSession().setAttribute("messageClass", "alert-warning");
            request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
        }
    }

		
}
	
