$(function () {
    var serverURL="http://2.152.165.114:80/rest/";
    var localhost= window.location.href.split("src")[0]+"src";
    document.body.style.backgroundImage = "url('https://i.pinimg.com/originals/1d/b4/7d/1db47d885bd31d1f96a5bb590f850284.jpg')";


    $( "#register-btn" ).click(function() {
        var email = $("#email").val();
        var username = $("#username").val();
        var password = $("#password").val();

        if (email != "" && password != ""){
            ExecuteRestQuery(serverURL+"UserManagement/Register/"+email+"/"+username+"/"+password)
            .then(function(data){
                if (data ==0){
                alert("Registration successful.");
                window.location.href= localhost+"/Login/Login.html";
                }
                else{
                alert("Registration error.");
                window.location.href= localhost+"/Login/Register.html";
                }

            });

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
});