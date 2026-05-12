<template>
  <div class="launch-form">
    <h3>Schedule New Launch</h3>
    <div v-if="error" class="error">{{ error }}</div>
    <form @submit.prevent="handleSubmit">
      <div>
        <label>Rocket:</label>
        <select v-model="form.rocketId" required>
          <option v-for="rocket in rockets" :key="rocket.id" :value="rocket.id">
            {{ rocket.name }} (Capacity: {{ rocket.capacity }})
          </option>
        </select>
      </div>
      <div>
        <label>Date:</label>
        <input v-model="form.date" type="date" required />
      </div>
      <div>
        <label>Price:</label>
        <input v-model.number="form.price" type="number" min="0" step="0.01" required />
      </div>
      <div>
        <label>Min Occupancy:</label>
        <input v-model.number="form.minOccupancy" type="number" min="1" required />
      </div>
      <button type="submit">Schedule Launch</button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted, defineEmits } from 'vue';
import { getAllRockets } from '../services/rocketService';
import { createLaunch } from '../services/launchService';
import { notify } from '../services/notificationService';

const emit = defineEmits(['launch-created']);

const rockets = ref([]);
const form = ref({
  rocketId: '',
  date: '',
  price: 0,
  minOccupancy: 1
});
const error = ref(null);

const fetchRockets = async () => {
  try {
    const allRockets = await getAllRockets();
    rockets.value = allRockets.filter(r => !r.decommissioned);
    if (rockets.value.length > 0) {
      form.value.rocketId = rockets.value[0].id;
    }
  } catch (e) {
    error.value = 'Failed to load rockets';
  }
};

const handleSubmit = async () => {
  try {
    await createLaunch(form.value);
    notify('Launch scheduled successfully!');
    emit('launch-created');
    resetForm();
  } catch (e) {
    error.value = 'Failed to schedule launch';
    notify('Failed to schedule launch', 'error');
  }
};

const resetForm = () => {
  form.value = {
    rocketId: rockets.value.length > 0 ? rockets.value[0].id : '',
    date: '',
    price: 0,
    minOccupancy: 1
  };
  error.value = null;
};

onMounted(fetchRockets);
</script>

<style scoped>
.launch-form {
  border: 1px solid #ccc;
  padding: 15px;
  margin-bottom: 20px;
}
.error {
  color: red;
}
form div {
  margin-bottom: 10px;
}
label {
  display: inline-block;
  width: 120px;
}
</style>
