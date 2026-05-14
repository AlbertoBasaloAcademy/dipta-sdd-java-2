<template>
  <div class="app-container">
    <header class="app-header">
      <h1 class="headline-xl">AstroBookings</h1>
      <p class="body-lg">Celestial Journey Management</p>
    </header>
    <main class="app-content">
      <NotificationToast />
      <HealthStatus />
      <div class="glass-panel">
        <RocketFleet />
      </div>
      <div class="glass-panel mt-8">
        <LaunchForm @launch-created="refreshLaunches" />
      </div>
      <div class="glass-panel mt-8">
        <LaunchDashboard ref="dashboard" />
      </div>
    </main>
  </div>
</template>

<script setup>
import NotificationToast from './components/NotificationToast.vue'
import HealthStatus from './components/HealthStatus.vue'
import RocketFleet from './components/RocketFleet.vue'
import LaunchForm from './components/LaunchForm.vue'
import LaunchDashboard from './components/LaunchDashboard.vue'
import { ref } from 'vue'

const dashboard = ref(null)
const refreshLaunches = () => {
  if (dashboard.value) {
    dashboard.value.fetchLaunches()
  }
}
</script>

<style>
.app-container {
  max-width: var(--container-max);
  margin: 0 auto;
  padding: var(--margin-mobile);
}

@media (min-width: 1024px) {
  .app-container {
    padding: var(--margin-desktop) var(--gutter);
  }
}

.app-header {
  text-align: center;
  margin-bottom: 64px;
}

.app-content {
  display: flex;
  flex-direction: column;
  gap: 32px;
}

.mt-8 {
  margin-top: 64px;
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
