$(function () {
    var serverURL="http://localhost:8081/rest/";
    var localhost= window.location.href.split("src")[0]+"src";
    document.body.style.backgroundImage = "url('https://i.pinimg.com/originals/1d/b4/7d/1db47d885bd31d1f96a5bb590f850284.jpg')";


    $( "#register-btn" ).click(function() {
        var email = $("#email").val();
        var password = $("#password").val();
        if (email != "" && password != ""){
            ExecuteRestQuery(serverURL+"UserManagement?"+email+";"+password);
        }
        else{
        alert("Please fill the entire form");
        }
    });

    $( "#login-btn" ).click(function() {
        window.location.href= localhost+"/Login/Login.html"
    });





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
});