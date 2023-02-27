registerUser = async () => {
    let x = document.getElementById("snackbar");
    x.className = "show";
    
    const name = document.getElementById("name");
    const surname = document.getElementById("surname");
    const login = document.getElementById("username");
    const password = document.getElementById("password");
    const address = document.getElementById("address");
    const phone = document.getElementById("phone");

    await fetch('http://localhost:8080/api/v1/users', {
      method: 'POST',
      headers : {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
          name: name.value,
          surname: surname.value,
          login: login.value,
          password: password.value,
          address: address.value,
          phone: phone.value
      })
      }).then(result => {
          if(result.status === 500 || result.status === 400) {
            console.log("Błąd dodania użytkownika")
            x.innerHTML = 'Login lub telefon są już zajęte'
            setTimeout(() =>{ x.className = x.className.replace("show", ""); }, 3000);
          } else  {
            console.log("Wszystko się powiodło")
            x.innerHTML = 'Pomyślnie zarejestrowano użytkownika'
            setTimeout(() =>{ x.className = x.className.replace("show", ""); }, 3000);
          }
      } )
      .catch( err => {
          console.log(err);
      });
}

const register = document.getElementById("register");
register.addEventListener("click", registerUser);




const authenticateUser = async () => {

    let x = document.getElementById("snackbar");
    x.className = "show";

    const login = document.getElementById("authenticate_login");
    const password = document.getElementById("authenticate_password");

    await fetch('http://localhost:8080/api/v1/authentication', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        login: login.value, 
        password: password.value
      })
      }).then(
        response => response.json()
      ).then( json => {
        x.innerHTML = 'Niepoprawny login lub hasło';
            setTimeout(() =>{ x.className = x.className.replace("show", ""); }, 3000);
        if(json.userId !== -1 && json.token !== -1){
          localStorage.setItem('user_id', json.userId);
          localStorage.setItem('token', json.token);
            x.innerHTML = `Zalogowano<br>
            Przekierowanie do strony głównej`;
            setTimeout(() =>{ x.className = x.className.replace("show", ""); }, 3000);
            setTimeout(() =>{location.href="index.html"} , 2000); 
          }
      }).catch(error => {
        console.log(error);
      });
}

const authenticate = document.getElementById("authenticate");
authenticate.addEventListener("click", authenticateUser);