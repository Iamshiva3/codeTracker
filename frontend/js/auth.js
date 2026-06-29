const API_BASES = [
    'http://localhost:8081/api/auth',
    'http://127.0.0.1:8081/api/auth',
    'http://localhost:8080/api/auth',
    'http://127.0.0.1:8080/api/auth'
];

function switchTab(tab) {
    document.querySelectorAll('.tab-btn').forEach(btn => btn.classList.remove('active'));
    document.querySelectorAll('.auth-form').forEach(form => form.classList.remove('active-form'));

    if(tab === 'login') {
        document.querySelectorAll('.tab-btn')[0].classList.add('active');
        document.getElementById('login-form').classList.add('active-form');
    } else {
        document.querySelectorAll('.tab-btn')[1].classList.add('active');
        document.getElementById('signup-form').classList.add('active-form');
    }
}

async function parseResponse(response) {
    const contentType = response.headers.get('content-type') || '';
    const text = await response.text();

    if (!text) {
        return {};
    }

    try {
        if (contentType.includes('application/json')) {
            return JSON.parse(text);
        }
        return { message: text };
    } catch (err) {
        return { message: text };
    }
}

async function requestAuth(endpoint, payload) {
    let lastError = null;

    for (const base of API_BASES) {
        try {
            const response = await fetch(`${base}/${endpoint}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
            const data = await parseResponse(response);
            return { response, data };
        } catch (err) {
            lastError = err;
        }
    }

    throw lastError || new Error('Unable to reach backend.');
}

document.getElementById('login-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const errorDiv = document.getElementById('login-error');
    errorDiv.textContent = '';
    
    const username = document.getElementById('login-username').value.trim();
    const password = document.getElementById('login-password').value;

    try {
        const { response, data } = await requestAuth('login', { username, password });
        
        if(response.ok) {
            localStorage.setItem('token', data.token);
            localStorage.setItem('username', data.username);
            window.location.href = 'index.html';
        } else {
            errorDiv.textContent = data.message || data.error || 'Login failed.';
        }
    } catch (err) {
        errorDiv.textContent = 'Network error. Make sure the backend is running.';
    }
});

document.getElementById('signup-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const errorDiv = document.getElementById('signup-error');
    const successDiv = document.getElementById('signup-success');
    errorDiv.textContent = '';
    successDiv.textContent = '';
    
    const username = document.getElementById('signup-username').value.trim();
    const password = document.getElementById('signup-password').value;

    try {
        const { response, data } = await requestAuth('signup', { username, password });
        
        if(response.ok) {
            successDiv.textContent = 'Signup successful! You can now login.';
            document.getElementById('signup-form').reset();
            setTimeout(() => switchTab('login'), 2000);
        } else {
            errorDiv.textContent = data.message || data.error || 'Signup failed.';
        }
    } catch (err) {
        errorDiv.textContent = 'Network error. Make sure the backend is running.';
    }
});

// Check if already logged in
if(localStorage.getItem('token')) {
    window.location.href = 'index.html';
}
