document.addEventListener('DOMContentLoaded', () => {
    const resultsContainer = document.getElementById('resultsContainer');
    const searchQueryElement = document.getElementById('searchQuery');

    // Extract the query from the URL
    const urlParams = new URLSearchParams(window.location.search);
    const query = urlParams.get('query');

    // Display the search query
    if (searchQueryElement) {
        searchQueryElement.textContent = query || 'No search query provided';
    }

    // Fetch and display results if a query is present
    if (query) {
        fetchSearchResults(query);
    }

    async function fetchSearchResults(query) {
        try {
            const response = await fetch(`/search?query=${encodeURIComponent(query)}`, {
                headers: { 'Accept': 'application/json' }
            });
            if (!response.ok) {
                throw new Error('Failed to fetch search results');
            }
            const data = await response.json();
            displayResults(data);
        } catch (error) {
            console.error('Error fetching search results:', error);
            resultsContainer.innerHTML = '<p>Error loading search results. Please try again later.</p>';
        }
    }

    function displayResults(data) {
        resultsContainer.innerHTML = ''; // Clear previous results

        if (!data || Object.values(data).every(list => list.length === 0)) {
            resultsContainer.innerHTML = '<p>No results found.</p>';
            return;
        }

        for (const [entityType, entities] of Object.entries(data)) {
            if (entities.length > 0) {
                const section = document.createElement('div');
                section.innerHTML = `<h3>${capitalize(entityType)}</h3>`;

                entities.forEach(entity => {
                    const link = document.createElement('a');
                    if (entityType === 'artists') {
                        link.href = `entityDetails.html?type=artist&id=${entity.artistId}`;
                        link.textContent = entity.artistName;
                    }
                    section.appendChild(link);
                    section.appendChild(document.createElement('br'));
                });

                resultsContainer.appendChild(section);
            }
        }
    }
    function capitalize(str) {
        return str.charAt(0).toUpperCase() + str.slice(1);
    }
});