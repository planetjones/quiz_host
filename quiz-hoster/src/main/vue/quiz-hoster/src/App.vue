<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark thin-header">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">QUIZ HOSTER</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><RouterLink to="/">Home </RouterLink></li>
                <li class="nav-item"> <RouterLink to="/about">About</RouterLink></li>
            </ul>
        </div>
    </div>
</nav>

  <main class="container-fluid">
    <RouterView />
  </main>


  <footer class="container-fluid">
    Footer
  </footer>
</template>

<style scoped></style>
