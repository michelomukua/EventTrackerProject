window.addEventListener('load', function(e) {
	console.log('document loaded');
	init();
});

function init() {
	getWorkoutList();


	document.addWorkoutForm.addWorkoutButton.addEventListener('click', function(event) {
		event.preventDefault();

		let wdate = new Date(document.addWorkoutForm.workoutDate.value);
		wdate = wdate.toISOString().substring(0, 19);
		let newWorkout = {
			member: {
				id: document.addWorkoutForm.memberId.value,
			},
			activity: {
				id: document.addWorkoutForm.activityId.value,
			},
			duration: document.addWorkoutForm.duration.value,
			workoutDate: wdate,
			comment: document.addWorkoutForm.comment.value,
		};
		addWorkout(newWorkout);
		//window.location.reload();
	});

	const displayTable = document.getElementById("workoutTable");
	const form = document.getElementById("updateWorkoutForm");

	displayTable.addEventListener("click", function(event)  {
		console.log(event.target.tagName);
		if (event.target.tagName === "TD") {
			const row = event.target.parentElement;
			const id = row.cells[0].textContent;
			const memberId = row.cells[7].textContent;
			const activityId = row.cells[8].textContent;
			const duration = row.cells[5].textContent;
			const workoutDate = row.cells[4].textContent;
			const comment = row.cells[6].textContent;

			form.id.value = id;
			form.memberId.value = memberId;
			form.activityId.value = activityId;
			form.duration.value = duration;
			form.workoutDate.value = workoutDate.substring(0, 10);
			form.comment.value = comment;
		}
	});

	document.updateWorkoutForm.updateWorkoutButton.addEventListener('click', function(event) {
		event.preventDefault();

		let wdate = new Date(document.updateWorkoutForm.workoutDate.value);
		wdate = wdate.toISOString().substring(0, 19);
		let workout = {
			id: document.updateWorkoutForm.id.value,
			member: {
				id: document.updateWorkoutForm.memberId.value,
			},
			activity: {
				id: document.updateWorkoutForm.activityId.value,
			},
			duration: document.updateWorkoutForm.duration.value,
			workoutDate: wdate,
			comment: document.updateWorkoutForm.comment.value,
		};
		updateWorkout(workout);
	});

	document.updateWorkoutForm.deleteWorkoutButton.addEventListener('click', function(event) {
		event.preventDefault();

		let deleteId = document.updateWorkoutForm.id.value;

		deleteWorkout(deleteId);
	});

}



function displayError(errorMessage) {
	let dataDIv = document.getElementById('errorData');
	dataDIv.textContent = '';
	let h1 = document.createElement('h1');
	h1.textContent = errorMessage;
	dataDIv.appendChild(h1);
}

function displayTotal(total) {
	let dataDIv = document.getElementById('ActivityData');
	dataDIv.textContent = '';
	let h5 = document.createElement('h5');
	h5.textContent = total;
	dataDIv.appendChild(h5);
}

function getTotal() {

	let xhr = new XMLHttpRequest();
	let url = 'api/workouts/total';
	xhr.open('GET', url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200) {
				let data = xhr.responseText;
				let total = data;
				displayTotal(total);
			} else if (xhr.status === 404) {
				console.error();
				displayError();
			} else {
				console.error('Error retrieving workout Total');
			}
		}
	}
	xhr.send();
}


function getWorkoutList() {

	let xhr = new XMLHttpRequest();
	//let url = 'api/films/' + filmId + '/actors';
	let url = `api/workouts`;
	xhr.open('GET', url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status === 200) {
				// * On success, if a response was received parse the film data
				//   and pass the film object to displayFilm().
				let data = xhr.responseText;
				//console.log(data);
				let workouts = JSON.parse(xhr.responseText);
				displayWorkouts(workouts);
			} else if (xhr.status === 404) {
				console.error('Film not found');
				displayError('Film not found');
				//console.log(data);
			} else {
				// * On failure, or if no response text was received, put "Film not found" 
				//   in the filmData div.
				console.error('Error retrieving workouts ' + workoutId + ":" + xhr.status);
			}
		}
	}
	xhr.send();
	getTotal();

}
function displayWorkouts(workouts) {
	let table = document.getElementById("workoutTable");
	let tbody = document.createElement("tbody");
	for (let workout of workouts) {

		let tr = document.createElement("tr");

		let tdid = document.createElement('td');
		tdid.textContent = workout.id;
		tr.appendChild(tdid);

		let tdfname = document.createElement('td');
		tdfname.textContent = workout.member.firstName;
		tr.appendChild(tdfname);

		let tdlname = document.createElement('td');
		tdlname.textContent = workout.member.lastName;
		tr.appendChild(tdlname);

		let tdactivity = document.createElement('td');
		tdactivity.textContent = workout.activity.name;
		tr.appendChild(tdactivity);

		let tddate = document.createElement('td');
		tddate.textContent = workout.workoutDate;
		tr.appendChild(tddate);

		let tdduration = document.createElement('td');
		tdduration.textContent = workout.duration;
		tr.appendChild(tdduration);

		let tdcomment = document.createElement('td');
		tdcomment.textContent = workout.comment;
		tr.appendChild(tdcomment);

		let tdmemberid = document.createElement('td');
		tdmemberid.className = "hidden-col";
		tdmemberid.textContent = workout.member.id;
		tr.appendChild(tdmemberid);

		let tdactivityid = document.createElement('td');
		tdactivityid.className = "hidden-col";
		tdactivityid.textContent = workout.activity.id;
		tr.appendChild(tdactivityid);

		tbody.appendChild(tr);

	}

	table.appendChild(tbody);

}

function addWorkout(newWorkout) {
	let url = 'api/workouts';
	let xhr = new XMLHttpRequest();
	xhr.open('POST', url)

	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			if (xhr.status == 201) {
				let createdWorkout = JSON.parse(xhr.responseText);
				if (createdWorkout.id > 0) {
					window.location.reload();
				}

			}
			else {
				displayError('Error creating workout');
			}
		}
	};

	xhr.setRequestHeader("Content-type", "application/json");
	let workoutJson = JSON.stringify(newWorkout);
	xhr.send(workoutJson);
	//return refresh;
}
function updateWorkout(updateWorkout) {
	let url = 'api/workouts/' + updateWorkout.id;
	let xhr = new XMLHttpRequest();
	xhr.open('PUT', url)

	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			console.log(xhr.status);
			if (xhr.status == 200) {
				let updatedWorkout = JSON.parse(xhr.responseText);
				if (updatedWorkout.id > 0) {
					window.location.reload();
				}

			}
			else {
				displayError('Error updating workout');
			}
		}
	};

	xhr.setRequestHeader("Content-type", "application/json");
	let workoutJson = JSON.stringify(updateWorkout);
	console.log(workoutJson);
	xhr.send(workoutJson);
}

function deleteWorkout(deleteId) {
	let url = 'api/workouts/' + deleteId;
	let xhr = new XMLHttpRequest();
	xhr.open('DELETE', url)

	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			console.log(xhr.status);
			if (xhr.status == 200) {
				let result = JSON.parse(xhr.responseText);
				if (result) {
					window.location.reload();
				}

			}
			else {
				displayError('Error deleting workout');
			}
		}
	};

	xhr.send();
}

