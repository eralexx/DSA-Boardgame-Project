$(function () {
    var localhost= window.location.href.split("src")[0]+"src";
    var serverURL="http://localhost:8081/rest/";
    var User = {};
    var status = 1;

    $("audio")[0].play();

    var b = jsboard.board({attach:"game", size:"20x30", style:"checkerboard"})
    b.cell("each").style({width:"20px", height:"20px"});

    $( "#chat-btn" ).click(function() {
        window.location.href= localhost+"/ChatRoom/ChatRoom.html"
    });

    $( "#music-btn" ).click(function() {

        if (status==1){
            $("audio")[0].pause();
            status=0;
         }
         else{
            $("audio")[0].play();
            status=1;
         }
    });

    $( "#stats-btn" ).click(function() {
            window.location.href= localhost+"/Game/Stats.html"
    });
    $( "#profile-btn" ).click(function() {
                window.location.href= localhost+"/Game/MyProfile.html"
        });

    $( "#queue-btn" ).click(function() {
            window.location.href= localhost+"/ChatRoom/ChatRoom.html"
    });

    $( "#exit-btn" ).click(function() {
        var email = $("#email").val();
        var password = $("#password").val();
        ExecuteRestQuery(serverURL+"UserManagement/Logout");
    });

    ExecuteRestQuery(serverURL+"UserManagement/GetUserInfo/"+localStorage.getItem("MazeGameEmail"))
    .then(function(data){
    User =  data;
    });

    function ExecuteRestQuery(url) {
            var defer = $.Deferred();
            $.ajax({
                url: url,
                dataType: 'JSON',
                type: 'GET',
                success: function (data) {
                    defer.resolve(data);
                },
                error: function (jqXHR) {
                    defer.reject(jqXHR);
                }
        });
        return defer.promise();
    }
});
