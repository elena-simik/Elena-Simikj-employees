import { Component, Input } from '@angular/core';
import { EmployeePair } from '../../interface/employee-pair.interface';

@Component({
  selector: 'employee-pair',
  templateUrl: './employee-pair.component.html',
  styleUrls: ['./employee-pair.component.scss']
})
export class EmployeePairComponent {
  @Input() employeePairs!: EmployeePair[];

}
