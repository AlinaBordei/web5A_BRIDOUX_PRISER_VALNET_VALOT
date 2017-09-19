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
		$( "#btn-disconnect" ).fadeIn(200);
	});

	$( "#btn-disconnect" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#btn-disconnect" ).hide();
		$( "#message-ui" ).hide();
		$( "#signin-form" ).show(200);
	});

});