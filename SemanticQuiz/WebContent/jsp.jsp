<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>query sparql</title>
<jsp:useBean id="var" class="fr.semantic_learning.semquiz.RDFSPARQL" scope="session" />
</head>

<body>


		<textarea rows="30" cols="60">
	<%=var.returnSectionsThatHave("i")%>
		</textarea>
		
	

</body>

</html>