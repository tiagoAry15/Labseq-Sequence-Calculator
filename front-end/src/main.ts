import { bootstrapApplication } from '@angular/platform-browser';
import { LabseqComponent } from './app/pages/labseq/labseq.component';
import { provideHttpClient } from '@angular/common/http';
bootstrapApplication(LabseqComponent,{providers: [
   [provideHttpClient()],
]})
  .catch((err) => console.error(err));
