<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>


<!-------------------------------- Search Result Area ------------------------------------->
<c:forEach var="result" items="${searchList}">
	<c:choose>
	
		<c:when test="${username == result.username}">
		</c:when>
		
		<c:otherwise>
			<form action="addFriend" method="POST">
				<div class="alert alert-info alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					
					<table>
						<tr>
							<th rowspan=2><% for(int i=0; i<20; i++) { %>&nbsp;<% } %>
								<img src="resources/userImg/${result.photo}" class="img-rounded" width="50px" height="50px">
							</th>
							<th>&nbsp;&nbsp;${result.name}</th>
							<th><% for(int i=0; i<80; i++) { %>&nbsp;<% } %></th>
							<th  rowspan=2>
								<input type="hidden" name="friendId" value="${result.id}">&nbsp; 
								<input type="submit" class="btn btn-primary" value="+ Add Friend">
							</th>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;@${result.username}</td>
						</tr>
					</table>
					
				</div>
			</form>
		</c:otherwise>
		
	</c:choose>
</c:forEach>
<!---------------------------------- Search Result Area ------------------------------------->


<!-------------------------------- Notification Area ------------------------------------->
		<c:forEach var="result" items="${friendRequest}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				
				<table>
						<tr>
							<td><b>${result.senderName}</b> send you a friend request.&nbsp;&nbsp;&nbsp;</td>
							<td style="padding-top:15px;">
								<form action="confirmFriend" method="post">
									<input type="hidden" name="friendsTableId" value="${result.id}">
									<button type="submit"  class="btn btn-success">Confirm</button>
								</form>			
							</td>
							<td style="padding-top:15px;">
								<form action="cancelFriend" method="post">	
									<input type="hidden" name="friendsTableId" value="${result.id}">
									<button type="submit" class="btn btn-danger">Delete</button>
								</form>
							</td>
						</tr>
				</table>
		
			</div>
		</c:forEach>
		
		<c:forEach var="result" items="${confirmMember}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				
				<table>
						<tr>
							<td><b>${result.senderName}</b> have added you to <b>${result.groupName}</b> group.&nbsp;&nbsp;&nbsp;</td>
							<td style="padding-top:15px;">
								<form action="confirmAddMember" method="post">
									<input type="hidden" name="baithakMemberId" value="${result.id}">
									<button type="submit"  class="btn btn-success">Confirm</button>
								</form>			
							</td>
							<td style="padding-top:15px;">
								<form action="removeConfirmMember" method="post">	
									<input type="hidden" name="baithakMemberId" value="${result.id}">
									<button type="submit" class="btn btn-danger">Delete</button>
								</form>
							</td>
						</tr>
				</table>
		
			</div>
		</c:forEach>
		
		<c:forEach var="result" items="${notifications}">
			<div class="a alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				${result.message} on <strong>${result.created_at}</strong>
			</div>
		</c:forEach>
<!-------------------------------- Notification Area ------------------------------------->


<div class="panel panel-primary">

	<div class="panel-heading">
    	<h3 class="panel-title">Your Own Groups</h3>
  	</div>
  	
  	<div class="panel-body">
		<div class="col-md-3" align="center"><br>
			<p data-toggle="modal" data-target="#myModal"> 
				<img src="resources/img/createGroup.png" data-toggle="tooltip" data-placement="bottom" 
					title="Create Own Group" class="img-circle" width="180px">
			</p>
		</div>
		<c:forEach var="result" items="${baithakList}">	
			<form action="baithak" method="post">
				<div class="col-md-3" align="center">
					<img src="resources/baithakImg/${result.image}" class="img-circle" data-toggle="tooltip" 
						data-placement="right" title="${result.discription}" width="150px" height="150px">
					<input type="hidden" name="userName" value="${username}">
					<input type="hidden" name="groupId" value="${result.id}"><br><br>
					<input type="submit" class="btn btn-success" value="${result.name}"><br><br>								
				</div>
			</form>
		</c:forEach>		
	</div>
	
</div>


<div class="panel panel-primary">

	<div class="panel-heading">
    	<h3 class="panel-title">Friends Groups</h3>
  	</div>
  	
  	<div class="panel-body">
		<c:forEach var="result" items="${groupList}">
			<form action="baithak" method="post">
				<div class="col-md-3" align="center">
					<img src="resources/baithakImg/${result.image}" class="img-circle" data-toggle="tooltip" 
							data-placement="right" title="${result.discription}" width="150px" height="150px"> 
					<input type="hidden" name="userName" value="${username}"> 
					<input type="hidden" name="groupId"	value="${result.id}"><br><br> 
					<input type="submit" class="btn btn-success" value="${result.name}"><br><br>
				</div>
			</form>
		</c:forEach>
	</div>
	
</div>


<!-- 
**	Model action handling area. 
-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Add Group</h4>
			</div>
			
			<form action="creategroup" method="post" model="baithak" enctype="multipart/form-data">
				
				<div class="modal-body">
					<div class="form-group">
						<label for="gName">Group Name:</label> 
						<input type="text" class="form-control" id="name" placeholder="Group Name" name="name" value="" required >
					</div>
					<div class="form-group">
						<label for="gDesc">Description :</label> 
						<input type="text" class="form-control" id="discription" placeholder="Short Description for the group" 
							name="discription" value="" required >
					</div>
					<div class="form-group">
						<label for="gPhoto">Group Picture :</label> 
						<input type="file" accept="image/*" id="gPhoto" name="gPhoto" value="" required >
						<p class="help-block">
							<span style="color: red">* </span> Select Suitable Group Picture.<br>
							<span style="color: red">* </span> You can add the members after creating the group.
						</p>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-success">Save Changes</button>
				</div>
				
			</form>

		</div>
	</div>
</div>
