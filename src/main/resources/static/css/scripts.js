	const formulaire = document.getElementById("sectionFormulaire");
	const main = document.getElementById("mainBlock");
	const addProjetCard = document.getElementById("addProject");
	const projetContainer = document.getElementById("projetContainer");
	let isVisible = false;

	addProjetCard.addEventListener("click", (event) => {
		if(!isVisible){
			main.style.display='flex';
			formulaire.style.display='flex';
		    formulaire.style.visibility='visible';
		    formulaire.style.opacity='100%';
		    projetContainer.style.width='60%'
		    isVisible = true;
		} else {
			main.style.display='block';
			formulaire.style.display='none';
		    formulaire.style.visibility='hidden';
		    formulaire.style.opacity='0%';
		    projetContainer.style.width='80%';
		    projetContainer.style.margin='auto';
		    projetContainer.style.marginTop='2%';
		    isVisible = false;
		}
	});