const API_BASE_URL = 'http://localhost:8080';

async function fetchData(endpoint) {
    try {
        const response = await fetch(`${API_BASE_URL}/${endpoint}`);
        if (!response.ok) {
            throw new Error(`Error: ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        console.error('API fetch error:', error);
        return null;
    }
}