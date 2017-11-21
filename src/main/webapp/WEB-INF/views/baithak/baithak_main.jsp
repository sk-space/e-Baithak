<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--  
**	send message using ajax on click
-->
<script type="text/javascript">

	$(document).ready(function(){
		 $('#send_chat').click(function(){
			 
			var msg = $('#chat_data').val();
			baithakId = ${baithak.id}
			myId = ${myId}
			
			$.ajax({
			    type: "GET",
		        url: "setMessages",
		        data: {
		        	"message" : msg,
		        	"bId" : baithakId,
		        	"uId" : myId,
		        	},
		        contentType: "application/json",
		        
		        success: function (data) {
		            console.log("Message = "+msg+" \nFrom userId = "+myId+" \nTo groupId = "+baithakId);
		            
		            document.getElementById('chat_data').value = "";
		            $("#chat_data").focus();
		        },
		        
		        error: function (data) {
		        	$("#chat_data").focus();
		            console.log("message sending error")
		        },
			});
		});
	});

</script>


<!-- 
**	get all messages using ajax 
-->
<script type="text/javascript">

$(document).ready(function(){
	baithakId = ${baithak.id}
	myId = ${myId}
	
	var interval = 1000;  // 1000 = 1 second, 3000 = 3 seconds
	function doAjax() {
	
	$.ajax({
        type: "GET",
        url: "getMessages",
        data: {
        	"bId" : baithakId
        	},
        //data:"",
        contentType: "application/json",
        
        success: function (data) {
            //alert(data + " success");
            $.each(data, function(index, msg) {
            	
                //console.log(msg); //to print the json data
                
                content = "<strong>"+msg.senderId+" : </strong>"+msg.message+"";
                
                if (msg.senderId == myId ){
                	div = "<div class='col-md-10'><div class='alert alert-success' role='alert'>"+content+"</div></div>";
                	//div = "<p style='color:red'>"+ msg.message+" "+msg.senderId+"</p>";
                	$('#Msg').append(div);
                }
                else{
                	div = "<div class='col-md-8 col-md-offset-4'><div class='alert alert-info' role='alert'>"+content+"</div></div>";
                	$('#Msg').append(div);
                }
                
                //$('#response1').html(msg.senderId);
            });
        },
        error: function (data) {
            alert(data + " error");
        },
        complete: function (data) {
            // Schedule the next
            //$( "#Msg" ).remove();
            //setTimeout(doAjax, interval);
    	}
    });
	}
	setTimeout(doAjax, interval);
	
});

</script>


<!-- 
**	including chatframe.jsp in main body
-->
<iframe src="chatframe?bId=${baithak.id}" width="100%" height="72%"></iframe>


<!-- 
**	main chat or message area 
-->
<div class="well" id="" style="overflow-y: hidden; overflow-x: hidden; height: auto;">
	<div class="col-md-12" style="height: 78px;">
		<div class="row">
		
			<div class="input-group">
				<input type="hidden" name="groupId" value="${baithak.id}"> 
				<input type="text" class="form-control input-lg" name="message"
					id="chat_data" placeholder="Enter Message ....." autofocus required >
				
				<!-- <textarea class="form-control" rows="2"></textarea> -->
				<span class="input-group-btn"> 
					<a data-toggle="modal" data-target="#emojiModal">
						<img src="resources/img/emoji.png" height="35px" style="padding-left:10px;">
					</a>
					<button id="send_chat" class="btn btn-info btn-xs"  style="background:#F5F5F5; border:#F5F5F5;">
						<img src="resources/img/send.png"  height="50px">
					</button>
				</span>
			</div>
			<div>
				<form action="attachment" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>
							<input type="hidden" name="groupId" value="${baithak.id}">
							<input type="file" name="attachment">
						</td>
						<td>
							<button type="submit" style="background:#F5F5F5; border:#F5F5F5; padding-top:5px;">
								<img src="resources/img/ok.jpg" width="30px" height="30px">
							</button>
						</td>
					</tr>					
				</table>
				</form>
			</div>
			
		</div>
	</div>
</div>

<!-- 
**	Model to handle the emoji.
-->
<div class="modal fade" id="emojiModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Available Emoji And Stickers</h4>
      </div>
      <div class="modal-body" style="background:#DBDBDB;">
      	<form action="setemoji" method="post">
      		<c:forEach var="result" items="${emojiList}">
	      		<button type="submit" name="emojiName" value="${result.name}" style="border:#DBDBDB; background:#DBDBDB;">
	      			<img src="resources/emoji/${result.image}"  height="60px">
	      		</button>
      		</c:forEach>
      	</form>
      </div>
    </div>
  </div>
</div>