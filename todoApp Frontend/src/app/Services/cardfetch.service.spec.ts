import { TestBed } from '@angular/core/testing';

import { CardfetchService } from './cardfetch.service';

describe('CardfetchService', () => {
  let service: CardfetchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CardfetchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
