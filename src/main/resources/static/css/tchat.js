
const formtchat = document.getElementById('formtchat');

formtchat.addEventListener('keypress', (event) => {
	if (event.keyCode == 13) {
		event.preventDefault();
		formtchat.submit();
	}
});