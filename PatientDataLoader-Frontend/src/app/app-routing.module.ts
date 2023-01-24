import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { FileuploadComponent } from './fileupload/fileupload.component';
import { PatientdataComponent } from './patientdata/patientdata.component';
import { EditpatientComponent } from './editpatient/editpatient.component';
import { ProcessedPatientComponent } from './processed-patient/processed-patient.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'fileupload', component: FileuploadComponent },
  { path: 'updatePatient/:patientId', component: EditpatientComponent },
  { path: 'patientdata', component: PatientdataComponent },
  { path: 'processed-patient', component: ProcessedPatientComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
