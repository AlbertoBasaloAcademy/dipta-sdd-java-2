<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HealthStatus />
  <RocketFleet />
  <HelloWorld msg="AstroBookings - Rockets Fleet Management"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import HealthStatus from './components/HealthStatus.vue'
import RocketFleet from './components/RocketFleet.vue'

export default {
  name: 'App',
  components: {
    HelloWorld,
    HealthStatus,
    RocketFleet
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
