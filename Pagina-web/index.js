fetch('https://fakestoreapi.com/products')
            .then(res => res.json())
            .then(json => {
                const container = document.getElementById('producto-container');
                json.forEach(product => {
                    const div = document.createElement('div');
                    div.innerHTML = `
                        <div class="producto" style="background-color:white">
                        <h2 style="height:55px; overflow:hidden;">${product.title}</h2>
                        <img class="img-producto" src="${product.image}" alt="${product.title}" style="height:300px;">
                        <p>${product.price}€</p>
                        <button>Añadir al carrito</button></div>
                        <br>
                        `;
                    container.appendChild(div);
                });
            })
            .catch(error => console.error('Error al cargar los productos:', error));