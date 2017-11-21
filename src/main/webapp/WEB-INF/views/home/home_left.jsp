<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>


<c:forEach var="result" items="${userDetail}">
	<div align="center">
		<img src="resources/userImg/${result.photo}" alt="profile" class="thumbnail" width="150px" height="150px">
		<h3>${result.name}</h3>
		<p>@${result.username}</p>
	</div>
</c:forEach>

<br>

<form action="searchfriend" method="post" model="user">
	<div align="center">
		<input type="search" class="form-control text-center" value="" name="username" placeholder="Search Friends...">
	</div>
</form>


<h4 align="center">Friends</h4>
<div class="col-md-12" id="" style="overflow-y: scroll; overflow-x: hidden; height: 225px;" align="center">

	<c:forEach var="result" items="${friendsList}">
		<c:choose>
			<c:when test="${result.status == 0}">
				<table>
					<tr>
						<td>
							<img src="resources/userImg/${result.photo}" class="img-circle" width="35px" height="35px">
						</td>
						<td>&nbsp;${result.username}</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>
							<img src="resources/userImg/${result.photo}" class="img-circle" width="35px" height="35px">
						</td>
						<td style="color:GREEN;" width="60px">&nbsp;${result.username}</td>
						<td>
							<img src="resources/img/online.png" class="img-circle" width="15px" height="15px">
						</td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
	</c:forEach>

</div>

<br>


<script type="text/javascript">
		function addFriend(id){
			
			location.href="${pageContext.request.contextPath}/"+id+"/addFriend"; 
		}
	</script>
