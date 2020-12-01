window.onload = function () {
    let xhr = new XMLHttpRequest();
    const url = "http://localhost:9090/training";
    
    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        switch (xhr.readyState) {
            case 4:
                if (xhr.status === 200) {
                    let trainingList = JSON.parse(xhr.responseText);
                    console.log(trainingList);
                    console.log(trainingList[0]);
                    trainingList.forEach(element => {
                        addRow(element);
                    });
                }
                break;
        }
    }
    xhr.open("GET", url, true);
    xhr.send();
}

let addRow = function(mytraining) {
    let container = document.getElementById("card-holder");
    let card = document.createElement("div");
    let row = document.createElement("div");
    let statusCol = document.createElement("div");
    let span = document.createElement("span");
    let infoCol = document.createElement("div");
    let description = document.createElement("h3");
    let reason = document.createElement("p");
    let buttonCol = document.createElement("div");
    let buttons = document.createElement("ul");
    let statusLink = document.createElement("a");
    let statusButton = document.createElement("li");
    let statusIcon = document.createElement("i");
    let updateLink = document.createElement("a");
    let updateButton = document.createElement("li");
    let updateIcon = document.createElement("i");
    let deleteLink = document.createElement("a");
    let deleteButton = document.createElement("li");
    let deleteIcon = document.createElement("i");
    let status = 'Pending';
    if (mytraining.trainingStatus < 5) {
        status = 'Pending';
    } else {
        status = 'complete';
    }

    deleteButton.appendChild(deleteIcon);
    deleteLink.appendChild(deleteButton);
    updateButton.appendChild(updateIcon);
    updateLink.appendChild(updateButton);
    statusButton.appendChild(statusIcon);
    statusLink.appendChild(statusButton);
    buttons.appendChild(statusLink);
    buttons.appendChild(updateLink);
    buttons.appendChild(deleteLink);
    buttonCol.appendChild(buttons);
    infoCol.appendChild(description);
    infoCol.appendChild(reason);
    
    statusCol.appendChild(span);
    row.appendChild(statusCol);
    row.appendChild(infoCol);
    row.appendChild(buttonCol);
    card.appendChild(row);
    container.appendChild(card);

    deleteIcon.innerHTML = " Delete Request";
    updateIcon.innerHTML = " Update Request Info";
    statusIcon.innerHTML = " Check Status";
    span.innerHTML = status;
    console.log(mytraining.trainingInfo.description);
    reason.innerHTML = mytraining.trainingInfo.justification;
    description.innerHTML = mytraining.trainingInfo.description;
    deleteIcon.className = "fa fa-minus-circle pr-1";
    deleteButton.className = "list-group-item delete";
    updateIcon.className = "fa fa-edit pr-1";
    updateButton.className = "list-group-item update";
    statusIcon.className = "fa fa-flag-checkered pr-1";
    statusButton.className = "list-group-item board";
    buttons.className = "list-group";
    buttonCol.className = "col-md-4 d-none d-lg-block";
    infoCol.className = "col-lg-6 col-md-4 col-8";
    statusCol.className = "col-2";
    row.className = "row";
    card.className = "card card-body bg-light mb-3"

    deleteLink.setAttribute('href', '#');
    updateLink.setAttribute('href', '#');
    statusLink.setAttribute('href', '#');
    
    description.setAttribute('id', 'training-name');
}

/*
// Get all requests
let url  = "http://localhost:9090/training";
let xhr  = new XMLHttpRequest()
xhr.open('GET', url, true)
xhr.onload = function () {
	let trainings = JSON.parse(xhr.responseText);
	if (xhr.readyState == 4 && xhr.status == "200") {
		console.table(training);
	} else {
		console.error(training);
	}
}
xhr.send(null);


// Get a training
let url  = "http://localhost:9090/training";
let xhr  = new XMLHttpRequest()
xhr.open('GET', url, true)
xhr.onload = function () {
	let training = JSON.parse(xhr.responseText);
	if (xhr.readyState == 4 && xhr.status == "200") {
		console.table(training);
	} else {
		console.error(training);
	}
}
xhr.send(null);


// Post a training
let url = "http://localhost:9090/training";

let data = {};
data.firstname = "John";
data.lastname  = "Snow";
let json = JSON.stringify(data);

let xhr = new XMLHttpRequest();
xhr.open("POST", url, true);
xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
xhr.onload = function () {
	let training = JSON.parse(xhr.responseText);
	if (xhr.readyState == 4 && xhr.status == "201") {
		console.table(training);
	} else {
		console.error(training);
	}
}
xhr.send(json);


// Update a training
let url = "http://localhost:9090/training";

let data = {};
data.firstname = "John2";
data.lastname  = "Snow2";
let json = JSON.stringify(data);

let xhr = new XMLHttpRequest();
xhr.open("PUT", url, true);
xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
xhr.onload = function () {
	let training = JSON.parse(xhr.responseText);
	if (xhr.readyState == 4 && xhr.status == "200") {
		console.table(training);
	} else {
		console.error(training);
	}
}
xhr.send(json);


// Delete a training
let url = "http://localhost:9090/training";
let xhr = new XMLHttpRequest();
xhr.open("DELETE", url, true);
xhr.onload = function () {
	let training = JSON.parse(xhr.responseText);
	if (xhr.readyState == 4 && xhr.status == "200") {
		console.table(training);
	} else {
		console.error(training);
	}
}
xhr.send(null);
*/