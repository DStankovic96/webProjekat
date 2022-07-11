<template>
    <div id="home">

            <div class="container">
                <h1 style="margin-top:10px;color:#35424a;">Welcome {{user.username}}!</h1>
                <hr style='background:#35424a;height:1px;'>
            </div>


            <div class="container">
                <div class="row">
                    
                    
                    
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                             <router-link style='text-decoration: none;color:#35424a;' to="/profile" class="nav-link" exact>
                                <img height="248" src="../assets/profilee.png" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Moj Profil</h5>
                                </div>
                            </router-link>
                        </div>
                    </div>

                    <div v-show="isAdmin" class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/newManager" class="nav-link"
                                exact>
                                <img src="../assets/managerr.jpg" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Kreiranje novog menadžera</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>

                    <div v-show="isAdmin" class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/newObject" class="nav-link"
                                exact>
                                <img height="223" src="../assets/sportskicentar.jpg" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Kreiranje novog sportskog objekta</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>

                    <div v-show="isAdmin" class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/newCoach" class="nav-link"
                                exact>
                                <img height="223" src="../assets/coach.jpg" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Dodavanje novog trenera</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>
                    <div v-show="isAdmin" class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/sviKorisnici" class="nav-link"
                                exact>
                                <img height="223" src="../assets/logo.png" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Prikaz svih korisnika u sistemu</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>
                     <div v-show="isManager" class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/object/details" class="nav-link"
                                exact>
                                <img height="223" src="../assets/logo.png" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Prikaz Vaseg objekta</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>

                      <div v-show="isCoach" class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/treninzi/details" class="nav-link"
                                exact>
                                <img height="223" src="../assets/logo.png" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Pregled treninga</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>

                     <div v-show="isManager" class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/noviTrening" class="nav-link"
                                exact>
                                <img height="223" src="../assets/coach.jpg" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Dodavanje novog treninga</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>

                     <div v-show="isAdmin" class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/moderation" class="nav-link"
                                exact>
                                <img height="223" src="../assets/comment1.png" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Moderacija komentara</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>

                     <div v-show="isCustomer" class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/treninzi/detailsKupac" class="nav-link"
                                exact>
                                <img src="../assets/comment1.png" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Pregledajte svoje treninge</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>
                    

                <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/searchObjects" class="nav-link"
                                exact>
                                <img height="248" src="../assets/sportskicenn.jpg" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Pretraga objekata</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>



                  
                </div>
            </div>

    </div>
    
</template>

<script>


import dataService from '../services/DataService'
export default {
   components:{
    
   },
   data(){
        return{
            user:{
                username:''
            },
            mode: false,
            isAdmin: false,
            isCustomer: false,
            isObican: false,
            isManager: false,
            isCoach: false,
            role: '',

            object: [],
            id:''

        
        }
   },
   methods:{
        parseJwt:function(token) {
            var base64Url = token.split('.')[1];
            var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));

            return JSON.parse(jsonPayload);
        }
    },
   created(){
        // 

         // let parsToken = this.parseJwt(localStorage.getItem('token'));
        //console.log("ISPIS TOKENA:" + JSON.stringify(parsToken))
        //localStorage.setItem('parsToken', JSON.stringify(parsToken));

        
         let temp = JSON.parse(localStorage.getItem('token'));
        if(temp==null)
        {
            this.isnCustomer=true;
        }
        if(temp.role ==='admin')
        {
            this.isAdmin=true;
            this.role='admin';
        }
        if(temp.role==='manager')
        {
            this.isManager=true;
            this.role='manager';
            dataService.getObjectByManager(temp.username).then(response => {
                this.object = response.data;
                this.id=this.object.name;
                console.log(this.object)
                console.log('stigao objekat sa servera: ' + this.object.name);
            }).catch(error => {
                console.log(error.response);
            });
        
         
        }
        if(temp.role==='customer')
        {
            this.isCustomer=true;
            this.role='customer';
        }
        if(temp.role==='coach')
        {
            this.isCoach=true;
            this.role='coach';
        }       
   },
   methods : {
        
   }
}
</script>

<style scoped>
    #titleEffect{
    color:gold;
    font-weight: bold;
    }
</style>
