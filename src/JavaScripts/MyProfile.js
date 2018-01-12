$(function() {
    var localhost= window.location.href.split("src")[0]+"src";
    var serverURL="http://localhost:8081/rest/";
    var User = {};
    var UserEmail = localStorage.getItem("MazeGameEmail")

     ExecuteRestQuery(serverURL+"UserManagement/GetUserInfo/"+UserEmail)
        .then(function(data){
        User =  data;

        $("#UserName").append(User.userName);
        $("#Id").append(User.id);
        $("#Email").append(User.email);
        $("#Password").append(User.password);
        if (User.ImagePath==null){
            $("#imagePlaceholder").append("<img src='http://www.free-icons-download.net/images/anonymous-user-icon-80332.png'>");
        }
        else{
           $("#imagePlaceholder").append("<img src="+User.ImagePath+" alt='Girl in a jacket'>")
        }

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
})