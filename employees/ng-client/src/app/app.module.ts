import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ApiInterceptor } from './interceptor/api-interceptor';
import { FileUploadPage } from './pages/file-upload/file-upload.page';
import { EmployeeService } from './service/employee.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ReactiveFormsModule } from '@angular/forms';
import { EmployeePairComponent } from './components/employee-pair/employee-pair.component';
import { FileSelectPage } from './pages/file-select/file-select.page';

@NgModule({
  declarations: [
    AppComponent,
    FileUploadPage,
    EmployeePairComponent,
    FileSelectPage
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: ApiInterceptor, multi: true }, EmployeeService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
