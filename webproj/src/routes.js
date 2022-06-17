import Home from './components/Home'
import login from './regilogin/login'
import registration from './regilogin/registration'
import About from './components/About'

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
    }
]