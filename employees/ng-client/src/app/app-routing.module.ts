import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FileUploadPage } from './pages/file-upload/file-upload.page';
import { FileSelectPage } from './pages/file-select/file-select.page';

const routes: Routes = [
  { path: '', redirectTo: '/upload', pathMatch: 'full' },
  { path: 'upload', component: FileUploadPage },
  { path: 'select', component: FileSelectPage }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
