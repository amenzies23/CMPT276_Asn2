window.onload = onLoadFunctions;
src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js">

function onLoadFunctions() {
    // Initialization code here
}

async function toggleModal(studentId) {
    console.log("toggleModal called for student " + `${studentId}`);
    try {
        const response = await fetch(`/students/${studentId}`);
        if (response.ok) {
            const student = await response.json();
            document.getElementById("studentDetailsModalLabel").textContent= student.name;
            // Now populate the modal with student details
            const modalBody = document.getElementById('studentModalBody');
            modalBody.innerHTML = `
                <p>Major: ${student.major}</p>
                <p>GPA: ${student.gpa}</p>
                <p>Height: ${student.height}</p>
                <p>Weight: ${student.weight}</p>
                <p>Hair Color: ${student.hair_color}</p>`;
            // Show the modal
            new bootstrap.Modal(document.getElementById('studentDetailsModal')).show();
            const deleteButton = document.getElementById('dlt-btn');

            // Assign an event listener for the delete operation
            var form = document.getElementById('dlt-btn');
            form.setAttribute('action', `/students/${studentId}/delete`);

            var editButton = document.getElementById('edit-btn');
            editButton.addEventListener('click', function() {
                window.location.href = `/students/${studentId}/edit`;
            });
        } else {
            console.error('Student details not found:', response.status);
        }
    } catch (error) {
        console.error('Error fetching student details:', error);
    }
}