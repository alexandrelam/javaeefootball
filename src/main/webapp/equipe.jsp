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
			<table class="table table-bordered">
				<thead>
					<tr>
					
						<th>Equipe</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${test}" var="item">
					
						<tr>
						
							<td><img
								src="<c:out value="images/logo/${item.logoEquipe}" />"
								width="50px" /><br /> <c:out value="${item.nomEquipe}" /></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>