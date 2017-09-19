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
		});	
	}	

	$( ".signin-link" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#signin-form" ).fadeIn(200);
	});

	$( "#signin-btn" ).click(function() {
		$( "#signup-form" ).hide();
		$( "#signin-form" ).hide();
		$( "#message-ui" ).show();
	});
});