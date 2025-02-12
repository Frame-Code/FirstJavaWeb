const d = document;

window.addEventListener("load", getUsers());

async function getUsers() {
    try {
        const response = await fetch("users", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }

        });
        let data = await response.json();
        console.log(data);
        loadUsers(data);
    } catch (error) {
        console.log("Error: ", error);
    }
}
function loadUsers(data) {
    let divUsers = d.querySelector("#users");
    data.json().foreach(obj => {
        divUsers.innerHTML += `<br/>
        <p>name: ${obj.name}</p>
        <p>last name: ${obj.lastName}</p>
        <p>last name: ${obj.phone}</p>
        <br/>
`;
    });
}


