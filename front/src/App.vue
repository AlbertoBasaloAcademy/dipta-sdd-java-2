<template>
  <NotificationToast />
  <img alt="Vue logo" src="./assets/logo.png">
  <HealthStatus />
  <RocketFleet />
  <hr />
  <LaunchForm @launch-created="refreshLaunches" />
  <LaunchDashboard ref="dashboard" />
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
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
