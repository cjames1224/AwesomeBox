function changeImage(element) {
     element.src = element.bln ? "resources/images/kobePlay.png" : "resources/images/kobePause.png";
     element.bln = !element.bln;  /* assigns opposite boolean value always */
 }