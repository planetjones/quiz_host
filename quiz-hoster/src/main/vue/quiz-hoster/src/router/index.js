import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/AdminView.vue'),
    },
    {
      path: '/admin/:quizId',
      name: 'Admin',
      component: () => import('../views/AdminQuizView.vue'),
    },
    {
      path: '/adminLobby/:quizSessionId',
      name: 'AdminLobby',
      component: () => import('../views/AdminLobby.vue'),
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
    },
  ],
})

export default router
