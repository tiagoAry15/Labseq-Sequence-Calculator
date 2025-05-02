import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from "@angular/common"
import { FormsModule } from "@angular/forms"
import  { LabseqService } from "../../services/labseq/labseq.service"
import  { Subscription } from "rxjs"

interface HistoryItem {
  input: number
  result: number
  executionTime: number
  timestamp: Date
}

@Component({
  selector: 'app-labseq',
  imports: [CommonModule, FormsModule],
  templateUrl: './labseq.component.html',
  styleUrl: './labseq.component.scss'
})


export class LabseqComponent {
  history: HistoryItem[] = []
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
      this.executionTime = response.executionTimeMs
      this.loading = false
      this.addToHistory(this.inputNumber, response.result, response.executionTimeMs)
      },
      error: (error) => {
        this.loading = false
        this.error = "Erro ao calcular labseq"
        console.error("Error calculating labseq:", error)
      },
    })
  }

  addToHistory(input: number, result: number, executionTime: number) {
    const historyItem: HistoryItem = {
      input,
      result,
      executionTime,
      timestamp: new Date(),
    }

    
    this.history.unshift({ ...historyItem });
    
    if (this.history.length > 10) {
      this.history = this.history.slice(0, 10)
    }

    
    localStorage.setItem("calculationHistory", JSON.stringify(this.history))
  }

  clearHistory() {
    this.history = []
    localStorage.removeItem("calculationHistory")
  }

  allowOnlyDigits(event: KeyboardEvent) {
    const char = event.key;
    if (!/^[0-9]$/.test(char)) {
      event.preventDefault();
    }
  }
  
  formatDate(date: Date): string {
      return date.toLocaleString("pt-BR", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
      })
      return date.toLocaleString("en-US", {
        month: "2-digit",
        day: "2-digit",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
        hour12: true,
        timeZone: "UTC"
      })
    }
  }



