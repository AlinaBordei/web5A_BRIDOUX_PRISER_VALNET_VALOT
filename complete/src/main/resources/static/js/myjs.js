$(document).ready(function() {
	$( ".signin-link" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#signin-form" ).show(300);
	});

	$( ".signup-link" ).click(function() {
		$( "#signin-form" ).hide();
		$( "#signup-form" ).show(300);
	});
});