<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HealthStatus />
  <HelloWorld msg="Welcome to Your Vue.js App"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import HealthStatus from './components/HealthStatus.vue'

export default {
  name: 'App',
  components: {
    HelloWorld,
    HealthStatus
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
