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
                                    <!-- <slide :index="0">
                                        <img  src="/src/assets/dadada.png">
                                    </slide>
                                    <slide :index="1">
                                        <img  src="/src/assets/hrana.jpg">
                                    </slide>
                                    <slide :index="2">
                                        <img  src="/src/assets/restau.png">
                                    </slide>
                                    <slide :index="3">
                                        <img  src="/src/assets/delivery.png">
                                    </slide> -->
                                    <!-- <slide v-for="(slide, i) in loadRestaurant.logo" :index="i" :key="i">
                                        <template slot-scope="{ index, isCurrent, leftIndex, rightIndex }">
                                            <img :data-index="index" :class="{ current: isCurrent, onLeft: (leftIndex >= 0), onRight: (rightIndex >= 0) }" :src="slide.src">
                                        </template>
                                    </slide> -->
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
                            <label>Menadzer objekta:</label>
                        </td>
                        <td>
                            <label>{{loadObject.manager}}</label>
                            <button class="btn btn-primary" @click="managerProfile()">Profil</button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Ocena objekta({{ocenaObjekta}}):</label>
                            <!-- <button class="btn btn-primary" style="background:white">{{ocenaRestorana}}</button> -->
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
                            <div class="col-md-6" v-bind:key="index" v-for="(tempTrening, index) in loadObject.treningItems">
                                <div style="margin-bottom:30px;" class="card h-100">
                                    <img class="card-img-top" style="width:100%;height:50%;" :src="tempArtikal.slika" alt="card image collar">
                                    <div class="card-body">
                                        <h5 class="card-title">Trening: {{tempTrening.naziv}}</h5>
                                        <p class="card-text">Cena: {{tempTrening.trener}}</p>
                                        <input id="kolicina" v-model="inputValues[index]" style="width:50%" type="number" ref="inputValue" placeholder="Kolicina..." min="0">
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
        
        // this.setLocationLatLng();
         setTimeout(() => this.getLocationFromAddress(), 1500);
        // this.getLocationFromAddress();
    },
    
    data(){
        return{

           
            //mapa varijable
            // center: { 
            //     lat: 39.7837304, 
            //     lng: -100.4458825 
            // },
            // lokacije:[],

            loadObject:{
                name:'',
                type:'',
                treningItems:[],
                opened:true,
                lokacija:{
                    ulica:'',
                    broj:'',
                    mesto:'',
                    drzava:'',
                    // xCoord:0,
                    // yCoord: 0,
                },
                logo:'',
                comments:[],
                manager:'',
                ocena:0
            },

            //umesto tempProduct-a napravim slozeniji objekat sa poljem tipa string i listom kao atributima
            tempRestaurantOrder:{
                izKogRestorana: '',
                listOfProducts:[],
            },

             tempProduct:{
                izKogRestorana : '',
                nazivArtikla: '',
                datumIVremePorudzbine : '',
                cenaJedinice : '',
                idKupca : '',
                brKomada : '',
                artikal : ''
            },
            inputValues : [],
        }
    },
    methods:{
        
        managerProfile(){
            this.$router.push(`/profile/${this.manager}`)
        },
        showComments(){
            this.$router.push(`/commentNew/${this.loadRestaurant.name}`);
        },
        getObject(){
            dataService.getRestaurant(this.id).then(response => {
                this.loadRestaurant = response.data;
                console.log("Naziv pronadjenog restorana je: " + this.loadRestaurant.name);
                // console.log("string slike: " + this.loadRestaurant.logo)
            }).catch(error => {
                console.log(error.response);
            })
        },
      
    },
    computed:{
        
        ocenaRestorana(){
            let tempSize = this.loadRestaurant.comments.length;
            let tempOcena = 0
            for(let i = 0; i < tempSize; i++){
                tempOcena += this.loadRestaurant.comments[i].ocena;
            }
            let ocena = tempOcena/tempSize;
            return  parseInt(ocena);
        },
        id(){
            return this.$route.params.id;
        },
        getOtherImgs: function () {
            console.log("getOtherImgs: ");
            //Prva slika mora da se manuelno postavi, a ostale se dodaju preko v-for:
            let imgs = this.loadRestaurant.images.slice(1);
            //Ako ima samo jednu sliku onda se sklanjaju strelice < > za kretanje kroz slike.
            if (imgs.length === 0) {
                console.log("imgs.lenght je = 0 ");
                this.isOtherImgs = false;
                console.log("this.isOtherImgs: " + this.isOtherImgs);
            } else {
                //vec je true, ali za svaki slucaj
                this.isOtherImgs = true;
                return imgs;
            }
    },
    },
    created(){
        // if(JSON.parse(localStorage.getItem('token')) == null){
        //     this.$router.push(`/login`);
        // }else{
        //     this.getRestaurant();
        // }
        this.getObject();
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