const getProducts = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/product')
            .then( async result => {

                const data = await result.json();
                resolve(data);

            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

getProducts()
    .then( products => {
        const wrapper = document.querySelector('.products');
        products.forEach( product => {
            wrapper.innerHTML += `
            <div class="product">
                <div class="tooltip" title="${product.description}">
                    <img class="product__image" src="${product.image}" alt="LOGO"/>
                </div>
                <div class="product__information">
                    <div>
                    <a href="product_detail.html?product=${product.id}"><h4>${product.name}</h4></a>
                    </div>
                    <div class="product__details">
                        <span class="product__price"> ${product.price} zł</span>
                        <a class="product__cart"><img class="img__cart" src="img/add_cart.svg" onclick="addToCart(${product.id})"/></a>
                    </div>
                </div>
            </div>
            `
        } );
});


const addToCart = async (product_id) => {

    if(localStorage.getItem('token')) {
        isAuthorized()
        .then(response => {
        
          if(response.response === 'pass'){
            getCartId()
                .then(async result => {
                    let x = document.getElementById("snackbar");
                    x.className = "show";
                    await fetch('http://localhost:8080/api/v1/cartproduct/+', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        cartId: result.id,
                        productId: product_id,
                        quantity: 1
                    })
                    }).then( 
                        console.log("Dodano")
                    ).then( json => {
                        x.innerHTML = 'Dodano do koszyka!';
                        setTimeout(() =>{ x.className = x.className.replace("show", ""); }, 3000);
                    }).catch(error => {
                        console.log(error);
                    });
                })
            }
        })
    } else {
        let x = document.getElementById("snackbar");
        x.className = "show";
        x.innerHTML = 'Zaloguj się, aby dodać coś do koszyka!';
        setTimeout(() =>{ x.className = x.className.replace("show", ""); }, 3000);
    }
}


const getCartId = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/cart/user/' + localStorage.getItem('user_id'))
            .then( async result => {
      
                const data = await result.json();
                resolve(data);
      
            } )
            .catch( err => {
                reject(err);
            } );
    } );
}
      