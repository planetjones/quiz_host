<script setup>
import { useRoute } from 'vue-router';
import { ref } from 'vue';

const API_BASE_URL = 'http://127.0.0.1:8080';

const route = useRoute();
const quizSessionId = route.params.quizSessionId;

const playerName = ref('');
const isDisabled = ref(false);
const quizUrl = ref('');

isDisabled.value = false;

const startGame = () => {
  if (!playerName.value) 
    return;

    const registerUrl = API_BASE_URL + `/api/player/registerForQuiz`;

    fetch(registerUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        playerName: playerName.value,
        quizSessionId: quizSessionId
      })
    })
    .then(response => response.json())
    .then(data => {

      if(data == 'PLAYER_REGISTERED') {
        quizUrl.value = WEB_BASE_URL + `/play/` + quizSessionId;
      }

      
    })
    .catch((error) => {
      console.error('Error:', error);
      isDisabled.value = false;
    });
  isDisabled.value = true;
};



</script>

<template>

<div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="d-flex justify-content-center">
          <div class="alert alert-warning text-center" role="alert"
            v-show="isDisabled"
          >
            Waiting for other players to join
          </div>
        </div>
        <div class="card p-4 shadow"  v-show="!isDisabled">
          <h3 class="text-center mb-3">Enter Player Name</h3>
          <form @submit.prevent="startGame">
            <div class="mb-3">
              <input 
                type="text" 
                class="form-control" 
                placeholder="Player Name" 
                v-model="playerName"
                :disabled="isDisabled"
                required
              />
            </div>
            <button type="submit" class="btn btn-primary w-100" :disabled="isDisabled">
              Play
            </button>
          </form>
          <div v-if="quizUrl" class="mt-3 text-center">
            <a :href="quizUrl" target="_blank">{{ quizUrl }}</a>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<style>


  </style>
