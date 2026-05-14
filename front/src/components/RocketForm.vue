<template>
  <form class="rocket-form" @submit.prevent="handleSubmit">
    <h3 class="headline-md">{{ isEditing ? 'Update' : 'Register' }} Rocket</h3>
    <div class="form-grid">
      <div class="form-group">
        <label>Name</label>
        <input v-model="form.name" class="input-field w-full" placeholder="Ex: Falcon Heavy" required />
      </div>
      <div class="form-group">
        <label>Capacity (1-9)</label>
        <input v-model.number="form.capacity" class="input-field w-full" type="number" min="1" max="9" required />
      </div>
      <div class="form-group">
        <label>Range</label>
        <select v-model="form.range" class="select-field" required>
          <option value="Earth">Earth</option>
          <option value="Moon">Moon</option>
          <option value="Mars">Mars</option>
        </select>
      </div>
    </div>
    <div class="form-actions">
      <button class="btn-primary" type="submit">{{ isEditing ? 'Update' : 'Register' }}</button>
      <button v-if="isEditing" class="btn-secondary" type="button" @click="$emit('cancel')">Cancel</button>
    </div>
  </form>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits } from 'vue';

const props = defineProps({
  modelValue: {
    type: Object,
    required: true
  },
  isEditing: {
    type: Boolean,
    default: false
  }
});

const emit = defineEmits(['update:modelValue', 'submit', 'cancel']);

const form = ref({ ...props.modelValue });

watch(() => props.modelValue, (newValue) => {
  if (JSON.stringify(newValue) !== JSON.stringify(form.value)) {
    form.value = { ...newValue };
  }
}, { deep: true });

watch(form, (newValue) => {
  if (JSON.stringify(newValue) !== JSON.stringify(props.modelValue)) {
    emit('update:modelValue', newValue);
  }
}, { deep: true });

const handleSubmit = () => {
  emit('submit');
};
</script>

<style scoped>
.rocket-form {
  margin-bottom: 32px;
}

.headline-md {
  margin-bottom: 24px;
  color: var(--primary);
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 20px;
  margin-bottom: 24px;
}

@media (min-width: 768px) {
  .form-grid {
    grid-template-columns: 2fr 1fr 1fr;
  }
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-actions {
  display: flex;
  gap: 12px;
}

.w-full {
  width: 100%;
}

.select-field {
  background-color: var(--surface-container-lowest);
  color: var(--on-surface);
  font-family: var(--font-body);
  font-size: 16px;
  border-radius: var(--radius-lg);
  padding: 12px;
  border: 1px solid var(--outline-variant);
  width: 100%;
}
</style>
