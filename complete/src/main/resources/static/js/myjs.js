$(document).ready(function() {
	
	$( "#signup-btn" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#signin-form" ).fadeIn(200);
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
	});

	$( "#btn-disconnect" ).click(function() {
		alert("deco");
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
		</div>
	</li>*/