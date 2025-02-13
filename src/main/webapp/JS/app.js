const d = document;

d.querySelector("#btnSubmit").addEventListener("click", function (e) {
    const userData = {
        name: d.querySelector("#lblName").value,
        lastName: d.querySelector("#lblLastName").value,
        phone: d.querySelector("#lblPhone").value
    };
    console.log(userData);
    fetch("http://localhost:8080/FirstWeb/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userData)
    })
            .then(response => response.json())
            .then(data => {
                alert("Response of the server: " + data.message);
            })
            .catch(error => {
                console.log("Request Error: ", error);
            });
});

d.querySelector("#btnDelete").addEventListener("click", function (e) {
    const id = d.querySelector("#IDtoDelete").value;
    fetch(`http://localhost:8080/FirstWeb/delete?id=${id}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
    })
            .then(response => response.json())
            .then(response => console.log(response))
            .catch(error => console.log(error));

});

