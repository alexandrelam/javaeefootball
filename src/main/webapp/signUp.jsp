<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Galerie: Inscription</title>
<c:import url="component/headContent.jsp" />
</head>
<body>
	<c:import url="component/navigationBar.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h2>Inscription</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<p>
					<span class="label label-danger">${error}</span>
				</p>
			</div>
		</div>
		<form method="post" action="signUp">
			<div class="form-group">
				<label for="pseudonym">Pseudonyme</label> <input type="text"
					class="form-control" id="pseudonym" name="pseudonym"
					placeholder="pseudonym" value="${param.pseudonym}">
			</div>
			<div class="form-group">
				<label for="email">Adresse email</label> <input type="email"
					class="form-control" id="email" name="email" placeholder="Email"
					value="${param.email}">
			</div>
			<div class="form-group">
				<label for="password">Mot de passe</label> <input type="password"
					class="form-control" id="password" name="password"
					placeholder="Password" value="${param.password}">
			</div>
			<div class="checkbox">
				<label for="cgu"> <input type="checkbox" id="cgu" name="cgu"
					${param.cgu == "on" ? "checked":""}> J'ai bien lu les CGU
				</label>
			</div>
			<button type="submit" class="btn btn-default">Inscription</button>
		</form>
	</div>
</body>
</html>