document.addEventListener('DOMContentLoaded', () => {
    fetch('https://fakestoreapi.com/products')
        .then(res => res.json())
        .then(products => {
            mostrarProductosMasVendidos(products);
            mostrarMejoresCalificados(products);
            mostrarMasCarosYBaratos(products);
        })
        .catch(error => console.error('Error al cargar los productos:', error));
});

function mostrarProductosMasVendidos(products) {
    // Suponiendo que los productos más vendidos son aquellos con más rating.count
    const masVendidos = products.sort((a, b) => b.rating.count - a.rating.count).slice(0, 5);
    const container = document.getElementById('mas-vendidos');
    masVendidos.forEach(product => {
        const div = document.createElement('div');
        div.className = 'producto';
        div.innerHTML = `
            <h2>${product.title}</h2>
            <img src="${product.image}" alt="${product.title}">
            <p>Vendidos: ${product.rating.count}</p>
        `;
        container.appendChild(div);
    });
}

function mostrarMejoresCalificados(products) {
    // Suponiendo que los mejores calificados son aquellos con más rating.rate
    const mejoresCalificados = products.sort((a, b) => b.rating.rate - a.rating.rate).slice(0, 5);
    const container = document.getElementById('mejores-calificados');
    mejoresCalificados.forEach(product => {
        const div = document.createElement('div');
        div.className = 'producto';
        div.innerHTML = `
            <h2>${product.title}</h2>
            <img src="${product.image}" alt="${product.title}">
            <p>Calificación: ${product.rating.rate} (${product.rating.count} votos)</p>
        `;
        container.appendChild(div);
    });
}

function mostrarMasCarosYBaratos(products) {
    const masCaros = products.sort((a, b) => b.price - a.price).slice(0, 5);
    const masBaratos = products.sort((a, b) => a.price - b.price).slice(0, 5);

    const containerCaros = document.getElementById('mas-caros');
    masCaros.forEach(product => {
        const div = document.createElement('div');
        div.className = 'producto';
        div.innerHTML = `
            <h2>${product.title}</h2>
            <img src="${product.image}" alt="${product.title}">
            <p>Precio: ${product.price}€</p>
        `;
        containerCaros.appendChild(div);
    });

    const containerBaratos = document.getElementById('mas-baratos');
    masBaratos.forEach(product => {
        const div = document.createElement('div');
        div.className = 'producto';
        div.innerHTML = `
            <h2>${product.title}</h2>
            <img src="${product.image}" alt="${product.title}">
            <p>Precio: ${product.price}€</p>
        `;
        containerBaratos.appendChild(div);
    });
}