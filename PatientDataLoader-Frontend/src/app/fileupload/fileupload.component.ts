import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-fileupload',
  templateUrl: './fileupload.component.html',
  styleUrls: ['./fileupload.component.css']
})
export class FileuploadComponent implements OnInit {

  file: any;
  isUploadFailed=false;
  isSuccess=false;
  errorMsg="";
  validPatients?: any;
  inValidPatients?: any;
  fileUploader: any;
  // fileUploader: any;
  

  constructor(private userService: UserService,private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
  }
  
  selectFile(event: any) {
    const user = this.tokenStorage.getUser();
    console.log(event);
    this.file= event.target.files[0];
  }

   uploadFile() {
    let formData=new FormData();
    formData.append("file",this.file);

    this.userService.uploadFile(formData).subscribe(
      data => {
        // console.log(data);
        // console.log(Object.values(data));
        this.validPatients=JSON.parse(data).patientList;
        this.inValidPatients=JSON.parse(data).invalidPatientList;
        this.isUploadFailed=false;
        this.isSuccess=true;
      },
      err => {
        this.isUploadFailed=true;
        this.isSuccess=false;
        this.errorMsg = JSON.parse(err.error).message;
        console.log(this.errorMsg)
      }
    );
     }
  

  //   resetFileUploader() { 
  //     console.warn(this.fileUploader.name)
  //     this.file.name=null;
  //     console.warn(this.file.name)
  // }
}
  