import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { EmployeePair } from "../interface/employee-pair.interface";
import { Document } from "../interface/document.interface";

@Injectable()
export class EmployeeService {
  private url = "/api/employee/document"


  constructor(private _http: HttpClient) {
  }

  getAllDocuments():Observable<Document[]> {
    return this._http.get<Document[]>(`${this.url}`);
  }

  processFile(file: File, dateFormat: string): Observable<EmployeePair[]> {
     const formData = new FormData();
     formData.append('file', file);
     formData.append('dateFormat', dateFormat);
    return this._http.post<EmployeePair[]>(`${this.url}`, formData);
  }

  processExistingFile(documentId: number): Observable<EmployeePair[]> {
    return this._http.post<EmployeePair[]>(`${this.url}?documentId=${documentId}`, {});
  }

}
