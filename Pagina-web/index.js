fetch('https://fakestoreapi.com/products?limit=5')
    .then(res => res.json())
    .then(json => {
        const container = document.getElementById('producto-container');
        container.innerHTML = '';
        json.forEach(product => {
            const div = document.createElement('div');
            div.innerHTML = `
                <div class="producto">
                    <img class="img-producto" src="${product.image}" alt="${product.title}" style="height:300px;margin-top:20px;" onclick="verProducto(${product.id})">
                    <h2 style="height:55px; overflow:hidden;">${product.title}</h2>
                    <p><b>${product.price}€</b></p>
                </div>
                <br>
            `;
            container.appendChild(div);
        });
    })
    .catch(error => console.error('Error al cargar los productos:', error));

function verProducto(productId) {
    fetch(`https://fakestoreapi.com/products/${productId}`)
        .then(res => res.json())
        .then(product => {
            const container = document.getElementById('producto-container');
            container.innerHTML = '';
            const div = document.createElement('div');
            div.innerHTML = `
                <div class="producto">
                    <img class="img-producto" src="${product.image}" alt="${product.title}" style="height:300px;margin-top:20px;">
                    <h2 style="height:55px; overflow:hidden;">${product.title}</h2>
                    <p><b>${product.price}€</b></p>
                </div>
                <br>
            `;
            container.appendChild(div);
        })
        .catch(error => console.error('Error al cargar el producto:', error));
}