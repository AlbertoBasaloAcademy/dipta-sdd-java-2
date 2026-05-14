<template>
  <div class="health-status glass-panel">
    <h3 class="label-md">Backend Systems</h3>
    <div v-if="loading" class="body-md">Pulsing systems...</div>
    <div v-else class="status-grid">
      <div class="status-item">
        <span class="label">Status</span>
        <span :class="['value', statusClass]">{{ health.status }}</span>
      </div>
      <div class="status-item">
        <span class="label">Uptime</span>
        <span class="value">{{ health.uptime }}</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { getHealthStatus } from '../services/healthService';
import { useErrorHandler } from '../composables/useErrorHandler';

const health = ref({
  status: 'UNKNOWN',
  uptime: 'N/A'
});
const loading = ref(true);
const { handleError } = useErrorHandler();

const statusClass = computed(() => ({
  'status-up': health.value.status === 'UP',
  'status-down': health.value.status === 'DOWN',
  'status-unknown': health.value.status === 'UNKNOWN'
}));

onMounted(async () => {
  try {
    health.value = await getHealthStatus();
  } catch (error) {
    handleError('Failed to check backend status', error);
    health.value = { status: 'DOWN', uptime: 'N/A' };
  } finally {
    loading.value = false;
  }
});
</script>

<style scoped>
.health-status {
  padding: 16px;
  max-width: fit-content;
  margin: 0 auto;
  border-radius: var(--radius-lg);
}

.label-md {
  margin-bottom: 12px;
  color: var(--secondary);
}

.status-grid {
  display: flex;
  gap: 24px;
}

.status-item {
  display: flex;
  flex-direction: column;
}

.label {
  font-size: 10px;
  text-transform: uppercase;
  color: var(--on-surface-variant);
  letter-spacing: 0.1em;
}

.value {
  font-size: 14px;
  font-weight: 600;
  font-family: var(--font-header);
}

.status-up {
  color: #4caf50;
  text-shadow: 0 0 10px rgba(76, 175, 80, 0.3);
}

.status-down {
  color: var(--error);
  text-shadow: 0 0 10px rgba(255, 180, 171, 0.3);
}

.status-unknown {
  color: var(--on-surface-variant);
}
</style>
