// DOM Elements
const cartItemsContainer = document.getElementById('cart-items');
const emptyCartElement = document.getElementById('empty-cart');
const cartItemsElement = document.getElementById('cart-items-container');
const subtotalElement = document.getElementById('subtotal');
const shippingElement = document.getElementById('shipping');
const totalElement = document.getElementById('total');
const checkoutBtn = document.getElementById('checkout-btn');
const cartCountElement = document.getElementById('cart-count');

// Cart state
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Initialize the cart page
document.addEventListener('DOMContentLoaded', () => {
    updateCartCount();
    renderCart();
    
    // Add event listener for checkout button
    if (checkoutBtn) {
        checkoutBtn.addEventListener('click', handleCheckout);
    }
});

// Render the cart
function renderCart() {
    if (cart.length === 0) {
        emptyCartElement.style.display = 'block';
        cartItemsElement.style.display = 'none';
        return;
    }
    
    emptyCartElement.style.display = 'none';
    cartItemsElement.style.display = 'block';
    
    // Render cart items
    cartItemsContainer.innerHTML = cart.map((item, index) => `
        <div class="cart-item" data-index="${index}">
            <img src="${item.imageUrl || 'https://via.placeholder.com/80'}" alt="${item.name}" class="cart-item-image">
            <div class="cart-item-details">
                <h3 class="cart-item-title">${item.name}</h3>
                ${item.category ? `<p class="cart-item-category">${item.category}</p>` : ''}
            </div>
            <div class="cart-item-price">$${item.price.toFixed(2)}</div>
            <div class="quantity-selector">
                <button class="quantity-btn" onclick="updateQuantity(${index}, ${item.quantity - 1})">-</button>
                <input type="number" class="quantity-input" value="${item.quantity}" min="1" 
                       onchange="updateQuantity(${index}, this.value)">
                <button class="quantity-btn" onclick="updateQuantity(${index}, ${item.quantity + 1})">+</button>
            </div>
            <button class="remove-item" onclick="removeItem(${index})">&times;</button>
        </div>
    `).join('');
    
    // Update totals
    updateTotals();
}

// Update item quantity
function updateQuantity(index, newQuantity) {
    newQuantity = parseInt(newQuantity);
    
    if (isNaN(newQuantity) || newQuantity < 1) {
        newQuantity = 1;
    }
    
    cart[index].quantity = newQuantity;
    
    // If quantity is 0, remove the item
    if (newQuantity === 0) {
        cart.splice(index, 1);
    }
    
    updateCart();
    renderCart();
}

// Remove item from cart
function removeItem(index) {
    cart.splice(index, 1);
    updateCart();
    renderCart();
    
    // Show removed notification
    showNotification('Item removed from cart');
}

// Update cart in local storage and UI
function updateCart() {
    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartCount();
    updateTotals();
}

// Update cart count in the navigation
function updateCartCount() {
    if (cartCountElement) {
        const totalItems = cart.reduce((total, item) => total + (item.quantity || 1), 0);
        cartCountElement.textContent = totalItems;
    }
}

// Update order summary totals
function updateTotals() {
    const subtotal = cart.reduce((sum, item) => sum + (item.price * (item.quantity || 1)), 0);
    const shipping = subtotal > 0 ? 5.99 : 0; // Example shipping cost
    const total = subtotal + shipping;
    
    if (subtotalElement) subtotalElement.textContent = `$${subtotal.toFixed(2)}`;
    if (shippingElement) shippingElement.textContent = `$${shipping.toFixed(2)}`;
    if (totalElement) totalElement.textContent = `$${total.toFixed(2)}`;
}

// Handle checkout process
function handleCheckout() {
    // In a real application, you would redirect to a checkout page or process payment
    alert('Proceeding to checkout! In a real app, this would take you to the payment page.');
    
    // For demo purposes, we'll just clear the cart
    // cart = [];
    // updateCart();
    // renderCart();
    // showNotification('Order placed successfully!');
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
