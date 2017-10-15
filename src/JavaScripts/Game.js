$(function () {

    var b = jsboard.board({attach:"game", size:"20x30", style:"checkerboard"});
    b.cell("each").style({width:"20px", height:"20px"});

});
