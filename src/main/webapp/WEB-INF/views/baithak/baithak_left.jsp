<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<div align="center">
	<img src="resources/baithakImg/${baithak.image}" alt="profile" class="thumbnail" width="170px" height="170px">
	<h4>${baithak.name}<br><br>
		<small>${baithak.discription}</small>
	</h4>
</div>

<br>


<p style="color: BLUE; font-size: 18px;" align="center">
	<Strong>Online Members :</Strong>
<p>
<div class="col-md-12" style="overflow-x: hidden; overflow-y: scroll; height: 250px;">
	<c:forEach var="result" items="${activeMemberList}">
		<table>
			<tr>
				<th rowspan="3"><img src="resources/userImg/${result.photo}"
					class="img-circle" width="40px" height="40px"></th>
				<td style="color: GREEN" width="60px">&nbsp;${result.username}</td>
				<td>
					<img src="resources/img/online.png" class="img-circle" width="15px" height="15px">
				</td>
			</tr>
		</table>
	</c:forEach>
</div>

<br>