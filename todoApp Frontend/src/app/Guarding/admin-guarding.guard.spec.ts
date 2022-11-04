import { TestBed } from '@angular/core/testing';

import { AdminGuardingGuard } from './admin-guarding.guard';

describe('AdminGuardingGuard', () => {
  let guard: AdminGuardingGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminGuardingGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
