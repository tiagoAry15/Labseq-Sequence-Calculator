import { Injectable } from "@angular/core"
import { BehaviorSubject } from "rxjs"

export type Language = "pt" | "en"

export type TranslationKey =
  | "title"
  | "inputLabel"
  | "calculateButton"
  | "calculating"
  | "result"
  | "executionTime"
  | "ms"
  | "error"
  | "languageToggle"

type Translations = Record<Language, Record<TranslationKey, string>>

@Injectable({
  providedIn: "root",
})
export class LanguageService {
  private readonly translations: Translations = {
    pt: {
      title: "Calculadora de Sequência Labseq",
      inputLabel: "Digite um número:",
      calculateButton: "Calcular",
      calculating: "Calculando...",
      result: "Resultado:",
      executionTime: "Tempo de execução:",
      ms: "ms",
      error: "Ocorreu um erro ao calcular a sequência. Por favor, tente novamente.",
      languageToggle: "English",
    },
    en: {
      title: "Labseq Sequence Calculator",
      inputLabel: "Enter a number:",
      calculateButton: "Calculate",
      calculating: "Calculating...",
      result: "Result:",
      executionTime: "Execution time:",
      ms: "ms",
      error: "An error occurred while calculating the sequence. Please try again.",
      languageToggle: "Português",
    },
  }

  private readonly currentLanguageSubject = new BehaviorSubject<Language>(
    this.getInitialLanguage()
  )
  readonly currentLanguage$ = this.currentLanguageSubject.asObservable()

  constructor() {}

  private getInitialLanguage(): Language {
    const savedLanguage = localStorage.getItem("preferredLanguage")
    return savedLanguage === "en" || savedLanguage === "pt" ? savedLanguage : "pt"
  }

  getCurrentLanguage(): Language {
    return this.currentLanguageSubject.value
  }

  setLanguage(language: Language): void {
    localStorage.setItem("preferredLanguage", language)
    this.currentLanguageSubject.next(language)
  }

  toggleLanguage(): void {
    const newLanguage: Language = this.getCurrentLanguage() === "pt" ? "en" : "pt"
    this.setLanguage(newLanguage)
  }

  translate(key: TranslationKey): string {
    const lang = this.getCurrentLanguage()
    return this.translations[lang][key]
  }
}
