function changeImage(element) {
     element.src = element.bln ? "resources/images/kobePlay.png" : "resources/images/kobePause.png";
     element.bln = !element.bln;  /* assigns opposite boolean value always */
     return element.src;
 }



function toggleRating() {
	$('select[name="j_idt27:j_idt35"]').toggle();
	$('select[name="j_idt27:j_idt31"]').toggle();
}

function togglePlays() {
	$('select[name="j_idt27:j_idt44"]').toggle();
	$('input[name="j_idt27:playsSearch"]').toggle();
}

function toggleGenre() {
	$('input[name="j_idt27:genreSearch"]').toggle();
}

function toggleAlbumRating() {
	$('select[name="j_idt27:j_idt52"]').toggle();
	$('select[name="j_idt27:j_idt56"]').toggle();
}

function toggleArtistRating() {
	$('select[name="j_idt27:j_idt65"]').toggle();
	$('select[name="j_idt27:j_idt69"]').toggle();
}

function toggleYear() {
	$('select[name="j_idt27:j_idt78"]').toggle();
	$('input[name="j_idt27:yearSearch"]').toggle();
}

function toggleLength() {
	$('select[name="j_idt27:j_idt84"]').toggle();
	$('input[name="j_idt27:lengthSearch"]').toggle();
}
