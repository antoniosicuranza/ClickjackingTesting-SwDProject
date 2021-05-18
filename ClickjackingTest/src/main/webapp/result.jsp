<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the counter cards */
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  padding: 16px;
  text-align: center;
  background-color: #f1f1f1;
  width: fit-content;
  height: fit-content;
  margin:2%;
  border-radius: 40px;
  display: inline-block;
  
}
</style>
<title>Clickjacking test</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div align="center" style="margin-top:3%;"><img alt="Clickjacking test" src="images/logo.jpg"height=25% width=25% align="top" >
</div>
<div style=" margin-top:5%;display: flex;
    justify-content: center;
    flex-wrap: wrap;">
<%
String result=(String)request.getAttribute("results");
String executed=(String)request.getAttribute("executed");
String[] testMethods={"Disabling Javascript","Double Framing","Filter XSS","onBeforeUnloadEvent","RedefiningLocation"};
for(int i=0;i<5;i++){
	if(executed.charAt(i)=='1'){
		%>
  
    <div class="card" align="center" >
      <h3><%out.println(testMethods[i]); %></h3>
      <p><%out.println(result.charAt(i)=='1'? "Not robust":"Robust") ;%></p>
      <img src=<%out.println(result.charAt(i)=='1'? "images/false.png":"images/correct.png") ; %> alt="result" style="width:50px;height:50px;">
    </div>  
  <%
	}
}%>
</div>
<p align="center">
<a href="csv/test.csv" download="ClickJackingCSV.csv">
         <button class="button_start" type="button">Download CSV</button>
         </a>
         </p>
</body>
</html>