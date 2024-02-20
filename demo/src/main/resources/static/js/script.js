window.onload = onLoadFunctions;


document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('addStudent').addEventListener('click', function() {
        // Implement Add functionality
        window.location.href = '/students/add';
    });

    document.getElementById('viewStudents').addEventListener('click', function() {
        // Implement View functionality
        window.location.href = '/students/view';
    });

    document.getElementById('modifyStudent').addEventListener('click', function() {
        // Implement Modify functionality
        window.location.href = '/students/view';
    });

    document.getElementById('deleteStudent').addEventListener('click', function() {
        // Implement Delete functionality
        window.location.href = '/students/delete';
    });
});