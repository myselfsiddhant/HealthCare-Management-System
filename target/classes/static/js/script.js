function handleLogin(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const responseDiv = document.getElementById('response');

    responseDiv.innerText = 'Logging in...';

    fetch('/api/authenticate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: username, password })
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Invalid credentials');
        }
    })
    .then(message => {
        // Hide login section and show dashboard
        document.getElementById('loginSection').style.display = 'none';
        document.getElementById('dashboard').style.display = 'block';

        // Display logged-in username
        document.getElementById('usernameDisplay').innerText = username;
        
        // Show user info and logout option
        document.getElementById('userInfo').style.display = 'block';
        document.getElementById('logoutOption').style.display = 'block'; // Show logout option

        // Clear response message after 5 seconds
        responseDiv.innerText = message;
        setTimeout(() => responseDiv.innerText = '', 5000);
    })
    .catch(error => {
        responseDiv.innerText = error.message;
    });
}

function logout() {
    // Clear user data (e.g., username display)
    document.getElementById('usernameDisplay').innerText = '';
    
    // Hide dashboard and show login section
    document.getElementById('dashboard').style.display = 'none';
    document.getElementById('loginSection').style.display = 'block';

    alert("You have been logged out.");
}

function showTab(tabId) {
    const tabs = document.querySelectorAll('.tab-content');
    tabs.forEach(tab => tab.classList.remove('active'));
    
    const buttons = document.querySelectorAll('.tab-button');
    buttons.forEach(button => button.classList.remove('active'));

    document.getElementById(tabId).classList.add('active');
    document.querySelector(`.tab-button[data-tab="${tabId}"]`).classList.add('active');

    if (tabId === 'viewReportsSection') {
        fetchReportDates(); // Fetch report dates when this tab is shown
    }
}

function fetchReportDates() {
    const reportDatesContainer = document.getElementById('reportDates');
    reportDatesContainer.innerHTML = ''; // Clear previous data

    // Dummy data for report dates, replace with actual data as needed
    const dates = ["2024-10-01", "2024-09-25", "2024-09-10"]; // Replace with actual fetch call

    // Display each date as a clickable item
    dates.forEach(date => {
        const dateItem = document.createElement('p');
        dateItem.textContent = date;
        dateItem.onclick = () => toggleReport(date);
        reportDatesContainer.appendChild(dateItem);
    });
}

function toggleReportDates() {
    const reportDatesContainer = document.getElementById('reportDates');
    const toggleLabel = document.getElementById('toggleLabel');
    
    // Check if the checkbox is checked
    if (document.getElementById('reportToggle').checked) {
        fetchReportDates(); // Fetch and display report dates
        reportDatesContainer.classList.remove('hidden-section'); // Show the report dates
        toggleLabel.innerText = "Hide Report Dates"; // Update label text
    } else {
        reportDatesContainer.innerHTML = ''; // Clear previous data
        reportDatesContainer.classList.add('hidden-section'); // Hide the report dates
        toggleLabel.innerText = "Show Report Dates"; // Update label text
    }
}

function fetchReportDates() {
    const reportDatesContainer = document.getElementById('reportDates');
    
    // Dummy data for report dates, replace with actual data as needed
    const dates = ["2024-10-01", "2024-09-25", "2024-09-10"]; // Replace with actual fetch call

    // Clear previous data
    reportDatesContainer.innerHTML = ''; 

    // Display each date as a clickable item
    dates.forEach(date => {
        const dateItem = document.createElement('p');
        dateItem.textContent = date;
        dateItem.onclick = () => toggleReport(date);
        reportDatesContainer.appendChild(dateItem);
    });
}

// Other existing functions remain unchanged...

function fetchUpcomingAppointments() {
    const patientId = document.getElementById('username').value;

    fetch(`/api/upcoming-appointments/${patientId}`)
        .then(response => response.json())
        .then(appointments => {
            const upcomingAppointmentsDiv = document.getElementById('upcomingAppointmentsContainer');
            upcomingAppointmentsDiv.innerHTML = '<h3>Upcoming Appointments:</h3>' + appointments.map(app => `<p>${app.date}</p>`).join('');
        })
        .catch(error => console.error('Error:', error));
}

function bookAppointment() {
    const patientId = document.getElementById('username').value;

    fetch('/api/available-appointment-dates/' + patientId)
       .then(response => response.json())
       .then(dates => {
           const availableDatesDiv = document.getElementById("availableDates");
           availableDatesDiv.innerHTML = '<h3>Available Dates:</h3>' + dates.map(date => `<p>${date}</p>`).join('');
       })
       .catch(error => console.error("Error fetching available dates:", error));
}

function cancelAppointment() {
    const patientId = document.getElementById('username').value; 
    const cancelDate = document.getElementById('cancelAppointmentDate').value;

    if (!cancelDate) {
        alert("Please select a date to cancel the appointment.");
        return;
    }

    fetch('/api/cancel-appointment', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ patientId, date: cancelDate })
    })
    .then(response => response.text())
    .then(message => {
        document.getElementById('cancelResponse').innerText = message; // Show success or failure message
        fetchUpcomingAppointments(); // Refresh upcoming appointments
    })
    .catch(error => console.error('Error:', error));
}


