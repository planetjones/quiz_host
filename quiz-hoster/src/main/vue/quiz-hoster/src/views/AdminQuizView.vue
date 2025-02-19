<template>
  <div>
    <h1>Admin View for Quiz ID: {{ quizId }}</h1>
    <!-- Add your admin quiz content here -->
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { ref, onMounted } from 'vue';

const route = useRoute();
const quizId = ref(null);

onMounted(() => {
  quizId.value = route.params.quizId;
});
</script>
