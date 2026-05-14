<template>
  <form @submit.prevent="handleSubmit">
    <h3>{{ isEditing ? 'Update' : 'Register' }} Rocket</h3>
    <div>
      <label>Name:</label>
      <input v-model="form.name" required />
    </div>
    <div>
      <label>Capacity (1-9):</label>
      <input v-model.number="form.capacity" type="number" min="1" max="9" required />
    </div>
    <div>
      <label>Range:</label>
      <select v-model="form.range" required>
        <option value="Earth">Earth</option>
        <option value="Moon">Moon</option>
        <option value="Mars">Mars</option>
      </select>
    </div>
    <button type="submit">{{ isEditing ? 'Update' : 'Register' }}</button>
    <button v-if="isEditing" type="button" @click="$emit('cancel')">Cancel</button>
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
form {
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 15px;
}
form div {
  margin-bottom: 10px;
}
</style>
