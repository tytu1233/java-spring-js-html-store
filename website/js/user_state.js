const store = document.querySelector("#login");
const orders = document.querySelector(".option");

const isAuthorized = async () => {

    try {

    const response = await fetch('http://localhost:8080/api/v1/authentication', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem('token')
        },
      })
    return response.json();
    } catch(e) {
        console.log(e)
        return Promise.reject(e);
    }
}

if(localStorage.getItem('token')) {
isAuthorized()
.then(response => {

  if(response.response === 'pass'){
        store.innerHTML = `
        <a>
            <img src="img/login.svg" alt="log in"/>
            <p style="font-size: 10px; cursor:pointer" onclick="logoutUser()">Wyloguj się</p>
        </a>
        `

        orders.innerHTML = `
        <a href="orders.html">
            <img src="img/orders.svg" alt="orders"/>
            <p style="font-size: 10px; cursor:pointer"">Zamówienia</p>
        </a>
        <a href="cart.html">
          <img width="24" height="24"src="img/cart.svg" alt="cart"/>
          <p id="cart" style="font-size: 10px;">Twój koszyk</p>
      </a>
        `
  } else {
    store.innerHTML = `
    <a href="login.html">
        <img src="img/login.svg" alt="log in"/>
        <p style="font-size: 10px;">Zaloguj się</p>
    </a>
    `;
  }

}).catch(error =>{
    console.log(error);
})
} else {
    store.innerHTML = `
    <a href="login.html">
        <img src="img/login.svg" alt="log in"/>
        <p style="font-size: 10px;">Zaloguj się</p>
    </a>
    `;
}