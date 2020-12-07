window.onload = function() {
	let xhr = new XMLHttpRequest();
	const url = "http://localhost:9090/employee";

	xhr.onreadystatechange = function() {
		switch (xhr.readyState) {
			case 4:
				if (xhr.status === 200) {
					let employeeList = JSON.parse(xhr.responseText);
					console.log(employeeList);
					employeeList.forEach(element => {
						addRow(element);
					});
				}
				break;

		}
	}
	xhr.open("GET", url, true);
	xhr.send();
}

let addRow = function(myEmployee) {
	let table = document.getElementById("guest-table");
	let tableRow = document.createElement("tr");
	let nameCol = document.createElement("td");
	let emailCol = document.createElement("td");
	let roleCol = document.createElement("td");

	tableRow.appendChild(nameCol);
	tableRow.appendChild(emailCol);
	tableRow.appendChild(roleCol);
	table.appendChild(tableRow);

	nameCol.innerHTML = myEmployee.firstName + ' ' + myEmployee.lastName;
	emailCol.innerHTML = myEmployee.email;
	roleCol.innerHTML = myEmployee.role;

	nameCol.className = "table-style";
	emailCol.className = "table-style";
	roleCol.className = "table-style";
	tableRow.className = "table-style";
}