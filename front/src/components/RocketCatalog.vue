<template>
  <div class="catalog">
    <h3 class="headline-md">Rocket Catalog</h3>
    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Capacity</th>
            <th>Range</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="rocket in rockets" :key="rocket.id">
            <td class="font-bold">{{ rocket.name }}</td>
            <td>{{ rocket.capacity }}</td>
            <td>
              <span class="badge">{{ rocket.range }}</span>
            </td>
            <td>
              <span :class="['status-dot', rocket.decommissioned ? 'bg-error' : 'bg-success']"></span>
              {{ rocket.decommissioned ? 'Decommissioned' : 'Operational' }}
            </td>
            <td>
              <div class="actions">
                <button class="btn-icon" @click="$emit('edit', rocket)" :disabled="rocket.decommissioned" title="Edit">
                  ✎
                </button>
                <button class="btn-icon text-error" @click="$emit('decommission', rocket.id)" :disabled="rocket.decommissioned" title="Decommission">
                  ✖
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
import { defineProps, defineEmits } from 'vue';

defineProps({
  rockets: {
    type: Array,
    required: true
  }
});

defineEmits(['edit', 'decommission']);
</script>

<style scoped>
.headline-md {
  margin-bottom: 16px;
  color: var(--primary);
}

.table-container {
  overflow-x: auto;
}

.font-bold {
  font-weight: 600;
  color: var(--primary);
}

.actions {
  display: flex;
  gap: 8px;
}

.btn-icon {
  background: transparent;
  border: 1px solid var(--outline-variant);
  color: var(--on-surface);
  border-radius: var(--radius-md);
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.2s;
}

.btn-icon:hover:not(:disabled) {
  background-color: var(--surface-container-high);
  border-color: var(--secondary);
  color: var(--secondary);
}

.btn-icon:disabled {
  opacity: 0.3;
  cursor: not-allowed;
}

.text-error {
  color: var(--error);
}

.text-error:hover:not(:disabled) {
  color: var(--on-error-container);
  background-color: var(--error-container);
  border-color: var(--error);
}

.status-dot {
  display: inline-block;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  margin-right: 8px;
}

.bg-success {
  background-color: #4caf50;
  box-shadow: 0 0 10px rgba(76, 175, 80, 0.5);
}

.bg-error {
  background-color: var(--error);
}
</style>
