import Home from './components/Home'
import login from './regilogin/login'
import registration from './regilogin/registration'
import About from './components/About'
import Homepage from './components/Homepage'
import Profile from './components/Profile'
import ProfileUpdate from './components/ProfileUpdate'
import NoviManager from './components/NoviManager'
import NoviSportskiObjekat from './components/NoviSportskiObjekat'
import NoviTrener from './components/NoviTrener'
import ModeracijaKomentara from './components/ModeracijaKomentara'
import CommentNew from './components/CommentNew'
import NoviTrening from './components/NoviTrening'
import Clanarina from './components/Clanarina'
import SportskiObjekatDetalji from './components/SportskiObjekatDetalji'
import PretragaObjekata from './components/PretragaObjekata'
import SviKorisnici from './components/SviKorisnici'
import MojObjekat from './components/MojObjekat'
import MojiTreninzi from './components/MojiTreninzi'
import MojiTreninziKupac from './components/MojiTreninziKupac'
import MojaClanarina from './components/MojaClanarina'

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
        path:'/clanarine',
        name:'Clanarina',
        component:Clanarina
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
        path:'/profile/:id/update',
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
    },
    {
        path:'/newCoach',
        name:'NoviTrener',
        component:NoviTrener
    },
    {
        path:'/moderation',
        name:'ModeracijaKomentara',
        component:ModeracijaKomentara
    },
    {   
        path:'/commentNew/:id/',
        name:'CommentNew',
        component:CommentNew
    },
    {   
        path:'/noviTrening',
        name:'NoviTrening',
        component:NoviTrening
    },
    {   
        path:'/objects/:id/details',
        name:'SportskiObjekatDetalji',
        component:SportskiObjekatDetalji
    },
    {   
        path:'/searchObjects',
        name:'PretragaObjekata',
        component:PretragaObjekata
    },
    {
        path:'/sviKorisnici',
        name:'SviKorisnici',
        component:SviKorisnici
    },
    {
        path:'/object/details',
        name:'MojObjekat',
        component:MojObjekat
    },
    {
        path:'/treninzi/details',
        name:'MojiTreninzi',
        component:MojiTreninzi
    }
    ,
    {
        path:'/treninzi/detailsKupac',
        name:'MojiTreninziKupac',
        component:MojiTreninziKupac
    } ,
    {
        path:'/mojaClanarina',
        name:'MojaClanarina',
        component:MojaClanarina
    }
]