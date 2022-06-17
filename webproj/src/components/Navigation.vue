<template>
  <div id="navigation">
  <nav class="navbar navbar-expand-lg navbar-dark static-top">
      <div class="container">
        <a class="navbar-brand" href="/"><h1><span id='titleEffect'>Web Projekat 2022</span></h1></a>
        <!-- <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button> -->
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            
            <li class="nav-item active">
              <router-link to='/' class="nav-link" exact> Home
                <span class="sr-only">(current)</span>
              </router-link>
            </li>
            <li class="nav-item">
              <router-link to='/about' class="nav-link" exact>About</router-link>
            </li>
          </ul>
          
          <router-link to='/login' class="nav-link" exact> <button class="btn" id='btnLogin'>Log In</button> </router-link>
          
        </div>
      </div>
    </nav> 

  </div>
</template>

<script> 
import axios from 'axios'
// import { bus } from '../main';

export default {
    name: 'Navigation',
    components: {

    },

    data:function(){
        return{

        }
    }
}

</script>

<style scoped>
#navigation{
  background-color:#D3D3D3;
  color:#fff;
  padding-top:10px;
  min-height: 100px;
  border-bottom:#35424a 5px solid;
} 
/* Ostaviti za sada ako ne obrisati */
ul{
  list-style-type: none;
  text-align: center;
  margin:0;
}
li{
  display:inline-block;
  margin: 0;
}

a{
  color:#fff;
  text-decoration: none;
  padding:6px 8px;
  border-radius: 10px;
}

#btnLogin{
  color:#fff;
  border:none;
  padding: 0 20px;
}
#btnLogin{
  height:38px;
  background-color:#35424a;;
  color:#fff;
  border:none;
  padding: 0 20px;
}

#btnLogin:hover{
  background-color:#3aa708;
  color:#fff;
  font-weight: bold;
}

#btnLogout{
  height:38px;
  background-color:#35424a;;
  color:#fff;
  border:none;
  padding: 0 20px;
}

#btnLogout:hover{
  background-color:#cccccc;
  color:#fff;
  font-weight: bold;
}

#titleEffect{
  /* color:#9400D3;FFD300 */
  color:black;
  font-weight: bold;
  font-size: 40px;
}

.router-link-active{
  border-bottom: #fff solid 1px;
  font-weight: bold;
}
</style>

