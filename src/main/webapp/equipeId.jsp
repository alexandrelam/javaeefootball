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
				<h1><c:out value="${item.nomEquipe}" /> ( ${item.abrEquipe} )</h1>
				<img src="<c:out value="images/logo/${item.logoEquipe}" />" width="100px"/>
				
				<div class="row">
					<div class="col-sm-4">
						<h2>Maillot</h2>
						<div class="row">
							<div class="col-sm-4">
								<img src="<c:out value="images/maillot/${item.maillotDom}.png" />" width="50px" /><br />
								Dom.
							</div>
							<div class="col-sm-4">
								<img src="<c:out value="images/maillot/${item.maillotExt}.png" />" width="50px" /><br />
								Ext.
							</div>
							<div class="col-sm-4">
								<img src="<c:out value="images/maillot/${item.maillotTrois}.png" />" width="50px" /><br />
								Trois.
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<h2>Infos</h2>
						<div class="row">
							<div class="col-sm-12">
								<strong><c:out value="${item.nomEquipe}" /></strong><br />
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<h2>Stade</h2>
						<div class="row">
							<div class="col-sm-12">
								<c:out value="${item.nomStade}" /><br />
								Capacité : <c:out value="${item.capaciteStade}" /> places<br />
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>