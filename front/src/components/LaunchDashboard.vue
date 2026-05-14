<template>
  <div class="launch-dashboard">
    <h2 class="headline-lg">Launch Dashboard</h2>
    <div v-if="error" class="error">{{ error }}</div>
    
    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>Rocket</th>
            <th>Launch Date</th>
            <th>Price</th>
            <th>Min Occ.</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="launch in processedLaunches" :key="launch.id">
            <td class="font-bold">{{ launch.rocketId }}</td>
            <td>{{ launch.launchDate }}</td>
            <td>
              <span class="text-secondary">{{ launch.price }}€</span>
            </td>
            <td>{{ launch.minOccupancy }}</td>
            <td>
              <span :class="['status-badge', getStatusClass(launch.status)]">
                {{ launch.status }}
              </span>
            </td>
            <td>
              <div class="actions">
                <button 
                  v-if="launch.canConfirm" 
                  class="btn-primary-sm"
                  @click="handleUpdateStatus(launch.id, 'CONFIRMED')"
                >
                  Confirm
                </button>
                <button 
                  v-if="launch.canComplete" 
                  class="btn-secondary-sm"
                  @click="handleUpdateStatus(launch.id, 'Completed')"
                >
                  Complete
                </button>
                <button 
                  v-if="launch.canCancel" 
                  class="btn-outline-sm"
                  @click="handleUpdateStatus(launch.id, 'CANCELLED')"
                >
                  Cancel
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, defineExpose } from 'vue';
import { getAllLaunches, updateLaunchStatus } from '../services/launchService';
import { notify } from '../services/notificationService';
import { useErrorHandler } from '../composables/useErrorHandler';

const launches = ref([]);
const { error, handleError, clearError } = useErrorHandler();

const processedLaunches = computed(() => {
  return launches.value.map(launch => ({
    ...launch,
    canConfirm: launch.status === 'CREATED',
    canComplete: launch.status === 'CONFIRMED',
    canCancel: launch.status !== 'CANCELLED' && launch.status !== 'Completed'
  }));
});

const getStatusClass = (status) => {
  switch (status) {
    case 'CREATED': return 'status-created';
    case 'CONFIRMED': return 'status-confirmed';
    case 'Completed': return 'status-completed';
    case 'CANCELLED': return 'status-cancelled';
    default: return '';
  }
};

const fetchLaunches = async () => {
  try {
    launches.value = await getAllLaunches();
    clearError();
  } catch (error) {
    handleError('Failed to load launches', error);
  }
};

const handleUpdateStatus = async (id, status) => {
  try {
    await updateLaunchStatus(id, status);
    notify(`Launch status updated to ${status}`);
    await fetchLaunches();
  } catch (error) {
    handleError(`Failed to update status to ${status}`, error);
  }
};

onMounted(fetchLaunches);

defineExpose({ fetchLaunches });
</script>

<style scoped>
.launch-dashboard {
  text-align: left;
}

.headline-lg {
  margin-bottom: 32px;
  color: var(--primary);
  border-bottom: 1px solid var(--outline-variant);
  padding-bottom: 16px;
}

.table-container {
  overflow-x: auto;
}

.font-bold {
  font-weight: 600;
  color: var(--primary);
}

.text-secondary {
  color: var(--secondary);
  font-weight: 500;
}

.actions {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
}

.status-badge {
  padding: 4px 12px;
  border-radius: var(--radius-full);
  font-size: 12px;
  font-weight: 600;
  text-transform: uppercase;
}

.status-created { background-color: var(--surface-container-high); color: var(--on-surface); }
.status-confirmed { background-color: var(--secondary-container); color: var(--on-secondary-container); }
.status-completed { background-color: #4caf50; color: white; }
.status-cancelled { background-color: var(--error-container); color: var(--on-error-container); }

.btn-primary-sm, .btn-secondary-sm, .btn-outline-sm {
  padding: 6px 12px;
  font-size: 12px;
  font-weight: 600;
  border-radius: var(--radius-md);
  cursor: pointer;
  transition: all 0.2s;
  text-transform: uppercase;
  font-family: var(--font-header);
}

.btn-primary-sm {
  background-color: var(--primary);
  color: var(--on-primary);
  border: none;
}

.btn-primary-sm:hover {
  background-color: var(--primary-fixed);
}

.btn-secondary-sm {
  background-color: var(--secondary);
  color: var(--on-secondary);
  border: none;
}

.btn-outline-sm {
  background-color: transparent;
  color: var(--on-surface);
  border: 1px solid var(--outline-variant);
}

.btn-outline-sm:hover {
  border-color: var(--error);
  color: var(--error);
}
</style>
