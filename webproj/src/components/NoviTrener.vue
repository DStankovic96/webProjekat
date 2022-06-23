<template>
    
        <div class="container">
              <br>
            <br>
            <br>
            <h2>Kreiranje novog trenera</h2>
            <h3>Popunite formular kako biste kreirali novog trenera...</h3>
            <table class="table" id="table_newcoach">
                <tbody>
                    <tr>
                        <td>
                            Korisnicko ime:
                        </td>
                        <td>
                            <input type="text" placeholder="Unesite korisnicko ime..."  v-model="newCoach.username">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Ime:
                        </td>
                        <td>
                            <input type="text" placeholder="Unesite ime..."  v-model="newCoach.name">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Prezime:
                        </td>
                        <td>
                            <input type="text" placeholder="Unesite prezime..."  v-model="newCoach.lastname">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Lozinka:
                        </td>
                        <td>
                            <input type="password" placeholder="Unesite lozinku..." v-model="newCoach.password">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Pol:
                        </td>
                        <td>
                            <span >
                                <select style="width:100%" v-model="newCoach.gender">
                                    <option disabled value="">Odabir pola</option>
                                    <option v-bind:key="polTemp.naziv" v-for="polTemp in polovi">{{polTemp.naziv}}</option>
                                </select>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Datum rodjenja:
                        </td>
                        <td>
                            <vuejsDatepicker v-model="selectedDate" style="padding:5px;" placeholder="Odaberite datum rodjenja" ></vuejsDatepicker>
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2">
                            <div class="text-center">
                                <div v-if='messages.successResponse' class="alert alert-success" v-html="messages.successResponse"></div>
                                <div v-if='messages.errorResponse' class="alert alert-error" v-html="messages.errorResponse"></div>
                                <button class="btn btn-success" v-on:click='createCoach()'>Potvrdi</button>
                            </div>
                            
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
        
    
</template>

<script>
// import dataService from '../services/DataService'
import Datepicker from 'vuejs-datepicker'

export default {
    components:{
        vuejsDatepicker:Datepicker
    },
    watch:{
        'selectedDate' : function(val, oldVal){
            console.log('Datum pre: ' + this.newCoach.dateOfBirth);
             this.newCoach.dateOfBirth = this.selectedDate.toString().substring(4, 15);
            console.log('Datum posle: ' + this.newCoach.dateOfBirth);
        }
    },
    data(){
        return{
            newCoach:{
                username:'',
                name:'',
                lastname:'',
                password:'',
                gender:'',
                dateOfBirth:''              
            },
            selectedDate: '',

            messages:{
                successResponse:'',
                errorResponse:'',
            },
            polovi:[
                {naziv:'musko'},
                {naziv:'zensko'}
            ]
        }
    },
    methods:{
        createCoach:function(){
            console.log("Kreiranje trenera: " + this.newCoach.username);
            dataService.addCoach(this.newCoach).then(response => {
                console.log("Stigao odgovor sa beka: " + response);
                this.messages.successResponse= "<h4>Uspesno ste kreirali trenera.</h4>"
                setTimeout(() => this.$router.push("/home"),5000);
            }).catch(error => {
                if(error.response.status === 500 || error.response.status === 404){
                        this.messages.errorResponse= `<h4>We had some server errors, please try again later!</h4>`;
                        setTimeout(() => this.messages.errorResponse='', 5000);
                }
            })
        }
    },
    created(){
        // if(JSON.parse(localStorage.getItem('token')) == null){
        //     this.$router.push(`/login`);
        // }else{

        // }
      }
}

</script>


<style scoped>


#table_newcoach td:first-child{
    font-weight: 700;
    width:30%;
    
}
#table_newcoach tr:nth-child(even){
    background-color: lightgray;
    font-weight: 600;
}
#table_newcoach td:last-child{
    width:100%;
}
#table_newcoach{
    border-style: solid;
}
input{
    width: 100%;
}
h2 {
    text-align:center;
}
h3 {
    text-align:center;
}

</style>