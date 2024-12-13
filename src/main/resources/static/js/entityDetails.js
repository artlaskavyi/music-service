document.addEventListener('DOMContentLoaded', async () => {
    const params = new URLSearchParams(window.location.search);
    const entityType = params.get('type');
    const entityId = params.get('id');
    const detailsContainer = document.getElementById('detailsContainer');

    if (!entityType || !entityId) {
        detailsContainer.innerHTML = '<p>Invalid entity.</p>';
        return;
    }

    const data = await fetchData(`${entityType}/${entityId}`);
    if (!data) {
        detailsContainer.innerHTML = '<p>Entity not found.</p>';
        return;
    }

    displayEntityDetails(data);
});

function displayEntityDetails(data) {
    const detailsContainer = document.getElementById('detailsContainer');
    detailsContainer.innerHTML = '';

    for (const [key, value] of Object.entries(data)) {
        const item = document.createElement('p');
        item.textContent = `${capitalize(key)}: ${value}`;
        detailsContainer.appendChild(item);
    }
}

function capitalize(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
}