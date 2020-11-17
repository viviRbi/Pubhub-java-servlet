	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
		
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Book Details - ${book.isbn13 }</small></h1>
		<hr class="book-primary">
		
		<form action="UpdateBook" method="post" class="form-horizontal" id="updateBook">
		  
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" />
		  
		  <div class="form-group">
		    <label for="title" class="col-sm-4 control-label">Title</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="title" name="title" placeholder="Title" required="required" value="${book.title }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="author" class="col-sm-4 control-label">Author</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="author" name="author" placeholder="Author" required="required" value="${book.author }" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="price" class="col-sm-4 control-label">Price</label>
		    <div class="col-sm-5">
		      <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" required="required" value="${book.price }" />
		    </div>
		  </div>
		  
		 </form>
		 
		  <!-- Get Tags from the Database and display it here  -->

		  <div class="form-group">
		    <label for="tags" class="col-sm-4 control-label text-right">Tags</label>
		    <div class="col-sm-5 text-left" style="margin-top:10px;">
		    
		    <c:forEach var="tag" items="${booktags}"> 
		    	 <strong style="display:inline-block; margin-right:10px; margin-top:5px; margin-bottom:10px;">
		      		<button onclick="javascript:previousInputValue(this)" data-id="${tag.id}" class='label btn btn-warning edit-tag' type="button" data-toggle="modal" data-target="#editTagModal"><c:out value="${tag.tagName}"></c:out> </button>
		      		<form action="RemoveBookTag" method="post" style="display:inline;">
		      			<input type="hidden" name="id" value="${tag.id}"/>
		      			<input type="hidden" name="isbn13" value="${book.isbn13}"/>
		      			<button type="submit" class="label btn btn-primary esc-tag">x</button>
		      		</form>
		      	</strong>
		    </c:forEach>

		      <strong style="display:inline-block; margin-top:5px;margin-bottom:10px;"><button class='label btn-success mr-1 btn add-tag' type="button" data-toggle="modal" data-target="#addTagModal">Add &nbsp;  &nbsp;  &nbsp;  &nbsp; +</button></strong>
		    </div>
		  </div>
		
		  <!-- Get Tags from the Database and display it here end -->
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" form="updateBook" class="btn btn-info">Update</button>
		    </div>
		  </div>
		
	  </div>
	  
	</header>

<!-- Add Tag Modal -->
<div class="modal fade" id="addTagModal" tabindex="-1" role="dialog" aria-labelledby="editTagModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Add Tag Name</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
       <form action="AddBookTag" method="post">
	      <div class="modal-body">
	      		<input type="hidden" name="isbn13" value="${book.isbn13 }"/>
	        	<input class="form-control" type="text" placeholder="id" name="id"/> 
	        	<input class="form-control" type="text" name="tag_name" placeholder="Tag Name"/>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <input type="submit" class="btn btn-primary" value="Save changes"/>
	      </div>
      </form>
    </div>
  </div>
</div> 

<!-- Edit Tag Modal -->
<div class="modal fade" id="editTagModal" tabindex="-1" role="dialog" aria-labelledby="editTagModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Edit Tag Name</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
       <form action="UpdateBookTag" method="post" id="updateTag">
	      <div class="modal-body">
	      		<input type="hidden" name="isbn13" value="${book.isbn13}"/>
	        	<input name="id" type="text" id="editTagId" class="form-control" readonly/> 
	        	<input name="tag_name" type="text" id="editTagName" class="form-control"/> <%-- value="${book_tags.tag_name}" --%>
	      </div>
	       <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <input type="submit" class="btn btn-primary" value="Save changes"/>
	      </div>
      </form>
  
    </div>
  </div>
</div>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
