<template>
    <div style="width:100%">
        <div class="container">

            
            
             <div class="container" style="margin-bottom:100px;max-height:300px; overflow:auto;">>
                <h2>Prikaz aktivne clanarine</h2>
                 
                
                  <section style="margin-left: 10px; margin-bottom:10px">
                    <div v-show="pokazati">
                   
                      
                                <div style="margin-bottom:30px;" class="card h-100">
                                    
                                    <div class="card-body">
                                      <b>  <h4 class="card-title">Ime clanarine: {{this.clanarina.ime}}</h4>
                                          <p class="card-text">Datum kupovine: {{this.clanarina.datumPlacanja}}</p>
                                           <p class="card-text">Datum vazenja: {{this.clanarina.datumVazenja}}</p>
                                          <p class="card-text">Broj termina: {{this.clanarina.brojTermina}}</p>
                                           <p class="card-text">Tip clanarine: {{this.clanarina.typeOfClanarina}}</p></b>
                                         
                                      
                                    </div>
                                </div>
                            
                    </div>
                </section>
            </div>
           
       
            
            <div class="container" style="margin-bottom:100px;max-height:300px; overflow:auto;">>
                <h2>Prikaz svih clanarina</h2>
                 
                <section style="margin-left: 10px; margin-bottom:10px">
                    <div>
                    <!-- if we are 3 cards wide start a new row -->
                        <div class="row">
                            <div  class="col-md-2" v-bind:key="tempClanarina.id" v-for="tempClanarina in loadClanarine">
                                <div style="margin-bottom:30px;" class="card h-100">
                                    <!-- <img class="card-img-top" style="width:100%;height:50%;" :src="tempTrening.slika" alt="card image collar"> -->
                                    <div class="card-body">
                                       <b> <h5 class="card-title">Ime clanarine: {{tempClanarina.ime}}</h5>
                                          <p class="card-text">Datum kupovine: {{tempClanarina.datumPlacanja}}</p>
                                          <p class="card-text">Status: {{tempClanarina.statusClanarine}}</p></b>
                                         
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

         
         
            pokazati:false,

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
            
            dataService. getAllClanarineKupac(temp).then(response => {
                this.loadClanarine = response.data;
                if(this.loadClanarine.length>0){
                    this.pokazati=true;
                }
                console.log("CLANARINAAA" + JSON.stringify(this.loadClanarine[0]));
                 for(let i = 0; i<this.loadClanarine.length; i++){
                 
                 
                    if(this.loadClanarine[i].statusClanarine==='aktivna'){
                          console.log("TESTB");
                        
                        this.clanarina=this.loadClanarine[i];
                        console.log("CLANARINAAA" + JSON.stringify(this.loadClanarine[i]));
                        console.log("CLANARINA" + JSON.stringify(this.clanarina));
                    }
                 }
                
                
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