import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-patientdata',
  templateUrl: './patientdata.component.html',
  styleUrls: ['./patientdata.component.css']
})

export class PatientdataComponent implements OnInit {

  isSuccess=false;
  patients: any;
  errorMsg="";
  

  constructor(private userService: UserService,private tokenStorage: TokenStorageService,private router:Router) { }

  ngOnInit(): void {

    this.userService.getPatients().subscribe(
      data => {
        // console.log(data);
        this.patients = JSON.parse(data).patientList;
        this.isSuccess=true;
      },
      err => {
        // this.content = err.error.message;
        this.errorMsg = JSON.parse(err.error).message;
        this.isSuccess=false;
        console.log(this.errorMsg);
      }
    );
  }

}
