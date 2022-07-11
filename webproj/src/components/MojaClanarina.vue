<template>
    <div style="width:100%">
        <div class="container">

            
            
             <div class="container" style="margin-bottom:100px;max-height:300px; overflow:auto;">>
                <h2>Prikaz aktivne clanarine</h2>
                 
                <!-- <section style="margin-left: 10px; margin-bottom:10px">
                    <div> -->
                    <!-- if we are 3 cards wide start a new row -->
                      
                                <!-- <div style="margin-bottom:30px;" class="card h-100"> -->
                                    <!-- <img class="card-img-top" style="width:100%;height:50%;" :src="tempTrening.slika" alt="card image collar"> -->
                                    <!-- <div class="card-body">
                                        <h5 class="card-title">Kupac: {{clanarina.kupac}}</h5>
                                          <p class="card-text">Datum: {{tempTrening.datum}}</p>
                                          <p class="card-text">Trener: {{tempTrening.trener}}</p>
                                          <button v-on:click="Details(tempTrening.trening)" class="btn btn-primary">Detalji</button> -->
                                        <!-- <b><p class="card-text">Tip treninga: {{tempTrening.tipTreninga}}</p>
                                        <p class="card-text">Trener zaduzen za ovaj trening: {{tempTrening.trener}}</p></b> -->
                                        <!-- <button v-on:click="Treniraj(tempTrening, index)" class="btn btn-primary">Dodaj</button> -->
                                        <!-- <button v-show="isOwner" v-on:click="removeEntity(tempVikendica.id)" class="btn btn-danger">Ukloni</button> -->
                                    <!-- </div>
                                </div>
                            
                    </div>
                </section> -->
                  <!-- <section style="margin-left: 10px; margin-bottom:10px">
                    <div>
                   
                      
                                <div style="margin-bottom:30px;" class="card h-100">
                                    
                                    <div class="card-body">
                                        <h5 class="card-title">Kupac: {{clanarina.kupac}}</h5>
                                          <p class="card-text">Datum: {{tempTrening.datum}}</p>
                                          <p class="card-text">Trener: {{tempTrening.trener}}</p>
                                          <button v-on:click="Details(tempTrening.trening)" class="btn btn-primary">Detalji</button>
                                      
                                    </div>
                                </div>
                            
                    </div>
                </section> -->
            </div>
           
       
            
            <div class="container" style="margin-bottom:100px;max-height:300px; overflow:auto;">>
                <h2>Prikaz svih clanarina</h2>
                 
                <section style="margin-left: 10px; margin-bottom:10px">
                    <div>
                    <!-- if we are 3 cards wide start a new row -->
                        <div class="row">
                            <div  class="col-md-2" v-bind:key="tempTrening.trening" v-for="tempTrening in loadTreninzi">
                                <div style="margin-bottom:30px;" class="card h-100">
                                    <!-- <img class="card-img-top" style="width:100%;height:50%;" :src="tempTrening.slika" alt="card image collar"> -->
                                    <div class="card-body">
                                        <h5 class="card-title">Kupac: {{tempTrening.kupac}}</h5>
                                          <p class="card-text">Datum: {{tempTrening.datum}}</p>
                                          <p class="card-text">Trener: {{tempTrening.trener}}</p>
                                          <button v-on:click="Details(tempTrening.trening)" class="btn btn-primary">Detalji</button>
                                        <!-- <b><p class="card-text">Tip treninga: {{tempTrening.tipTreninga}}</p>
                                        <p class="card-text">Trener zaduzen za ovaj trening: {{tempTrening.trener}}</p></b> -->
                                        <!-- <button v-on:click="Treniraj(tempTrening, index)" class="btn btn-primary">Dodaj</button> -->
                                        <!-- <button v-show="isOwner" v-on:click="removeEntity(tempVikendica.id)" class="btn btn-danger">Ukloni</button> -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            
        </div>
    
    </div>
</template>

<script>
import dataService from '../services/DataService'

import Datepicker from 'vuejs-datepicker';


export default {
    mounted(){
        
    },
    
    data(){
        return{

         
         
       

           loadTreninzi:[{
             datum : "dwadwadwa",
             trener: "Misko",
             kupac: "Milan",
             trening: "individualni trening",
           },
           {
            datum : "bbbbbbb",
             trener: "Stefan",
             kupac: "Mihajlo",
             trening: "grupni trening",
           },
           {
             datum : "fffff",
             trener: "Radule",
             kupac: "Pera",
             trening: "individualni snaga",
           }
           ],
           

            // loadObject:{
            //     name:'',
            //     type:'',
            //     treningList:[],
            //     opened:true,
            //     lokacija:{
            //         ulica:'',
            //         broj:'',
            //         mesto:'',
            //         drzava:'',
                  
            //     },
            //     logo:'',
            //     comments:[],
            //     manager:'',
            //     ocena:0
            // },
            
            loadClanarine : [],
            clanarina:{
                id:0,
		        ime:'',
		        typeOfClanarina:'',
                datumPlacanja:'',
                datumVazenja:'',
                cena:0,
                customer:'',
                statusClanarine:'',
                brojTermina:0,
            }
           
           
            // inputValues : [],
           
        }
    },
     
    methods:{
        
        getMojiClanarine(temp){
            console.log("dsadasdsa" + temp);
            dataService. getAllClanarineKupac(temp).then(response => {
                this.loadClanarine = response.data;
                
                
            }).catch(error => {
                console.log(error.response);
            })
        },
        
      
    },
   created(){
        if(JSON.parse(localStorage.getItem('token')) == null){
            this.$router.push(`/login`);
        }else{
            let parsToken = JSON.parse(localStorage.getItem('token'));
            console.log("TOKEN PROCITAN IZ LOCALSTORAGE-a: " + JSON.stringify(parsToken));
          
            let temp = parsToken.username;
             console.log("usERNAME" + temp);
            this.getMojiClanarine(temp);
        }
    },
   
    
    
    




}
</script>

<style scoped>
#titleEffect{
  
  font-weight: bold;
}

#user-profile ul#services li{
    list-style: none;
    padding:15px;
    border-bottom: gray solid 1px;
    border-radius: 15px;
    margin-bottom: 3px; 
    background-color:#fff;
    width:90%;
}

/* #user-profile#buttonUpdate{
  float:left;
} */
#searcIcon img:hover{
    width:45px;
    padding-bottom:3px;
    cursor: pointer;
}

.span_search{
    /* padding:5px; */
    padding-top:5px;
    padding-left:5px;
    padding-right:5px;
    color:#35424a;
    font-weight: bold;
}

.header5{
    color:#1E90FF;
    font-weight: bold;
}

#search_icon:hover{
    cursor: pointer;
}

.marg{
    margin-top:15px;
}
</style>