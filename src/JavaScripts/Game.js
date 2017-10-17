$(function () {
    var localhost= window.location.href.split("src")[0]+"src";
    var serverURL="http://localhost:8081/rest/";

    var b = jsboard.board({attach:"game", size:"20x30", style:"checkerboard"})
    b.cell("each").style({width:"20px", height:"20px"});

    $( "#menu-btn" ).click(function() {
        window.location.href= localhost+"/ChatRoom/ChatRoom.html"
    });
    $( "#chat-btn" ).click(function() {
        window.location.href= localhost+"/ChatRoom/ChatRoom.html"
    });
    $( "#exit-btn" ).click(function() {
        var email = $("#email").val();
        var password = $("#password").val();
        ExecuteRestQuery(serverURL+"UserManagement/Logout");
    });


});
