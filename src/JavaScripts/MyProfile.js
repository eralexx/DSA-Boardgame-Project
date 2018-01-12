$(function() {
    var localhost= window.location.href.split("src")[0]+"src";
    var serverURL="http://localhost:8081/rest/";
    var User = {};
    var UserEmail = localStorage.getItem("MazeGameEmail")

     ExecuteRestQuery(serverURL+"UserManagement/GetUserInfo/"+UserEmail)
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
})