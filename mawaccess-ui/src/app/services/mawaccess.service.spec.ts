import { TestBed } from '@angular/core/testing';

import { MawaccessService } from './mawaccess.service';

describe('MawaccessService', () => {
  let service: MawaccessService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MawaccessService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
