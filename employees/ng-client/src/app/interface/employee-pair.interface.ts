import { ProjectEmployeePair } from './project-employee-pair.interface';

export interface EmployeePair {
  totalDaysWorked: number;
  employeeProjectPairs: ProjectEmployeePair[];
}
