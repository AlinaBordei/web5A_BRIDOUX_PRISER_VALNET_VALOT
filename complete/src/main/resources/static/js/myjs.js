var idConversationCourante;
var idUserAuthentificated = 0;
var stompClient = null;

$(document).ready(function () {

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
    });


    $(".signin-link").click(function () {
        $("#signup-form").hide();
        $("#signin-form").fadeIn(200);
    });

    /*$( "#signin-btn" ).click(function() {
     $( "#signup-form" ).hide();
     $( "#signin-form" ).hide();
     $( "#message-ui" ).show();
     $( "#btn-disconnect" ).fadeIn(200);
     });*/

    $("#btn-disconnect").click(function () {
        $("#signup-form").hide();
        $("#btn-disconnect").hide();
        $("#message-ui").hide();
        $("#signin-form").show(200);
    });

    /*$( "#btn-send-msg" ).click(function() {
     
     $clone = $("#recieved").clone().attr('id','').show();
     $("p", $clone).text($('#msg').val());
     $clone.appendTo(".chat-list");
     $("#msg").val('');
     });*/

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

    $("#signup-test").click(function () {
        var test = getAdressees(6);
        console.log(test);
    });

    $("#newConv").click(function () {
        $("#searchAdressees").show();
    });

    $("#validateAdressees").click(function (event) {
        event.preventDefault();
        //Tableau qui va contenir tes requêtes
        var promises = [];
        //Tu push toutes tes requêtes dans l'ordre dans ton tableau
        promises.push(newConversation());
        promises.push(updateIdConv);
        promises.push(alert(lastIdConv()));
        promises.push(getAdressees(lastIdConv()));

        promises.push($("#userNameList").hide());
        promises.push($("#validateAdressees").hide());
        promises.push($(".chat-list").empty());
        promises.push(getConversationByUser(idUserAuthentificated).delay(500));
        promises.push(getMessageFromConversation(lastIdConv));
        
        //On exécute toutes les requêtes
        $.when.apply(null, promises).done(function () {
        });
        /*newConversation();
         idConversationCourante = lastIdConv();
         alert(idConversationCourante);
         var test = getAdressees(idConversationCourante);
         $("#searchAdressees").hide();
         console.log(test);*/
        //Ajouter à la liste des conv sur la gauche
    });

    $('.list_conv').on("click", "li", function (event) {
        event.preventDefault();
        var select = $(this);
        var id = select.attr('id');
        //I separate all contacts get from the input, the result is an array
        var splitResult = id.split("idConv_");
        //I get the array size
        var idEndOfTheSplit = Object.keys(splitResult).length;
        //Append each contact into the list
        var i = 0;
        for (i = 0; i < idEndOfTheSplit; i++) {
            if (splitResult[i] !== "") {
                id = splitResult[i];
                getMessageFromConversation(id);
                idConversationCourante = id;
            }
        }

    });

    $("#signin-btn").click(function (event) { /*connect();*/
        event.preventDefault();
        authentification({mail: $("#inputEmail").val(), password: $("#inputPassword").val()});
        connect();
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
            console.log("getAdressees2 : ");
            console.log(dataString);
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
 <img src="/pictures/alina.JPG">
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
            console.log("findIdUser : ");
            console.log(dataString);
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
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/findUser/" + dataString,
        success: function (data)
        {
            $("#userNameList").empty();
            $("#userNameList").append('<option id=""></option>');
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
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/messageByConversationId/" + id,
        success: function (data)
        {
            $(".chat-list").empty();
            $.each(data, function (i, index) {
                $(".chat-list").append('<li id="recieved" class="left clearfix"><span class="pull-left"><img src="/pictures/alina.JPG"></span><div class="chat-body1 clearfix"><p>' + index.message + '</p><div class="chat_time pull-right"><small>' + index.sdf + '</small></div></div></li>');
            });

        }
    });
}

function getConversationByUser(id) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/userConversationByUser/" + id,
        success: function (data)
        {


            $("#listConversation").empty();
            $.each(data, function (i, index) {
                $.ajax({
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    type: "GET",
                    url: "http://localhost:8080/userConversationById/" + index.conversationId,
                    success: function (userId)
                    {
                        console.log(userId);
                        $.ajax({
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            type: "GET",
                            url: "http://localhost:8080/usersById/" + userId[0].userId,
                            success: function (user)
                            {
                                console.log(user);
                                console.log(index);
                                $("#listConversation").append('<li id="idConv_' + index.conversationId + '" class="left clearfix"><span class="pull-left"><img src="/pictures/alina.JPG"></span><div class="chat-body clearfix"><div class="header_sec"><strong class="primary-font">' + user.userName + '</strong><strong class="pull-right">09:45</strong></div><div class="history_sec"><small class="primary-font">Coucou</small></div></div></li>');

                            }
                        });
                    }
                });

            });

        }
    });
}

function toast(text) {
    $("#toasted").empty();
    $("#toasted").append(text);
    $("#toasted").css("visibility", "show");
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
				$("#message-ui").show();
				idUserAuthentificated = data;
			}
		}
	}).done(function () {
		getConversationByUser(idUserAuthentificated);
	});
}

function connect() {
    var socket = new SockJS('/esieamessenger');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/message/'+idUserAuthentificated, function (message) {
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
    stompClient.send("/app/message", {}, JSON.stringify({
    	'message': $("#msg").val(), 
    	'conversationID': idConversationCourante, 
    	'userID': idUserAuthentificated
    }));
}

function showMessage(message) {
    //$("#greetings").append("<tr><td>" + message + "</td></tr>");
    var dt = new Date();
    var h = dt.getHours();
    if (h < 10) {
        h = "0" + h;
    }
    var m = dt.getMinutes();
    if (m < 10) {
        m = "0" + m;
    }
    var time = h + ":" + m;
    $clone = $("#recieved").clone().attr('id', '').show();
    $("p", $clone).text(message);
    $("small", $clone).text(time);
    $clone.appendTo(".chat-list");
    $("#msg").val('');
}

$(function () {

    $("#disconnect").click(function () {
        disconnect();
    });
    $("#btn-send-msg").click(function () {
        sendMessage();
    });
});
