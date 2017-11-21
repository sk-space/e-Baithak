<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!-- refresh the page after 3 seconds. -->
<% response.setIntHeader("Refresh", 1); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>

	<link href="resources/css/chat.css" type="text/css" rel="stylesheet" />
	<link href="resources/css/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src ="resources/js/jquery.min.js"></script>
<script type="text/javascript" src ="resources/js/jquery-ui.min.js"></script>
<script type="text/javascript" src ="resources/js/bootstrap.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	$('#chat').scrollTop($('#chat')[0].scrollHeight);
}); 
	
</script>

</head>
<body>

	<div id="chat" style="background:#E8E8E8; overflow-y: auto; overflow-x: hidden; height: 468px;">
		<div class="col-md-12 col-sm-12 col-xs-12" style="height: 400px;">
			<div class="row" style="padding-left:10px; padding-right:10px;">
			
				<c:forEach var="result" items="${messageList}">
					<c:choose>			
					
						<c:when test="${result.senderId == myId}">
							<div class="row">
								<div class="col-md-2 col-sm-2 col-xs-2"></div>
								<div class="col-md-10 col-sm-10 col-xs-10">
										<c:forEach var="users" items ="${allUserList}">
											<c:if test="${users.id == result.senderId}">
												<table align="right">
													<tr>
														<td><br>
															<c:set var="token" scope="session" value="0"></c:set>
																<c:forEach var="rst" items="${emojiList}">
																	<c:if test="${rst.name == result.message}">
																		<img src="resources/emoji/${rst.image}" height="120px">
																		<c:set var="token" scope="session" value="1"></c:set>
																	</c:if>
																</c:forEach>
																<c:forEach var="rst" items="${attachmentList}">
																	<c:if test="${rst.file == result.message}">
																		<div class="popover fade left in" style="left:-10px; display: block;">
																			<div class="arrow" style="top: 50%;"></div>
																			<div class="popover-content" style="color:#FFF;">
																				<a href="resources/files/${rst.file}"  download>
																					${result.message}&nbsp;
																					<img src="resources/img/download.png" width="25px" height="25px">
																				</a>
																				<c:set var="token" scope="session" value="1"></c:set>
																			</div>
																		</div>	
																	</c:if>
																</c:forEach>
																	<c:if test="${token == 0}">
																		<div class="popover fade left in" style="left:-10px; display: block;">
																			<div class="arrow" style="top: 50%;"></div>
																			<div class="popover-content" style="color:#FFF;">
																				${result.message}
																			</div>
																		</div>	
																	</c:if>
															</td>		
															<td>&nbsp;
																<img src="resources/userImg/${users.photo}" class="img-circle" width=40px height=40px>
															</td>
														</tr>
													</table>
												</c:if>
										</c:forEach>				
								</div>
							</div>
						</c:when>
						
						<c:otherwise>
							<div class="row">
								<div class="col-md-10 col-sm-10 col-xs-10">
										<c:forEach var="users" items ="${allUserList}">
											<c:if test="${users.id == result.senderId}">
											<table>
												<tr>
													<td>
														<img src="resources/userImg/${users.photo}" class="img-circle" width=40px height=40px>
														&nbsp;
													</td>
													<td><br>
														<c:set var="token" scope="session" value="0"></c:set>
														<c:forEach var="rst" items="${emojiList}">
																<c:if test="${rst.name == result.message}">
																	<img src="resources/emoji/${rst.image}" height="120px">
																	<c:set var="token" scope="session" value="1"></c:set>
																</c:if>
														</c:forEach>
														<c:forEach var="rst" items="${attachmentList}">
																	<c:if test="${rst.file == result.message}">
																		<div class="popover fade right in" style="left:0px; display: block;">
																			<div class="arrow" style="top: 50%;"></div>
																			<div class="popover-content">
																				<a href="resources/files/${rst.file}"  download>
																					${result.message}&nbsp;
																					<img src="resources/img/download.png" width="25px" height="25px">
																				</a>
																				<c:set var="token" scope="session" value="1"></c:set>
																			</div>
																		</div>	
																	</c:if>
																</c:forEach>
														<c:if test="${token == 0}">
															<div class="popover fade right in" style="left:0px; display: block;">
																<div class="arrow" style="top: 50%;"></div>
																<div class="popover-content">
																	${result.message}
																</div>
															</div>
														</c:if>														
													</td>
												</tr>
											</table>
											</c:if>
										</c:forEach>
								</div>
								<div class="col-md-2"></div>
							</div>
						</c:otherwise>
						
					</c:choose>	
				</c:forEach>
				
			</div>
		</div>
	</div>
	
</body>
</html>