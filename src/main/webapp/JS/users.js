const d = document;
window.addEventListener("load", getUsers());

async function getUsers() {
    try {
        const response = await fetch("http://localhost:8080/FirstWeb/users", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }

        });
        const data = await response.json();
        loadUsers(data);
    } catch (error) {
        console.log("Error: ", error);
    }
}
function loadUsers(data) {
    let divUsers = d.querySelector("#users");
    for (let i = 0; i < data.length; i++) {
        divUsers.innerHTML += `<br/>
        <p>name: ${data[i].name}</p>
        <p>last name: ${data[i].lastName}</p>
        <p>phone: ${data[i].phone}</p>
        <br/>`;
    }
}


