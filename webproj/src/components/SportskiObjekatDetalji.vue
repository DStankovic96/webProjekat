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
                            <label>Menadzer objekta:</label>
                        </td>
                        <td>
                            <label>{{loadObject.manager}}</label>
                            <!-- <button class="btn btn-primary" @click="managerProfile()">Profil</button> -->
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
                                        <button v-show="isCustomer" v-on:click="Treniraj(tempTrening)" class="btn btn-primary">Treniraj</button>
                                        <!-- <button v-show="isOwner" v-on:click="removeEntity(tempVikendica.id)" class="btn btn-danger">Ukloni</button> -->
                                        <!-- <div v-if='messages.successResponse' class="alert alert-success" v-html="messages.successResponse"></div>
                                        <div v-if='messages.errorResponse' class="alert alert-success" v-html="messages.errorResponse"></div> -->
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

            messages:{
                errorResponse:'',
                successResponse:'',
                errorResponse2:'',
                successResponse2:''
            },
           

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

            username:'',
            isCustomer:false,
            inputValues : [],
            customer:[],
            clanarine:[],
            istorijaTreninga:[],
            istorijaTrening:{
                datum:'',
                trening:'',
                kupac:'',
                trener:'',
            }
        }
    },
    methods:{

        getClanarine(username){
             dataService.getAllClanarineKupac(username).then(response => {
                    this.clanarine = response.data;
                     
                })
        },
        getIstorijaTreningaKupac(username){
             dataService.getITreningaKupac(username).then(response => {
                    this.istorijaTreninga = response.data;
                    // console.log("DAAAAAAAAAAA" + JSON.parse(this.istorijaTreninga));
                     
                })
        },
        Treniraj(tempTrening){
            
            console.log("MSG" + JSON.stringify(this.clanarine));
             if(this.clanarine.length === 0){
                alert("NEMATE UPLACENU CLANARINU!");
             }
            for(let i = 0; i<this.clanarine.length; i++){
                
                if(this.clanarine[i].statusClanarine==='aktivna'){
                    
                        if(this.clanarine[i].brojTermina>0){
                    
                            this.clanarine[i].brojTermina=this.clanarine[i].brojTermina-1;
                            // this.successResponse = "<h4>Doslo je do greske!.</h4>"
                            // setTimeout(() => this.successResponse='', 3000);
                            this.istorijaTrening.datum=new Date().toString().substring(4, 15);
                            this.istorijaTrening.trening=tempTrening.naziv;
                            this.istorijaTrening.kupac=this.username;
                            this.istorijaTrening.trener=tempTrening.trener;

                            this.istorijaTreninga.push(this.istorijaTrening);

                           

                            dataService.posaljiClanarinu1(this.username,this.clanarine).then(response => {
                                 console.log("poslato na bek");
                            
                          
                            })

                          
                             
                        }else{
                        
                            // this.errorResponse = "<h4>Doslo je do greske!.</h4>"
                            // setTimeout(() => this.errorResponse='', 3000);
                            console.log("vas broj termina je 0");
                             alert("VAS BROJ TERMINA JE 0!");
                             break;
                        }
                           console.log("poslato na bek istorija treninga" + JSON.stringify(this.istorijaTreninga));
                             dataService.posaljiTrening(this.username, this.istorijaTreninga).then(response => {
                                 console.log("poslato na bek istorija treninga");
                            
                          
                            })
                 console.log("NOVO" + this.clanarine[i].brojTermina);
                }
            }

        },
        managerProfile(){
            this.$router.push(`/profile/${this.manager}`)
        },
        showComments(){
            this.$router.push(`/commentNew/${this.loadObject.name}`);
        },
        getObject(){
            dataService.getObject(this.id).then(response => {
                this.loadObject = response.data;
                console.log("Naziv pronadjenog objekta je: " + this.loadObject.name);
                
            }).catch(error => {
                console.log(error.response);
            })
        },
      
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
        },
        id(){
            return this.$route.params.id;
        },
        getOtherImgs: function () {
            console.log("getOtherImgs: ");
            //Prva slika mora da se manuelno postavi, a ostale se dodaju preko v-for:
            let imgs = this.loadObject.images.slice(1);
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
        let temp = JSON.parse(localStorage.getItem('token'));
        if(temp == null){
            this.$router.push(`/login`);
        }else{
             if(temp.role==='customer')
        {
            this.isCustomer=true;
            this.username=temp.username;
            this.getClanarine(temp.username);
            this.getIstorijaTreningaKupac(temp.username);
           
        }
            this.getObject();
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