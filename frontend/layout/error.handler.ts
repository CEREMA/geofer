import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(private injector: Injector) {}

  handleError(error: any): void {
    // Log the error or send it to your server
    console.error('Global error:', error);

    // Get the router to navigate to the error page
    const router = this.injector.get(Router);
    //router.navigate(['/500']);
  }
}
