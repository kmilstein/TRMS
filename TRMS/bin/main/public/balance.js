window.onload = function () {
	let xhr = new XMLHttpRequest();
    let url = "/funds";
    
    xhr.onreadystatechange = function () {
        switch (xhr.readyState) {
            case 4:
                if (xhr.status === 200) {
                    let funds = JSON.parse(xhr.responseText);
                    setBalance(funds);
                }
                break;
        }
    }
    xhr.open("GET", url, true);
    xhr.send();
}

let setBalance = function(funds) {
	let span = document.getElementById("balance");
	console.log(funds);
	span.innerHTML = funds;

}