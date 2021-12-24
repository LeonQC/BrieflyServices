import axios from 'axios'

const EMPLOYEE_BASE_REST_API_URL = 'http://localhost:8080/url/transform';

class EmployeeService{


    LongToShortUrl(url){
        return axios.post(EMPLOYEE_BASE_REST_API_URL, url)
    }
}

export default new EmployeeService();
