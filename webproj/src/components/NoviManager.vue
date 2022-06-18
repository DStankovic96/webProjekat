<template>
        
        <div class="container">
             <br>
            <br>
            <br>
            <h2>Kreiranje novog menadzera</h2>
            <h3>Popunite formular kako biste kreirali novog menadzera...</h3>
            <table class="table"  id="table_newmanager">
                <tbody>
                    <tr>
                        <td>
                            <label class='label'>Korisnicko ime menadzera:</label>
                        </td>
                        <td>
                            <input type="text" placeholder="Unesite korisnicko ime menadzera..."  v-model="newManager.username">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class='label'>Ime menadzera:</label>
                        </td>
                        <td>
                            <input  type="text" placeholder="Unestite ime menadzera..."  v-model="newManager.name">
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label class='label'>Prezime menadzera:</label>
                        </td>
                        <td>
                            <input type="text" placeholder="Unestite ime menadzera..."  v-model="newManager.lastname">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class='label'>Pol menadzera:</label>
                        </td>
                        <td>
                            <span >
                                <select style="padding:5px; width:100%;" v-model="newManager.gender">
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
                             <vuejsDatepicker style="padding:5px; width:100%;" placeholder="Odaberite datum rodjenja" v-model="selectedDate"></vuejsDatepicker>
                        </td>
                    </tr>
                    <tr>
                        <td>*Sportski objekat:</td>
                        <td>
                            <select style="padding:5px; width:100%;" v-model="newManager.objekat">
                                <option disabled value="">Odabir sportskog objekta</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2">
                            <div class="text-center">
                                <div v-if='messages.successResponse' class="alert alert-success" v-html="messages.successResponse"></div>
                                <div v-if='messages.errorResponse' class="alert alert-success" v-html="messages.errorResponse"></div>
                                <button class="btn btn-success" v-on:click='createManager()'>Potvrdi</button>
                            </div>
                        </td>
                    </tr>
                </tfoot>
            </table>
            
        </div>

</template>


<script>
import dataService from '../services/DataService'
import Datepicker from 'vuejs-datepicker'

export default{
    data(){
        return{

            selectedDate:'',
            
            
            polovi:[
                {naziv:'musko'},
                {naziv:'zensko'}
            ],
            newManager:{
                username:'',
                name:'',
                lastname:'',
                password:'1234',
                gender:'',
                dateOfBirth:'',
                objekat:''
            },
            messages:{
                successResponse:'',
                errorResponse:'',
            },
            allManagers:[]
        }
        
    },
    watch:{
        'selectedDate' : function(val, oldVal){
            console.log('Datum pre obrade: ' + this.newManager.dateOfBirth);
            this.newManager.dateOfBirth = this.selectedDate.toString().substring(4, 15);
            console.log("Datum posle obrade: " + this.newManager.dateOfBirth)
        }
    },
    methods:{
        
        
    },
    components:{
        vuejsDatepicker:Datepicker,
    },
    created(){      
       
    }
}
</script>


<style scoped>


#table_newmanager td:first-child{
    font-weight: 700;
    width:30%;
    
}
#table_newmanager tr:nth-child(even){
    background-color: lightgray;
    font-weight: 600;
}
#table_newmanager td:last-child{
    width:100%;
}
#table_newmanager{
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