import { TestBed } from '@angular/core/testing';

import { LabseqService } from './labseq.service';

describe('LabseqService', () => {
  let service: LabseqService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LabseqService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
