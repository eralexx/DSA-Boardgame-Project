$(function () {

    var b = jsboard.board({attach:"game", size:"25x40", style:"checkerboard"});
    b.cell("each").style({width:"20px", height:"20px"});

});
