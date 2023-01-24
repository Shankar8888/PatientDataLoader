import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../_services/user.service';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-editpatient',
  templateUrl: './editpatient.component.html',
  styleUrls: ['./editpatient.component.css']
})
export class EditpatientComponent implements OnInit {

  form: any = {
    patientId: null,
    patientName: null,
    patientAddress: null,
    patientDOB: null,
    patientEmail: null,
    patientPhone: null,
    drugId: null,
    grugName: null,
    status: null
  };

  isSuccessful = false;
  errorMessage = "";
  successMsg = "";
  isUpdateFailed = false;
  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    console.warn(this.route.snapshot.params.patientId);
    this.userService.getCurrentPatient(this.route.snapshot.params.patientId)
      .subscribe((data) => {
        // console.warn(data);
        this.form = {
          patientId: JSON.parse(data).patientDTO.patientId,
          patientName: JSON.parse(data).patientDTO.patientName,
          patientAddress: JSON.parse(data).patientDTO.patientAddress,
          patientDOB: JSON.parse(data).patientDTO.patientDOB,
          patientEmail: JSON.parse(data).patientDTO.patientEmail,
          patientPhone: JSON.parse(data).patientDTO.patientPhone,
          drugId: JSON.parse(data).patientDTO.drugId,
          drugName: JSON.parse(data).patientDTO.drugName
    }
      }
      )
  }

  editPatient() {
    console.warn(this.form);
    this.userService.updatePatient(this.form, this.route.snapshot.params.patientId)
      .subscribe(data => {
        // console.log(data);
        this.successMsg = JSON.parse(data).patientList;
        this.isSuccessful = true;
      },
        err => {
          console.log(err.error.message);
          // this.content = err.error.message;
          this.errorMessage = JSON.parse(err.error).message;
          this.isSuccessful = false;
        }
      )
  }
}

