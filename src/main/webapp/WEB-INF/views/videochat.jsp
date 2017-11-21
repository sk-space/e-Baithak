<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <title>PubRTC + Mobile</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script type="text/javascript">
      var host = "localhost:8443/ebaithak/videochat";
      if ((host == window.location.host) && (window.location.protocol != "https:"))
          window.location.protocol = "https";
  </script>
    
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/style.css" />
		<link href="resources/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome.min.css" />
    <script src="resources/js/modernizr.custom.js"></script>
  <link rel="stylesheet" type="text/css" href="resources/stylesheets/normalize.css" />


    
</head>
<body>

<div class="container">
<div class="row">

<div class = "bodyDiv">
    
    <div class="md-modal md-effect-13" id="modal-13">
        <div>
                   <div id="vid-box"></div>
                    <button class="md-close">close</button>
                    
        </div>
    </div>
    
    
    <div class="panel panel-primary" style="margin-top:10px">
  <div class="panel-heading">
    <h3 class="panel-title"><b>Video Conference</b></h3>
  </div>
  <div class="panel-body">
    <div class="col-md-4 col-md-offset-1">
        <form name="loginForm" id="login" action="#" onsubmit="return errWrap(login,this);">
            <span class="input input--nao">
                <input class="input__field input__field--nao" type="text" name="username" id="username" placeholder="Enter Username"/>
                        <label class="input__label input__label--nao" for="username">
                            <span class="input__label-content input__label-content--nao">                                          
                            </span>
                        </label>
                    <svg class="graphic graphic--nao" width="300%" height="100%" viewBox="0 0 1200 60" preserveAspectRatio="none">
                        <path d="M0,56.5c0,0,298.666,0,399.333,0C448.336,56.5,513.994,46,597,46c77.327,0,135,10.5,200.999,10.5c95.996,0,402.001,0,402.001,0"/>
                    </svg>
            </span>
            
               <button class="cbutton cbutton--effect-radomir" type="submit" name="login_submit" value="Log In" style="margin-top: 40px; margin-left:-10px">
            	<img src="resources/img/ok.jpg" width="40px" height="40px">
        </button>
        </form>
</div>
    
    <div class="col-md-offset-6">
  <form name="callForm" id="call" action="#" onsubmit="return errWrap(makeCall,this);">
        <span class="input input--nao">
            <input class="input__field input__field--nao" type="text" name="number" id="call" placeholder="Enter User To Call!"/>
          <label class="input__label input__label--nao" for="number">
            <span class="input__label-content input__label-content--nao">                                          
                        </span>
          </label>
        <svg class="graphic graphic--nao" width="300%" height="100%" viewBox="0 0 1200 60" preserveAspectRatio="none">
            <path d="M0,56.5c0,0,298.666,0,399.333,0C448.336,56.5,513.994,46,597,46c77.327,0,135,10.5,200.999,10.5c95.996,0,402.001,0,402.001,0"/>
        </svg>
        </span>
        
        <button class="cbutton cbutton--effect-radomir md-trigger" type="submit" value="Call" style="margin-top: 40px; margin-left:-10px" data-modal="modal-13">
            <img src="resources/img/call.png" width="40px" height="40px">
        </button>

        
  </form>
    <button class="cbutton cbutton--effect-radomir md-trigger" value="Call" hidden="true" data-modal="modal-13" id="showModal"></button>
 </div>
    
    
  </div>
</div>
    
     
  <div class="col-md-8 col-md-offset-2">
  	<div class="alert alert-info" role="alert">
        <strong>Note:</strong><br>
        Enter a username and click Log in button. If input turns green you are ready to receive/place a call.<br>
        In a separate browser window, log in with a different username, and place a call to the first.
    </div>
  	
  </div>
  
     
</div>

</div>
</div>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdn.pubnub.com/pubnub.min.js"></script>
<script src="resources/js/webrtc.js"></script>
<script type="text/javascript">

var video_out = document.getElementById("vid-box");

function login(form) {
  var phone = window.phone = PHONE({
      number        : form.username.value || "Anonymous", // listen on username line else Anonymous
      publish_key   : 'pub-c-561a7378-fa06-4c50-a331-5c0056d0163c', // Your Pub Key
      subscribe_key : 'sub-c-17b7db8a-3915-11e4-9868-02ee2ddab7fe', // Your Sub Key
  }); 
  phone.ready(function(){form.username.style.background="#55ff5b"; form.login_submit.hidden="true"; });
  phone.receive(function(session){
      session.connected(function(session) { video_out.appendChild(session.video); showModal();});
      session.ended(function(session) { video_out.innerHTML=''; });
  });
  return false;
}
    
function makeCall(form){
  if (!window.phone) alert("Login First!");
  else phone.dial(form.number.value);
  return false;
}
    
function showModal(){
    $("#showModal").click();
}

function errWrap(fxn, form){
  try {
    return fxn(form);
  } catch(err) {
    alert("WebRTC is currently only supported by Chrome, Opera, and Firefox");
    return false;
  }
}

</script>
<script src="resources/js/modalEffects.js"></script>
<script src="resources/js/classie.js"></script>

</html>