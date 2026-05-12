<template>
  <Transition name="fade">
    <div v-if="notificationState.visible" :class="['notification-toast', notificationState.type]">
      {{ notificationState.message }}
    </div>
  </Transition>
</template>

<script setup>
import { notificationState } from '../services/notificationService';
</script>

<style scoped>
.notification-toast {
  position: fixed;
  top: 20px;
  right: 20px;
  padding: 15px 25px;
  border-radius: 4px;
  color: white;
  font-weight: bold;
  z-index: 1000;
  box-shadow: 0 4px 6px rgba(0,0,0,0.1);
}

.success {
  background-color: #4caf50;
}

.error {
  background-color: #f44336;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
