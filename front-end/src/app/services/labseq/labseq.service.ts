import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // <-- import normal
import { Observable } from 'rxjs';

export interface LabseqResponse {
  result: number
}

@Injectable({
  providedIn: 'root'
})

export class LabseqService {
private apiUrl = "http://localhost:8080/labseq"
constructor(private http: HttpClient) {}

calculateLabseq(n: number): Observable<LabseqResponse> {
  return this.http.get<LabseqResponse>(`${this.apiUrl}/${n}`);
}

}
