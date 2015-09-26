<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	table, #resultblock {
		margin-left:auto;
		margin-right:auto;
		text-align:left;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="javascript">
	
</script>
<title>SQL Injection Demo</title>
</head>
<body>
	<div id="formdiv">	
		<form name="EMP_form" method="post" action="SQL_Injection_Servlet">	
			<table>
				<tr>
					<td><label>Enter Name: </label></td>
					<td><input type="text" name="nametextbox1" ></td>
					<td><input type="submit" name="submit1" value="Submit"></td>
				</tr>
				<tr>
					<td><label>Enter Name (SQL injection - enter - ' or '1'='1): </label></td>
					<td><input type="text" name="nametextbox2" ></td>
					<td><input type="submit" name="submit2" value="Submit"></td>
				</tr>
			</table>
		</form>
	</div>
	<table id="resultblock" border="1">	
		<% List<String> strList = (List<String>) request.getAttribute("strList"); 
		   Integer col = (Integer) request.getAttribute("col");
		   int i = 0;
		   boolean closeFlag = false;
		   if (col != null && strList != null) {
			   for (String temp : strList) { 
					if (i % col == 0 && closeFlag == true) { %>
						</tr>
				<%   	closeFlag = false;
					} 
					if (i % col == 0 && closeFlag == false) { %>
						<tr>
				<%  	closeFlag = true;
					} %>
					<td><%=temp%></td>
				<%   
					i++;
			   }
		   } %>
		   </tr>
	</table>
</body>
</html>