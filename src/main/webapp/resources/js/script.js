function changeImage(element) {
     element.src = element.bln ? "resources/images/kobePlay.png" : "resources/images/kobePause.png";
     element.bln = !element.bln;  /* assigns opposite boolean value always */
 }

function toggleRating(element) {

    if (element.bln) {
        $('select').find('rating').removeClass('hiddenRating');
    }
    else {
        $('select').find('rating').addClass('hidden');
    }
    element.bln = !element.bln;
}