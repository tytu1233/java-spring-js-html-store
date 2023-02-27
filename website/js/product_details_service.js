const retrieveCurrentProduct = () => {

    const urlParams = new URLSearchParams(window.location.search);
    const product = urlParams.get('product');

    return product;
}


const getProduct = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/product/'+retrieveCurrentProduct())
            .then( async result => {

                const data = await result.json();
                resolve(data);

            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

getProduct()
    .then(product => {
        const wrapper = document.querySelector('.product_detail');
            wrapper.innerHTML += `
            <div class="product_image">
                <img class="img_details" src="${product.image}"/>
            </div>
            <div class="product_info">
                <div class="product_name">
                    <h1>${product.name}</h1>
                    <h4>${product.price} zł</h4>
                </div>
                <div class="product_description">
                    <p>
                    ${product.description}
                    </p>
                </div>
                <div class="product_add">
                    <button type="button" onclick="addToCart(${product.id})"><span class="product_add_text">Dodaj do koszyka</span></button>
                </div>
            </div>
            `
    } );


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
          