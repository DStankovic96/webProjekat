import Home from './components/Home'
import login from './regilogin/login'
import registration from './regilogin/registration'
import About from './components/About'
import Homepage from './components/Homepage'
import Profile from './components/Profile'
import ProfileUpdate from './components/ProfileUpdate'
import NoviManager from './components/NoviManager'
import NoviSportskiObjekat from './components/NoviSportskiObjekat'

export default[
    {
        path:'/',
        name:'Home',
        component:Home
    },
    {
        path:'/login',
        name:'login',
        component:login
    },
    {
        path:'/registration',
        name:'registration',
        component:registration
    },
    {
        path:'/about',
        name:'About',
        component:About
    },
    {
        path:'/profile',
        name:'Profile',
        component:Profile
    },
    {
        path:'/home',
        name:'Homepage',
        component:Homepage
    },
    {
        path:'/profileUpdate',
        name:'ProfileUpdate',
        component:ProfileUpdate
    },
    {
        path:'/newManager',
        name:'NoviManager',
        component:NoviManager
    },
    {
        path:'/newObject',
        name:'NoviSportskiObjekat',
        component:NoviSportskiObjekat
    }
]