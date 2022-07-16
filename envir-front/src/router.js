import Vue from 'vue'
import Router from 'vue-router'
import Manage from './views/Manage.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'Manage',
      component: Manage,
      redirect: "/home",
      children:[
        {path: 'home', name: 'Home', component: () => import('@/views/Home.vue')},
        {path: 'list', name: 'List', component: () => import('@/views/List.vue')},
        {path: 'data', name: 'Data', component: () => import('@/views/DataProcess.vue')},
      ]
    }
  ]
})
