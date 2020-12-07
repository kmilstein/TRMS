window.onload = function() {
	let path = window.location.search;
	let error = document.getElementById("error");
	if (path === '?error=failed-login') {
		error.innerHTML = 'Correct Username and Password';
	}
}