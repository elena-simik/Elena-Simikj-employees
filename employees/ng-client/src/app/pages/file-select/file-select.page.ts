import { Component, OnInit } from '@angular/core';
import { Document } from '../../interface/document.interface';
import { EmployeePair } from '../../interface/employee-pair.interface';
import { EmployeeService } from '../../service/employee.service';

@Component({
  templateUrl: './file-select.page.html',
  styleUrls: ['./file-select.page.scss']
})
export class FileSelectPage implements OnInit {

  documents!: Document[];
  employeePairs!: EmployeePair[];

  constructor(private _employeeService: EmployeeService) {
  }

  ngOnInit(): void {
    this._employeeService.getAllDocuments().subscribe(documents => this.documents = documents);
  }

  onSelect(event: any) {
    const documentId = event.target.value;
    this._employeeService.processExistingFile(documentId)
      .subscribe(employeePairs => this.employeePairs = employeePairs);
  }

}
