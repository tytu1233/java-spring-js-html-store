let final = 0;


const getCartProducts = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/usercartproduct/' + localStorage.getItem('user_id'))
            .then( async result => {

                const data = await result.json();
                resolve(data);


            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

const getQuantity = (cart_product_id) => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/cartproduct/' + cart_product_id)
            .then( async result => {

                const data = await result.json();
                resolve(data);


            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

const getFinalPrice = (cart_id) => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/cart/' + cart_id)
            .then( async result => {

                const data = await result.json();
                resolve(data);


            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

const getOrderId = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/order')
            .then( async result => {

                const data = await result.json();
                resolve(data);


            } )
            .catch( err => {
                reject(err);
            } );
    } );
}


const deleteCartProduct = (cart_product_id) => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/cartproduct/' + cart_product_id, {
            method: 'DELETE'
        })
            .then( async result => {

                const data = await result.json();
                resolve(data);


            } )
            .catch( err => {
                reject(err);
            } );
    } );
}




const plus_one = (product_id, cart_id , cart_product_id, sign) => {
    fetch('http://localhost:8080/api/v1/cartproduct/' + sign, {
                  method: 'POST',
                  headers: {
                      'Content-Type': 'application/json'
                  },
                  body: JSON.stringify({
                      cartId: cart_id,
                      productId: product_id,
                      quantity: 1
                  })
                  }).then(
                     
                  ).then( json => {

                     getQuantity(cart_product_id)
                     .then(cart_product => {
                        const cartProducts = document.querySelector('.cart_products_amount_quantity'+product_id)
                        if(cart_product.quantity === 0) {
                            deleteCartProduct(cart_product_id)
                            .then( deleted => {
                                console.log("USUNIETO");
                            })
                            document.location.reload(true)
                        } else {
                            cartProducts.innerHTML = cart_product.quantity
                        }
                     })

                     getFinalPrice(cart_id)
                     .then(cart => {
                         const cartProducts = document.querySelector('#final_price')
                         const cartProductsThings = document.querySelector('#things_price')
                         cartProductsThings.innerHTML = (Math.round(cart.finalPrice * 100) / 100) - 10 + ' zł' 
                         cartProducts.innerHTML =  (Math.round(cart.finalPrice * 100) / 100) + ' zł'
                         final = (Math.round(cart.finalPrice * 100) / 100)
                         
                     })

                  }).catch(error => {
                      console.log(error);
                  });
                  
}

const val = () => getCartProducts()
    .then( products => {
        if(products.length === 0) {
            const cartProducts = document.querySelector('.cart_products')
            cartProducts.innerHTML = '<h1 style="text-align:center">Koszyk jest pusty</h1>'
        } else {
            let cart;
            const cartProducts = document.querySelector('.cart_products')
            products.forEach(product => {
                cart = product.cart.id
                cartProducts.innerHTML += `
                <div class="cart_products_detail">
                    <div class="cart_products_image">
                        <img class="img_details" src="${product.product.image}"/>
                    </div>
                    <div class="cart_products_describe">
                        <span class="cart_products_name">${product.product.name}</span>
                        <div class="cart_products_data">
                            <span class="cart_products_price">${product.product.price} zł</span>
                            <div class="cart_products_amount_details">
                                <span tabindex="0" onclick="plus_one(${product.product.id}, ${product.cart.id}, ${product.cartProduct.id}, '-')" class="cart_products_amount cart_products_minus">-</span>
                                <span id="quantity" class="cart_products_amount_quantity${product.product.id}">${product.cartProduct.quantity}</span>
                                <span tabindex="0" onclick="plus_one(${product.product.id}, ${product.cart.id}, ${product.cartProduct.id}, '+')" class="cart_products_amount cart_products_plus">+</span>
                            </div>
                        </div>
                    </div>
                </div>
                `
            } );
            getFinalPrice(cart)
            .then(cart => {
                const cartProducts = document.querySelector('#final_price')
                const cartProductsThings = document.querySelector('#things_price')
                if(cart.finalPrice === undefined) {
                    cartProductsThings.innerHTML ='0 zł' 
                    cartProducts.innerHTML = '10 zł'
                } else {
                    cartProductsThings.innerHTML = (Math.round(cart.finalPrice * 100) / 100)-10  + ' zł' 
                    cartProducts.innerHTML =  Math.round(cart.finalPrice * 100) / 100 + ' zł'
                    final = Math.round(cart.finalPrice * 100) / 100;
                }
            })
        }
});


const addOrder = async () => {
    const street = document.getElementById('street');
    const city = document.getElementById('city');
    const post_code = document.getElementById('post-code');
    const address = city.value + ', ' + street.value + ', ' + post_code.value;

    if(city.value === "" || street.value === '' || post_code.value === '') {
        let x = document.getElementById("snackbar");
        x.className = "show";
        x.innerHTML = 'Uzupełnij wszystkie dane!'
        setTimeout(() =>{ x.className = x.className.replace("show", ""); }, 3000);
        return;
    }

    const todayDate = new Date().toISOString().slice(0, 10);
    console.log(address)

    await fetch('http://localhost:8080/api/v1/order/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            status: 'KOMPLETOWANIE',
            date: todayDate,
            finalPrice: final,
            address: address,
            userId: localStorage.getItem('user_id')
        })
    }).then(
       
    ).then( json => {
        var order_id;
        getOrderId()
        .then(order_id => {
        getCartProducts()
            .then(cart => {
                console.log(cart[0].cart)
                    for(let i = 0; i<cart.length; i++) {
                        console.log(cart)
                    fetch('http://localhost:8080/api/v1/orderproduct/', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            quantity: cart[i].cartProduct.quantity,
                            price: cart[i].product.price,
                            orderId: order_id[0].id,
                            productId: cart[i].product.id
                        })
                    })
                }
            })
        })
    }).catch(error => {
        console.log(error);
    });

}

const authenticate = document.getElementById("order");
authenticate.addEventListener("click", addOrder);