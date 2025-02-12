const d = document;

d.querySelector("#btnSubmit").addEventListener("click", function (e) {
    const userData = {
        name: d.querySelector("#lblName").value,
        lastName: d.querySelector("#lblLastName").value,
        phone: d.querySelector("#lblPhone").value
    };
    console.log(userData);
    fetch("users", {
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

