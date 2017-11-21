<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true" %>

<html>

	<head>
		<title>e-Baithak | ${baithak.name}</title>
		<link href="resources/css/chat.css" type="text/css" rel="stylesheet" />
		<link href="resources/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src ="resources/js/jquery.min.js"></script>
		<script type="text/javascript" src ="resources/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src ="resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src ="resources/js/bootstrap-tooltip.js"></script>
		<script type="text/javascript">
			$(function () {
	  			$('[data-toggle="tooltip"]').tooltip()
			});
		</script>
	</head>

	<body>

		<jsp:include page="include/baithak_header.jsp"></jsp:include>
		
		<div class="container">
			<div class="row">
		
				<div class="col-xs-12 col-sm-12 col-md-2">	
					<jsp:include page="baithak/baithak_left.jsp"></jsp:include>	
				</div>

				<div class="col-xs-12 col-sm-12 col-md-8">
					<div class="col-lg-12">
						<jsp:include page="baithak/baithak_main.jsp"></jsp:include>	
					</div>				
				</div>

				<div class="col-xs-12 col-sm-12 col-md-2">
					<jsp:include page="baithak/baithak_right.jsp"></jsp:include>			
				</div>
				
			</div>
		</div>
		
		<jsp:include page="include/baithak_footer.jsp"></jsp:include>
 
	</body>
	
</html>

