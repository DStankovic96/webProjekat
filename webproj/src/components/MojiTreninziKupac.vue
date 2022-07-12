<template>
    <div style="width:100%">
        <div class="container">

            <h1 style="margin-top:10px;"><span id='titleEffect'>Pregled istorije treninga</span></h1>
                <hr style='background:#35424a;height:1px;'>
                <div class='container ' id='search'>
                <nav class="navbar navbar-light bg-light justify-content-between">
                    <a class="navbar-brand">Pretraga istorije treninga</a>
                    <form class="form-inline">     
                        <div style='display:inline;' id="advanced_search">
                            <div id='second-row' class="row"  style="margin-top:5px;">
                                
                                <div class="input-group">
                                    <input class="form-control mr-sm-2" type="text" placeholder="Naziv treninga" aria-label="Search" v-model="searchedTreninzi.nazivTreninga">
                                    
                                    <input class="form-control mr-sm-2" type="number" min="0" placeholder="min cena" aria-label="Search" v-model="searchedTreninzi.cenaOd">
                                    <span style="padding-right:6px;" class="span_search"> - </span>
                                    <input class="form-control mr-sm-2" type="number" min="0" placeholder="max cena" aria-label="Search" v-model="searchedTreninzi.cenaDo">
                                
                                    <span class="span_search">Od</span>
                                    <vuejsDatepicker style="font-size:20px;"  placeholder="Pocetni datum..."
                                        v-model="selectedDate1" :highlighted=" searchedTreninzi" :disabled-dates="disabledDates">
                                    </vuejsDatepicker>
                                    <span class="span_search">Do</span>
                                    <vuejsDatepicker style="font-size:20px;"  placeholder="Krajnji datum..."
                                        v-model="selectedDate2" :highlighted="searchedTreninzi" :disabled-dates="disabledDates">
                                    </vuejsDatepicker>
                                </div>
                                
                                
                            </div><!--/second_row-->
                            <br>
                            <div class="button-group">
                                <span class="col-xl-2 col-md-6 mb-1">
                                    <button style='margin-right:5px;' class='btn btn-outline-primary my-2 my-sm-0' v-on:click="resetFilter()">Reset all</button>
                                </span> 
                                <span class="col-xl-3 col-md-6 mb-2"> 
                                    <button style="margin-left: 5px;" class="btn btn-outline-success my-2 my-sm-0" type="button" v-on:click.prevent='searchedTreninzi()'>Search</button>
                                </span>
                            </div>
                            
                        </div><!--/advanced_search-->
                        <!-- <div v-if='messages.errorResponse' class="alert alert-danger" v-html="messages.errorResponse"></div>
                        <div v-if='messages.successResponse' class="alert alert-success" v-html="messages.successResponse"></div>   -->
                    </form>
                </nav>
            </div>

               <div style="margin-top:30px;" class="container" id='main'>
            <div style="margin-bottom:30px;">
                <span class="span_search">Filtriranje po tipu sportskog objekta</span>
                <input v-model="filterInput">
                <span class="span_search">Filtriranje po tipu treninga</span>
                <input v-model="filterStatus">
            </div>
            
            
            <table class="table">
                <thead>
                    <tr>
                        <th @click="sort('nazivTreninga')" class="arrow">Naziv
                            <img v-if='currentSortDir == "asc" && currentSort== "nazivTreninga"' src='../assets/up-arrow1.1.png'>
                            <img v-if='currentSortDir == "desc" && currentSort== "nazivTreninga" ' src='../assets/down-arrow1.1.png'>
                        </th>
                        <th>Tip</th>
                        <th @click="sort('datumTreninga')" class="arrow">Datum 
                            <img v-if='currentSortDir == "asc" && currentSort== "datumTreninga"' src='../assets/up-arrow1.1.png'>
                            <img v-if='currentSortDir == "desc" && currentSort== "datumTreninga" ' src='../assets/down-arrow1.1.png'>
                        </th>
                        <th @click="sort('cenaTreninga')" class="arrow"> Cena
                            <img v-if='currentSortDir == "asc" && currentSort== "cenaTreninga"' src='../assets/up-arrow1.1.png'>
                            <img v-if='currentSortDir == "desc" && currentSort== "cenaTreninga" ' src='../assets/down-arrow1.1.png'>
                        </th>
                        
                    </tr>
                </thead>
                <tbody>
                  
                </tbody>
                
            </table>
        </div>
            
            <div class="container" style="margin-bottom:100px;max-height:300px; overflow:auto;">>
                <h2>Prikaz vasih treninga</h2>
                 
                <section style="margin-left: 10px; margin-bottom:10px">
                    <div>
                    <!-- if we are 3 cards wide start a new row -->
                        <div class="row">
                            <div  class="col-md-2" v-bind:key="tempTrening.id" v-for="tempTrening in loadTreninzi">
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
import starRating from 'vue-star-rating'
import Datepicker from 'vuejs-datepicker';
import StarRating from "vue-star-rating";

export default {
    mounted(){
        
    },
    
    data(){
        return{

            filterInput:'',
            
            filterStatus:'',
            currentSort: 'cenaTreninga',
            currentSortDir: 'asc',
            disabledDates: {
                to: null
            },
           searchedTreninzi: {
                //Prilikom povezivanja preimenovati da odgovara nazivima atributa sa beka
                datumOd: null,
                datumDo: null,
                cenaOd:null,
                cenaDo:null,
                nazivTreninga: '',
            },

        //    loadTreninzi:[{
        //      datum : "dwadwadwa",
        //      trener: "Misko",
        //      kupac: "Milan",
        //      trening: "individualni trening",
        //    },
        //    {
        //     datum : "bbbbbbb",
        //      trener: "Stefan",
        //      kupac: "Mihajlo",
        //      trening: "grupni trening",
        //    },
        //    {
        //      datum : "fffff",
        //      trener: "Radule",
        //      kupac: "Pera",
        //      trening: "individualni snaga",
        //    }
        //    ],
           

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
           
           
            // inputValues : [],
            selectedDate1: '',
            selectedDate2: '',
        }
    },
      watch:{
        'selectedDate1':function(){
            console.log('Datum pre obrade: ' + this.selectedDate1);
            this.searchedTreninzi.datumOd = this.selectedDate1.toString().substring(4, 15);
            console.log("Datum posle obrade: " + this.searchedTreninzi.datumOd)
        },
        'selectedDate2':function(){
            console.log('Datum pre obrade: ' + this.selectedDate2);
            this.searchedTreninzi.datumDo = this.selectedDate2.toString().substring(4, 15);
            console.log("Datum posle obrade: " + this.searchedTreninzi.datumDo)
        }
    },
    methods:{
        
        getMojiTreninzi(temp){
            console.log("dsadasdsa" + temp);
            dataService.getITreningaKupac(temp).then(response => {
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
    components: {
        vuejsDatepicker:Datepicker,
        "star-rating": StarRating,
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