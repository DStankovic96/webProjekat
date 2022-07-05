<template>
    <div id="restaurants-overview">
        <div class="container" id='page-title'>
            <h1 style="margin-top:10px;color:#35424a;">Lista <span id='titleEffect'>objekata</span></h1>
            <hr style='background:#35424a;height:1px;'>
        </div>
        

        <section style="margin-left: 10px;">
            <div>
            <!-- if we are 3 cards wide start a new row -->
                <div class="row">
                    <div class="col-md-4" v-bind:key="index" v-for="(tempEntitet, index) in displayedObjects.slice(0,15)">
                        <div class="card h-100">
                            
                            <!-- <img class="card-img-top" :src="product.thumbnailUrl" alt="card image collar"> -->
                            <div class="card-body">
                                <h5 class="card-title">Objekat {{tempEntitet.name}}</h5>
                                <p class="card-text">Objekat {{tempEntitet.type}} - {{tempEntitet.opened}}</p>
                                <!-- <p class="card-text">Objekat {{tempEntitet.logo}}</p> -->
                                
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

             getAllObjects(){
            dataService.getAllObjects().then(response => {
                this.displayedObjects = response.data;
                })
            },
        
            },
     created(){
        if(JSON.parse(localStorage.getItem('token')) == null){
            this.$router.push(`/login`);
        }else{
            this.getAllObjects();
        }
    }
    // computed:{
    //     sortObjects:function(){
    //         return this.displayedObjects.filter(this.filterByName).filter(this.filterByOpened).sort(this.sortiraj);
    //     }
    //},
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