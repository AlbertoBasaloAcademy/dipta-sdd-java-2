<template>
  <div class="rocket-fleet">
    <h2>Rockets Fleet Management</h2>
    
    <div v-if="error" class="error">{{ error }}</div>

    <form @submit.prevent="handleSubmit">
      <h3>{{ editingId ? 'Update' : 'Register' }} Rocket</h3>
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
      <button type="submit">{{ editingId ? 'Update' : 'Register' }}</button>
      <button v-if="editingId" type="button" @click="resetForm">Cancel</button>
    </form>

    <div class="catalog">
      <h3>Rocket Catalog</h3>
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
            <td>{{ rocket.name }}</td>
            <td>{{ rocket.capacity }}</td>
            <td>{{ rocket.range }}</td>
            <td>{{ rocket.decommissioned ? 'Decommissioned' : 'Operational' }}</td>
            <td>
              <button @click="editRocket(rocket)" :disabled="rocket.decommissioned">Edit</button>
              <button @click="handleDecommission(rocket.id)" :disabled="rocket.decommissioned">Decommission</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { getAllRockets, createRocket, updateRocket, decommissionRocket } from '../services/rocketService';
import { notify } from '../services/notificationService';

const rockets = ref([]);
const form = ref({
  name: '',
  capacity: 1,
  range: 'Earth'
});
const editingId = ref(null);
const error = ref(null);

const fetchRockets = async () => {
  try {
    rockets.value = await getAllRockets();
  } catch (e) {
    error.value = 'Failed to load rockets';
  }
};

const resetForm = () => {
  editingId.value = null;
  form.value = {
    name: '',
    capacity: 1,
    range: 'Earth'
  };
  error.value = null;
};

const handleSubmit = async () => {
  try {
    if (editingId.value) {
      await updateRocket(editingId.value, form.value);
      notify('Rocket updated successfully!');
    } else {
      await createRocket(form.value);
      notify('Rocket registered successfully!');
    }
    await fetchRockets();
    resetForm();
  } catch (e) {
    error.value = 'Failed to save rocket';
    notify('Failed to save rocket', 'error');
  }
};

const handleDecommission = async (id) => {
  try {
    await decommissionRocket(id);
    notify('Rocket decommissioned successfully');
    await fetchRockets();
  } catch (e) {
    error.value = 'Failed to decommission rocket';
    notify('Failed to decommission rocket', 'error');
  }
};

const editRocket = (rocket) => {
  editingId.value = rocket.id;
  form.value = {
    name: rocket.name,
    capacity: rocket.capacity,
    range: rocket.range
  };
};

onMounted(fetchRockets);
</script>

<style scoped>
.rocket-fleet {
  max-width: 800px;
  margin: 0 auto;
  text-align: left;
}
.error {
  color: red;
}
table {
  width: 100%;
  border-collapse: collapse;
}
th, td {
  border: 1px solid #ddd;
  padding: 8px;
}
form {
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 15px;
}
form div {
  margin-bottom: 10px;
}
</style>
