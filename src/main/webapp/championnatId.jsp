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
		
	
		<% 
            String attribut = (String) request.getAttribute("id");
            out.println( attribut );
            %>
            
            <table class="table table-bordered">
		<thead>
			<tr>
				<th>Rang</th>
				<th>Equipe</th>
				<th>Pts</th>
				<th>J.</th>
				<th>N.</th>
				<th>P.</th>
				<th>Diff.</th>
			</tr>
		</thead>
		<tbody>
		
		 <c:forEach items="${test}" var="item">
		 <c:set var="totalCount" value="${totalCount + 1}" />
			<tr>
				<td>${totalCount}</td>
				<td><img src="<c:out value="images/logo/${item.logo}" />" width="50px"/><br />
				<c:out value="${item.nom}" /></td>
				<td><c:out value="${item.points}" /></td>
				<td><c:out value="${item.journee}" /></td>
				<td><c:out value="${item.bp}" /></td>
				<td><c:out value="${item.bp}" /></td>
				<td><c:out value="${item.diff}" /></td>
			</tr>
		</c:forEach>
	
		</tbody>
	</table>
	</div>
</div>
	
</body>
</html>