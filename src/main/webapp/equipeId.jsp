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

			<c:forEach items="${test}" var="item">
				<h1><c:out value="${item.nomEquipe}" /></h1>
				<img src="<c:out value="images/logo/${item.logoEquipe}" />" width="100px"/>
			</c:forEach>
		</div>
	</div>

</body>
</html>