import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from "@angular/common"
import { FormsModule } from "@angular/forms"

import  { LabseqService } from "../../services/labseq/labseq.service"
import  { Subscription } from "rxjs"

@Component({
  selector: 'app-labseq',
  imports: [CommonModule, FormsModule],
  templateUrl: './labseq.component.html',
  styleUrl: './labseq.component.scss'
})
export class LabseqComponent {
  inputNumber = 0
  result: number | null = null
  executionTime: number | null = null
  loading = false
  error: string | null = null

  constructor(
    private labseqService: LabseqService,
  ) {}



  calculateLabseq() {
    this.loading = true
    this.error = null

    this.labseqService.calculateLabseq(this.inputNumber).subscribe({
      next: (response) => {
        this.result = response.result
        this.executionTime = response.executionTime
        this.loading = false
      },
      error: (error) => {
        this.loading = false
        console.error("Error calculating labseq:", error)
      },
    })
  }


}