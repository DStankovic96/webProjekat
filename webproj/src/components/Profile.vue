<template>
    <div id="user-profile">
    <div>
        <div class="container" id='page-title'>
            <h1 style="margin-top:10px;color:#35424a;">Profil Usera</h1>
            <hr style='background:#35424a;height:1px;'>
        </div>
        <div class="container">
            <form>
                <ul id="services" class="list-group">
                <!-- Osoba -->
                    <li class="list-group-item" >
                        <h5 class="header5">Korisnicko ime</h5>
                        <h4 v-show="isCoach">{{profileCoach.username}}</h4>
                        <h4 v-show="isManager">{{profileManager.username}}</h4>
                        <h4 v-show="isAdmin">{{profileAdmin.username}}</h4>
                        <h4 v-show="isCustomer">{{profileCustomer.username}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Ime</h5>
                        <h4 v-show="isCoach">{{profileCoach.name}}</h4>
                        <h4 v-show="isManager">{{profileManager.name}}</h4>
                        <h4 v-show="isAdmin">{{profileAdmin.name}}</h4>
                        <h4 v-show="isCustomer">{{profileCustomer.name}}</h4>
                    </li>

                    <li class="list-group-item" >
                        <h5 class="header5">Prezime</h5>
                        <h4 v-show="isCoach">{{profileCoach.lastname}}</h4>
                        <h4 v-show="isManager">{{profileManager.lastname}}</h4>
                        <h4 v-show="isAdmin">{{profileAdmin.lastname}}</h4>
                        <h4 v-show="isCustomer">{{profileCustomer.lastname}}</h4>
                    </li>

                    <li class="list-group-item" >
                        <h5 class="header5">Pol</h5>
                        <h4 v-show="isCoach">{{profileCoach.gender}}</h4>
                        <h4 v-show="isManager">{{profileManager.gender}}</h4>
                        <h4 v-show="isAdmin">{{profileAdmin.gender}}</h4>
                        <h4 v-show="isCustomer">{{profileCustomer.gender}}</h4>
                    </li>

                    <li class="list-group-item" >
                        <h5 class="header5">Datum rodjenja</h5>
                        <h4 v-show="isCoach">{{profileCoach.dateOfBirth}}</h4>
                        <h4 v-show="isManager">{{profileManager.dateOfBirth}}</h4>
                        <h4 v-show="isAdmin">{{profileAdmin.dateOfBirth}}</h4>
                        <h4 v-show="isCustomer">{{profileCustomer.dateOfBirth}}</h4>
                    </li>
                </ul>

                    <div id='buttonUpdate'>
                    <button v-show="isCoach" type='button' class="btn btn-lg btn-success marg" v-on:click='updateUser(profileCoach.username)'> Update</button> 
                    <button v-show="isAdmin" type='button' class="btn btn-lg btn-success marg" v-on:click='updateUser(profileAdmin.username)'> Update</button> 
                    <button v-show="isManager" type='button' class="btn btn-lg btn-success marg" v-on:click='updateUser(profileManager.username)'> Update</button> 
                    <button v-show="isCustomer" type='button' class="btn btn-lg btn-success marg" v-on:click='updateUser(profileCustomer.username)'> Update</button> 
                </div>
            </form>
        </div>
    </div>
</div>

</template>


<script>


export default {

    data(){
        return{
            profileCoach:{
                id:'',
                username:'',
                name:'',
                lastname:'',
                password:'',
                gender:'',
                dateOfBirth:'',
                listOfActiveOrders:[],
                listOfAllOrders:[],
            },
            //ako je menadzer
            profileManager: {
                id:'',
                username:'',
                name:'',
                lastname:'',
                password:'',
                gender:'',
                dateOfBirth:'',
                restaurant:''
            },
            //ako je Kupac
            profileCustomer:{
                id:'',
                username:'',
                name:'',
                lastname:'',
                password:'',
                gender:'',
                dateOfBirth:'',
                listOfAllOrders:null,
                cart:null,
                numberOfPoints:0,
                typeOfCustomer:''
            },
            //ako je admin
            profileAdmin:{
                id:'',
                username:'',
                name:'',
                lastname:'',
                password:'',
                gender:'',
                dateOfBirth:''
            },

            //utvrdjivanje uloge Usera
            isCoach:false,
            isAdmin:false,
            isManager:false,
            isCustomer:false
        }
    }
    
}
</script>


<style scoped>

#titleEffect{
  color:gold;
  font-weight: bold;
}

#user-profile ul#services li{
    list-style: none;
    padding:15px;
    border-bottom: gray solid 1px;
    border-radius: 15px;
    margin-bottom: 3px; 
    background-color:#fff;
    width:90%;
}

#user-profile#buttonUpdate{
  float:left;
}

.header5{
    color:#35424a;
    font-weight: bold;
}

.marg{
    margin-top:15px;
}

</style>
