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
			 <c:forEach items="${test}" var="item">
			<li><img src="<c:out value="images/championnat/${item.logo}" />"/>
			<c:out value="${item.nom}" /></li>
			</c:forEach>
		</ul>
		
		
	</div>
</div>
	
</body>
</html>