<template>
    <div id="home">

            <div class="container">
                <h1 style="margin-top:10px;color:#35424a;">Welcome <span id='titleEffect'>{{user.username}}</span>!</h1>
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

                    <div class="col-xl-3 col-md-6 mb-4">
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

                    <div class="col-xl-3 col-md-6 mb-4">
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

                    <div class="col-xl-3 col-md-6 mb-4">
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

                     <div class="col-xl-3 col-md-6 mb-4">
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

                     <div class="col-xl-3 col-md-6 mb-4">
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

                     <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-0 shadow">
                            <router-link style='text-decoration: none;color:#35424a;' to="/treninziCustomer" class="nav-link"
                                exact>
                                <img src="../assets/comment1.png" class="card-img-top" alt="...">
                                <div class="card-body text-center">
                                    <h5 class="card-title mb-0">Pregledajte svoje treninge</h5>
                                    
                                </div>
                            </router-link>
                        </div>
                    </div>




                  
                </div>
            </div>

    </div>
    
</template>

<script>


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
            role: ''
        }
   },
   created(){
        // 
   }
}
</script>

<style scoped>
    #titleEffect{
    color:gold;
    font-weight: bold;
    }
</style>
