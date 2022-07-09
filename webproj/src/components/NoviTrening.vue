<template>
   
        <div class="container">
              <br>
            <br>
            <br>
        <h2>Kreiranje novog treninga za objekat</h2> <h2 style="color:blue">{{object.name}}</h2>
        <h3>Popunite formular kako biste kreirali novi trening...</h3>
        <table class="table" id="table_newtrening">
            <tbody>
                <tr>
                   <td>
                       <label class='label'>Naziv treninga:</label>
                    </td> 
                    <td>
                        <input type="text" placeholder="Unesite naziv treninga(najvise 100 karaktera)" maxlength="100" v-model="newTrening.naziv">
                    </td>
                </tr>
                <tr>
                   <td>
                       <label class='label'>Unesite tip treninga:</label>
                    </td> 
                    <td>
                        <span >
                            <select style="width:100%;padding:5px;" v-model="newTrening.tipTreninga">
                                <option disabled value="">Odabir tipa</option>
                                <option v-bind:key="tipTemp.tipp" v-for="tipTemp in tipovi">{{tipTemp.tipp}}</option>
                            </select>
                        </span>                    
                    </td>
                </tr>
                  <tr>
                   <td>
                       <label class='label'>Trajanje treninga u minutima:</label>
                    </td> 
                    <td>
                        <input type="number" min="0" placeholder="Unesite trajanje treninga ..."  v-model="newTrening.trajanje">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label class='label'>Dodajte trenera za ovaj trening:</label>
                    </td>
                    <td>
                        <span>
                            <select style="width:100%;padding:5px;" v-model="newTrening.trener">
                                <option disabled value="">Odaberite trenera</option>
                                <option v-bind:key="tempTrener.username" v-for="tempTrener in listaTrenera">{{tempTrener.username}}</option>
                            </select>

                        </span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label class='label'>Opis:</label>
                    </td>
                    <td>
                        <input type="text" placeholder="Unesite opis"  v-model="newTrening.opis">
                    </td>
                </tr>
                <tr style="height:500px">
                        <td>Slike treninga:</td>
                        <td>
                            <table id="tabela_5" class="table table-light">
                                <thead>
                                    <tr>
                                        <th class="text-center" colspan="2">Odabrane slike treninga:</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div style="height:300px; overflow-y: scroll;" class="container">
                                                <div class="col-ml-4" v-bind:key="tempSlika" v-for="tempSlika in slike">
                                                    <div class="card">
                                                        <img class="card-img-top" style="margin-top:10px; margin-bottom:10px" :src="tempSlika">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <td colspan="2" class="text-center">
                                        <div>
                                            <input  type="file" style="display:inline-block; width:70%" class="filestyle"  @change="uploadImage" multiple/>
                                            <button style="font-weight:600;display:inline-block;width:100px;" class="btn btn-warning"  @click="ponistiIzbor()">Ponisti</button>
                                        </div> 
                                        
                                    </td>
                                </tfoot>
                            </table>
                        </td>
                    </tr>
            </tbody>
            <tfoot style="margin-bottom:200xp; height:150px;">
                <td colspan="2" class="text-center">
                    <button class="btn btn-success" v-on:click='createTrening()'>Potvrdi</button>
                    <div v-if='messages.successResponse' class="alert alert-success" v-html="messages.successResponse"></div>
                    <div v-if='messages.errorResponse' class="alert alert-danger" v-html="messages.errorResponse"></div>
                    
                </td>
            </tfoot>
        </table>
        </div>
      
 
    
</template>

<script>
import dataService from '../services/DataService'

export default {
    data(){
        return{
            username:'',
             object:{

             },

            listaTrenera:[],
            listaTrenera1:[
                {username:'Marko'},
                {username:'Petar'},
                {username:'Mika'}
                ],
            slike:[],
            newTrening:{
                naziv:'',
                tipTreninga:'',
                trener: '',
                trajanje:'',
                opis:'',               
                slika:[],       
            },
            messages:{
                successResponse:'',
                errorResponse:'',
            },
            tipovi:[
                {tipp:'grupni'},
                {tipp:'personalni'}
            ]
        }
    },

    methods:{
        getObject(){
            dataService.getObjectByManager(this.username).then(response => {
                this.object = response.data;
                console.log(this.object)
                console.log('stigao objekat sa servera: ' + this.object.name);
            }).catch(error => {
                console.log(error.response);
            });
        },
        getListaTrenera(){
            dataService.getListaTrenera().then(response => {
                this.listaTrenera = response.data;
            })
        },
        ponistiIzbor(){
            this.newTrening.slika = '',
            this.slike = [];
        },
        createTrening:function(){
            

            console.log(JSON.stringify(this.newTrening));
            console.log(this.newTrening);
            console.log("NA BEK IDE KORISNICKO IME: " + this.username);
            dataService.addTrening(this.object.name, this.newTrening).then(response => {
                console.log(response.data);
                if(response.data.length !==0){
                    console.log('aaaa')
                    this.messages.successResponse= "<h4>Uspesno ste kreirali trening.</h4>"
                    this.slike = [];
                    setTimeout(() => this.messages.successResponse='', 3000);
                }else{
                    console.log('bbbb')
                    this.messages.errorResponse= "<h4>Vec postoji taj trening.</h4>"
                    setTimeout(() => this.messages.errorResponse='', 3000);
                }
                
                
            }).catch(error => {
                console.log(error.response)
                if(error.response.status === 500 || error.response.status === 404){
                        this.messages.errorResponse= `<h4>We had some server errors, please try again later!</h4>`;
                        setTimeout(() => this.messages.errorResponse='', 5000);
                }
            })
        },
        uploadImage:function(e){
            console.log("uslo u upload");
            // ne moze da stoji const reader jer je filereader asinhron a ovde imamo
            // for loop sto znaci da je neophodno vise filereadera
            //const reader = new FileReader();
            let images = [];
            for(let i = 0; i < e.target.files.length; i++){
                images.push(e.target.files[i]);
                console.log("DODATA SLIKA BROJ: " + i);
            }
            for(let i = 0; i < images.length; i++){
                //kreiramo FileReader za svaku sliku pojedinacno
                let reader = new FileReader();
                reader.readAsDataURL(images[i]);
                reader.onload = () => {
                    this.newTrening.slika = reader.result.toString();
                    this.slike.push(reader.result);
                }
            }
        },
    },
    created(){      
        let temp = JSON.parse(localStorage.getItem('token'));
        this.username = temp.username;
        this.getObject();
        this.getListaTrenera();
        
    }
}
</script>


<style scoped>

#table_newtrening{
    border-style: solid;
}
#table_newtrening tr:nth-child(even){
    background-color: lightgray;
    font-weight: 600;
}
#table_newtrening tr:nth-child(odd){
    font-weight: 600;
    background-color: white;
}
#table_newtrening td:first-child{
    width: 30%;
}
#table_newtrening td:last-child{
    width: 100%;
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