let usersJson;
window.addEventListener("load", (e) => {
    fetch("SvUsers", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
            .then(response => {
                response.json();
                usersJson = response;
            })
            .then(data => console.log("Response of the server", data.message))
            .catch(error => {
                console.log("Error ", error);
            });
            
});

