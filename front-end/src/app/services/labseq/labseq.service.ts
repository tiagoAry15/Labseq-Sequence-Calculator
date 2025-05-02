import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http'; // <-- import normal
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface LabseqResponse {
  result: number
}

export interface LabseqWithTime {
  result: number;
  executionTimeMs: number;
}

@Injectable({
  providedIn: 'root'
})

export class LabseqService {
private apiUrl = "http://backend:8080/labseq"
constructor(private http: HttpClient) {}

calculateLabseq(n: number): Observable<LabseqWithTime> {
  return this.http.get<LabseqResponse>(`${this.apiUrl}/${n}`, { observe: 'response' })
    .pipe(
      map((resp: HttpResponse<LabseqResponse>) => {
        const result = resp.body?.result ?? 0;
        const timeHeader = resp.headers.get('x-execution-time-ms') ?? '0';
        const executionTimeMs = Number(timeHeader);
        return { result, executionTimeMs };
      })
    );
}

}
