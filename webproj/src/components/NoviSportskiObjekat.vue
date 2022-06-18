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
                    <!-- <tr style="height:500px">
                        <td>Slike restorana:</td>
                        <td>
                            <table id="tabela_5" class="table table-dark">
                                <thead>
                                    <tr>
                                        <th class="text-center" colspan="2">Odabrane slike restorana(vise slika):</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div style="height:300px; overflow-y: scroll;" class="container">
                                                <div class="col-ml-4" v-bind:key="tmpImg.name" v-for="tmpImg in images">
                                                    <div class="card">
                                                        <img class="card-img-top" style="margin-top:10px; margin-bottom:10px" :src="tmpImg">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <td colspan="2" class="text-center">
                                        <div>
                                            <input  type="file" style="display:inline-block; width:70%" class="filestyle"  @change="uploadImage2" multiple/>
                                            <button style="font-weight:600;display:inline-block;width:100px;" class="btn btn-warning"  @click="ponistiIzbor()">Ponisti</button>
                                        </div> 
                                    </td>
                                </tfoot>
                            </table>
                        </td>
                    </tr> -->

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
                xCoord:'',
                yCoord:'',
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
            tanjeAktivnosti: '',

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
    }
    
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
