<template>
    
        <div class="container">
             <br>
            <br>
            <h2>Moderacija komentara o sportskom centru</h2>
            <p>Klikom na dugme Odobri/Odbaci, omogucavate odobravanje/odbacivanje komentara</p>

            <div>
                <h3>Prikaz neodobrenih komentara</h3>
                <div>
                    <div class="container" style="min-height:300px;overflow:auto;">
                        <p>Prikaz komentara o sportskom centru i usluzi koji nisu prosli moderaciju...</p>
                        <section style="margin-left: 10px; margin-bottom:100px">
                            <div>
                                <div class="row">
                                    <div class="col-md-4" v-bind:key="tempKomentar.tekstKomentara" v-for="tempKomentar in neodobreniKomentari">
                                        <div v-show="!tempKomentar.odobren" style="margin-bottom:30px;" class="card h-100">
                                            <!-- <img class="card-img-top" :src="tempArtikal.slika" alt="card image collar"> -->
                                            <div class="card-body">
                                                <h5 class="card-title">Komentar</h5>
                                                <p class="card-text">Komentator: {{tempKomentar.kupacKomentator}}</p>
                                                <p class="card-text">Sportski centar: {{tempKomentar.komentarisaniCentar}}</p>
                                                <p class="card-text">Tekst komentara: {{tempKomentar.tekstKomentara}}</p>
                                                <starrating v-model="tempKomentar.ocena" :star-size="20" read-only></starrating>
                                                <button style="margin-top:10px;" v-on:click="odobriKomentar(tempKomentar)" class="btn btn-primary">Odobri</button>
                                                <button style="margin-top:10px;" v-on:click="odbaciKomentar(tempKomentar)" class="btn btn-danger">Odbaci</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                    <!-- <div style="margin-bottom:100px;" class="text-center">
                        <button class="btn btn-success" @click="potvrdiModeraciju()">Potvrdi</button>
                    </div> -->
                </div>
            </div>

        </div>
    
</template>

<script>
import dataService from '../services/DataService'
import starRating from 'vue-star-rating'
export default {
    components:{
        starrating: starRating,
    },
    data(){
        return{
            idManager: '',
            neodobreniKomentari:{

            },
             sviKomentari:{

             }
        }
    },
    methods:{
    
        odbaciKomentar(cmnt){
            console.log("odbacen" + JSON.stringify(cmnt));
            cmnt.odobren = false;
            console.log("odbacen" + JSON.stringify(cmnt));
            dataService.sendModeratedCommentList(cmnt).then(response => {
                console.log('moderacija poslata na server, stigao odgovor');
            }).catch(error => {
                console.log(error.response);
            })
        },
        odobriKomentar(cmnt){
             console.log("odobren" + JSON.stringify(cmnt));
            cmnt.odobren = true;
            console.log("odobren" + JSON.stringify(cmnt));
            dataService.sendModeratedCommentList(cmnt).then(response => {
                console.log('moderacija poslata na server, stigao odgovor');
            }).catch(error => {
                console.log(error.response);
            })
        },
        // getId(){
        //     this.idManager = (JSON.parse(localStorage.getItem('token'))).username;
        //     console.log('idManager: ' + this.idManager);
        // },
        getUnmoderated(){
            // let temp = JSON.parse(localStorage.getItem('token'));
            // idManager = temp.username;
            dataService.getAllUnmoderatedComments().then(response => {
            console.log('stigli svi nemoderirani komentari');
            this.neodobreniKomentari = response.data;
            console.log(JSON.stringify(this.neodobreniKomentari))
            }).catch(error => {
                console.log(error.response);
            });
        },
        getModerated(){
            dataService.getAllCommentsById(this.idManager).then(response => {
            console.log('stigli svi komentari');
            this.sviKomentari = response.data;
            }).catch(error => {
                console.log(error.response);
            });
        }
    },
    created(){
        // this.getId();
        // this.getModerated();
        this.getUnmoderated();
    }
}
</script>

<style scoped>

</style>