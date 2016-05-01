<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/library" />">Galerie</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><c:choose>
						<c:when test="${empty sessionScope.user}">
							<a href="<c:url value="/signUp" />">Inscription</a>
						</c:when>
						<c:otherwise>
							<a href="<c:url value="/profile.jsp" />">Profile</a>
						</c:otherwise>
					</c:choose></li>
			</ul>
		</div>
	</div>
</nav>