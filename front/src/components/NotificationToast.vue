<template>
  <Transition name="slide">
    <div v-if="notificationState.visible" :class="['notification-toast', notificationState.type, 'glass-panel']">
      <div class="notification-content">
        <span class="icon">{{ notificationState.type === 'success' ? '✓' : '⚠' }}</span>
        <span class="message">{{ notificationState.message }}</span>
      </div>
    </div>
  </Transition>
</template>

<script setup>
import { notificationState } from '../services/notificationService';
</script>

<style scoped>
.notification-toast {
  position: fixed;
  bottom: 40px;
  right: 40px;
  padding: 16px 24px;
  border-radius: var(--radius-lg);
  color: var(--on-surface);
  z-index: 1000;
  min-width: 280px;
  border-left: 4px solid var(--primary);
}

.success {
  border-left-color: #4caf50;
}

.error {
  border-left-color: var(--error);
  color: var(--error);
}

.notification-content {
  display: flex;
  align-items: center;
  gap: 12px;
}

.icon {
  font-size: 20px;
  font-weight: bold;
}

.message {
  font-family: var(--font-body);
  font-size: 14px;
  font-weight: 500;
}

.slide-enter-active,
.slide-leave-active {
  transition: all 0.4s cubic-bezier(0.16, 1, 0.3, 1);
}

.slide-enter-from {
  opacity: 0;
  transform: translateX(100px);
}

.slide-leave-to {
  opacity: 0;
  transform: scale(0.9);
}
</style>
