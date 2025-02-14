const d = document;
const urlParams = new URLSearchParams(window.location.search);

window.onload = function () {
    const name = urlParams.get("name");
    const lastName = urlParams.get("lastName");
    const phone = urlParams.get("phone");

    d.querySelector("#lblName").value = name;
    d.querySelector("#lblLastName").value = lastName;
    d.querySelector(("#lblPhone")).value = phone;
};

d.querySelector("#btnUpdate").addEventListener("click", function (e) {
    const user = {
        id: urlParams.get("id"),
        name: d.querySelector("#lblName").value,
        lastName: d.querySelector("#lblLastName").value,
        phone: d.querySelector("#lblPhone").value
    };
    console.log(user);
    fetch("http://localhost:8080/FirstWeb/edit", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    })
            .then(response => response.json())
            .then(response => console.log(response))
            .catch(error => console.log(error));
});
