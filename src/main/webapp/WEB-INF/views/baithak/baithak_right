<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>


<c:choose>
	<c:when test="${myId == baithak.created_by}">
		<hr>
			<p align="center">
			<a href="" data-toggle="modal" data-target="#myModal" class="btn btn-primary btn-lg">
					+ Add Members
			</a>
			</p>
		<hr>	
	</c:when>
	<c:otherwise>
		<hr>
			<p align="center">
				<a class="btn btn-primary btn-lg" data-toggle="modal" data-target="#leaveModal">Leave Group</a>
			</p>
		<hr>
	</c:otherwise>
</c:choose>


<p style="color: BLUE; font-size: 18px;" class="text-center">
	<Strong>Group Members</Strong>
<p>
<div style="overflow-y: scroll; overflow-x: hidden; height: 390px;">
	<c:choose>
		<c:when test="${myId == baithak.created_by}">
			<c:forEach var="result" items="${memberList}">
				<table>
					<form action="deleteMembers" method="post">
						<tr>
							<th rowspan="3"><img src="resources/userImg/${result.photo}"
								class="img-rounded" width="35px" height="35px"></th>
							<td width="85px">&nbsp;&nbsp;${result.username}</td>
							<th>
								<input type="hidden" name="userId" value="${result.id}">
								<button type="submit" class="btn btn-default btn-xs"
										data-toggle="tooltip" data-placement="right" title="Kick Out">
									<img src="resources/img/kick.png" height="32px">
								</button>
							</th>
						</tr>
					</form>
				</table>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:forEach var="result" items="${memberList}">
				<table>
					<tr>
						<th rowspan="3"><img src="resources/userImg/${result.photo}"
							class="img-rounded" width="35px" height="35px"></th>
						<td width="85px">&nbsp;&nbsp;${result.username}</td>
					</tr>
				</table>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>


<!-- 
**	Model for handling add member function. 
-->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
		
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					Add Members to <b>${baithak.name}</b>
				</h4>
			</div>
			
			<div class="modal-body"></div>
			
			<div class="modal-footer">
				<form action="addMembers" method="post" model="group">
					<input type="hidden" name="groupId" value="${baithak.id}"> 
					<input type="hidden" name="created_by" value="${baithak.created_by}">
					<c:forEach var="fresult" items="${friendsList}">
						<div class="form-group col-md-6">
							<table>
								<tr>
									<th rowspan="3">
										<img src="resources/userImg/${fresult.photo}" class="img-rounded" 
											width="80px" height="80px"></th>
									<th>&nbsp;&nbsp;&nbsp;&nbsp;${fresult.name}</th>
								</tr>
								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;${fresult.username}</td>
								</tr>
								<tr>
									<th>&nbsp;&nbsp;
										<button type="submit" class="btn btn-primary" name="userId"
											value="${fresult.id}">+ Add</button>
									</th>
								</tr>
							</table>
						</div>
					</c:forEach>
				</form>
			</div>
			
		</div>
	</div>
</div>


<!-- 
**	Model to handle the leave group confirmation.
-->

<div class="modal fade" id="leaveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Are you sure to leave <b>${baithak.name}</b> group?</h4>
      </div>
      <div class="modal-body">
        <p>Once you leave <b>${baithak.name}</b> group, you will not be able to visit the group. This process is irreversible...</p>
      </div>
      <div class="modal-footer">
		<form action="leavegroup" method="POST">
			<input type="hidden" name="userId" value="${myId}">
			<input type="submit" class="btn btn-success" value="Proceed">
			<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
		</form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
