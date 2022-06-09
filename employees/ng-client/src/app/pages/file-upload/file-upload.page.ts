import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { EmployeeService } from '../../service/employee.service';
import { EmployeePair } from '../../interface/employee-pair.interface';

@Component({
  templateUrl: './file-upload.page.html',
  styleUrls: ['./file-upload.page.scss']
})
export class FileUploadPage implements OnInit {
  file!: File;
  formGroup!: FormGroup;
  employeePairs!: EmployeePair[];

  constructor(private _toastr: ToastrService,
              private _formBuilder: FormBuilder,
              private _employeeService: EmployeeService) {
  }

  ngOnInit(): void {
    this.formGroup = this._formBuilder.group({
      file: new FormControl(null, Validators.required),
      dateFormat: new FormControl(null, Validators.required)
    })
  }

  onFileChange(event: any) {
    this.file = event.target.files[0];
  }

  onSubmit() {
    this._employeeService.processFile(this.file, this.formGroup.get('dateFormat')!.value)
      .subscribe({
        next: employeePairs => {
          this.employeePairs = employeePairs;
          this._toastr.success('Success');
        }, error: err => {
          this._toastr.error(err.error.message);
        }
      })
  }

  isValid(name: string) {
    return this.formGroup.get(name)?.valid;
  }

}
