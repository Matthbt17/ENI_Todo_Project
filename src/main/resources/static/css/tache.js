const borderForm = document.getElementById('borderForm');
const btnExtend = document.getElementById('showFormBtn');
let visible = false;


btnExtend.addEventListener("click", (event) => {
	if (visible) {
		borderForm.style.visibility = 'hidden';
		borderForm.style.opacity = '0%'
		visible = false;
	} else {
		borderForm.style.visibility = 'visible';
		borderForm.style.opacity = '100%'
		visible = true;
	}
})


// INFOBULLE

const infobulles = document.querySelectorAll(".infobulle");
const infobulleTexts = document.querySelectorAll(".infobulleText");

infobulles.forEach((infobulle, index) => {
	const infobulleText = infobulleTexts[index];

	infobulle.addEventListener("mouseenter", (event) => {
		infobulleText.style.visibility = 'visible';
		infobulleText.style.opacity = '100%';
		console.log(infobulleText);
	});

	infobulle.addEventListener("mouseout", (event) => {
		infobulleText.style.visibility = 'hidden';
		infobulleText.style.opacity = '0%';
	});
});