import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { LabseqComponent } from './app/pages/labseq/labseq.component';
import { provideHttpClient } from '@angular/common/http';
import { LanguageService } from './app/services/language/language.service';
bootstrapApplication(LabseqComponent,{providers: [
   [provideHttpClient(), LanguageService],
]})
  .catch((err) => console.error(err));
