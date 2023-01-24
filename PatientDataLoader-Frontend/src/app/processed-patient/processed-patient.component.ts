import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-processed-patient',
  templateUrl: './processed-patient.component.html',
  styleUrls: ['./processed-patient.component.css']
})
export class ProcessedPatientComponent implements OnInit {

  isSuccess=false;
  patients: any;
  errorMsg="";

  constructor(private userService: UserService,private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.userService.getPatients().subscribe(
      data => {
        // console.log(data);
        this.patients = JSON.parse(data).patientList;
        this.isSuccess=true;
      },
      err => {
        this.errorMsg = JSON.parse(err.error).message;
        this.isSuccess=false;
        console.log(this.errorMsg);
      }
    );
  }

}
