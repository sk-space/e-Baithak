<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<nav class="navbar navbar-default" style="background:#4267b2">
	<div class="container">
	
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="home"> 
				<img class="himg" src="resources/img/baithak.png" width="180px"> 
				<small style="color:FFF; font-size:14px;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					Real-Time Group Text Communication
				</small>
			</a>
		</div>
		
		
		<!-- 
		**	Collect the nav links, forms, and other content for toggling 
		-->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<div class="nav navbar-nav navbar-right" style="padding-top:20px;">
			
			<a href="home" style="color:white">Back</a> &nbsp;&nbsp;&nbsp;
			
			<!-- Added code of suzan --> 
			<c:if test="${not empty leaveList}">
						<img src="resources/img/notice.png" class="himg" height="35px" 
							data-toggle="modal" data-target="#leaveMsgModal" title="Leave Message">&nbsp;
			</c:if>
			<!-- Added code of suzan -->
							
				<a href="videochat">
					<img src="resources/img/video_chat.png" class="himg" height="35px"
						data-toggle="tooltip" data-placement="bottom" title="Video Chat">
				</a>&nbsp;
				
				<c:if test="${myId == baithak.created_by}">
					<a href="" data-toggle="modal" data-target="#alertModal">
						<img src="resources/img/kick.png" class="himg" height="35px" 
							data-toggle="tooltip" data-placement="bottom" title="Delete Group">
					</a>&nbsp;
						<img src="resources/img/notice.png" class="himg" height="35px" 
							data-toggle="modal" data-target="#reportModal" title="Generate Report">&nbsp;
				</c:if>
				
				<a href="${pageContext.request.contextPath}/logout">
					<img src="resources/img/logout.png" class="himg" height="35px"
						data-toggle="tooltip" data-placement="bottom" title="Logout">
				</a>
			
			</div>
		</div>
		
	</div>
</nav>

<!-- 
**	Model to handle the delete group confirmation.
-->

<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Are you sure to delete <b>${baithak.name}</b> group?</h4>
      </div>
      <div class="modal-body">
        <p>Deleting this group will delete the group with group members and group messages. This process can't be redo.</p>
      </div>
      <div class="modal-footer">
      	<a href="${pageContext.request.contextPath}/deletegroup" class="btn btn-success">Proceed</a>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 
**	Model to handle the leave Messages.
-->

<div class="modal fade" id="leaveMsgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">

        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Leave Messages Left by users to <b>${baithak.name}</b> group</h4>
      </div>
      <div class="modal-body form-group">
        <table class="table">
        	<tr>
        		<th>Username</th>
        		<th>Message</th>
        		<th>Left On</th>
        	</tr>
        	
			<c:forEach var="result" items="${leaveList}">
				<tr>
					<td>${result.username}</td>
					<td>${result.message}</td>
					<td>${result.created_at}</td>
				</tr>
				
			</c:forEach>
        	
        </table>
      
      </div>
      
      <div class="modal-footer">
			<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
		
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="reportModal" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Generate Baithak Report</h4>
      </div>
      
    	<form action="report" method="post" target="_blank">
	      <div class="modal-body">
	      		
	      			<c:forEach var="result" items="${memberList}">
		      			<div style="width: 550px;">
				      		<input type="hidden" class="form-control" id="inputEmail3" placeholder="Email" value="${ result.username }" name="user" readonly >
		      			</div>
	      			</c:forEach>
	      		
	      		<div class="form-group">
	      			<label>Baithak Subject: </label>
	      			<div style="width: 550px;">
			      		<input type="text" class="form-control" id="inputEmail3" placeholder="Subject" name="subject" autofocus />
	      			</div>
	      		</div>
	      		<div class="form-group">
	      			<label>Baithak Description: </label>
	      			<div style="width: 550px;">
			      		<textarea type="text" class="form-control" id="inputEmail3" placeholder="Description" name="description" cols="250" rows="15"></textarea>
	      			</div>
	      		</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary" data-close="model">Generate Report</button>
	      </div>
		</form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
