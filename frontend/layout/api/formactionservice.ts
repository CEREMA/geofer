import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FormActionService {
  private formActionSource = new BehaviorSubject<void>(null);
  formAction$ = this.formActionSource.asObservable();

  triggerFormAction() {
    this.formActionSource.next();
  }
}
