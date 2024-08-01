<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= 'utils.StringUtils' %>
<%
    String contextPath = request.getContextPath();
    String errMsg = (String) request.getAttribute(StringUtils.MESSAGE_ERROR);
    String successMsg = (String) request.getAttribute(StringUtils.MESSAGE_SUCCESS);
    String username = (String) request.getAttribute(StringUtils.USERNAME);
    String successParam = request.getParameter(StringUtils.SUCCESS);
    // Check if the user is logged in
    boolean isLoggedIn = (username != null && !username.isEmpty());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./stylesheets/style.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" integrity="sha384-vSIIfh2YWi9wW0r9iZe7RJPrKwp6bG+s9QZMoITbCckVJqGCCRhc+ccxNcdpHuYu" crossorigin="anonymous">
<title>Navbar</title>
</head>

<body style="margin: 0; padding: 0; background-image: url('resources/images/products/gallery2.jpeg'); background-size: cover; background-position: center;">
    <jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />

    <div style="display: flex; align-items: center; justify-content: center; height: 100vh; text-align: center; color: #fff;">
        <section style="position: relative; z-index: 1;">
            <span style="font-size: 1.5rem; font-weight: bold;">Time</span>
            <h1 style="font-size: 3rem; margin: 20px 0;">It's Moon o'clock</h1>
            <br>
            <a href="<%= contextPath%>/UserProductServlet" style="display: inline-block; padding: 10px 20px; background-color: #333; color: #fff; text-decoration: none; border-radius: 25px; transition: background-color 0.3s ease;">Visit Now</a>
        </section>
    </div>

    <jsp:include page="<%=StringUtils.PAGE_URL_FOOTER%>" />
</body>
</html>
