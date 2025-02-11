const d = document;

d.querySelector("#btnSubmit").addEventListener("click", function (e) {
    e.preventDefault();
    const userData = {
        name: d.querySelector("#lblName").value,
        lastName: d.querySelector("#lblLastName").value,
        phone: d.querySelector("#lblPhone").value
    };
    
    fetch("SvUsers", {
        method: "POST",
        header: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userData)
    })
            .then(response => response.json())
            .then(data => {
                alert("Respuesta del servidor: " + data.message);
            })
                    .catch(error => {
                        console.log("Error en la peticion: ", error);
                    });
                    console.log("Terminado");
});

