var idConversationCourante;
var idUserAuthentificated = 0;
var stompClient = null;

$(document).ready(function () {

	$("#disconnect").hide();
	
    $("#signup-btn").click(function () {
        var uname = $("#userName").val();
        var pwd = $("#password").val();
        var email = $("#mail").val();

        var dataString = {userName: uname, password: pwd, mail: email};
        var testIsUserExist = isUserExist(uname);

        if (uname === "") {
            $("#errorName").empty();
            $("#errorName").append("Hep ! We need a name please.");
        }
        if (pwd === "") {
            $("#errorPasswordNull").empty();
            $("#errorPasswordNull").append("Hep ! We need a password please.");
        } else {
            $("#errorPasswordNull").empty();
        }
        if (email === "") {
            $("#errorEmailNull").empty();
            $("#errorEmailNull").append("Hep ! We need an email please.");
        } else {
            $("#errorEmailNull").empty();
        }
        if (testIsUserExist === "true") {
            $("#errorName").empty();
            $("#errorName").append("Oooops ! This name already exists. Choose another one please.");
        } else {
            $("#errorName").empty();
        }

        if (uname !== "" & pwd !== "" & email !== "") {
            $("#errorPasswordNull").empty();
            $("#errorName").empty();
            $("#errorEmailNull").empty();
            addNewUser(dataString);
        }
        /*$( "#signup-form" ).hide();
         $( "#signin-form" ).fadeIn(200);*/
    });

    $(".signup-link").click(function () {
        $("#signin-form").hide();
        $("#signup-form").fadeIn(200);
        $("#disconnect").hide();
    });


    $(".signin-link").click(function () {
        $("#signup-form").hide();
        $("#signin-form").fadeIn(200);
    });

    //Search someone
    $("#toUser").keyup(function (e) {
        $("#errorMessageAdressees").empty();
        var toUser = $("#toUser").val();
        //If what I'm writting or deleting is not empty...
        if (toUser != "") {
            //I'm looking for ";"
            var testSeveralAddressees = toUser.indexOf(";");
            //If there are ";" into the input, this means I've already found someone
            if (testSeveralAddressees > 0) {
                //I separate all contacts get from the input, the result is an array
                var splitResult = toUser.split(";");
                //I get the array size
                var idEndOfTheSplit = Object.keys(splitResult).length;
                //The research will be for the last contact
                toUser = splitResult[idEndOfTheSplit - 1];
            }
            //...I search the begining of the name in the database 
            searchUser(toUser);
            //Otherwise I clean the list option
        } else {
            $("#userNameList").empty();
        }
        //If I delete what I'm writting...
        if (e.keyCode === 8) {
            //...I search the begining of the name in the database 
            searchUser(toUser);
        }

    });

    //When I select a name in the list of addressees
    $('#userNameList').change(function () {
        //Getting value selected
        var userNameSelected = $('#userNameList option:selected').val();
        //If I selected an option which is not the first (the empty one)...
        if (userNameSelected != "") {
            //...I get the text into the input to search someone,
            var textInputToUser = $("#toUser").val();
            //I'm looking for ";"
            var testSeveralAddressees = textInputToUser.indexOf(";");
            //I clear the input
            $("#toUser").empty();
            //If there are ";" into the input, this means I've already found someone
            if (testSeveralAddressees > 0) {
                //I separate all contacts get from the input, the result is an array
                var splitResult = textInputToUser.split(";");
                //I get the array size
                var idEndOfTheSplit = Object.keys(splitResult).length;
                //I clear the last research of the input
                splitResult[idEndOfTheSplit - 1] = "";
                //String reformat
                textInputToUser = splitResult.join(";");
                //Then, I add new contact to the list in the input
                $("#toUser").val(textInputToUser + userNameSelected + ";");
                //Otherwise, it's my first contact
            } else {
                //and I add it in the input whith an ";" at the end for the next contact to add
                $("#toUser").val(userNameSelected + ";");
            }
            //I clear the list of results
            $("#userNameList").empty();
            //Otherwise I clear it
        } else {
            //I clear the input
            $("#toUser").val("");
            //I clear the list of results
            $("#userNameList").empty();
        }
    });

    $("#newConv").click(function () {
    	$("#chatArea").empty();
        $("#searchAdressees").show();
        $("#validateAdressees").show();
        $(".message_write").hide();
    });

    $("#validateAdressees").click(function (event) {
    	$(".message_write").show();
        event.preventDefault();
        //Tableau qui va contenir tes requêtes
        var promises = [];
        //Tu push toutes tes requêtes dans l'ordre dans ton tableau
        promises.push(newConversation());
        promises.push(updateIdConv());
        promises.push(getAdressees(lastIdConv()));
        promises.push($("#userNameList").hide());
        promises.push($("#searchAdressees").hide());
        promises.push($(".chat-list").empty());
        promises.push(getConversationByUser(idUserAuthentificated));
        promises.push(getMessageFromConversation(idConversationCourante));

        //On exécute toutes les requêtes
        $.when.apply(null, promises).done(function () {
        });
        //Ajouter à la liste des conv sur la gauche
        getConversationByUser(idUserAuthentificated);
    });

    $('.list_conv').on("click", "li", function (event) {
        $(".message_write").show();
        event.preventDefault();
        var select = $(this);
        var id = select.attr('data-id-conv');
//        //I separate all contacts get from the input, the result is an array
//        var splitResult = id.split("idConv_");
//        //I get the array size
//        var idEndOfTheSplit = Object.keys(splitResult).length;
//        //Append each contact into the list
//        var i = 0;
//        for (i = 0; i < idEndOfTheSplit; i++) {
//            if (splitResult[i] !== "") {
//                id = splitResult[i];
                getMessageFromConversation(id);
                //idConversationCourante = id;
//            }
//        }

    });

    $("#signin-btn").click(function (event) { /*connect();*/
        event.preventDefault();
        authentification({mail: $("#inputEmail").val(), password: $("#inputPassword").val()});
    });
    
    $("#disconnect").click(function () {
        disconnect();
        location.reload(true);
    });

});

/**FUNCTIONS**/

function updateIdConv() {
    idConversationCourante = lastIdConv();
}

function getAdressees(idConv) {
    //List of contacts
    var adresseesArray = new Array();
    //Getting the input value
    var textInputToUser = $("#toUser").val();
    //If the input is empty...
    if (textInputToUser === "") {
        //Error message displayed.
        $("#errorMessageAdressees").append("Enter adressee(s) please...");
    } else {
        //I'm looking for ";"
        var testSeveralAddressees = textInputToUser.indexOf(";");
        var dataString;
        //If there are ";" into the input, this means I've already found someone
        if (testSeveralAddressees > 0) {
            //I separate all contacts get from the input, the result is an array
            var splitResult = textInputToUser.split(";");
            //I get the array size
            var idEndOfTheSplit = Object.keys(splitResult).length;
            //Append each contact into the list
            var i = 0;
            for (i = 0; i < idEndOfTheSplit; i++) {
                if (splitResult[i] !== "") {
                    adresseesArray.push(splitResult[i]);
                    var idUser = findIdUser(splitResult[i], idConv);
                    /*dataString = {userId:idUser,conversationId:idConv};
                     console.log("getAdressees1 : ");
                     console.log(dataString);
                     addAdresseesToConversation(dataString);*/
                }
            }
            //Otherwise, it's my only contact,
        } else {
            //and I add it into the list
            adresseesArray.push(textInputToUser);
            dataString = {userId: textInputToUser, conversationId: idConv};
            addAdresseesToConversation(dataString);
        }
        //I include myself in the conversation
        dataString = {userId: idUserAuthentificated, conversationId: idConv};
        addAdresseesToConversation(dataString);
    }
    return adresseesArray;
}
/*<li class="left clearfix">
 <span class="pull-left">
 <img src="/pictures/profil.png">
 </span>
 <div class="chat-body1 clearfix">
 <p>Ca été ton weekend ?</p>
 <div class="chat_time pull-right"><small>20:31</small></div>
 </div>*/

function addNewUser(dataString) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/addUser",
        data: JSON.stringify(dataString),
        dataType: 'json'
    }).done(function () {
        alert("Tu es enregistré !");
        //toast("Bien inscrit(e) !");
    });
}

function addAdresseesToConversation(dataString) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/addUserConversation",
        data: JSON.stringify(dataString),
        dataType: 'json'
    });
}

function isUserExist(dataString) {
    var bodyContent = $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/isUserExist/" + dataString,
        global: false,
        crossDomain: true,
        cache: false,
        async: false
    }).responseText;
    return bodyContent;
}

function findIdUser(dataString, idConv) {
    var bodyContent = $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/findUserId/" + dataString,
        success: function (data)
        {
            dataString = {userId: data.userId, conversationId: idConv};
            addAdresseesToConversation(dataString);

        },
        global: false,
        crossDomain: true,
        cache: false,
        async: false
    }).responseText;
    return bodyContent;
}

function searchUser(dataString) {
    $("#userNameList")
    	.empty()
    	.append('<option id=""></option>');
	if (!dataString) return;
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/findUser/" + dataString,
        success: function (data)
        {
            $.each(data, function (i, index) {
                $("#userNameList").append('<option id="' + index.userId + '">' + index.userName + '</option>');
            });

        }
    });
}

function newConversation() {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/addConversation"
    });
}

function lastIdConv() {
    var bodyContent = $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/lastConversationCreated",
        global: false,
        crossDomain: true,
        cache: false,
        async: false
    }).responseText;
    return bodyContent;
}

function getMessageFromConversation(id) {
	$('#idConv_' + idConversationCourante).removeClass('btn-info');
	$('#idConv_' + id).removeClass('btn-warning').addClass('btn-info');
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/messageByConversationId/" + id,
        success: function (data)
        {
        	idConversationCourante = id;
            $(".chat-list").empty();
            $.each(data, function (i, messageObject) {
            	showMessage(messageObject)
            });
            var n = $('.chat_area').prop("scrollHeight");
            $('.chat_area').animate({ scrollTop: n }, 0);
        }
    });
}

function getConversationByUser(id) {
    return $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/conversationByUser/" + id,
        success: function (data)
        {
            $("#listConversation").empty();
            $.each(data, function (i, conversation) {
            	var conversationId = conversation.conversationId;
            	var users = conversation.userNames;
            	var lastMessageTime = conversation.lastMessage? conversation.lastMessage.datetime : "";
            	var lastMessageText = conversation.lastMessage? conversation.lastMessage.message : "";
            	appendConv({id: conversationId, username: users, time: lastMessageTime, text: lastMessageText});
            });
        }
    });
}
    
function appendConv(data) {
	$conv = $('<li id="idConv_' + data.id + '" data-id-conv="' + data.id + '" class="left clearfix">\
			<div class="chat-body clearfix"><div class="header_sec"><strong class="primary-font">' + data.username + '</strong>\
			<span class="time pull-right">'+ data.time +'</span></div><div class="history_sec">\
			<small class="text primary-font"></small></div></div></li>');
	$("#listConversation").append($conv);
	if (data.text) {
		updateConv(data.id, {text: data.text});
	}
	return $conv;
}

function updateConv(id, data) {
    var $conv = $('#idConv_' + id);
	if (data.text) {		
		var text = data.text;
		var maxLen = 30;
		if (text.length > maxLen) {
			text = text.substring(0, maxLen-3) + "...";
		}
		$conv.find('.text').text(text);
	}
	if (data.time) {
		$conv.find('.time').text(data.time);
	}
}

function toast(text) {
    $("#toasted").empty();
    $("#toasted").append(text);
    $("#toasted").slideDown();
}

function authentification(dataString) {
	$.ajax({
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		type: "POST",
		url: "http://localhost:8080/authUser",
		data: JSON.stringify(dataString),
		dataType: 'json',
		success: function (data)
		{
			if (data == 0) {
				alert("Mauvais identifiants !");
			} else {
				alert("Bienvenue !");
				$("#signinup").hide();
				$("#disconnect").show();
				$("#message-ui").show();
				$(".message_write").hide();
				idUserAuthentificated = data;
				connect();
				getConversationByUser(idUserAuthentificated);
			}
		}
	});
}

function connect() {
    var socket = new SockJS('/esieamessenger');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/message/'+idUserAuthentificated, function (message) {
        	var message = JSON.parse(message.body);
        	if (message.conversationID != idConversationCourante) { // Le message ne correspond pas à la conversation ouverte
        		var $conv = $('#idConv_' + message.conversationID);
        		if ($conv.length == 0) { // On m'écrit un message dans une conversation que je n'ai pas encore dans ma liste
        			$conv = appendConv({id: message.conversationID, username: message.username, time: message.datetime}); // Ajoute la conversation à la liste des conversations
        			$conv.addClass('btn-warning');
        		} else { // On m'écrit dans une conversation déjà dans ma liste
        			$conv.addClass('btn-warning');
        		}
        	} else { // Message adressé dans la conv ouverte        		
        		showMessage(message);
        	}
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({
    	'message': $("#msg").val(), 
    	'conversationID': idConversationCourante, 
    	'userID': idUserAuthentificated
    }));
}

function showMessage(message) {
	var isMyMessage = message.userID == idUserAuthentificated;
	if (isMyMessage) {		
		var $clone = $(".msg-sent").clone().removeClass("msg-sent");
	} else {
		var $clone = $(".msg-received").clone().removeClass("msg-received");
	}
    $("p", $clone).text(message.message);
    $(".time", $clone).text(message.datetime);
    $(".username", $clone).text(message.username);
    $clone.appendTo(".chat-list");
    $clone.show();
    $("#msg").val('');
    
    updateConv(message.conversationID, {text: message.message, time:message.datetime});
}

$(function () {

    $("#disconnect").click(function () {
        disconnect();
    });
    $("#btn-send-msg").click(function () {
        sendMessage();
    });
});
