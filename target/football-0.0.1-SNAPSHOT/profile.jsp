<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Galerie: Profile</title>
<c:import url="component/headContent.jsp" />
</head>
<body>
	<c:import url="component/navigationBar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h2>${user.pseudonym}</h2>
				<p>${user.email}</p>
			</div>
		</div>
	</div>
</body>
</html>