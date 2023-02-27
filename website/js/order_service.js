const getOrders = () => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/order/orders/' + localStorage.getItem('user_id'))
            .then( async result => {

                const data = await result.json();
                resolve(data);


            } )
            .catch( err => {
                reject(err);
            } );
    } );
}

getOrderProducts = (order_product_id) => {
    return new Promise( (resolve, reject) => {
        fetch('http://localhost:8080/api/v1/orderproduct/user/' + order_product_id)
            .then( async result => {

                const data = await result.json();
                resolve(data);


            } )
            .catch( err => {
                reject(err);
            } );
    } );
}


getOrders()
    .then( products => {
        const wrapper = document.querySelector('.order_container');
        products.forEach( product => {
            console.log(product)
            wrapper.innerHTML += `
            <button type="button" class="collapsible">ID zamówienia: ${product.id}, adres: ${product.address}, ${product.finalPrice} zł, data: ${product.date}, status: ${product.status}</button>
            <div class="order_detail">
                    <h2>Lista produktów</h2>
                    <div id="orderas${product.id}" class="orderek">
                    </div>
            </div>
            `
            getProductInOrder(product.id)
                .then(orders => {
                    const wrapper2 = document.querySelector('#orderas'+product.id);
                        orders.forEach(order => {
                            console.log(order)
                            wrapper2.innerHTML += `
                                    
                                        <div class="test">
                                            <div class="order_products_image">
                                                <img class="img_details" alt="" src="${order.image}"/>
                                            </div>
                                            <div class="order_products_describe">
                                                <div class="order_products_data">
                                                    <div class="order_products_name"><span>${order.name}</span></div>
                                                    <div class="order_products_price"><span class="order_products_price">${order.price} zł</span></div>
                                                    <div class="order_products_price"><span class="order_products_price">Ilośc: ${order.quantity}</span></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            `
                        })
            })
        } );
        var coll = document.getElementsByClassName("collapsible");
        var i;
        
        for (i = 0; i < coll.length; i++) {
          coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "flex") {
              content.style.display = "none";
            } else {
              content.style.display = "flex";
            }
          });
        }

});


const getProductInOrder = async (orderId) => {

    try {
      const response = await fetch('http://localhost:8080/api/v1/productInOrder/' + orderId)
      return response.json();
      } catch(e) {
          return Promise.reject(e);
      }
    }