// DOM Elements
const productsGrid = document.getElementById('products-grid');
const searchInput = document.getElementById('search-input');
const categoryFilter = document.getElementById('category-filter');
const sortBy = document.getElementById('sort-by');
const pagination = document.getElementById('pagination');
const cartCountElement = document.getElementById('cart-count');

// State
let products = [];
let filteredProducts = [];
let currentPage = 1;
const productsPerPage = 12;

// Cart state
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Initialize the products page
document.addEventListener('DOMContentLoaded', () => {
    updateCartCount();
    loadProducts();
    
    // Add event listeners
    searchInput.addEventListener('input', filterProducts);
    categoryFilter.addEventListener('change', filterProducts);
    sortBy.addEventListener('change', sortProducts);
});

// Load products from the API
async function loadProducts() {
    try {
        // In a real app, you would fetch from your API
        // const response = await fetch(`${API_BASE_URL}/customer/products?keyword=&pageSize=100&pageNo=0`);
        // products = await response.json();
        
        // For demo purposes, we'll use mock data
        products = generateMockProducts(24);
        
        filterProducts();
    } catch (error) {
        console.error('Error loading products:', error);
        productsGrid.innerHTML = '<p class="empty-state">Failed to load products. Please try again later.</p>';
    }
}

// Generate mock products for demo
function generateMockProducts(count) {
    const mockProducts = [];
    const categories = ['Electronics', 'Clothing', 'Books', 'Home & Garden'];
    
    for (let i = 1; i <= count; i++) {
        const category = categories[Math.floor(Math.random() * categories.length)];
        const price = Math.floor(Math.random() * 900) + 100; // Random price between 100 and 1000
        
        mockProducts.push({
            id: i,
            name: `Product ${i}`,
            description: `This is a sample description for product ${i}. This product belongs to the ${category} category.`,
            price: price,
            category: category,
            imageUrl: `https://source.unsplash.com/random/300x300/?${category.toLowerCase().replace(/\s+/g, '')},product,${i}`
        });
    }
    
    return mockProducts;
}

// Filter products based on search and category
function filterProducts() {
    const searchTerm = searchInput.value.toLowerCase();
    const category = categoryFilter.value;
    
    filteredProducts = products.filter(product => {
        const matchesSearch = product.name.toLowerCase().includes(searchTerm) || 
                            product.description.toLowerCase().includes(searchTerm);
        const matchesCategory = !category || product.category === category;
        
        return matchesSearch && matchesCategory;
    });
    
    currentPage = 1; // Reset to first page when filters change
    sortProducts();
}

// Sort products based on selected option
function sortProducts() {
    const sortValue = sortBy.value;
    
    filteredProducts.sort((a, b) => {
        switch (sortValue) {
            case 'price-low':
                return a.price - b.price;
            case 'price-high':
                return b.price - a.price;
            case 'name-asc':
                return a.name.localeCompare(b.name);
            case 'name-desc':
                return b.name.localeCompare(a.name);
            default: // 'featured' or any other value
                return 0; // Keep original order
        }
    });
    
    displayProducts();
}

// Display products with pagination
function displayProducts() {
    // Calculate pagination
    const totalPages = Math.ceil(filteredProducts.length / productsPerPage);
    const startIndex = (currentPage - 1) * productsPerPage;
    const endIndex = startIndex + productsPerPage;
    const paginatedProducts = filteredProducts.slice(startIndex, endIndex);
    
    // Display products
    if (paginatedProducts.length === 0) {
        productsGrid.innerHTML = '<p class="empty-state">No products found matching your criteria.</p>';
        pagination.innerHTML = '';
        return;
    }
    
    productsGrid.innerHTML = paginatedProducts.map(product => `
        <div class="product-card">
            <img src="${product.imageUrl || 'https://via.placeholder.com/300'}" alt="${product.name}" class="product-image">
            <div class="product-info">
                <h3 class="product-title">${product.name}</h3>
                <p class="product-category">${product.category}</p>
                <p>${product.description.substring(0, 80)}...</p>
                <p class="product-price">$${product.price.toFixed(2)}</p>
                <button onclick="addToCart(${JSON.stringify(product).replace(/"/g, '&quot;')})" class="cta-button">
                    Add to Cart
                </button>
            </div>
        </div>
    `).join('');
    
    // Update pagination
    updatePagination(totalPages);
}

// Update pagination controls
function updatePagination(totalPages) {
    if (totalPages <= 1) {
        pagination.innerHTML = '';
        return;
    }
    
    let paginationHTML = '';
    
    // Previous button
    paginationHTML += `
        <button onclick="changePage(${currentPage - 1})" ${currentPage === 1 ? 'disabled' : ''}>
            &laquo; Previous
        </button>
    `;
    
    // Page numbers
    const maxVisiblePages = 5;
    let startPage = Math.max(1, currentPage - Math.floor(maxVisiblePages / 2));
    let endPage = startPage + maxVisiblePages - 1;
    
    if (endPage > totalPages) {
        endPage = totalPages;
        startPage = Math.max(1, endPage - maxVisiblePages + 1);
    }
    
    // First page
    if (startPage > 1) {
        paginationHTML += `
            <button onclick="changePage(1)" ${1 === currentPage ? 'class="active"' : ''}>
                1
            </button>
        `;
        
        if (startPage > 2) {
            paginationHTML += '<span>...</span>';
        }
    }
    
    // Page numbers
    for (let i = startPage; i <= endPage; i++) {
        paginationHTML += `
            <button onclick="changePage(${i})" ${i === currentPage ? 'class="active"' : ''}>
                ${i}
            </button>
        `;
    }
    
    // Last page
    if (endPage < totalPages) {
        if (endPage < totalPages - 1) {
            paginationHTML += '<span>...</span>';
        }
        
        paginationHTML += `
            <button onclick="changePage(${totalPages})" ${totalPages === currentPage ? 'class="active"' : ''}>
                ${totalPages}
            </button>
        `;
    }
    
    // Next button
    paginationHTML += `
        <button onclick="changePage(${currentPage + 1})" ${currentPage === totalPages ? 'disabled' : ''}>
            Next &raquo;
        </button>
    `;
    
    pagination.innerHTML = paginationHTML;
}

// Change current page
function changePage(page) {
    const totalPages = Math.ceil(filteredProducts.length / productsPerPage);
    
    if (page < 1 || page > totalPages || page === currentPage) {
        return;
    }
    
    currentPage = page;
    displayProducts();
    
    // Scroll to top of products
    window.scrollTo({
        top: productsGrid.offsetTop - 100,
        behavior: 'smooth'
    });
}

// Add item to cart (called from product card buttons)
function addToCart(product) {
    // Parse the product if it's a string (from HTML onclick)
    if (typeof product === 'string') {
        try {
            product = JSON.parse(product.replace(/&quot;/g, '"'));
        } catch (e) {
            console.error('Error parsing product:', e);
            return;
        }
    }
    
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
