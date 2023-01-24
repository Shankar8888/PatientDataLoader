import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/';
// const API_URL = 'https://e370ubs9di.execute-api.ap-northeast-1.amazonaws.com/UAT/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'multipart/form-data',
  'responseType': 'text'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  uploadFile(formData: FormData) {
    return this.http.post(API_URL + 'load/patientdata',formData, { responseType: 'text' });
  }

  getPatients() {
    return this.http.get(API_URL + 'patients', { responseType: 'text' });
  }

  getCurrentPatient(patientId:string) {
    return this.http.get(API_URL + 'retrieve/'+patientId, { responseType: 'text' });
  }

  updatePatient(_patient:any,_id:string) {
    return this.http.put(API_URL + 'updatepatient/'+_id , _patient, { responseType: 'text' });
  }
}
