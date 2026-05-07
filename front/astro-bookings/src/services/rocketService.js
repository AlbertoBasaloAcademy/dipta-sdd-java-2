const API_URL = 'http://localhost:8080/api/rockets';

export async function getAllRockets() {
  const response = await fetch(API_URL);
  if (!response.ok) throw new Error('Failed to fetch rockets');
  return await response.json();
}

export async function createRocket(rocket) {
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(rocket)
  });
  if (!response.ok) throw new Error('Failed to create rocket');
  return await response.json();
}

export async function updateRocket(id, rocket) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(rocket)
  });
  if (!response.ok) throw new Error('Failed to update rocket');
  return await response.json();
}

export async function decommissionRocket(id) {
  const response = await fetch(`${API_URL}/${id}/decommission`, {
    method: 'PATCH'
  });
  if (!response.ok) throw new Error('Failed to decommission rocket');
}
