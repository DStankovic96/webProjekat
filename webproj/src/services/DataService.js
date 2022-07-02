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
        return axios.post(`${API_URL}/objects/`, object);
    }


    //trener
    getListaTrenera(){
        return axios.get(`${API_URL}/coaches/`);
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
    // getManager(id){
    //     return axios.get(`${API_URL}/managers/${id}`);
    // }
    // updateManager(manager){
    //     return axios.put(`${API_URL}/managers/`, manager);
    // }
    updateManagerObjekat(manager){
        return axios.put(`${API_URL}/managers/dodelaObjekta`, manager);
    }

    //trening


    //admin
    // getAdmin(id){
    //     return axios.get(`${API_URL}/administrators/${id}`);
    // }
    // updateAdmin(admin){
    //     return axios.put(`${API_URL}/administrators`, admin);
    // }
    
    

   

}

export default new DataService();