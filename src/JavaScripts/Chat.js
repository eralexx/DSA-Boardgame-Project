$(function () {
    var serverURL="http://localhost:8080/rest/";
    var localhost= window.location.href.split("src")[0]+"src";

    var loopFunction = function() {
        GetNewMessages()
            .then(function(data)
            {
                RefreshMessages(data);
            });
    }; window.setInterval(loopFunction, 3000);
    loopFunction();
    $( "#back-btn" ).click(function() {
        window.location.href= localhost+"/Game/GameWindow.html"
    });

    window.setInterval(loopFunction, 3000);
    loopFunction();

    function GetNewMessages(){
        var defer = $.Deferred();
        ExecuteRestQuery(serverURL+"Chat/GetChatMessages")
            .then(function(data){
                defer.resolve(data);
            });
        return defer.promise();
    }

    function RefreshMessages(dataArray){
        for(var i=1; i<dataArray.length; i++){
            var selector= "mensaje"+ i.toString();
            $(selector).innerText=dataArray[i];
        }
    }

    function ExecuteRestQuery(url) {
        var defer = $.Deferred();
        $.ajax({
            url: url,
            type: "GET",
            headers: {
                "Accept": "application/json;odata=verbose",
            },
            success: function (data, textStatus, jqXHR) {
                defer.resolve(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                defer.reject(jqXHR);
            }
        });
        return defer.promise();
    }

    $( "#send-btn" ).click(function() {
        var message = $("#input").val();
        ExecuteRestQuery(serverURL+"AddMessage?message="+message);
    });

});
