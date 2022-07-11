<template>
    
</template>

<script>
import dataService from '../services/DataService'
import Datepicker from 'vuejs-datepicker';
import StarRating from "vue-star-rating";

export default{
    created(){
        let tempUsername = JSON.parse(localStorage.getItem('token')).username;
        console.log('Username: ' + tempUsername)
        this.getMojiTreninziKupac(tempUsername);
        
    },
   
  
    methods:{
        getMojiTreninziKupac(username){
            dataService.getMojiTreninziKupac(username).then(response => {
            console.log("stigli podaci o svojim treninzima");
            this.treninzi = response.data;
            }).catch(error =>{
                console.log(error.response);
            });
        },
        
       

       
      
       
    },
    
   
  
    data() {
        return{
            selectedDate1: '',
            selectedDate2: '',
           

            currentSortDir: 'asc',
            filterInput:'',
          
            filterStatus:'',

            sort(s){
                if(s == this.currentSort){
                    this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
                }
                this.currentSort = s;
            },

           
            messages: {
                errorAddress: '',
                errorPrice: '',
                errorPlannedCm: '',
                errorDates: '',
                errorResponse: '',
                successResponse: '',
            },
           
            //pomocna varijabla za prikaz rezultata pretrage
            searchResults: [],
             dates: {
                from: null,
                to: null
            },
            disabledDates: {
                to: null
            },


            
        treninzi:[],
        
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