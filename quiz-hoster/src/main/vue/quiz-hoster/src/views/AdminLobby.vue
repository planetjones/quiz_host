<script setup>
import { useRoute } from 'vue-router';

const route = useRoute();
const quizSessionId = route.params.quizSessionId;
const quizUrl = 'http://localhost:8080/play?quizSessionId=' + quizSessionId;
console.log('Quiz Session ID:', quizUrl);

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
                    const playerDiv = document.createElement('div');
                    playerDiv.innerHTML = playerName;
                    document.getElementById('player-container').appendChild(playerDiv);
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

<div class="container-fluid">
  <div class="col-md-12">
    <a :href="quizUrl">{{ quizUrl }}</a>
  </div>
</div>

</template>

<style>


  </style>
