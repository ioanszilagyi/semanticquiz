<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="fr.semantic_learning.semquiz.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>introducere domenii</title>
</head>
<body>
<%!
	//public  rdfClass.rdful _sh = new rdfClass.rdful();
	public void getRDF(HttpServletRequest request,javax.servlet.jsp.JspWriter out) throws java.io.IOException
	{
		String btnPush1=request.getParameter("btn1");
		
 		if(btnPush1!=null){
 			String titlu = request.getParameter("dom1");
 			String formatiune = request.getParameter("coddom1");
 			String[] semestre = {request.getParameter("sem1"),request.getParameter("sem2")};
			
			out.print(RDFConstruction.createYearRDF(titlu,semestre,formatiune));
 		}
 		
 		
 	}
	%>


<form action="introducereAni.jsp" method="get" style="height: 187px">

<table style="height: 55px; width: 331px">
<tr>
<td>
<label>Denumire An</label></td>
<td><input type="text" name="dom1" id="dom1" style="height: 23px"></td>
</tr>
<tr>
<td>Formatiunea</td>
<td>
<input type="text" name="coddom1" id="coddom1" style="height: 23px">
</td>
</tr>
<tr>
<td>Semestrul 1</td>
<td>
<input type="text" name="sem1" id="sem1" style="height: 23px">
</td>
</tr>
<tr>
<td>Semestrul 2</td>
<td>
<input type="text" name="sem2" id="sem2" style="height: 23px">
</td>
</tr>

<tr>
<td></td>
<td></td>
</tr>
</table>

<input type="submit" style="height: 32px" id="btn1" name="btn1" >

</form>
<textarea rows="50" cols="100">
<%getRDF(request,out);%>
</textarea> 
</body>
</html>