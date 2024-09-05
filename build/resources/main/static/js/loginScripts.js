const mostrarInfo = document.getElementById('submit');

mostrarInfo.addEventListener('click', () => {
    const correoElectronico = document.getElementById('email').value;
    const url = `http://3.18.110.241:8080/api/v1/pandelovers/email?pandemail=${correoElectronico}`;

    // fetch para método get
    fetch(url)
        .then(response => response.json())
        .then(data => {
            userInfo.innerHTML = `
                <h3>Información del usuario:</h3>
                <p>Nombre de usuario: ${data.username}</p>
                <p>Correo electrónico: ${data.email}</p>
                <p>Contraseña: ${data.password}</p>
            `
        })
        .catch(error => {
            userInfo.innerHTML = `
                Usuario no encontrado
            `
            console.error(error)
        })
})