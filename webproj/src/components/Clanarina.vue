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
                                    <li><b><font size="5">{{clanarina1.tipClanarine}}</font></b></li>
                                    <li><b><font size="5">{{clanarina1.cena}} RSD</font></b></li>
                                    <li><b><font size="5">{{clanarina1.brojTermina}} treninga mesecno</font></b></li>
                                    </ul>
                                <img height="280" width="300" src="../assets/logo.png" class="card-img-top" alt="...">
                                </div>
                                <div v-show="isCustomer" class="card-footer  text-center">
                                    <button type='submit' class="btn button_1" id='btnRegistration'><b>Kupi</b></button>
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
                                    <li><b><font size="5">{{clanarina2.tipClanarine}}</font></b></li>
                                    <li><b><font size="5">{{clanarina2.cena}} RSD</font></b></li>
                                    <li><b><font size="5">{{clanarina2.brojTermina}} treninga mesecno</font></b></li>
                                    </ul>
                                <img height="280" width="300" src="../assets/logo.png" class="card-img-top" alt="...">
                                </div>
                                <div v-show="isCustomer" class="card-footer  text-center">
                                    <button type='submit' class="btn button_1" id='btnRegistration'><b>Kupi</b></button>
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
                                    <li><b><font size="5">{{clanarina3.tipClanarine}}</font></b></li>
                                    <li><b><font size="5">{{clanarina3.cena}} RSD</font></b></li>
                                    <li><b><font size="4">{{clanarina3.brojTermina}} treninga godisnje</font></b></li>
                                    </ul>
                                <img height="280" width="300" src="../assets/logo.png" class="card-img-top" alt="...">
                                </div>
                                <div v-show="isCustomer" class="card-footer  text-center">
                                    <button v-on:click='kupiClanarinu()' class="btn button_1" id='btnRegistration'><b>Kupi</b></button>
                                </div>
                            
                        </div>
                    </div>               
                </div>
            </div>

    </div>
    
</template>


<script>


export default {
    data(){
        return{

            clanarina1:{
                ime: "S LITE",
                tipClanarine: "1 mesec",
                datumPlacanja: "",
                datumIvremeVazenja: "",
                cena:2000,
                kupac:"",
                status:"neaktivna",
                brojTermina:14
            },
            clanarina2:{
                ime: "S CLASSIC",
                tipClanarine: "3 mesecna",
                datumPlacanja: "",
                datumIvremeVazenja: "",
                cena:4500,
                kupac:"",
                status:"neaktivna",
                brojTermina:40
            },
            clanarina3:{
                ime: "S PREMIUM",
                tipClanarine: "godisnja",
                datumPlacanja: "",
                datumIvremeVazenja: "",
                cena:18000,
                kupac:"",
                status:"neaktivna",
                brojTermina:250
            },
            isCustomer : true,
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
            this.isCustomer=true;
        }
        
   },
   methods(){
    
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


</style>



