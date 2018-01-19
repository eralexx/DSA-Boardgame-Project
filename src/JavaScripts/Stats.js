$(function() {
    var localhost= window.location.href.split("src")[0]+"src";
    var serverURL="http://2.152.165.114:8081/rest/";
    var User = {};
    var UserEmail = localStorage.getItem("MazeGameEmail")
    $( "#back-btn" ).click(function() {
        window.location.href= localhost+"/Game/GameWindow.html"
    })
     ExecuteRestQuery(serverURL+"UserManagement/GetUserInfo/"+UserEmail)
        .then(function(data){
        User = data;

        $("#UserName").append(User.userName);
        $("#GamesPlayed").append(User.gamesPlayed);
        $("#GamesWon").append(User.gamesWon);
        $("#WinRate").append(getWinRate(User.gamesPlayed, User.gamesWon));
        if (User.ImagePath==null){
            $("#imagePlaceholder").append("<img src='http://www.free-icons-download.net/images/anonymous-user-icon-80332.png'>");
        }
        else{
           $("#imagePlaceholder").append("<img src="+User.ImagePath+" alt='Girl in a jacket'>")
        }
    });
    ExecuteRestTextQuery(serverURL+"UserManagement/GetUserGames/"+UserEmail)
        .then(function(data){
            User = data;
            for(var i=0; i<data.length; i++){
                $("#GamesPlayedList").append("<h5 style='color: black;'>"+data[i]+"</h5>");
            }
        });
    ExecuteRestTextQuery(serverURL+"UserManagement/GetUserWins/"+UserEmail)
        .then(function(data){
            User = data;
            for(var i=0; i<data.length; i++){
                $("#GamesWonList").append("<h5 style='color: green;'>"+data[i]+"</h5>");
            }
        });
function getWinRate(x, y){
    if  (x==0){
        return "No games played yet."
    }
    else
        return (y/x*100);
}
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
    function ExecuteRestTextQuery(url) {
                var defer = $.Deferred();
                $.ajax({
                    url: url,
                    dataType: 'Text',
                    type: 'GET',
                    success: function (data) {
                        defer.resolve(data.split(';'));
                    },
                    error: function (jqXHR) {
                        defer.reject(jqXHR);
                    }
            });
            return defer.promise();
        }
})