const API_URL = 'http://localhost:8080/api/launches';

export async function getAllLaunches() {
  console.info('Fetching all launches');
  const response = await fetch(API_URL);
  if (!response.ok) {
    console.error('Failed to fetch launches');
    throw new Error('Failed to fetch launches');
  }
  return await response.json();
}

export async function getLaunchById(id) {
  console.info(`Fetching launch with id: ${id}`);
  const response = await fetch(`${API_URL}/${id}`);
  if (!response.ok) {
    console.error(`Failed to fetch launch with id: ${id}`);
    throw new Error('Failed to fetch launch');
  }
  return await response.json();
}

export async function createLaunch(launch) {
  console.info('Creating new launch', launch);
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(launch)
  });
  if (!response.ok) {
    console.error('Failed to create launch');
    throw new Error('Failed to create launch');
  }
  const data = await response.json();
  console.info('Launch created successfully', data);
  return data;
}

export async function updateLaunchStatus(id, status) {
  console.info(`Updating status for launch ${id} to ${status}`);
  const response = await fetch(`${API_URL}/${id}/status`, {
    method: 'PATCH',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(status)
  });
  if (!response.ok) {
    console.error(`Failed to update status for launch ${id}`);
    throw new Error('Failed to update launch status');
  }
  const data = await response.json();
  console.info(`Launch status updated successfully for ${id}`, data);
  return data;
}
