<template>
    <div style="width:50%">
        <div class="container">
            <h2>Pregled detalja o sportskom objektu {{loadObject.name}}</h2>
            <p>Prikaz detalja i statistike o objektu...</p>
            <table class="table" id="table_objectdetails">
                <tbody>
                    <tr>
                        <td colspan="2"> 
                            <div width="90%">
                                <!-- <img style="width:400px;height:400px" src="/src/assets/profile.png"> -->
                                <carousel-3d style="width:400px;height:600">
                                    <slide :index="0">
                                        <img  :src="loadObject.logo">
                                    </slide>
                                 
                                </carousel-3d>
                            </div>
                            <div  class="text-center">
                                <button @click="showComments()" class="btn btn-primary">Komentari</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Naziv objekta:</label>
                        </td>
                        <td>
                           
                            <label>{{loadObject.name}}</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Tip objekta:</label>
                        </td>
                        <td>
                            <label>{{loadObject.type}}</label>
                        </td>
                    </tr>
                  
                    <tr>
                        <td>
                            <label>Ocena objekta({{ocenaObjekta}}):</label>
                            
                        </td>
                        <td>
                            <starrating v-model="loadObject.ocena" :star-size="30" read-only></starrating>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Adresa objekta:</label>
                        </td>
                        <td>
                            <div style="display:inline-block">
                                <label style="width:100%">{{loadObject.lokacija.ulica}} {{loadObject.lokacija.broj}} {{loadObject.lokacija.mesto}} {{loadObject.lokacija.drzava}}</label>
                            </div>
                        </td>
                    </tr>
                    
                </tbody>
              
            </table>
            <div class="container" style="min-height:300px;">
                <h2>Pregled ponude objekta</h2>
                 <p>Prikaz treninga u ponudi...</p>
                <section style="margin-left: 10px; margin-bottom:100px">
                    <div>
                    <!-- if we are 3 cards wide start a new row -->
                        <div class="row">
                            <div class="col-md-6" v-bind:key="index" v-for="(tempTrening, index) in loadObject.treningList">
                                <div style="margin-bottom:30px;" class="card h-100">
                                    <img class="card-img-top" style="width:100%;height:50%;" :src="tempTrening.slika" alt="card image collar">
                                    <div class="card-body">
                                        <h5 class="card-title">Trening: {{tempTrening.naziv}}</h5>
                                        <b><p class="card-text">Tip treninga: {{tempTrening.tipTreninga}}</p>
                                        <p class="card-text">Trener zaduzen za ovaj trening: {{tempTrening.trener}}</p></b>
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

           
           

            loadObject:{
                name:'',
                type:'',
                treningList:[],
                opened:true,
                lokacija:{
                    ulica:'',
                    broj:'',
                    mesto:'',
                    drzava:'',
                  
                },
                logo:'',
                comments:[],
                manager:'',
                ocena:0
            },
           
           
            inputValues : [],
        }
    },
    methods:{
        
        getMojObjekat(temp){
            console.log("dsadasdsa" + temp);
            dataService.getObjectByManager(temp).then(response => {
                this.loadObject = response.data;
                console.log("Naziv pronadjenog objekta je: " + this.loadObject.name);
                
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
            this.getMojObjekat(temp);
        }
    },
     computed:{
        
        ocenaObjekta(){
            let tempSize = this.loadObject.comments.length;
            let tempOcena = 0
            for(let i = 0; i < tempSize; i++){
                tempOcena += this.loadObject.comments[i].ocena;
            }
            let ocena = tempOcena/tempSize;
            return  parseInt(ocena);
        }
     },
      components:{
        starrating : starRating,
        carousel3d:Carousel3d,
         slide:Slide,
      
    }
    




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