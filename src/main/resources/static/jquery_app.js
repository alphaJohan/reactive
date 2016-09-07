var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/reactive-with-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/data', function (message) {
            // showGreeting(JSON.parse(greeting.body).content);
            showMessage(message.body);
        });
    });
    $.ajax({url: "/openstream", type:'POST', success: function(result){

    }});
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

//function sendName() {
//    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
//}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function generate() {
    $.ajax({url: "/generate", type:'POST', success: function(result){
        console.log('Data file generated: ' + result);
    }});
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#generate" ).click(function() { generate(); });
});

