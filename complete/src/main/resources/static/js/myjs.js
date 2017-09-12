$(document).ready(function() {
	
	$( "#signin-form" ).show();
	$( "#signup-form" ).hide();
	$( "#list-conv" ).hide();
	
	$( "#signup-btn" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#signin-form" ).show(200);
	});

	$( ".signup-link" ).click(function() {
		$( "#signin-form" ).hide();
		$( "#signup-form" ).show(200);
	});
	
	$( "#signin-btn" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#signin-form" ).hide();
		$( "#list-conv" ).show();
	});
});