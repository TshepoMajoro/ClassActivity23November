function createResourse(url, resource){
    fetch(url,
        {
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(resource)
        }
    )
        .then(response => {
            const output = document.querySelector(".result");
            if(response.ok){
                console.log(`You have successfully created a new resource, ${resource.name}.`);
                output.innerHTML = `You have successfully created a new resource, ${resource.name}.`;
            } else {
                output.innerHTML = "Failed to create a new resource!";
            }

        })
}

let url = "http://localhost:8080/api/people";
const form = document.querySelector("#registerForm");
form.addEventListener("submit",(event) => {
    event.preventDefault();

    const name = document.querySelector("#name").value;
    const email = document.querySelector("#email").value;
    const resource = {"name": name, "email": email};

    createResourse(url, resource);
})