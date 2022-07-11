<template>
    <div id="objects-overview">
        <div class="container" id='page-title'>
            <h1 style="margin-top:10px;color:#35424a;">Lista objekata</h1>
            <hr style='background:#35424a;height:1px;'>
        </div>
        

         <div class='container ' id='search'>
            <nav class="navbar navbar-light bg-light justify-content-between">
                <a class="navbar-brand">Pretraga objekata</a>
                <form class="form-inline">     
                    <div style='display:inline;' id="advanced_search">
                        <div id='second-row' class="row"  style="margin-top:5px;">
                            
                            <div class="input-group">
                                <span class="col-xl-3 col-md-6 mb-1">
                                <span class="span_search">Naziv objekta</span>
                                <input class="form-control mr-sm-2" type="text" placeholder="Naziv objekta" aria-label="Search" v-model="searchedObjekat.name">
                            </span>
                            <span class="col-xl-3 col-md-6 mb-3"> 
                                <span class="span_search">Tip objekta</span>
                                <select style="padding:5px;" v-model="searchedObjekat.type">
                                    <option disabled value="">Tip objekta </option>
                                    <option @select="ponistiIzbor()" value=''>Ponisti izbor</option>
                                    <option v-bind:key="tempTip.naziv"  v-for="tempTip in typesOfObjects">{{tempTip.naziv}}</option>
                                </select>
                            </span>
                            <span class="col-xl-3 col-md-6 mb-3"> 
                                <span class="span_search">Lokacija objekta</span>
                                <input class="form-control mr-sm-2" type="text" placeholder="Mesto..." aria-label="Search" v-model="searchedObjekat.lokacija.drzava">
                            </span>
                            <span class="col-xl-3 col-md-6 mb-3"> 
                                <span class="span_search">Ocena objekta</span>
                                <input class="form-control mr-sm-2" type="text" placeholder="Ocena objekta..." aria-label="Search" v-model="searchedObjekat.ocena">
                            </span>
                            </div>
                            </div><!--/second_row-->
                        <div class="button-group">
                            <span class="col-xl-2 col-md-6 mb-1">
                                <button style='margin-right:5px;' class='btn btn-outline-primary my-2 my-sm-0' v-on:click="resetFilter()">Reset all</button>
                            </span> 
                            <span class="col-xl-3 col-md-6 mb-2"> 
                                <button style="margin-left: 5px;" class="btn btn-outline-success my-2 my-sm-0" type="button" v-on:click.prevent='search()'>Search</button>
                            </span>
                        </div>
                        
                    </div><!--/advanced_search-->
                    <div v-if='messages.errorResponse' class="alert alert-danger" v-html="messages.errorResponse"></div>
		            <div v-if='messages.successResponse' class="alert alert-success" v-html="messages.successResponse"></div>  
                </form>
            </nav>
         </div>
          <div style="margin-top:30px;" class="container" id='main'>
            <div style="margin-bottom:30px;">
                <span class="span_search">Filter objekata</span>
                <input v-model="filterInput">
                <span class="span_search">Prikaz samo otvorenih objekata</span>
                <input type="checkbox" v-model="filterOpenedOnly">
            </div>
            
            
            <table class="table">
                <thead>
                    <tr>
                        <th @click="sort('nazivObjekta')" class="arrow">Naziv
                            <img v-if='currentSortDir == "asc" && currentSort== "nazivObjekta"' src='../assets/up-arrow1.1.png'>
                            <img v-if='currentSortDir == "desc" && currentSort== "nazivObjekta" ' src='../assets/down-arrow1.1.png'>
                        </th>
                        <th>Tip</th>
                        <th @click="sort('lokacijaObjekta')" class="arrow">Lokacija
                            <img v-if='currentSortDir == "asc" && currentSort== "lokacijaObjekta"' src='../assets/up-arrow1.1.png'>
                            <img v-if='currentSortDir == "desc" && currentSort== "lokacijaObjekta" ' src='../assets/down-arrow1.1.png'>
                        </th>
                        <th @click="sort('ocenaObjekta')" class="arrow"> Ocena
                            <img v-if='currentSortDir == "asc" && currentSort== "ocenaObjekta"' src='../assets/up-arrow1.1.png'>
                            <img v-if='currentSortDir == "desc" && currentSort== "ocenaObjekta" ' src='../assets/down-arrow1.1.png'>
                        </th>
                        <th>Menadzer</th>
                        <th>Logo:</th>
                        <th>Otvoren</th>
                        <th>Detaljno</th>
                        <th>Komentarisi</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-bind:key='rst.name' v-for="rst in sortObjects">
                        <td>{{rst.name}}</td>
                        <td>{{rst.type}}</td>
                        <td>{{rst.lokacija.ulica}}, {{rst.lokacija.broj}}, {{rst.lokacija.mesto}}, {{rst.lokacija.drzava}}</td>
                        <!-- <td>{{rst.ocena}}</td> -->
                        <td><starrating read-only v-model="rst.ocena" :star-size="20"></starrating></td>
                        <td>{{rst.manager}}</td>
                        <td>
                            <img class="card-img-top" style="margin-top:10px; margin-bottom:10px" :src="rst.logo">
                        </td>
                        <td v-show='rst.opened'>{{"otvoren"}}</td>
                        <td v-show='!rst.opened'>{{"zatvoren"}}</td>
                        <td>
                            <button style="width:100px; height:40px;" @click="showDetails(rst.name)" class=' btn btn-sm classButton shadow'>Detalji</button>
                        </td>
                        <td>
                            <button style="width:100px; height:40px;" v-show="isCustomer" @click="comment(rst.name)" class=' btn btn-sm classButton shadow'>Komentarisi</button>
                        </td>
                    </tr>
                </tbody>
                
            </table>
        </div>

       

    </div>
</template>



<script>

import dataService from '../services/DataService'
import starRating from 'vue-star-rating'


export default {
    
    data(){
        return{
            //dodatne promenljive za mapu
          



            filterOpenedOnly:false,
            filterInput:'',
            currentSort: 'ocenaObjekta',
            currentSortDir: 'asc',

            isCustomer:true,

            searchedObjekat:{
                id:'',
                name:'',
                type:'',
                treningList:[],
                opened:'',
                lokacija:{
                    id:'',
                    ulica:'',
                    broj:'',
                    mesto:'',
                    drzava:'',
                },
                logo:'',
                comments:[],
                manager:'',
                ocena:''
            },
            displayedObjects:[],
        
            typesOfObjects:[
                {
                    naziv:'teretana'
                },
                {
                    naziv: 'bazen'
                },
                {
                    naziv: 'sportski centar'
                },
                {
                    naziv: 'plesni studio'
                }
            ],
             messages: {
                errorResponse: '',
                successResponse: '',
            },
            sort(s){
                if(s == this.currentSort){
                    this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
                }
                this.currentSort = s;
            }
        }
        
    },
    methods:{
                 ponistiIzbor(){
                    this.searchedObjekat.type = '';
                },
                sortiraj(){
                    if(this.currentSort == 'nazivObjekta'){
                        if(this.currentSortDir == 'asc'){
                            return this.displayedObjects.sort((a, b) => (a.name > b.name) ? 1 : -1)
                        }
                        else{
                            return this.displayedObjects.sort((a, b) => (a.name < b.name) ? 1 : -1)
                        }
                    }
                    if(this.currentSort == 'ocenaObjekta'){
                        if(this.currentSortDir == 'asc'){
                            return this.displayedObjects.sort((a, b) => (a.ocena > b.ocena) ? 1 : -1)
                        }
                        else{
                            return this.displayedObjects.sort((a, b) => (a.ocena < b.ocena) ? 1 : -1)
                        }
                    }
                    if(this.currentSort == 'lokacijaObjekta'){
                        if(this.currentSortDir == 'asc'){
                            return this.displayedObjects.sort((a, b) => (a.lokacija > b.lokacija) ? 1 : -1)
                        }
                        else{
                            return this.displayedObjects.sort((a, b) => (a.lokacija < b.lokacija) ? 1 : -1)
                        }
                    }
                },
                search(){
                    
                    console.log("na bek ide objekat: " + JSON.stringify(this.searchedObjekat));
                    let zahtev = 'name:';
                    if(this.searchedObjekat.name != ""){
                        zahtev += this.searchedObjekat.name;
                    }
                    else{
                        zahtev += "_";
                    }
                    zahtev += "&lokacija:"
                    if(this.searchedObjekat.lokacija.drzava != ''){
                        zahtev += this.searchedObjekat.lokacija.drzava;
                    }
                    else{
                        zahtev += "_";
                    }
                    zahtev += "&type:";
                    if(this.searchedObjekat.type != ""){
                        zahtev += this.searchedObjekat.type;
                    }
                    else{
                        zahtev += "_";
                    }
                    zahtev += "&ocena:";
                    if(this.searchedObjekat.ocena != ""){
                        zahtev += this.searchedObjekat.ocena;
                    }
                    else{
                        zahtev += "_";
                    }
                    console.log("ZAHTEV: " + zahtev);
                    dataService.searchObject(zahtev).then(response =>{
                        console.log("Stigli rezultati pretrage");
                        this.displayedObjects = response.data;     
                    }).catch(error => {
                        console.log(error.response);
                    });
                },
                getAllObjects(){
                dataService.getAllObjects().then(response => {
                    this.displayedObjects = response.data;
                    })
                },
                filterByName:function(rst){
                    if(this.filterInput.length != 0){
                        return  (rst.type.toLowerCase().indexOf(this.filterInput.toLowerCase()) > -1);
                    }
                    return true;
                },
                filterByOpened:function(rst){
                    if(this.filterOpenedOnly == true){
                        return (rst.opened == true);
                    }
                    return true;
                    
                },

                filterChange(){
                    console.log("ispis filtera: " + this.filterOpenedOnly)
                },
                showDetails:function(id){
                    this.$router.push(`/objects/${id}/details`);
                },
                comment:function(id){
                    this.$router.push(`/commentNew/${id}`)
                },
               
                filterReset(){
                    this.searchedObjekat.name = null;
                    this.searchedObjekat.type = null;
                    this.searchedObjekat.lokacija = null;
                    this.searchedObjekat.ocena = null;
                    this.searchedObjekat.logo = null;
                    this.searchedObjekat.manager = null;
                },
        
            },


    created(){
        if(JSON.parse(localStorage.getItem('token')) == null){
            this.$router.push(`/login`);
        }else{
            this.getAllObjects();
        }
    },
    components:{
        starrating : starRating,
    },
    computed:{
        sortObjects:function(){
            return this.displayedObjects.filter(this.filterByName).filter(this.filterByOpened).sort(this.sortiraj);
        }
    },
}

</script>

<style scoped>
#titleEffect{
  color:gold;
  font-weight: bold;
}

#second_raw{
    margin-top:5px;
}

#third_raw{
    margin-top:5px;
}

#fourth_raw{
    margin-top:5px;
}

.span_search{
    /* padding:5px; */
    padding-top:5px;
    padding-left:5px;
    padding-right:5px;
    color:#35424a;
    font-weight: bold;
}
#search_icon:hover{
    cursor: pointer;
    
}

.arrow:hover{
    cursor: pointer;
}


.classButton{
  margin-left: 5px;
  color:#fff;
  background-color:#35424a;
  padding:5px;
}

.classButton:hover{
  margin-left: 5px;
  color:#fff;
  background-color:gold;
  font-weight: bold;
  padding:10px;
}

form{
    position: relative;
}

form #search_icon{
    position: absolute;
    right:15px;
    top:-15px;
}


</style>