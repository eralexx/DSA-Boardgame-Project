$(function () {
    var serverURL="http://2.152.165.114:8081/rest/";
    var localhost= window.location.href.split("src")[0]+"src";
    document.body.style.backgroundImage = "url('https://i.pinimg.com/originals/1d/b4/7d/1db47d885bd31d1f96a5bb590f850284.jpg')";
    $( "#login-btn" ).click(function() {
        var email = $("#email").val();
        var password = $("#password").val();
        if (email != "" && password != ""){
            ExecuteRestQuery(serverURL+"UserManagement/Login/"+email+"/"+password)
            .then(function(data){
                   if (data==0){
                   localStorage.setItem("MazeGameEmail", email);
                   window.location.href=localhost+"/Game/GameWindow.html";
                   }
                   else{
                   alert("Username or password incorrect.");
                   }
            });
        }
        else{
        alert("Please fill the entire form");
        }
    });

    $( "#register-btn" ).click(function() {
        window.location.href= localhost+"/Login/Register.html"
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
