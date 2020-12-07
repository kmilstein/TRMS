window.onload = function() {
	let xhr = new XMLHttpRequest();
	let url = "/training";

	xhr.onreadystatechange = function() {
		switch (xhr.readyState) {
			case 4:
				if (xhr.status === 200) {
					let trainingList = JSON.parse(xhr.responseText);
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

	let status = 'Submitted';
	if (mytraining.trainingStatus == 7) {
		status = 'Denied';
	} else if (mytraining.trainingStatus == 6) {
		status = 'Complete';
	} else if (mytraining.trainingStatus == 5) {
		status = 'Grade submitted';
	} else if (mytraining.trainingStatus == 4) {
		status = 'Ben Co Approved';
	} else if (mytraining.trainingStatus == 3) {
		status = 'Dept Head Approved';
	} else if (mytraining.trainingStatus == 2) {
		status = 'Supervisor Approved';
	} else if (mytraining.trainingStatus == 1) {
		status = 'Submitted';
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
	statusIcon.innerHTML = " Submit Grade";
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

	let trainingId = mytraining.trainingId;

	deleteLink.setAttribute('href', '#');
	updateLink.setAttribute('href', '#');
	statusLink.setAttribute('href', '/grade.html');


}
