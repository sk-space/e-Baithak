<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date"/> 

<taglib uri="http://pd4ml.com/tlds/pd4ml/2.5" prefix="pd4ml">

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<script src="resources/js/jquery-1.12.3.min.js"></script>

<script src="resources/js/jspdf.min.js" type="text/javascript"></script>

<link href="resources/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<title>Baithak Report</title>
</head>

<body>

<div class="container" id="content">
			<div class="row">
			
					<br><br>
					<div class="panel panel-default" > 

							<div class="panel-heading">
    								<h2 class="panel-title" align="center">Baithak Report 
    									<small>
											(										
											<fmt:formatDate value="${now}" dateStyle="long"/>
											<fmt:formatDate value="${now}" pattern="HH:mm a " />	
    										)
    									</small>
    								</h2>
  							</div>
  							
  							<div class="panel-body">
  								<div>
  									<h3>Member List:</h3>
									<c:forEach var="m" items="${ report.user }">
										<li>${ m }</li>
									</c:forEach>
								</div>
								<div>
									<h3>Conclusion: </h3>
										<p>${ report.subject }</p>
									
								</div>
									<div>
										<h3>Brief Detail:</h3>
										<br> ${ report.description }
									</div>
							</div>
						
					</div>
			</div>
		</div>
		
		<div id="ignore"></div>


		<div align="center">
			<button id="cmd">Generate PDF</button>
			<a href="${ pageContext.request.contextPath }/home" class="btn btn-success" >Back to Home</a>
		</div>
		
		<script type="text/javascript">
			var doc = new jsPDF();
			var specialElementHandlers = {
			    '#ignore': function (element, renderer) {
			        return true;
			    }
			};
	
			$('#cmd').click(function () {
			    doc.fromHTML($('#content').html(), 15, 15, {
			        'width': 170,
			            'elementHandlers': specialElementHandlers
			    });
			    doc.save('sample-file.pdf');
			});
		</script>

	</body>
</html>