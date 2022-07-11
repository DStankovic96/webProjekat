<template>
      <div >

            <div class="container">
                <center><h1 style="margin-top:10px;color:#35424a;">Kupite Clanarinu!</h1></center>
                <hr style='background:#35424a;height:1px;'>
            </div>


            <div class="container">
                <div class="row">
                    
                    
                    
                    <div class="col-xl-4 col-md-6 mb-4">
                        <div class="card shadow">
                            
                              <div class="card-header  text-center">
                                    <h4 class="card-title mb-0"><b><font color="white">{{clanarina1.ime}}</font></b></h4>
                                </div>
                                <div class="card-body text-center">
                                     <br>
                                     <ul style="line-height:300%">
                                    <li><b><font size="5">{{clanarina1.typeOfClanarina}}</font></b></li>
                                    <li><b><font size="5">{{clanarina1.cena}} RSD</font></b></li>
                                    <li><b><font size="5">{{clanarina1.brojTermina}} treninga mesecno</font></b></li>
                                    </ul>
                                <img height="280" width="300" src="../assets/logo.png" class="card-img-top" alt="...">
                                </div>
                                <div v-show="isCustomer" class="card-footer  text-center">
                                    <button v-on:click='kupiClanarinu(clanarina1)' class="btn button_1" id='btnRegistration'><b>Kupi</b></button>
                                </div>
                               
                           
                        </div>
                    </div>   

                     <div class="col-xl-4 col-md-6 mb-4">
                        <div class="card shadow">
                               <div class="card-header1  text-center">
                                    <h4 class="card-title mb-0"><b><font color="white">{{clanarina2.ime}}</font></b></h4>
                                    
                                </div>
                                <div class="card-body text-center">
                                    <br>
                                    <ul style="line-height:300%">
                                    <li><b><font size="5">{{clanarina2.typeOfClanarina}}</font></b></li>
                                    <li><b><font size="5">{{clanarina2.cena}} RSD</font></b></li>
                                    <li><b><font size="5">{{clanarina2.brojTermina}} treninga mesecno</font></b></li>
                                    </ul>
                                <img height="280" width="300" src="../assets/logo.png" class="card-img-top" alt="...">
                                </div>
                                <div v-show="isCustomer" class="card-footer  text-center">
                                    <button v-on:click='kupiClanarinu(clanarina2)' class="btn button_1" id='btnRegistration'><b>Kupi</b></button>
                                </div>
                               
                        </div>
                    </div> 

                     <div class="col-xl-4 col-md-6 mb-4">
                        <div class="card shadow">
                             <div class="card-header2  text-center">
                                    <h4 class="card-title mb-0"><b><font color="white">{{clanarina3.ime}}</font></b></h4>
                                </div>
                                <div class="card-body text-center">
                                    <br>
                                     <ul style="line-height:300%">
                                    <li><b><font size="5">{{clanarina3.typeOfClanarina}}</font></b></li>
                                    <li><b><font size="5">{{clanarina3.cena}} RSD</font></b></li>
                                    <li><b><font size="4">{{clanarina3.brojTermina}} treninga godisnje</font></b></li>
                                    </ul>
                                <img height="280" width="300" src="../assets/logo.png" class="card-img-top" alt="...">
                                </div>
                                <div v-show="isCustomer" class="card-footer  text-center">
                                    <button v-on:click='kupiClanarinu(clanarina3)' class="btn button_1" id='btnRegistration'><b>Kupi</b></button>
                                </div>
                                  <!-- <div v-if='messages.errorReport' class="alert alert-danger" v-html="messages.errorReport"></div>
		                <div v-if='messages.successReport' class="alert alert-success" v-html="messages.successReport"></div>   -->
                            
                        </div>

                    </div>      

                    <section  v-show="isCustomer" id="newsletter">
                    <div class="container" id="levo">
                        <h2 style="color: orange "> <b> &nbsp;&nbsp;&nbsp;{{clanarina4.ime}} (1 mesec, 10 treninga, 1500 RSD ) </b>
                        </h2>
                       <div  id="clana">
                        <button v-on:click='kupiClanarinu(clanarina4)' class="btn button_12" id='btnRegistration'><b>Kupi</b></button>
                        </div>
                         <!-- <div v-if='messages.errorReport' class="alert alert-danger" v-html="messages.errorReport"></div>
		                <div v-if='messages.successReport' class="alert alert-success" v-html="messages.successReport"></div>   -->
                        <!-- <form id="desno">
                            <button v-on:click='kupiClanarinu(clanarina4)' class="btn button_12" id='btnRegistration'><b>Kupi</b></button>
                        </form> -->
                    </div>
                </section>    
                 <section v-show="!isCustomer" id="newsletter12">
                    <div class="container" id="levo">
                        <center><h2 style="color: orange "> <b> {{clanarina4.ime}} (1 mesec, 10 treninga, 1500 RSD ) </b>
                        </h2></center>
                      
                        
                        <!-- <form id="desno">
                            <button v-on:click='kupiClanarinu(clanarina4)' class="btn button_12" id='btnRegistration'><b>Kupi</b></button>
                        </form> -->
                    </div>
                </section>        
                </div>
            </div>

    </div>
    
</template>


<script>
import DataService from '../services/DataService';


export default {
    data(){
        return{

             messages:{
                successReport:'',
                errorReport:'',
            },

            clanarina1:{
                ime: "S LITE",
                typeOfClanarina: "1 mesec",
                datumPlacanja: "",
                datumVazenja: "",
                cena:2000,
                customer:"",
                statusClanarine:"neaktivna",
                brojTermina:14
            },
            clanarina2:{
                ime: "S CLASSIC",
                typeOfClanarina: "3 meseca",
                datumPlacanja: "",
                datumVazenja: "",
                cena:4500,
                customer:"",
                statusClanarine:"neaktivna",
                brojTermina:40
            },
            clanarina3:{
                ime: "S PREMIUM",
                typeOfClanarina: "1 godina",
                datumPlacanja: "",
                datumVazenja: "",
                cena:18000,
                customer:"",
                statusClanarine:"neaktivna",
                brojTermina:250
            },
            clanarina4:
            {
                ime: "SKOLSKA",
                typeOfClanarina: "1 mesec",
                datumPlacanja: "",
                datumVazenja: "",
                cena:1500,
                customer:"",
                statusClanarine:"neaktivna",
                brojTermina:10
            },
            isCustomer : false,
            clanarineKupac:[],
            
        }
    },
    created(){
        // 

         // let parsToken = this.parseJwt(localStorage.getItem('token'));
        //console.log("ISPIS TOKENA:" + JSON.stringify(parsToken))
        //localStorage.setItem('parsToken', JSON.stringify(parsToken));

        
         let temp = JSON.parse(localStorage.getItem('token'));
        if(temp.role==='customer')
        {
            let tempUsername = JSON.parse(localStorage.getItem('token')).username;
            console.log("trenutni korisnik:" + temp.username + this.isCustomer);
            this.isCustomer=true;
            this.getClanarineKupac(tempUsername);
             console.log("CCCB:" + JSON.stringify(this.clanarineKupac));
        }
        
    },
   methods:{
            getClanarineKupac(tempUsername){
                
                DataService.getAllClanarineKupac(tempUsername).then(response => {
                   
                    this.clanarineKupac=response.data;
                    console.log("dawawdaasdwa:" + JSON.stringify(this.clanarineKupac));
                }).catch(error => {
                    
                console.log(error.response);
                });
            },
            kupiClanarinu(clanarina){
                
                let tempUsername = JSON.parse(localStorage.getItem('token')).username;
                
                // this.getClanarineKupac(tempUsername);
               console.log("clanarine pozvane iz metode kupi: " + JSON.stringify( this.clanarineKupac));
                // console.log("clanarina pre unosenja vrednosti: " + JSON.stringify(clanarina));
               if(this.clanarineKupac.length === 0){
                console.log("PRVI TESTTA" + JSON.stringify(this.clanarineKupac));
                clanarina.statusClanarine="aktivna";
                clanarina.customer=tempUsername;
                clanarina.datumPlacanja=new Date().toString().substring(4, 15);
                if(clanarina.typeOfClanarina === '1 mesec'){
                    let myDate = new Date(new Date().getTime()+(30*24*60*60*1000));
                    clanarina.datumVazenja = myDate.toString().substring(4, 15);
                    // console.log("clanarina.datumVazenja: " + clanarina.datumVazenja);
                }
                 if(clanarina.typeOfClanarina === '3 meseca'){
                    let myDate = new Date(new Date().getTime()+(92*24*60*60*1000));
                    clanarina.datumVazenja = myDate.toString().substring(4, 15);
                    // console.log("clanarina.datumVazenja: " + clanarina.datumVazenja);
                }
                 if(clanarina.typeOfClanarina === '1 godina'){
                  
                    let myDate = new Date(new Date().getTime()+(365*24*60*60*1000));
                    clanarina.datumVazenja = myDate.toString().substring(4, 15);
                    // console.log("clanarina.datumVazenja: " + clanarina.datumVazenja);
                }
                
               
                this.clanarineKupac.push(clanarina);
            
               DataService.posaljiClanarinu1(tempUsername,this.clanarineKupac).then(response=>{
                console.log('poslata clanarina na server. stigao odgovor');
                //  this.messages.successReport = `<h4>Vasa kupovina clanarine je poslata.</h4>`;
                //   setTimeout(() => this.messages.successReport = '', 3000)
                    // setTimeout(() => this.$router.push('/home'), 5000);
               }).catch(error => {
                    console.log(error.response);
                });
                 console.log("PRVI TESTTB" + JSON.stringify(this.clanarineKupac));
            }else{
                this.getClanarineKupac(tempUsername);
                 console.log("DRUGI TESTTA" + JSON.stringify(this.clanarineKupac));
                 for(let i = 0; i < this.clanarineKupac.length; i++){
                    if(this.clanarineKupac[i].statusClanarine === "aktivna")
                    {
                        this.clanarineKupac[i].statusClanarine = "neaktivna";
                    }
                    // console.log(this.clanarineKupac[i].statusClanarine);
                }
                
                clanarina.statusClanarine="aktivna";
                clanarina.customer=tempUsername;
                clanarina.datumPlacanja=new Date().toString().substring(4, 15);
                if(clanarina.typeOfClanarina === '1 mesec'){
                    let myDate = new Date(new Date().getTime()+(30*24*60*60*1000));
                    clanarina.datumVazenja = myDate.toString().substring(4, 15);
                    // console.log("clanarina.datumVazenja: " + clanarina.datumVazenja);
                }
                 if(clanarina.typeOfClanarina === '3 meseca'){
                    let myDate = new Date(new Date().getTime()+(92*24*60*60*1000));
                    clanarina.datumVazenja = myDate.toString().substring(4, 15);
                    // console.log("clanarina.datumVazenja: " + clanarina.datumVazenja);
                }
                 if(clanarina.typeOfClanarina === '1 godina'){
                  
                    let myDate = new Date(new Date().getTime()+(365*24*60*60*1000));
                    clanarina.datumVazenja = myDate.toString().substring(4, 15);
                    // console.log("clanarina.datumVazenja: " + clanarina.datumVazenja);
                }
                
                

                this.clanarineKupac.push(clanarina);

            
               DataService.posaljiClanarinu1(tempUsername,this.clanarineKupac).then(response=>{
                console.log('poslata clanarina na server. stigao odgovor');
                //  this.messages.successReport = `<h4>Vasa kupovina clanarine je poslata.</h4>`;
                //   setTimeout(() => this.messages.successReport = '', 3000)
                    // setTimeout(() => this.$router.push('/home'), 5000);
               }).catch(error => {
                    console.log(error.response);
                });
                console.log("DRUGI TESTTB" + JSON.stringify(this.clanarineKupac));
            }


        },
    
   }
   
}


</script>

<style scoped>

.card-header{
    background: rgb(78, 77, 77);
    /* position: absolute; */
    padding:25px;
    box-shadow: 0 4px 8px 0 rgba(199, 21, 21, 0.2);
    
}
.card-header1{
    background: rgb(0, 60, 255);
    /* position: absolute; */
    padding:25px;
    box-shadow: 0 4px 8px 0 rgba(199, 21, 21, 0.2);
    
}

.card-header2{
    background: gold;
    /* position: absolute; */
    padding:25px;
    box-shadow: 0 4px 8px 0 rgba(199, 21, 21, 0.2);
    
}

.button_1{
    background-color:#ff0000;
   
     height:50px;
    color:#fff;
    border:none;
    padding: 0 20px;
    font-size: 20px;
    width:80%;
}
#newsletter{
    padding:15px;
    background-color:#020000;
    color:#fff;
     width:100%;
}
#newsletter h2{
   float:left;
   width:75%;
}
#clana{
    float:right;
    padding-top:1px;
    width:25%;
 }
 .button_12{
    background-color:#ff0000;
   
     height:50px;
    color:#fff;
    border:none;
    padding: 0 10px;
    font-size: 25px;
    width:90%;
}
#newsletter12{
    padding:15px;
    background-color:#020000;
    color:#fff;
     width:100%;
}


</style>



