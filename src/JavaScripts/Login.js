$(function () {
    var serverURL="http://localhost:8081/rest/";
    $( "#login-btn" ).click(function() {
        var email = $("#email").val();
        var password = $("#password").val();
        ExecuteRestQuery(serverURL+"UserManagement?"+email+";"+password);
    });

    $( "#register-btn" ).click(function() {
        var email = $("#email").val();
        var password = $("#password").val();
        ExecuteRestQuery(serverURL+"UserManagement/Register?"+email+";"+password);
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
