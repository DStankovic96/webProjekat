import axios from "axios";

const API_URL = "http://localhost:8080/webProj/rest";

class DataService{
    
    //komentari
    // addComment(comment){
    //     return axios.post(`${API_URL}/comments/`, comment);
    // }
    // getAllCommentsByObjectId(idObjekta){
    //     return axios.get(`${API_URL}/comments/byObjekat/${idObjekta}`);
    // }
    // getAllCommentsById(idManager){
    //     return axios.get(`${API_URL}/comments/${idManager}`);
    // }
    // getAllUnmoderatedComments(idManager){
    //     return axios.get(`${API_URL}/comments/unmoderated/${idManager}`);
    // }
    // sendModeratedCommentList(idManagera, komentari){
    //     return axios.post(`${API_URL}/comments/moderated/${idManagera}`, komentari);
    // }


    // login i registracija
    // sendLogin(form){
    //     return axios.post(`${API_URL}/login/`, form);
    // }
    addCustomer(customer){
        return axios.post(`${API_URL}/customers/`, customer);
    }


    // sportski objekat
    addObject(object){
        console.log("ADOBJTEST1");
        return axios.post(`${API_URL}/objects/`, object);
        console.log("ADOBJTEST2");
    }
    addTrening(id, treningg){
        return axios.put(`${API_URL}/objects/${id}`, treningg)
    }
    getAllObjects(){
        return axios.get(`${API_URL}/objects/`)
    }


    //trener
    addTrener(trener){
        console.log("DataService:addTrener");
        return axios.post(`${API_URL}/coaches/`, trener);
    }
    getTrener(name){
        console.log("DataService:getTrener");
        return axios.get(`${API_URL}/coaches/${name}`);
    }
    getListaTrenera(){
        return axios.get(`${API_URL}/coaches/`);
    }
    updateTrener(coach){
        return axios.put(`${API_URL}/coaches/`, coach);
    }


    //manager
    addManager(manager){
        console.log("DataService:addManager");
        return axios.post(`${API_URL}/managers/`, manager);
        console.log("TESTMANAGER");
    }
    getFreeManagers(){
        return axios.get(`${API_URL}/managers/free/`);
    }
    getAllManagers(){
        return axios.get(`${API_URL}/managers/`);
    }
    getManager(id){
        return axios.get(`${API_URL}/managers/${id}`);
    }
    updateManager(manager){
        return axios.put(`${API_URL}/managers/`, manager);
    }
    updateManagerObjekat(manager){
        return axios.put(`${API_URL}/managers/dodelaObjekta`, manager);
    }
    getObjectByManager(manager){
        return axios.get(`${API_URL}/objects/bymanager/${manager}`);
    }
    getObject(id){
        return axios.get(`${API_URL}/objects/${id}`);
    }

    //trening


    //admin
    getAdmin(id){
        console.log("testadminn1");
        return axios.get(`${API_URL}/administrators/${id}`);
        console.log("testadminn2");
    }
    updateAdmin(admin){
        return axios.put(`${API_URL}/administrators`, admin);
    }
    
    //customer
    updateCustomer(customer){
        return axios.put(`${API_URL}/customers/`, customer);
    }
    getCustomer(username){
        
        return axios.get(`${API_URL}/customers/${username}`);
    }
    getAllClanarineKupac(username){
        console.log("uslo u dobijanje clanarina kupca");
        return axios.get(`${API_URL}/clanarine/${username}`);
        console.log("izaslo u dobijanje clanarina kupca");
    }

    posaljiClanarinu1(username,clanarineKupac)
    {
        return axios.post(`${API_URL}/clanarine/${username}`, clanarineKupac);
    }
   

}

export default new DataService();