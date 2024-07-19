const borderForm = document.getElementById('borderForm');
const btnExtend = document.getElementById('showFormBtn');
const codeAffiche = document.getElementById('codeAffiche');
const showAddBtn = document.getElementById('showAddBtn');
let visible = false;
let visibleForm = false;


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

showAddBtn.addEventListener("click", (event) => {
	if (visibleForm) {
		codeAffiche.style.visibility = 'hidden';
		codeAffiche.style.opacity = '0%'
		visibleForm = false;
	} else {
		codeAffiche.style.visibility = 'visible';
		codeAffiche.style.opacity = '100%'
		visibleForm = true;
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