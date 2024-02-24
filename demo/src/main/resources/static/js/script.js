window.onload = onLoadFunctions;
src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js">

function onLoadFunctions() {
    window.location.href = '/students/view';
}

//I struggled a lot with this function and had to find help online with how to go about it, but the code I wrote
//is still my own. My main idea was to have each student populate a modal with all their info displayed everytime you click
//on the students rectangle. It was a big learning curve working with async functions and various other functions in here.
async function toggleModal(studentId) {
    console.log("toggleModal called for student " + `${studentId}`);
    try {
        const response = await fetch(`/students/${studentId}`);
        if (response.ok) {
            const student = await response.json();
            document.getElementById("studentDetailsModalLabel").textContent= student.name;
            // Now that I have the student, populate the modal with the student info
            const modalBody = document.getElementById('studentModalBody');
            modalBody.innerHTML = `
                <p>Major: ${student.major}</p>
                <p>GPA: ${student.gpa}</p>
                <p>Height: ${student.height}</p>
                <p>Weight: ${student.weight}</p>
                <p>Hair Color: ${student.hair_color}</p>`;
            // Display the modal
            new bootstrap.Modal(document.getElementById('studentDetailsModal')).show();
            const deleteButton = document.getElementById('dlt-btn');

            // Assign an event listener for the delete operation
            var form = document.getElementById('dlt-btn');
            form.setAttribute('action', `/students/${studentId}/delete`);

            var editButton = document.getElementById('edit-btn');
            editButton.addEventListener('click', function() {
                window.location.href = `/students/${studentId}/edit`;
            });
        }
    //Error handling if we cannot find any of the student details
    } catch (error) {
        console.error('Error fetching student details:', error);
    }
}