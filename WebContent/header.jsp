<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PubHub</title>
<link rel="shortcut icon" href="resources/imgs/favicon.png" type="image/x-icon">

<!-- CSS - Custom fonts -->
<link href="resources/libraries/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
<!-- <link href="http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css"> -->

<!-- CSS - jQuery DataTables -->
<link href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />

<!-- CSS - Bootstrap -->
<link rel="stylesheet" type="text/css" href="resources/libraries/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="resources/libraries/css/freelancer.css"/>
<link rel="stylesheet" type="text/css" href="resources/libraries/css/half-slider.css"/>

<!-- CSS - Custom -->
<link rel="stylesheet" type="text/css" href="resources/styles/custom.css"/>

</head>
<body id="page-top" class="index">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div><a class="navbar-brand col-xs-4" href="${pageContext.request.contextPath }">PubHub</a> </div>   
                </br>      
                <form action="SearchTag" method="POST" class="form-row">
	                <input class="search-input" placeholder="Search Tag to find book" name="search_tag" required/>   
	                <button class="btn btn-secondary" style="height:24px; padding:2px;" type="submit">Find</button>
                </form>
                
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Book Publishing<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="BookPublishing">Home (Level 100)</a></li>
                          <li class="divider"></li>
                          <li><a href="PublishBook">Publish Book</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Marketplace<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="Marketplace">Home (Level 200)</a></li>
                          <li class="divider"></li>
					      <li><a href="#">Cart</a></li>
					      <li><a href="#">Orders</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Fan Outreach<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="FanOutreach">Home (Level 300)</a></li>
                          <li class="divider"></li>
                          <li><a href="#">Follow/Unfollow</a></li>
                          <li><a href="#">Talk to your fans</a></li>
                        </ul>
                    </li> </br>
                    <c:choose>
					<c:when test="${not empty headerMessage }">
					  <p class="alert ${messageClass}">${headerMessage }</p>
					<%
					  session.setAttribute("headerMessage", null);
					  session.setAttribute("messageClass", null);
					%>
					</c:when>
				</c:choose>                    
                </ul>
               
            </div>
            <!-- /.navbar-collapse -->
            <div style="margin-top:15px;">
            	<c:forEach var="tag" items="${tags}"> 
                	<a class="btn btn-warning" style="display:inline-block; margin-right:10px;" href="/PubHub/ViewBookDetails?isbn13=${tag.isbn13}"><c:out value="${tag.tagName}"></c:out></a>
                </c:forEach> 
             </div> 
        </div>
        <!-- /.container-fluid -->
    </nav>