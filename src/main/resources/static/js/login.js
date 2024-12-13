document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector("form");

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        const username = form.elements["username"].value;
        const password = form.elements["password"].value;

        fetch(form.action, {
            method: form.method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, password })
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = "/dashboard"; // Redirect to a dashboard or homepage on success
                } else {
                    alert("Invalid login. Please try again.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("An error occurred. Please try again.");
            });
    });
});