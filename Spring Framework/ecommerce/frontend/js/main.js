// API Base URL - Update this with your Spring Boot backend URL
const API_BASE_URL = 'http://localhost:8080/api';

// DOM Elements
const featuredProductsContainer = document.getElementById('featured-products');
const cartCountElement = document.getElementById('cart-count');

// Cart state
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Initialize the application
document.addEventListener('DOMContentLoaded', () => {
    updateCartCount();
    loadFeaturedProducts();
});

// Fetch featured products from the API
async function loadFeaturedProducts() {
    try {
        // In a real app, you would fetch from your API
        // const response = await fetch(`${API_BASE_URL}/customer/products?keyword=&pageSize=8&pageNo=0`);
        // const products = await response.json();
        
        // For demo purposes, we'll use mock data
        const products = [
            {
                id: 1,
                name: 'Wireless Headphones',
                description: 'High-quality wireless headphones with noise cancellation',
                price: 99.99,
                imageUrl: 'https://source.unsplash.com/random/300x300/?headphones'
            },
            {
                id: 2,
                name: 'Smartphone',
                description: 'Latest smartphone with advanced camera features',
                price: 699.99,
                imageUrl: 'https://source.unsplash.com/random/300x300/?smartphone'
            },
            {
                id: 3,
                name: 'Laptop',
                description: 'Powerful laptop for work and entertainment',
                price: 999.99,
                imageUrl: 'https://source.unsplash.com/random/300x300/?laptop'
            },
            {
                id: 4,
                name: 'Smart Watch',
                description: 'Track your fitness and stay connected',
                price: 199.99,
                imageUrl: 'https://source.unsplash.com/random/300x300/?smartwatch'
            }
        ];
        
        displayProducts(products);
    } catch (error) {
        console.error('Error loading products:', error);
        featuredProductsContainer.innerHTML = '<p>Failed to load products. Please try again later.</p>';
    }
}

// Display products in the grid
function displayProducts(products) {
    if (!products || products.length === 0) {
        featuredProductsContainer.innerHTML = '<p>No products found.</p>';
        return;
    }

    featuredProductsContainer.innerHTML = products.map(product => `
        <div class="product-card">
            <img src="${product.imageUrl || 'https://via.placeholder.com/300'}" alt="${product.name}" class="product-image">
            <div class="product-info">
                <h3 class="product-title">${product.name}</h3>
                <p>${product.description}</p>
                <p class="product-price">$${product.price.toFixed(2)}</p>
                <button onclick="addToCart(${JSON.stringify(product).replace(/"/g, '&quot;')})" class="cta-button">
                    Add to Cart
                </button>
            </div>
        </div>
    `).join('');
}

// Add item to cart
function addToCart(product) {
    const existingItem = cart.find(item => item.id === product.id);
    
    if (existingItem) {
        existingItem.quantity = (existingItem.quantity || 1) + 1;
    } else {
        cart.push({ ...product, quantity: 1 });
    }
    
    updateCart();
    showNotification(`${product.name} added to cart!`);
}

// Update cart in local storage and UI
function updateCart() {
    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartCount();
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
    const notification = document.createElement('div');
    notification.className = 'notification';
    notification.textContent = message;
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.classList.add('show');
    }, 100);
    
    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => {
            document.body.removeChild(notification);
        }, 300);
    }, 3000);
}

// Add notification styles
const style = document.createElement('style');
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
    }
    
    .notification.show {
        transform: translateX(-50%) translateY(0);
        opacity: 1;
    }
`;
document.head.appendChild(style);
