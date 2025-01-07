// Configuration
const API_BASE_URL = '/api/healthcare';

// Authentication and Session Management
document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');
    const logoutBtn = document.getElementById('logoutBtn');
    const loginSection = document.getElementById('loginSection');
    const dashboardSection = document.getElementById('dashboardSection');
    const welcomeMessage = document.getElementById('welcomeMessage');
    const loginError = document.getElementById('loginError');

    // Tab Navigation
    const tabButtons = document.querySelectorAll('.tab-btn');
    const tabs = document.querySelectorAll('.tab');

    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            const tabId = button.getAttribute('data-tab');
            
            // Remove active class from all buttons and tabs
            tabButtons.forEach(btn => btn.classList.remove('active'));
            tabs.forEach(tab => tab.classList.remove('active'));

            // Add active class to clicked button and corresponding tab
            button.classList.add('active');
            document.getElementById(`${tabId}Tab`).classList.add('active');
        });
    });

    // Login Form Submission
    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        try {
            const response = await fetch(`${API_BASE_URL}/authenticate`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password })
            });

            if (response.ok) {
                loginSection.classList.add('hidden');
                dashboardSection.classList.remove('hidden');
                welcomeMessage.textContent = `Welcome, ${username}!`;
                
                // Load initial dashboard data
                loadAppointments(username);
                loadMedicalReports(username);
                loadUserProfile(username);
            } else {
                const errorData = await response.json();
                loginError.textContent = errorData.message || 'Login failed';
            }
        } catch (error) {
            loginError.textContent = 'Network error. Please try again.';
        }
    });

    // Logout
    logoutBtn.addEventListener('click', () => {
        dashboardSection.classList.add('hidden');
        loginSection.classList.remove('hidden');
        welcomeMessage.textContent = '';
        loginError.textContent = '';
    });

    // Appointment Booking Modal
    const bookAppointmentBtn = document.getElementById('bookAppointmentBtn');
    const appointmentModal = document.getElementById('appointmentModal');
    const closeModalBtn = document.querySelector('.close-btn');
    const appointmentForm = document.getElementById('appointmentForm');

    bookAppointmentBtn.addEventListener('click', () => {
        appointmentModal.classList.remove('hidden');
    });

    closeModalBtn.addEventListener('click', () => {
        appointmentModal.classList.add('hidden');
    });

    appointmentForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        const appointmentDate = document.getElementById('appointmentDate').value;
        
        try {
            const response = await fetch(`${API_BASE_URL}/book-appointment`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ date: appointmentDate })
            });

            if (response.ok) {
                appointmentModal.classList.add('hidden');
                loadAppointments(); // Refresh appointments
            }
        } catch (error) {
            console.error('Appointment booking failed', error);
        }
    });
});

// Data Loading Functions
async function loadAppointments(username) {
    const appointmentsList = document.getElementById('appointmentsList');
    
    try {
        const response = await fetch(`${API_BASE_URL}/appointments/${username}`);
        const appointments = await response.json();

        appointmentsList.innerHTML = appointments.map(appt => `
            <div class="appointment-card">
                <p>Date: ${appt.date}</p>
                <p>Status: ${appt.status}</p>
            </div>
        `).join('');
    } catch (error) {
        console.error('Failed to load appointments', error);
    }
}

async function loadMedicalReports(username) {
    const reportsList = document.getElementById('reportsList');
    
    try {
        const response = await fetch(`${API_BASE_URL}/reports/${username}`);
        const reports = await response.json();

        reportsList.innerHTML = reports.map(report => `
            <div class="report-card">
                <p>Date: ${report.date}</p>
                <p>Diagnosis: ${report.diagnosis}</p>
            </div>
        `).join('');
    } catch (error) {
        console.error('Failed to load reports', error);
    }
}

async function loadUserProfile(username) {
    const profileDetails = document.getElementById('profileDetails');
    
    try {
        const response = await fetch(`${API_BASE_URL}/profile/${username}`);
        const profile = await response.json();

        profileDetails.innerHTML = `
            <p>Name: ${profile.name}</p>
            <p>Email: ${profile.email}</p>
        `;
    } catch (error) {
        console.error('Failed to load profile', error);
    }
}
