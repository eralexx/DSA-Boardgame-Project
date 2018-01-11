$(function () {
    var serverURL="http://localhost:8081/rest/";
    var localhost= window.location.href.split("src")[0]+"src/";
    var User = {};

    var loopFunction = function() {
        GetNewMessages()
            .then(function(data)
            {
                RefreshMessages(data);
            });
    }; window.setInterval(loopFunction, 3000);
    loopFunction();

    $( "#back-btn" ).click(function() {
        window.location.href= localhost+"Game/GameWindow.html"
    })

    $( "#send-btn" ).click(function() {
        var input = $("#input").val();
        var mail =  localStorage.getItem("MazeGameEmail");
        ExecuteRestQuery(serverURL+"ChatWindow/AddMessage/" + mail + "/" + input);
         var input = $("#input").val("");
    });

    window.setInterval(loopFunction, 3000);
    loopFunction();

    function GetNewMessages(){
        var defer = $.Deferred();
        ExecuteRestQuery(serverURL+"ChatWindow/GetAllMessages")
            .then(function(data){
                defer.resolve(data.messages);
            });
        return defer.promise();
    }



    function RefreshMessages(dataArray){
        $("#ChatBox > span")[0].innerHTML="";
        for(var i=0; i<dataArray.length; i++){
            $("#ChatBox > span").append(dataArray[i]+"<br>");
        }
        return;
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
});
