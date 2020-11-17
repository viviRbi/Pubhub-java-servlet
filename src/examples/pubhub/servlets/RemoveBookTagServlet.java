package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.BookTag;
//import examples.pubhub.dao.BookTagDAO;
//import examples.pubhub.model.BookTag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/RemoveBookTag")
public class RemoveBookTagServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess= false;
		
		String isbn13 = request.getParameter("isbn13");
        String id = request.getParameter("id");
	
        BookTagDAO tagdao = DAOUtilities.getBookTagDAO();
        BookTag tag = tagdao.getBookTagById(id);
	     
        if (tag != null) {
	        isSuccess = tagdao.deleteBookTagById(tag);
		}else {
			isSuccess = false;
		}
        
		if(isSuccess == true) {
			
			request.getSession().setAttribute("message", "Book Tag successfully removed");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		}else {
			request.getSession().setAttribute("message", "There was a problem updating this tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
	}
}
