<script setup>
import { useRoute } from 'vue-router';
import { ref } from 'vue';

const API_BASE_URL = 'http://127.0.0.1:8080';
const WEB_BASE_URL = 'http://127.0.0.1:5173';

const route = useRoute();
const quizSessionId = route.params.quizSessionId;
const quizUrl = WEB_BASE_URL + `/play/` + quizSessionId;
console.log('Quiz Session ID:', quizUrl);

const players = ref([]);
const emojis = ["👻", "😺", "🐉", "🦄", "🐼", "🐸", "🦊", "🐵", "🐧", "🐳"];

const addPlayer = (name) => {
  const randomEmoji = emojis[Math.floor(Math.random() * emojis.length)];
  players.value.push({ name, emoji: randomEmoji });
};

const startQuiz = () => {
      console.log('Quiz started!');
}

const client = new StompJs.Client({
  brokerURL: 'ws://localhost:8080/web-socket',
  debug: function (str) {
    console.log(str);
  },
  reconnectDelay: 5000,
  heartbeatIncoming: 4000,
  heartbeatOutgoing: 4000,
});

client.onConnect = function (frame) {
  console.log('connected to Stomp');

  client.subscribe('/topic/' + quizSessionId, message => {
    console.log(`Received_x: ${message.body}`);
    const messageBody = JSON.parse(message.body);

    if (messageBody.eventType === 'PLAYER_REGISTERED') {
      const playerName = messageBody.playerName;
      addPlayer(playerName);
    }
  }
  );

};

client.onStompError = function (frame) {
  console.log('Broker reported error: ' + frame.headers['message']);
  console.log('Additional details: ' + frame.body);
};

client.activate();

</script>

<template>

  <div class="container mt-4 text-center">

    <div class="d-flex justify-content-between align-items-center bg-light p-3 rounded">
      <div>
        <strong>Join at <a :href="quizUrl" target="_blank">{{ quizUrl }}</a></strong>
      </div>
      <div class="fs-1 fw-bold"></div>
      <div>
        <img :src="`${API_BASE_URL}/api/quiz/image?quizUrl=${quizUrl}`" alt="QR Code" class="qr-code">
      </div>
      <a></a>
    </div>

    <h1 class="mt-4 fw-bold text-primary">Quiz Lobby</h1>

    <div class="players-container mt-3">
      <div v-for="(player, index) in players" :key="index" class="player-badge">
        <span class="player-icon">{{ player.emoji }}</span>
        <span class="player-name">{{ player.name }}</span>
      </div>
    </div>

    <div class="alert alert-warning text-center" role="alert" v-show="players.length == 0">
      Waiting for players...
    </div>
  

  <div class="mt-5">
    <button type="button" class="btn btn-success btn-lg" v-show="players.length != 0" @click="startQuiz">
      Start Quiz
    </button>
  </div>

</div>

</template>

<style>
.qr-code {
  width: 100px;
  height: 100px;
}

.players-container {
  display: flex;
  justify-content: center;
  gap: 10px;
  flex-wrap: wrap;
}

.player-badge {
  background: #0033cc;
  color: white;
  padding: 10px 15px;
  border-radius: 10px;
  font-weight: bold;
}

.ghost-icon {
  margin-right: 5px;
}

.waiting-message {
  background: white;
  padding: 10px;
  border-radius: 5px;
  display: inline-block;
}
</style>
