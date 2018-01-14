var localGame ={};
var gettingGame= false;
localGame.isMyTurn=false;
localGame.canMoveNorth=false;
localGame.canMoveSouth=false;
localGame.canMoveEast=false;
localGame.canMoveWest=false;


$(function () {
    var localhost= window.location.href.split("src")[0]+"src";
    var serverURL="http://localhost:8081/rest/";
    var User = {};
    var status = 1;

    window.setInterval( attemptToGetGame, 5000);

    $(document).keydown(function(e) {
        switch (e.which) {
        case 37:
            if (localGame.isMyTurn){
               if (localGame.canMoveWest){
                   ExecuteRestQuery(serverURL+"Game/Move/"+localStorage.getItem("MazeGameEmail")+"/W")
                   .then(function(data){
                        console.log("Moving West.");
                        gettingGame=true;
                        clearBoard();
                        localGame.isMyTurn = (User.email == data.playerTurn.email);
                        var a=-1;
                        for(var i = 0; i<data.nPlayers; i++){
                           if (data.players[i].email==User.email)
                           a=i;
                        }
                        if(a>=0){
                            localGame.canMoveNorth=data.board.positions[a].moves.includes('N');
                            localGame.canMoveSouth=data.board.positions[a].moves.includes('S');
                            localGame.canMoveEast=data.board.positions[a].moves.includes('E');
                            localGame.canMoveWest=data.board.positions[a].moves.includes('W');
                        }

                        localGame.players= data.players;
                        localGame.nPlayers= data.nPlayers;
                        localGame.board = data.board;
                        localGame.boardSize = data.board.cells.length;
                        localGame.actualPositions= data.board.positions;
                        localGame.winningCell =  data.board.winningCell;
                        printAgainstPlayers();
                        printPlayingBoard();
                        console.log("game updated.");
                        break;
                   })
               }
            }  break;//left arrow key

        case 38:
            if (localGame.isMyTurn){
                if (localGame.canMoveNorth){
                   ExecuteRestQuery(serverURL+"Game/Move/"+localStorage.getItem("MazeGameEmail")+"/N")
                   .then(function(data){
                        console.log("Moving North.");
                        gettingGame=true;
                        clearBoard();
                        localGame.isMyTurn = (User.email == data.playerTurn.email);
                        var a=-1;
                        for(var i = 0; i<data.nPlayers; i++){
                           if (data.players[i].email==User.email)
                           a=i;
                        }
                        if(a>=0){
                            localGame.canMoveNorth=data.board.positions[a].moves.includes('N');
                            localGame.canMoveSouth=data.board.positions[a].moves.includes('S');
                            localGame.canMoveEast=data.board.positions[a].moves.includes('E');
                            localGame.canMoveWest=data.board.positions[a].moves.includes('W');
                        }

                        localGame.players= data.players;
                        localGame.nPlayers= data.nPlayers;
                        localGame.board = data.board;
                        localGame.boardSize = data.board.cells.length;
                        localGame.actualPositions= data.board.positions;
                        localGame.winningCell =  data.board.winningCell;
                        printAgainstPlayers();
                        printPlayingBoard();
                        console.log("game updated.");

                   })
                }
            }; break; //up arrow key

        case 39:
            if (localGame.isMyTurn){
                if (localGame.canMoveEast){
                   ExecuteRestQuery(serverURL+"Game/Move/"+localStorage.getItem("MazeGameEmail")+"/E")
                   .then(function(data){
                        console.log("Moving East.");
                        gettingGame=true;
                        clearBoard();
                        localGame.isMyTurn = (User.email == data.playerTurn.email);
                        var a=-1;
                        for(var i = 0; i<data.nPlayers; i++){
                           if (data.players[i].email==User.email)
                           a=i;
                        }
                        if(a>=0){
                            localGame.canMoveNorth=data.board.positions[a].moves.includes('N');
                            localGame.canMoveSouth=data.board.positions[a].moves.includes('S');
                            localGame.canMoveEast=data.board.positions[a].moves.includes('E');
                            localGame.canMoveWest=data.board.positions[a].moves.includes('W');
                        }

                        localGame.players= data.players;
                        localGame.nPlayers= data.nPlayers;
                        localGame.board = data.board;
                        localGame.boardSize = data.board.cells.length;
                        localGame.actualPositions= data.board.positions;
                        localGame.winningCell =  data.board.winningCell;
                        printAgainstPlayers();
                        printPlayingBoard();
                        console.log("game updated.");

                   })
                }
            }      break;//right arrow key

        case 40:
            if (localGame.isMyTurn){
                if (localGame.canMoveSouth){
                    ExecuteRestQuery(serverURL+"Game/Move/"+localStorage.getItem("MazeGameEmail")+"/S")
                    .then(function(data){
                        console.log("Moving South.");

                        gettingGame=true;
                        clearBoard();
                        localGame.isMyTurn = (User.email == data.playerTurn.email);
                        var a=-1;
                        for(var i = 0; i<data.nPlayers; i++){
                           if (data.players[i].email==User.email)
                           a=i;
                        }
                        if(a>=0){
                            localGame.canMoveNorth=data.board.positions[a].moves.includes('N');
                            localGame.canMoveSouth=data.board.positions[a].moves.includes('S');
                            localGame.canMoveEast=data.board.positions[a].moves.includes('E');
                            localGame.canMoveWest=data.board.positions[a].moves.includes('W');
                        }
                        localGame.players= data.players;
                        localGame.nPlayers= data.nPlayers;
                        localGame.board = data.board;
                        localGame.boardSize = data.board.cells.length;
                        localGame.actualPositions= data.board.positions;
                        localGame.winningCell =  data.board.winningCell;
                        printAgainstPlayers();
                        printPlayingBoard();
                        console.log("game updated.");

                    })
                }
            }
              break;//bottom arrow key

        }
    })

    /*getRandomGame()
    .then(function(data){
        localGame.players= data.players;
        localGame.nPlayers= data.nPlayers;
        localGame.board = data.board;
        localGame.boardSize = data.board.cells.length;
        localGame.actualPositions= data.board.positions;
        localGame.winningCell =  data.board.winningCell;
        printAgainstPlayers();
        printPlayingBoard();
        console.log("Got a random game");
    });*/
    getAllUserInfo();
    function getAllUserInfo(){
        ExecuteRestQuery(serverURL+"UserManagement/GetUserInfo/"+localStorage.getItem("MazeGameEmail"))
        .then(function(data){
            User = data;
            $("#player-name")[0].innerText=User.userName;
            $("#player-wins")[0].innerText=User.gamesWon+" wins";
            if (User.imagePath!= null && User.imagePath != ""){
                 $("#playerimage").find("img").attr('src', User.imagePath);
            }
        });
    }
    function getRandomGame(){
    var defer= $.Deferred();
        ExecuteRestQuery(serverURL+"Game/GetRandomGame").then(function(data){
        defer.resolve(data);
        })
        return defer.promise()
    }

    function printAgainstPlayers(){
        var enemyPlayerNames = [];
        for ( var i =0; i<localGame.players.length; i++){
             if (localGame.players[i].email != localStorage.getItem("MazeGameEmail")){
             enemyPlayerNames.push(localGame.players[i].userName);
             }
        }
        //jQuery to print that names on screen;
    }

    function JoinQueue(){
        ExecuteRestQuery(serverURL+"Game/JoinQueue/"+localStorage.getItem("MazeGameEmail"));
        clearBoard();
        $("#game").append("<div id='queueText' style='width:200px; color: red; margin:0 auto;'><h2>In queue...</h2></div>");
    }

    function printPlayingBoard(){
        console.log("Drawing board...");

        localGame.cellSizeX= ($("#game")[0].clientWidth - (localGame.boardSize+1)*2)/localGame.boardSize;
        localGame.cellSizeY= ($("#game")[0].clientHeight - (localGame.boardSize+1)*2)/localGame.boardSize ;
        $("#queueText").remove();
        $("#game").append("<div id='upperBorder' style='background-color: black; width:"+$("#game")[0].clientWidth+"px; height:2px'></div>");
        //$("#game").append("<div id='leftBorder' style='width:2px; height:"+$("#game")[0].clientHeight+"'></div>");
        for (var i=0; i<localGame.boardSize; i++){
            $("#game").append("<div id='fila"+i+"' style='width:"+$("#game")[0].clientWidth+"px; height:"+localGame.cellSizeY+"px'></div>");
            //$("#game").append("<div id='interfila"+i+"' style='background-color: black; width:"+$("#game")[0].clientWidth+"px; height:2px'></div>");
            for (var b=0; b<localGame.boardSize; b++){
                $("#game").append("<div id='interfila"+b+i+"' style='display: inline-block; background-color: black; width:"+localGame.cellSizeX+"px; height:2px'></div>");
                $("#game").append("<div id='corner"+b+i+"' style='display: inline-block; background-color: black; width:2px; height:2px'></div>");
            }
            for (var a=0; a<localGame.boardSize; a++){
                $('#fila'+i).append("<div id='celda"+a+i+"' style='display: inline-block; width:"+localGame.cellSizeX+"; height:"+localGame.cellSizeY+"'></div>");
                $('#fila'+i).append("<div id='intercolumna"+a+i+"' style='background-color: black; display: inline-block; width:2px; height:"+localGame.cellSizeY+"'></div>");

            }
        }
        HideNecessaryLines();
        printStartingPlayersPositionsAndMeta();
   }
    function attemptToGetGame(){
    if (gettingGame){
        ExecuteRestQuery(serverURL+"Game/AttemptToGetGame/"+localStorage.getItem("MazeGameEmail"))
        .then(function(data){
            clearBoard();
            if (data.winner==null || data.winner ==""){
                localGame.isMyTurn = (User.email == data.playerTurn.email);
                gettingGame=true;
                var a=-1;
                for(var i = 0; i<data.nPlayers; i++){
                   if (data.players[i].email==User.email)
                   a=i;
                }
                if(a>=0){
                    localGame.canMoveNorth=data.board.positions[a].moves.includes('N');
                    localGame.canMoveSouth=data.board.positions[a].moves.includes('S');
                    localGame.canMoveEast=data.board.positions[a].moves.includes('E');
                    localGame.canMoveWest=data.board.positions[a].moves.includes('W');
                }

                localGame.players= data.players;
                localGame.nPlayers= data.nPlayers;
                localGame.board = data.board;
                localGame.boardSize = data.board.cells.length;
                localGame.actualPositions= data.board.positions;
                localGame.winningCell =  data.board.winningCell;
                printAgainstPlayers();
                printPlayingBoard();
                console.log("Got a LEGIT! game");
            }
            else{
                console.log("Destroyng game... " + data.winner + " won.")
                gettingGame=false;
                localGame={};
                GameOver(data.winner);
                ExecuteRestQuery(serverURL+"Game/DestroyGame/"+localStorage.getItem("MazeGameEmail"));
                getAllUserInfo();
                }
            });
        }
    }
    function GameOver(winner){
        if (winner==User.email){
            $("#game").append("<div id='winnerText' style='width:200px; color: green; margin:0 auto;'><h1>YOU WON</h1></div>");
        }
        else{
            $("#game").append("<div id='loserText' style='width:200px; color: red; margin:0 auto;'><h2>Loser...</h2></div>");
        }
    }
    function clearBoard(){
        $("#game")[0].innerHTML="";
    }

   function printStartingPlayersPositionsAndMeta(){
         $("#celda"+localGame.winningCell.posX+localGame.winningCell.posY).css("background-image", "url(/WebAppDSA/src/Game/finsih.jpg)");
         $("#celda"+localGame.winningCell.posX+localGame.winningCell.posY).css("background-size", "cover");
         for( var i = 0; i<localGame.actualPositions.length; i++){
            printPlayer(i, localGame.players[i], localGame.actualPositions[i].posX, localGame.actualPositions[i].posY);
         }
    };

    function printPlayer(n, player, x, y){
        var imageUrl= "";
        if (player.imagePath==null || player.imagePath ==""){
             var imageUrl="http://www.free-icons-download.net/images/anonymous-user-icon-80332.png";
        }
        else{
            var imageUrl=player.imagePath;
        }
        $("#celda"+x+y)[0].innerHTML="<div id='player"+n+"'><img style='width:30px; height:30px;' src='"+imageUrl+"'<p>Player "+(n+1)+"<br>"+player.userName+"</></div>";
             //$("#celda"+x+y).css("background-size", "cover");
             $("#player"+n).css("position", "absolute");
             //$("#player"+n).css("max-height", "100%");

    }

   function HideNecessaryLines(){
       var cells = localGame.board.cells;
       for(var i=0; i<cells.length; i++){
            a=0;
            for(var a=0; a<cells[i].item.length; a++){
                if (cells[i].item[a].moves.includes("E")){
                HideRight(i,a);
                }
                if (cells[i].item[a].moves.includes("S")){
                HideBottom(i,a);
                }
           }
       }
   }
   function HideRight(a, b){
        $("#intercolumna"+a+b).css({backgroundColor: 'white'});
   }

   function HideBottom(a, b){
        $("#interfila"+a+b).css({backgroundColor: 'white'});
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
    $("audio")[0].play();

    $( "#chat-btn" ).click(function() {
        window.location.href= localhost+"/ChatRoom/ChatRoom.html"
    });

    $( "#music-btn" ).click(function() {

        if (status==1){
            $("audio")[0].pause();
            status=0;
         }
         else{
            $("audio")[0].play();
            status=1;
         }
    });

    $( "#stats-btn" ).click(function() {
            window.location.href= localhost+"/Game/Stats.html"
    });
    $( "#profile-btn" ).click(function() {
                window.location.href= localhost+"/Game/MyProfile.html"
        });

    $( "#queue-btn" ).click(function() {
           localGame={};
           JoinQueue();
           gettingGame = true;
    });

    $( "#exit-btn" ).click(function() {
        var email = $("#email").val();
        var password = $("#password").val();
        ExecuteRestQuery(serverURL+"UserManagement/Logout");
    });
});
