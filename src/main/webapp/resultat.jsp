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
			 <c:forEach items="${resultat}" var="item">
			<li>
			<c:out value="${item.id_dom}" /> - <c:out value="${item.id_ext}" /><br />
			<c:out value="${item.buts_dom}" /> <c:out value="${item.buts_ext}" />
			</li>
			</c:forEach>
		</ul>
		
		
	</div>
</div>
	
</body>
</html>