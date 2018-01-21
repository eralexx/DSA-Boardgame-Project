$(function() {
    var localhost= window.location.href.split("src")[0]+"src";
    var serverURL="http://2.152.165.114:80/rest/";
    var User = {};
    var UserEmail = localStorage.getItem("MazeGameEmail")
    $( "#back-btn" ).click(function() {
        window.location.href= localhost+"/Game/GameWindow.html"
    })
    $( "#pwd-btn" ).click(function() {
        var x;
        var pwd=prompt("Please enter your new Password","");
        if (pwd!=null){
           ExecuteRestQuery(serverURL+"UserManagement/ChangePassword/"+UserEmail+"/"+pwd);
           x="Your password is now " + pwd + ".";
          alert(x);
       }
    })
    $( "#img-btn" ).click(function() {
       var x;
       var img=prompt("Please enter your new image path, make sure it is correct.","");
       if (img!=null){
          var data={};
          User.imagePath  =img;
          data.url= img;
          ExecutePostRestQuery(serverURL+"UserManagement/ChangeImage", User);
          x="Your profile picture has been changed.";
         alert(x);
      }
    })

     ExecuteRestQuery(serverURL+"UserManagement/GetUserInfo/"+UserEmail)
        .then(function(data){
        User = data;

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
    function ExecutePostRestQuery(url, info) {
                var defer = $.Deferred();
                $.ajax({
                    url: url,
                    dataType: 'JSON',
                    data: info,
                    type: 'POST',
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