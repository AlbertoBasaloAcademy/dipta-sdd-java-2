const API_URL = 'http://localhost:8080/api/rockets';

export async function getAllRockets() {
  console.info('Fetching all rockets');
  const response = await fetch(API_URL);
  if (!response.ok) {
    console.error('Failed to fetch rockets');
    throw new Error('Failed to fetch rockets');
  }
  return await response.json();
}

export async function createRocket(rocket) {
  console.info('Creating new rocket', rocket);
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(rocket)
  });
  if (!response.ok) {
    console.error('Failed to create rocket');
    throw new Error('Failed to create rocket');
  }
  const data = await response.json();
  console.info('Rocket created successfully', data);
  return data;
}

export async function updateRocket(id, rocket) {
  console.info(`Updating rocket ${id}`, rocket);
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(rocket)
  });
  if (!response.ok) {
    console.error(`Failed to update rocket ${id}`);
    throw new Error('Failed to update rocket');
  }
  const data = await response.json();
  console.info(`Rocket ${id} updated successfully`, data);
  return data;
}

export async function decommissionRocket(id) {
  console.info(`Decommissioning rocket ${id}`);
  const response = await fetch(`${API_URL}/${id}/decommission`, {
    method: 'PATCH'
  });
  if (!response.ok) {
    console.error(`Failed to decommission rocket ${id}`);
    throw new Error('Failed to decommission rocket');
  }
  console.info(`Rocket ${id} decommissioned successfully`);
}
