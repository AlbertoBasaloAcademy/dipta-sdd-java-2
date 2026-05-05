const API_URL = 'http://localhost:8080';

export async function getHealthStatus() {
  try {
    const response = await fetch(`${API_URL}/health`);
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return await response.json();
  } catch (error) {
    console.error('There was a problem with the fetch operation:', error);
    return { status: 'DOWN', uptime: 'N/A' };
  }
}
