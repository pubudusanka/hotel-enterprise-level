import { CanActivateFn } from '@angular/router';
import { CookieManagerService } from '../services/cookie-manager.service';
import { inject } from '@angular/core';

export const authGuardGuard: CanActivateFn = (route, state) => {
  const cookieManager: CookieManagerService = inject(CookieManagerService);

  // Return the promise directly
  return cookieManager.tokenExistsWithPromise('token')
    .then(response => true)
    .catch(error => false);
};
