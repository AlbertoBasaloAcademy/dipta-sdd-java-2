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

<script>
import { getAllRockets, createRocket, updateRocket, decommissionRocket } from '../services/rocketService';

export default {
  name: 'RocketFleet',
  data() {
    return {
      rockets: [],
      form: {
        name: '',
        capacity: 1,
        range: 'Earth'
      },
      editingId: null,
      error: null
    };
  },
  async created() {
    await this.fetchRockets();
  },
  methods: {
    async fetchRockets() {
      try {
        this.rockets = await getAllRockets();
      } catch (e) {
        this.error = 'Failed to load rockets';
      }
    },
    async handleSubmit() {
      try {
        if (this.editingId) {
          await updateRocket(this.editingId, this.form);
        } else {
          await createRocket(this.form);
        }
        await this.fetchRockets();
        this.resetForm();
      } catch (e) {
        this.error = 'Failed to save rocket';
      }
    },
    async handleDecommission(id) {
      try {
        await decommissionRocket(id);
        await this.fetchRockets();
      } catch (e) {
        this.error = 'Failed to decommission rocket';
      }
    },
    editRocket(rocket) {
      this.editingId = rocket.id;
      this.form = {
        name: rocket.name,
        capacity: rocket.capacity,
        range: rocket.range
      };
    },
    resetForm() {
      this.editingId = null;
      this.form = {
        name: '',
        capacity: 1,
        range: 'Earth'
      };
      this.error = null;
    }
  }
};
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
