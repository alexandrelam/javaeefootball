<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Galerie</title>
<c:import url="component/headContent.jsp" />
</head>
<body>
	<c:import url="component/navigationBar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h2>Librairie</h2>
			</div>
		</div>
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<p>Inscrivez-vous pour pouvoir visualiser la librairie ! </p>
			</c:when>
			<c:otherwise>
				<c:forEach var="image" items="${images}" varStatus="status">
					<c:if test="${status.index % 3 == 0 }">
						<div class="row">
					</c:if>

					<div class="col-md-4">
					${image.url}
						<img src="${image.url}" class="img-responsive img-rounded"
							alt="${image.name}" />
						<p class="text-center">
							<strong>${image.name}</strong>
						</p>
					</div>

					<c:if test="${(status.index % 3) == 2 || status.last}">
						</div>
					</c:if>
				</c:forEach>

			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>