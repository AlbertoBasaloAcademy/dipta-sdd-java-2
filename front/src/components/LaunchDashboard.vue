<template>
  <div class="launch-dashboard">
    <h2>Launch Dashboard</h2>
    <div v-if="error" class="error">{{ error }}</div>
    
    <table>
      <thead>
        <tr>
          <th>Rocket ID</th>
          <th>Date</th>
          <th>Price</th>
          <th>Min Occupancy</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="launch in launches" :key="launch.id">
          <td>{{ launch.rocketId }}</td>
          <td>{{ launch.date }}</td>
          <td>{{ launch.price }}</td>
          <td>{{ launch.minOccupancy }}</td>
          <td>{{ launch.status }}</td>
          <td>
            <button 
              v-if="launch.status === 'Created'" 
              @click="handleUpdateStatus(launch.id, 'Confirmed')"
            >
              Confirm
            </button>
            <button 
              v-if="launch.status === 'Confirmed'" 
              @click="handleUpdateStatus(launch.id, 'Completed')"
            >
              Complete
            </button>
            <button 
              v-if="launch.status !== 'Cancelled' && launch.status !== 'Completed'" 
              @click="handleUpdateStatus(launch.id, 'Cancelled')"
            >
              Cancel
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted, defineExpose } from 'vue';
import { getAllLaunches, updateLaunchStatus } from '../services/launchService';
import { notify } from '../services/notificationService';

const launches = ref([]);
const error = ref(null);

const fetchLaunches = async () => {
  try {
    launches.value = await getAllLaunches();
  } catch (e) {
    error.value = 'Failed to load launches';
  }
};

const handleUpdateStatus = async (id, status) => {
  try {
    await updateLaunchStatus(id, status);
    notify(`Launch status updated to ${status}`);
    await fetchLaunches();
  } catch (e) {
    error.value = `Failed to update status to ${status}`;
    notify(`Failed to update status to ${status}`, 'error');
  }
};

onMounted(fetchLaunches);

defineExpose({ fetchLaunches });
</script>

<style scoped>
.launch-dashboard {
  margin-top: 20px;
}
.error {
  color: red;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}
button {
  margin-right: 5px;
}
</style>
