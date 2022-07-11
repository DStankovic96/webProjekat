<template>
     <div id="user-profile">
        <div id='page-title'>
                <center> <b> <h1 style="margin-top:10px;color:#35424a;"> <span id='titleEffect'>Pregled svih korisnika sistema:<hr style="height:4px;border-width:0;color:gray;background-color:gray"></span> </h1> </b> </center>
            
            </div>
       <section style="margin-left: 10px;">
            <div>
            <!-- if we are 3 cards wide start a new row -->
            <div>
                  <center> <b> <h1 style="margin-top:10px;color:#35424a;"> <span id='titleEffect'>Svi korisnici:</span> </h1> </b> </center><br>
            </div>
                <div class="row">
                  
                    <div class="col-md-2" v-bind:key="index" v-for="(tempEntitet, index) in sviKupci.slice(0,15)">
                        <div class="card h-60 shadow">
                            <img class="card-img-top" style="width:100%;height:50%;" src='/src/assets/logo.png' alt="card image collar">
                            <!-- <img class="card-img-top" :src="product.thumbnailUrl" alt="card image collar"> -->
                            <div class="card-body">
                                   <center> <h5> USERNAME: </h5> </center>
                                <center><h5 class="card-title"> {{tempEntitet.username}}</h5></center>
                                <!-- <div  class="card-footer  text-center">
                                    <button v-on:click='profil(tempEntitet.username)' class="btn button_1" id='btnRegistration'><b>Profil</b></button>
                                </div> -->
                              
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <hr style="height:4px;border-width:0;color:gray;background-color:gray">

        <section style="margin-left: 10px;">
            <div>
            <!-- if we are 3 cards wide start a new row -->
            <div>
                  <center> <b> <h1 style="margin-top:10px;color:#35424a;"> <span id='titleEffect'>Svi manageri:</span> </h1> </b> </center><br>
            </div>
                <div class="row">
                    <div class="col-md-2" v-bind:key="index" v-for="(tempEntitet, index) in sviManageri.slice(0,15)">
                        <div class="card h-60">
                            <img class="card-img-top" style="width:100%;height:60%;" src='/src/assets/logo.png' alt="card image collar">
                            <!-- <img class="card-img-top" :src="product.thumbnailUrl" alt="card image collar"> -->
                            <div class="card-body">
                                <center> <h5> USERNAME: </h5> </center>
                                 <center><h5 class="card-title"> {{tempEntitet.username}}</h5></center>
                               <!-- <div  class="card-footer  text-center">
                                    <button v-on:click='profil(tempEntitet.username)' class="btn button_1" id='btnRegistration'><b>Profil</b></button>
                                </div> -->
                               
                              
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

         <hr style="height:4px;border-width:0;color:gray;background-color:gray">

        <section style="margin-left: 10px;">
            <div>
            <!-- if we are 3 cards wide start a new row -->
                 <div>
                  <center> <b> <h1 style="margin-top:10px;color:#35424a;"> <span id='titleEffect'>Svi treneri:</span> </h1> </b> </center><br>
            </div>
                <div class="row">
                    <div class="col-md-2" v-bind:key="index" v-for="(tempEntitet, index) in sviTreneri.slice(0,15)">
                        <div class="card h-60">
                            <img class="card-img-top" style="width:100%;height:50%;" src='/src/assets/logo.png' alt="card image collar">
                            <!-- <img class="card-img-top" :src="product.thumbnailUrl" alt="card image collar"> -->
                            <div class="card-body">
                                <center> <h5> USERNAME: </h5> </center>
                                <center><h5 class="card-title"> {{tempEntitet.username}}</h5></center>
                                 <!-- <div  class="card-footer  text-center">
                                    <button v-on:click='profil(tempEntitet.username)' class="btn button_1" id='btnRegistration'><b>Profil</b></button>
                                </div> -->
                                
                                
                              
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        


    </div>
</template>

<script>
import dataService from '../services/DataService'
export default{
    data(){
        return{
            sviKupci : [],
            sviManageri : [],
            sviTreneri: [],
            
        }
    },
    created(){
        //dobavljanje rezervacija na cekanju
        dataService.getCustomers().then(response => {
            
            this.sviKupci = response.data;
            
        });
         dataService.getAllManagers().then(response => {
            
            this.sviManageri = response.data;
            
        });
         dataService.getListaTrenera().then(response => {
            
            this.sviTreneri = response.data;
            
        });
    },
    methods:{
       profil(username){
       
       }
    },
    computed:{

    },
}

</script>


<style scoped>

.button_1{
    background-color:#0213f7;
   
     height:50px;
    color:#fff;
    border:none;
    padding: 0 20px;
    font-size: 20px;
    width:80%;
}

</style>
