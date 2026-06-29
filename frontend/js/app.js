const API_BASE = 'http://localhost:8081/api/activity';

// Check auth
const token = localStorage.getItem('token');
if (!token) {
    window.location.href = 'auth.html';
}

document.getElementById('user-greeting').textContent = `Hello, ${localStorage.getItem('username')}!`;

document.getElementById('logout-btn').addEventListener('click', () => {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    window.location.href = 'auth.html';
});

// Load Dashboard Data
async function loadDashboard() {
    await fetchLogs();
    await fetchSuggestions();
}

async function fetchLogs() {
    try {
        const response = await fetch(API_BASE, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
        if (response.status === 401) {
            localStorage.clear();
            window.location.href = 'auth.html';
            return;
        }
        const logs = await response.json();
        const tbody = document.getElementById('history-body');
        tbody.innerHTML = '';
        
        logs.forEach(log => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${log.date}</td>
                <td>${log.platform}</td>
                <td>${log.timeSpentMinutes}</td>
                <td>${log.problemsSolved}</td>
            `;
            tbody.appendChild(tr);
        });
    } catch (err) {
        console.error('Failed to load logs', err);
    }
}

async function fetchSuggestions() {
    try {
        const response = await fetch(`${API_BASE}/suggestions`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
        const suggestions = await response.json();
        const list = document.getElementById('suggestions-list');
        list.innerHTML = '';
        
        suggestions.forEach(s => {
            const div = document.createElement('div');
            div.className = 'suggestion-item';
            div.textContent = s.suggestionText;
            list.appendChild(div);
        });
    } catch (err) {
        console.error('Failed to load suggestions', err);
    }
}

document.getElementById('activity-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const msgDiv = document.getElementById('activity-msg');
    msgDiv.textContent = '';
    msgDiv.className = '';
    
    const platform = document.getElementById('platform').value;
    const date = document.getElementById('date').value;
    const timeSpentMinutes = parseInt(document.getElementById('time').value);
    const problemsSolved = parseInt(document.getElementById('problems').value);

    try {
        const response = await fetch(API_BASE, {
            method: 'POST',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({ platform, date, timeSpentMinutes, problemsSolved })
        });
        
        if (response.ok) {
            msgDiv.textContent = 'Activity logged successfully!';
            msgDiv.className = 'success-message';
            document.getElementById('activity-form').reset();
            // Default date to today again
            document.getElementById('date').valueAsDate = new Date();
            loadDashboard();
        } else {
            msgDiv.textContent = 'Failed to log activity.';
            msgDiv.className = 'error-message';
        }
    } catch (err) {
        msgDiv.textContent = 'Network error.';
        msgDiv.className = 'error-message';
    }
});

// Set default date
document.getElementById('date').valueAsDate = new Date();

// Init
loadDashboard();
