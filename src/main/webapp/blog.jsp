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
			
				
			

					<c:forEach var="item" items="${blog}" varStatus="status">
					<c:if test="${status.index % 3 == 0 }">
						<div class="row">
					</c:if>

					<div class="col-md-4">
					${image.url}
						
						<p class="text-center">
							<strong>${item.titre}</strong>
						</p>
						<img src="<c:out value="images/blog/${item.image}" />" width="100%" class="img-responsive"/>
					</div>

					<c:if test="${(status.index % 3) == 2 || status.last}">
						</div>
					</c:if>
				</c:forEach>
			
		</div>
	</div>

</body>
</html>