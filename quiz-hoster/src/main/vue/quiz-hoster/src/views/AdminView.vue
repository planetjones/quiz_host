<script setup>
import { ref, onMounted } from 'vue'

const data = ref(null)
const error = ref(null)

import { inject } from 'vue';

const API_BASE_URL = 'http://127.0.0.1:8080';

async function fetchData() {
    try {
        const response = await fetch(`${API_BASE_URL}/api/quiz/list`)
        data.value = await response.json()
        console.log(data.value)
    } catch (e) {
        error.value = e.message
    }
}

function startQuiz(router, quizId) {
    console.log('start quiz', quizId)

    fetch((`${API_BASE_URL}/api/quiz/begin`), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ quizId: quizId })
    })
        .then(response => {
            if (response.ok) {
                
                response.json().then(data => {
                    console.log('Quiz started successfully');
                    console.log(this.$router, data.id);
                    router.push({ name: 'AdminLobby', params: { quizSessionId: data.id } })
                });
            } else {
                console.error('Failed to start quiz')
            }
        })
        .catch(error => {
            console.error('Error starting quiz:', error);
        });

}

onMounted(() => {
    fetchData()
})

</script>

<template>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6">
                <div class="alert alert-danger" role="alert" v-if="error">
                    {{ error }}
                </div>
                <h2>It's time to choose a quiz</h2>
                <p>Select the quiz to start from the selection below</p>
            </div>
        </div>
    </div>

    <div class="row" v-if="data">
        <div class="col-sm-6 mb-3 " v-for="item in data" :key="item.id">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">{{ item.description }}</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <a href="#" class="btn btn-primary" @click="startQuiz(this.$router, item.id)">Play now</a>
                </div>
            </div>
        </div>
    </div>
</template>

<style></style>