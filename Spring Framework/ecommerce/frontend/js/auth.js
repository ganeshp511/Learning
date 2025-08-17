// DOM Elements
const loginForm = document.getElementById('login-form');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const errorMessage = document.getElementById('error-message');
const cartCountElement = document.getElementById('cart-count');

// Cart state
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Initialize the auth page
document.addEventListener('DOMContentLoaded', () => {
    updateCartCount();
    
    // Check if user is already logged in
    if (isUserLoggedIn()) {
        redirectToDashboard();
    }
    
    // Add form submission handler
    if (loginForm) {
        loginForm.addEventListener('submit', handleLogin);
    }
});

// Check if user is logged in
function isUserLoggedIn() {
    return localStorage.getItem('authToken') !== null;
}

// Get user role
function getUserRole() {
    return localStorage.getItem('userRole');
}

// Handle login form submission
async function handleLogin(e) {
    e.preventDefault();
    
    const email = emailInput.value.trim();
    const password = passwordInput.value;
    
    // Basic validation
    if (!email || !password) {
        showError('Please enter both email and password');
        return;
    }
    
    try {
        // In a real app, you would make an API call to your backend
        // const response = await fetch(`${API_BASE_URL}/auth/login`, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({ email, password })
        // });
        // 
        // const data = await response.json();
        // 
        // if (!response.ok) {
        //     throw new Error(data.message || 'Login failed');
        // }
        // 
        // // Save auth data
        // localStorage.setItem('authToken', data.token);
        // localStorage.setItem('userRole', data.role);
        // localStorage.setItem('userName', data.name);
        
        // For demo purposes, we'll simulate a successful login
        // In a real app, this would be handled by the backend
        if (email === 'admin@example.com' && password === 'admin123') {
            localStorage.setItem('authToken', 'demo-auth-token');
            localStorage.setItem('userRole', 'ADMIN');
            localStorage.setItem('userName', 'Admin User');
            redirectToDashboard();
        } else if (email === 'seller@example.com' && password === 'seller123') {
            localStorage.setItem('authToken', 'demo-auth-token');
            localStorage.setItem('userRole', 'SELLER');
            localStorage.setItem('userName', 'Seller User');
            redirectToDashboard();
        } else if (email === 'customer@example.com' && password === 'customer123') {
            localStorage.setItem('authToken', 'demo-auth-token');
            localStorage.setItem('userRole', 'CUSTOMER');
            localStorage.setItem('userName', 'Customer User');
            redirectToDashboard();
        } else {
            showError('Invalid email or password');
        }
    } catch (error) {
        console.error('Login error:', error);
        showError(error.message || 'An error occurred during login');
    }
}

// Handle Google login
function loginWithGoogle() {
    // In a real app, this would redirect to Google OAuth
    // window.location.href = `${API_BASE_URL}/oauth2/authorization/google`;
    
    // For demo purposes, we'll simulate a successful Google login
    localStorage.setItem('authToken', 'demo-google-auth-token');
    localStorage.setItem('userRole', 'CUSTOMER');
    localStorage.setItem('userName', 'Google User');
    
    showNotification('Logged in with Google');
    setTimeout(redirectToDashboard, 1000);
}

// Handle Facebook login
function loginWithFacebook() {
    // In a real app, this would redirect to Facebook OAuth
    // window.location.href = `${API_BASE_URL}/oauth2/authorization/facebook`;
    
    // For demo purposes, we'll simulate a successful Facebook login
    localStorage.setItem('authToken', 'demo-facebook-auth-token');
    localStorage.setItem('userRole', 'CUSTOMER');
    localStorage.setItem('userName', 'Facebook User');
    
    showNotification('Logged in with Facebook');
    setTimeout(redirectToDashboard, 1000);
}

// Redirect to appropriate dashboard based on user role
function redirectToDashboard() {
    const userRole = getUserRole();
    let redirectUrl = 'index.html';
    
    switch (userRole) {
        case 'ADMIN':
            redirectUrl = 'admin/dashboard.html';
            break;
        case 'SELLER':
            redirectUrl = 'seller/dashboard.html';
            break;
        case 'CUSTOMER':
        default:
            redirectUrl = 'index.html';
    }
    
    window.location.href = redirectUrl;
}

// Logout function (can be called from any page)
function logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('userRole');
    localStorage.removeItem('userName');
    window.location.href = 'login.html';
}

// Show error message
function showError(message) {
    if (errorMessage) {
        errorMessage.textContent = message;
        errorMessage.style.display = 'block';
        
        // Hide error after 5 seconds
        setTimeout(() => {
            errorMessage.style.display = 'none';
        }, 5000);
    } else {
        alert(message);
    }
}

// Update cart count in the navigation
function updateCartCount() {
    if (cartCountElement) {
        const totalItems = cart.reduce((total, item) => total + (item.quantity || 1), 0);
        cartCountElement.textContent = totalItems;
    }
}

// Show notification
function showNotification(message) {
    // Remove any existing notification
    const existingNotification = document.querySelector('.notification');
    if (existingNotification) {
        document.body.removeChild(existingNotification);
    }
    
    const notification = document.createElement('div');
    notification.className = 'notification';
    notification.textContent = message;
    document.body.appendChild(notification);
    
    // Trigger the animation
    setTimeout(() => {
        notification.classList.add('show');
    }, 100);
    
    // Remove the notification after 3 seconds
    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => {
            if (document.body.contains(notification)) {
                document.body.removeChild(notification);
            }
        }, 300);
    }, 3000);
}

// Add notification styles if not already added
if (!document.getElementById('notification-styles')) {
    const style = document.createElement('style');
    style.id = 'notification-styles';
    style.textContent = `
        .notification {
            position: fixed;
            bottom: 20px;
            left: 50%;
            transform: translateX(-50%) translateY(100px);
            background-color: #4CAF50;
            color: white;
            padding: 15px 25px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            z-index: 1000;
            opacity: 0;
            transition: all 0.3s ease;
            pointer-events: none;
        }
        
        .notification.show {
            transform: translateX(-50%) translateY(0);
            opacity: 1;
        }
    `;
    document.head.appendChild(style);
}
