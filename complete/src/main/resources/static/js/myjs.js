$(document).ready(function() {
	
	$( "#signup-btn" ).click(function() {
		var uname = $("#userName").val();
		var pwd = $("#password").val();
		var email = $("#mail").val();

		var dataString = {userName:uname, password:pwd, mail:email};
		addNewUser(dataString);
		/*$( "#signup-form" ).hide();
		$( "#signin-form" ).fadeIn(200);*/
	});

	$( ".signup-link" ).click(function() {
		$( "#signin-form" ).hide();
		$( "#signup-form" ).fadeIn(200);
	});
	

	$( ".signin-link" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#signin-form" ).fadeIn(200);
	});

	$( "#signin-btn" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#signin-form" ).hide();
		$( "#message-ui" ).show();
		$( "#btn-disconnect" ).fadeIn(200);
	});

	$( "#btn-disconnect" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#btn-disconnect" ).hide();
		$( "#message-ui" ).hide();
		$( "#signin-form" ).show(200);
	});
	
	$( "#btn-send-msg" ).click(function() {
		
		$clone = $("#recieved").clone().attr('id','').show();
		$("p", $clone).text($('#msg').val());
		$clone.appendTo(".chat-list");
		$("#msg").val('');
	});

});


	/*<li class="left clearfix">
		<span class="pull-left">
			<img src="/pictures/alina.JPG">
		</span>
		<div class="chat-body1 clearfix">
			<p>Ca été ton weekend ?</p>
			<div class="chat_time pull-right"><small>20:31</small></div>
		</div>*/

	function addNewUser(dataString){
		$.ajax({
		  headers:{
			  'Accept': 'application/json',
			  'Content-Type': 'application/json'
		  },
		  type: "POST",
		  url: "http://localhost:8080/addUser",
		  data: JSON.stringify(dataString),
		  dataType: 'json'
		}).done(function(){
			alert("Tu es enregistré !");	
			//toast("Bien inscrit(e) !");
		});	
	}
	
	function toast(text) {
	    $("#toasted").empty();
	    $("#toasted").append(text);
	    $("#toasted").css( "visibility", "show" );
	}
});
