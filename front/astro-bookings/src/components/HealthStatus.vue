<template>
  <div class="health-status">
    <h3>Backend Status</h3>
    <div v-if="loading">Checking status...</div>
    <div v-else>
      <p>Status: <span :class="statusClass">{{ health.status }}</span></p>
      <p>Uptime: {{ health.uptime }}</p>
    </div>
  </div>
</template>

<script>
import { getHealthStatus } from '../services/healthService';

export default {
  name: 'HealthStatus',
  data() {
    return {
      health: {
        status: 'UNKNOWN',
        uptime: 'N/A'
      },
      loading: true
    };
  },
  async created() {
    this.health = await getHealthStatus();
    this.loading = false;
  },
  computed: {
    statusClass() {
      return {
        'status-up': this.health.status === 'UP',
        'status-down': this.health.status === 'DOWN',
        'status-unknown': this.health.status === 'UNKNOWN'
      };
    }
  }
};
</script>

<style scoped>
.health-status {
  border: 1px solid #ddd;
  padding: 10px;
  border-radius: 8px;
  max-width: 300px;
  margin: 20px auto;
}
.status-up {
  color: green;
  font-weight: bold;
}
.status-down {
  color: red;
  font-weight: bold;
}
.status-unknown {
  color: gray;
}
</style>
