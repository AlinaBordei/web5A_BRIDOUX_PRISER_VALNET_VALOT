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
	
	/*$( "#btn-send-msg" ).click(function() {
		
		$clone = $("#recieved").clone().attr('id','').show();
		$("p", $clone).text($('#msg').val());
		$clone.appendTo(".chat-list");
		$("#msg").val('');
	});*/
	
	//Search someone
	$("#toUser").keyup(function(e) {
		var toUser = $("#toUser").val();
		//If what I'm writting or deleting is not empty...
		if(toUser != ""){
			//...I search the begining of the name in the database 
			searchUser(toUser);
		//Otherwise I clean the list option
		}else{
			$("#userNameList").empty();
		}
		//If I delete what I'm writting...
		if(e.keyCode === 8){
			//...I search the begining of the name in the database 
			searchUser(toUser);
		}
		
	});
	
	//When I select a name in the list of addressees
	$('#userNameList').change(function(){
		//Getting value selected
		var userNameSelected = $('#userNameList option:selected').val();
		//If I selected an option which is not the first (the empty one)...
		if(userNameSelected != ""){
			//...I put the name into the input of addressees
			$("#toUser").val(userNameSelected);
		//Otherwise I clear it
		}else{
			$("#toUser").val("");
		}
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
	
	function searchUser(dataString){
		$.ajax({
		  headers:{
			  'Accept': 'application/json',
			  'Content-Type': 'application/json'
		  },
		  type: "GET",
		  url: "http://localhost:8080/findUser/" + dataString,
		  success:function(data)
		    {
		        $("#userNameList").empty();
		        $("#userNameList").append('<option id=""></option>');
		        $.each(data, function(i, index){
		        	$("#userNameList").append('<option id="'+index.userId+'">'+index.userName+'</option>');
		        });
		        	
		    } 
		});	
	}
	
	function toast(text) {
	    $("#toasted").empty();
	    $("#toasted").append(text);
	    $("#toasted").css( "visibility", "show" );
	}

	
var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/message', function (message) {
        	console.log(message);
        	showMessage(JSON.parse(message.body).message);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'message': $("#msg").val()}));
}

function showMessage(message) {
    //$("#greetings").append("<tr><td>" + message + "</td></tr>");
    $clone = $("#recieved").clone().attr('id','').show();
	$("p", $clone).text(message);
	$clone.appendTo(".chat-list");
	$("#msg").val('');
}

$(function () {
    $( "#signin-btn" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#btn-send-msg" ).click(function() { sendMessage(); });
});
