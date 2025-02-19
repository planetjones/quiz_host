<template>
  <div class="pico-background-yellow" v-if="data">
    <div v-for="item in data" :key="item.id" @click="$router.push({ name: 'Admin', params: { quizId: item.id }})">
      {{ item.description }}
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router';

const router = useRouter();

const data = ref(null)
const error = ref(null)

const API_BASE_URL = 'http://127.0.0.1:8080';

async function fetchData() {
  try {
    const response = await fetch(`${API_BASE_URL}/api/quiz/list`)
    data.value = await response.json()
  } catch (e) {
    error.value = e.message
  }
}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.pico-background-yellow {
  padding: 10px;
  margin-bottom: 10px;
}
</style>
