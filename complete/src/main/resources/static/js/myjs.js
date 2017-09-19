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

});