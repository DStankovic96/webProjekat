<template>
    <div style="width:50%">
        <div class="container">
            
            <div class="container" style="min-height:300px;">
                <h2>Prikaz vasih treninga</h2>
                 
                <section style="margin-left: 10px; margin-bottom:100px">
                    <div>
                    <!-- if we are 3 cards wide start a new row -->
                        <div class="row">
                            <div class="col-md-6" v-bind:key="index" v-for="(tempTrening, index) in loadTreninzi.trening">
                                <div style="margin-bottom:30px;" class="card h-100">
                                    <img class="card-img-top" style="width:100%;height:50%;" :src="tempTrening.slika" alt="card image collar">
                                    <div class="card-body">
                                        <h5 class="card-title">Trening: {{tempTrening.naziv}}</h5>
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
import starRating from 'vue-star-rating'
import {Carousel3d, Slide} from 'vue-carousel-3d'

export default {
    mounted(){
        
    },
    
    data(){
        return{

           
           

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
            
            loadTreninzi : [],
           
           
            inputValues : [],
        }
    },
    methods:{
        
        getMojiTreninzi(temp){
            console.log("dsadasdsa" + temp);
            dataService.getTrener(temp).then(response => {
                this.loadTreninzi = response.data;
                console.log("Naziv pronadjenih treninga je: " + JSON.stringify(this.loadTreninzi));
                
            }).catch(error => {
                console.log(error.response);
            })
        },
         showComments(){
            this.$router.push(`/commentNew/${this.loadObject.name}`);
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
            this.getMojiTreninzi(temp);
        }
    },
    
    
    




}
</script>

<style scoped>
#table_objectdetails{
    border-style: solid;
}
#table_objectdetails tr:nth-child(even){
    background-color: lightgray;
    font-weight: 600;
}
#table_objectdetails tr:nth-child(odd){
    font-weight: 600;
    background-color: white;
}
#table_objectdetails td:first-child{
    width: 30%;
}
#table_objectdetails td:last-child{
    width: 100%;
}
input{
    width: 100%;
}

</style>