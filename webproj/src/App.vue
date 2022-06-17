<template>
  <div id="app">
    <app-nav></app-nav>
    <router-view></router-view>
    <app-footer></app-footer>
    
  </div>
</template>

<script>


import Footer from './components/Footer'
import Navigation from './components/Navigation'
import axios from "axios"


export default {
  name: 'app',
  components: {
    'app-footer':Footer,
    'app-nav' : Navigation
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<style>
</style>
