<!DOCTYPE html>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<c:import url="component/headContent.jsp" />
</head>

<body>
<c:import url="component/navigationBar.jsp" />

<div id="row">
	<div id="centerNav">
		<ul id="home-nav">
			 <c:forEach items="${championnat}" var="item">
			<li><img src="<c:out value="images/championnat/${item.logo}" />"/>
			<a href="championnat?id=<c:out value="${item.id}" />"><c:out value="${item.nom}" /></a></li>
			</c:forEach>
		</ul>
		
		
	</div>
</div>
	
</body>
</html>