<template>
  <div class="launch-form">
    <h3 class="headline-md">Schedule New Launch</h3>
    <div v-if="error" class="error">{{ error }}</div>
    <form @submit.prevent="handleSubmit">
      <div class="form-grid">
        <div class="form-group">
          <label>Rocket</label>
          <select v-model="form.rocketId" class="select-field" required>
            <option v-for="rocket in rockets" :key="rocket.id" :value="rocket.id">
              {{ rocket.name }} (Capacity: {{ rocket.capacity }})
            </option>
          </select>
        </div>
        <div class="form-group">
          <label>Launch Date</label>
          <input v-model="form.launchDate" class="input-field" type="date" required />
        </div>
        <div class="form-group">
          <label>Price</label>
          <input v-model.number="form.price" class="input-field" type="number" min="0" step="0.01" required />
        </div>
        <div class="form-group">
          <label>Min Occupancy</label>
          <input v-model.number="form.minOccupancy" class="input-field" type="number" min="1" required />
        </div>
      </div>
      <div class="form-actions mt-6">
        <button class="btn-primary" type="submit">Schedule Launch</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted, defineEmits } from 'vue';
import { getAllRockets } from '../services/rocketService';
import { createLaunch } from '../services/launchService';
import { notify } from '../services/notificationService';
import { useErrorHandler } from '../composables/useErrorHandler';
import { INITIAL_LAUNCH_FORM } from '../constants';

const emit = defineEmits(['launch-created']);

const rockets = ref([]);
const form = ref({ ...INITIAL_LAUNCH_FORM });
const { error, handleError, clearError } = useErrorHandler();

const fetchRockets = async () => {
  try {
    const allRockets = await getAllRockets();
    rockets.value = allRockets.filter(r => !r.decommissioned);
    if (rockets.value.length > 0 && !form.value.rocketId) {
      form.value.rocketId = rockets.value[0].id;
    }
    clearError();
  } catch (error) {
    handleError('Failed to load rockets', error);
  }
};

const handleSubmit = async () => {
  try {
    await createLaunch(form.value);
    notify('Launch scheduled successfully!');
    emit('launch-created');
    resetForm();
  } catch (error) {
    handleError('Failed to schedule launch', error);
  }
};

const resetForm = () => {
  form.value = {
    ...INITIAL_LAUNCH_FORM,
    rocketId: rockets.value.length > 0 ? rockets.value[0].id : ''
  };
  clearError();
};

onMounted(fetchRockets);
</script>

<style scoped>
.launch-form {
  text-align: left;
}

.headline-md {
  margin-bottom: 24px;
  color: var(--primary);
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr;
  gap: 20px;
}

@media (min-width: 768px) {
  .form-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (min-width: 1024px) {
  .form-grid {
    grid-template-columns: 2fr 1.5fr 1fr 1fr;
  }
}

.form-group {
  display: flex;
  flex-direction: column;
}

.mt-6 {
  margin-top: 24px;
}

.input-field, .select-field {
  width: 100%;
}
</style>
