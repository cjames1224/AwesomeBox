function changeImage(element) {
     element.src = element.bln ? "resources/images/kobePlay.png" : "resources/images/kobePause.png";
     element.bln = !element.bln;  /* assigns opposite boolean value always */
 }

function toggleRating(className) {

	
}

function toggle_visibility(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
       e.style.display = 'none';
    else
       e.style.display = 'block';
 }