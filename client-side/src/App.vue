<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <a href="/home" class="navbar-brand">
        <img src="./assets/logo.png" width="30" height="30" class="d-inline-block align-top"/>
        Vue.js
      </a>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <a href="/home" class="nav-link">
            Home
          </a>
        </li>
      </div>

      <div class="navbar-nav ml-auto" v-if="!currentUser">
        <li class="nav-item">
          <a href="/register" class="nav-link">
            Sign Up
          </a>
        </li>
        <li class="nav-item">
          <a href="/login" class="nav-link">
            Login
          </a>
        </li>
      </div>

      <div class="navbar-nav ml-auto" v-if="currentUser">
        <li class="nav-item">
          <a href="/profile" class="nav-link">
            <font-awesome-icon icon="user"/> {{currentUser.name}}
          </a>
        </li>
        <li class="nav-item">
          <a @click="logOut" class="nav-link">
            LogOut
          </a>
        </li>
      </div>
    </nav>
    <div class="container">
      <div v-if="alert.message" :class="alertStyle">
        {{alert.message}}
      </div>

      <router-view/>
    </div>
  </div>
</template>

<script>
import UserService from './service/user.service';
export default {
  data() {
    return {
      currentUser: null
    };
  },
  computed: {
    alert() {
      return this.$store.state;
    },
    alertStyle() {
      return this.$store.getters.alertStyle;
    }
  },
  created() {
    UserService.currentUser.subscribe(data => {
      this.currentUser = data;
    });
  },
  methods: {
    logOut() {
      UserService.logOut().then(() => {
        this.$router.push('/login');
      });
    },
    clearAlert() {
      this.$store.dispatch('clear');
    },
  },
  watch: {
    $route(to, from) {
      //You can get warning, if you don't use paremeters.
      console.log(to + " -> " + from);
      this.clearAlert();
    }
  }  
}
</script>


<style>
</style>