<template>
    
        <div class="container">
            <br>
            <br>
            <center><h2>Kreiranje novog objekta</h2>
            <h3>Popunite formular kako biste kreirali novi objekat...</h3></center>
            <table class="table"  id="table_newobject">
                <tbody>
                    <tr>
                        <td>
                            <label class='label'>Naziv sportskog objekta:</label>
                        </td>
                        <td>
                            <input type="text" placeholder="Unesite naziv objekta..."  v-model="newObject.name">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class='label'>Tip objekta:</label>
                        </td>
                        <td>
                            <select style="width:100%;padding:5px;" v-model="newObject.type">
                                <option disabled value="">Tip objekta</option>
                                <option  v-bind:key="tipObjekta.naziv"  v-for="tipObjekta in tipoviObjekta">{{tipObjekta.naziv}}</option> 
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class='label'>Aktivno stanje objekta:</label>
                        </td>
                        <td>
                            <span >
                                <select style="padding:5px; width:100%;" v-model="stanjeAktivnosti">
                                    <option disabled value="">Odabir stanja</option>
                                    <option v-bind:key="polTemp.naziv" v-for="polTemp in opcije">{{polTemp.naziv}}</option>
                                </select>
                            </span>
                        </td>
                    </tr>

                    <tr>
                        <td>Adresa</td>
                        <td>
                            <div style="display:inline-block">
                                <input style="width:30%" class="input-class" type="text" placeholder="Naziv ulice..." v-model="adresa.ulica"/>
                                <input style="width:10%" class="input-class" type="number" min="0" placeholder="Broj..." v-model="adresa.broj"/>
                                <input style="width:30%" class="input-class" type="text" placeholder="Naziv mesta..." v-model="adresa.mesto"/>
                                <input style="width:25%" class="input-class" type="text" placeholder="Naziv drzave..." v-model="adresa.drzava"/>
                            </div>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>Odabir menadzera:</td>
                        <td>
                            <table id="tabela_5" class="table table-light">
                                <thead>
                                    <tr>
                                        <th class="text-center" colspan="2">Slobodni menadzeri:</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <!-- <div style="height:200px; overflow-y: scroll;" class="container">
                                                <div class="col-ml-4" v-bind:key="tempManager.username" v-for="tempManager in slobodniMenadzeri">
                                                    <div class="card">
                                                        <div class="card-img-top" style="margin-top:10px; margin-bottom:10px">{{tempManager.username}}</div>
                                                    </div>
                                                </div>
                                            </div> -->
                                            <select v-model="selectedManager"  style="padding:5px; width:100%;">
                                                <option disabled value="">Odaberite slobodnog menadzera...</option>
                                                <option v-bind:key="tempMenadzer.username" v-for="tempMenadzer in slobodniMenadzeri">{{tempMenadzer.username}}</option>
                                            </select>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <td colspan="2" class="text-center">
                                        <div>
                                            <button v-if="slobodniMenadzeri.length == 0" style="font-weight:600;display:inline-block;width:100px;" class="btn btn-warning"  @click="createManagerDialog()">Dodaj</button>
                                            <button v-show="showCreateManager" style="font-weight:600;display:inline-block;width:100px;" class="btn btn-error"  @click="closeDialog()">Zatvori</button>
                                        </div> 
                                        <div v-show="showCreateManager">
                                            <table class="table"  id="table_newmanager">
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <label class='label'>Korisnicko ime menadzera:</label>
                                                        </td>
                                                        <td>
                                                            <input type="text" placeholder="Unesite korisnicko ime menadzera objekta..."  v-model="newManager.username">
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
                                                                <select style="padding:5px; width:100%;" v-model="newManager.gender" >
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
                                                            <vuejsDatepicker style="padding:5px; width:100%;" placeholder="Odaberite datum rodjenja" v-model="newManager.dateOfBirth"></vuejsDatepicker>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                                <tfoot>
                                                    <tr>
                                                        <td colspan="2">
                                                            <div class="text-center">
                                                                <div v-if='messagesManager.successResponse' class="alert alert-success" v-html="messagesManager.successResponse"></div>
                                                                <div v-if='messagesManager.errorResponse' class="alert alert-danger" v-html="messagesManager.errorResponse"></div>
                                                                <button class="btn btn-success" v-on:click='createManager()'>Potvrdi</button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </td>
                                </tfoot>
                            </table>
                        </td>
                    </tr>
                    <tr style="height:500px">
                        <td>Logo objekta:</td>
                        <td>
                            <table id="tabela_5" class="table table-dark">
                                <thead>
                                    <tr>
                                        <th class="text-center" colspan="2">Odabrani logo objekta(jedna slika):</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div style="height:300px; overflow-y: scroll;" class="container">
                                                <div class="col-ml-4" v-bind:key="tempSlika.name" v-for="tempSlika in logo">
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
                                            <input  type="file" style="display:inline-block; width:70%" class="filestyle"  @change="uploadImage"/>
                                            <button style="font-weight:600;display:inline-block;width:100px;" class="btn btn-warning"  @click="ponistiIzborLoga()">Ponisti</button>
                                        </div> 
                                    </td>
                                </tfoot>
                            </table>
                        </td>
                    </tr>
                

                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2">
                            <div class="text-center">
                                <!-- <button @click="testirajZahtev()" class="btn btn-primary">Test zahteva</button> -->
                                <button @click="createObject()" class="btn btn-success">Kreiraj</button>
                                <div v-if='messages.successResponse' class="alert alert-success" v-html="messages.successResponse"></div>
                                <div v-if='messages.errorResponse' class="alert alert-danger" v-html="messages.errorResponse"></div>
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

export default {
    data(){
        return{
            showCreateManager: false,
            newManager:{
                username:'',
                name:'',
                lastname:'',
                password:'1234',
                gender:'',
                dateOfBirth:'',
                object:''
            },
            polovi:[
                {naziv:'Musko'},
                {naziv:'Zensko'}
            ],
            opcije:[
                {naziv:'radi'},
                {naziv:'ne radi'}
            ],
            messagesManager:{
                successResponse:'',
                errorResponse:'',
            },
            logo:[],
            adresa:{
                ulica:'',
                broj:'',
                mesto:'',
                drzava:'',
                // xCoord:'',
                // yCoord:'',
            },
            menadzerDodat: false,

            newObject:{
                name:'',
                type:'',
                menuItems:[],
                opened:true,
                lokacija:{

                },
                logo:'',
                // images:[],
                comments:[],
                manager:'',
                ocena:''
            },
             tipoviObjekta:[
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
            stanjeAktivnosti: '',

            //pomocne promenljive za razdvajanje zauzetih od slobodnih menadzera -> trebalo bi na beku da se uradi ovo
            selectedManager:null,
            slobodniMenadzeri:[],
            kreiranNoviMenadzer: false,

              //pomocna promenljiva koja je kontejner poruka koje se ispisuju korisniku
            messages:{
                successResponse:'',
                errorResponse:'',
                errorName:'',
                errorType:'',
                errorLogo:'',
                errorManager:'',
                errorAddress:'',
            },
        }
    },
    watch:{
        'selectedManager' : function (){
            this.odabranManager();
        },
        'stanjeAktivnosti' : function  (){
            if(this.stanjeAktivnosti == 'radi'){
                console.log('Objekat radi');
                this.newObject.opened = true;
            }else{
                console.log('Objekat ne radi');
                this.newObject.opened = false;
            }
        }
    },
    components:{
        vuejsDatepicker:Datepicker
    },
    methods:{
        createManager:function(){
            console.log('pokusaj kreiranja novog menadzera');

            if(this.menadzerDodat != true){
                if(this.newManager.dateOfBirth == '' || this.newManager.username == '' || this.newManager.lastname == '' || this.newManager.name == '' ||
                this.newManager.password == '' || this.newManager.gender == ''){
                    console.log('greska');
                    this.messagesManager.errorResponse= "<h4>Niste popunili sva potrebna polja.</h4>"
                    setTimeout(() => this.messagesManager.errorResponse='', 3000);
                }else{
                    this.menadzerDodat = true;
                    console.log('uspesno kreiran menadzer');
                    this.newManager.dateOfBirth = this.newManager.dateOfBirth.toString().substring(4, 15);
                    this.messagesManager.successResponse= "<h4>Uspesno ste kreirali menadzera.</h4>"
                    this.slobodniMenadzeri.push(this.newManager);
                    setTimeout(() => this.messagesManager.successResponse='', 3000);
                    this.kreiranNoviMenadzer = true;
                    this.showCreateManager = false;
                    this.newObject.manager = this.newManager.username;
                }
            }else{
                this.messagesManager.errorResponse= "<h4>Vec ste dodali menadzera.</h4>"
                setTimeout(() => this.messagesManager.errorResponse='', 3000);
            }
            
            console.log(JSON.stringify(this.newManager));
            console.log(this.newManager);
            // dataService.addManager(this.newManager).then(response => {
            //     if(response.data !== ''){
            //         this.messages.successResponse= "<h4>Uspesno ste kreirali menadzera.</h4>"
            //         setTimeout(() => this.messages.successResponse='', 3000);
            //     }else{
            //         this.messages.errorResponse= "<h4>Vec postoji menadzer sa tim korisnickim imenom.</h4>"
            //         setTimeout(() => this.messages.errorResponse='', 3000);
            //     }
                
                
            // }).catch(error => {
            //     if(error.response.status === 500 || error.response.status === 404){
            //             this.messages.errorResponse= `<h4>We had some server errors, please try again later!</h4>`;
            //             setTimeout(() => this.messages.errorResponse='', 5000);
            //     }
            // })
        },
         closeDialog(){
            this.showCreateManager = !this.showCreateManager;
        },
        createManagerDialog(){
            this.showCreateManager = !this.showCreateManager;


        },
        uploadImage:function(e){
            
            console.log("uslo u upload");
            const reader = new FileReader();
            let image = e.target.files[0];
            console.log(image);
            reader.readAsDataURL(image);
            reader.onload = () => {
                this.newObject.logo = reader.result.toString();
                this.logo.push(reader.result);
                console.log("odabran logo");
            }
            
        },
         ponistiIzborLoga(){
            this.logo = [];
            this.newObject.logo = '';
        },
        createObject:function(){
            console.log('pokusaj kreiranja')
            if (this.newObject.name == "") {
                this.messages.errorResponse = `<h4>Polje naziv objekta ne moze biti prazno!</h4>`;
                setTimeout(() => this.messages.errorResponse = '', 3000);
            }
            else if (this.newObject.type == "") {
                this.messages.errorResponse = `<h4>Polje tip objekta ne moze biti prazno!</h4>`;
                setTimeout(() => this.messages.errorResponse = '', 3000);
            }
            else if (this.newObject.logo == "") {
                this.messages.errorResponse= `<h4>Morate izabrati logo objekta!</h4>`;
                setTimeout(() => this.messages.errorResponse = '', 3000);
            }
            else if (this.newObject.manager === "") {
                this.messages.errorResponse = `<h4>Morate izabrati menadzera objekta!</h4>`;
                setTimeout(() => this.messages.errorResponse = '', 3000);
            }
            else{
                console.log('proslo provere');
                this.newObject.lokacija = this.adresa;

                //postavljenje polja object na vrednost polja name objekta newObject(u oba slucaja, i kada ima slobodnih i kada kreira novi)
                this.newManager.object = this.newObject.name;
                console.log("NA BEK SE SALJE OBJEKAT newObject: " + JSON.stringify(this.newObject));
                console.log("Na server se salje objekat newManager: " + JSON.stringify(this.newManager));
                dataService.addObject(this.newObject).then(response =>{
                    if(response.data != null){
                        alert("Uspesno ste dodali objekat.");
                    }
                    // alert("Uspesno ste dodali objekat.");
                    // this.$router.push("/home");
                }).catch(error => {
                    console.log(error.response);
                });

                //ako je kreiran novi menadzer onda ga upisujemo u bazu
                if(this.kreiranNoviMenadzer == true){
                    dataService.addManager(this.newManager).then(response => {
                        console.log("Dodat novi menadzer");
                        alert("Uspesno ste dodali objekat.");
                        this.$router.push("/home");
                    }).catch(error => {
                        console.log(error.response);
                    });//ako je izabran neki od postojecih menadzera onda se vrsi izmena u bazi
                }else if(this.kreiranNoviMenadzer == false){
                    dataService.updateManagerObject(this.newManager).then(response => {
                        console.log(response.data)
                        console.log("Izmenjen postojeci menadzer");
                        // alert("Uspesno ste dodali objekat.");
                        this.$router.push("/home");
                    }).catch(error => {
                        console.log(error.response);
                    })
                }
            }

            
        },
         odabranManager(){
            console.log('Odabran menadzer: ' + this.selectedManager);
            for(let i = 0; i < this.slobodniMenadzeri.length; i++){
                if(this.slobodniMenadzeri[i].username == this.selectedManager){
                    this.newManager = this.slobodniMenadzeri[i];
                }
            }
            this.newObject.manager = this.selectedManager;
            console.log('Menadzer novog objekta: ' + this.newObject.manager)
        },
         getListOfManagers(){
            console.log('lista menadzera')
            dataService.getFreeManagers().then(response => {
                console.log('stigla lista slobodnih menadzera')
                this.slobodniMenadzeri = response.data;
            }).catch(error => {
                console.log(error.response);
            });
        }
    },
}
</script>

<style>
#table_newobject td:first-child{
    font-weight: 700;
    width:30%;
    
}
#table_newobject tr:nth-child(even){
    background-color: lightgray;
    font-weight: 600;
}
#table_newobject td:last-child{
    width:100%;
}
#table_newobject{
    border-style: solid;
}
input{
    width: 100%;
}
</style>
