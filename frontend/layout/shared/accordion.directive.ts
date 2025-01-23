import {
  Directive,
  ElementRef,
  HostListener,
  AfterViewInit,
  NgZone,
} from '@angular/core';

@Directive({
  selector: '[appAccordion]',
  standalone: true,
})
export class AccordionDirective implements AfterViewInit {
  private content: HTMLElement | null = null;
  private chevron: Element | null = null;

  constructor(private el: ElementRef, private ngZone: NgZone) {}

  ngAfterViewInit() {
    this.content = this.el.nativeElement.nextElementSibling
      ?.nextElementSibling as HTMLElement;
    this.chevron = this.el.nativeElement.nextElementSibling?.querySelector('i');

    // Initial setup based on the checkbox state
    this.ngZone.runOutsideAngular(() => {
      setTimeout(() => {
        this.updateAccordionState(this.el.nativeElement.checked);
      });
    });
  }

  @HostListener('change', ['$event'])
  onChange(event: Event) {
    const input = event.target as HTMLInputElement;
    this.updateAccordionState(input.checked);
  }

  private updateAccordionState(isChecked: boolean) {
    if (!this.content || !this.chevron) return;

    if (isChecked) {
      this.content.style.maxHeight = 'none'; // Remove max-height restriction
      this.content.style.overflow = 'visible'; // Allow content to be visible
      this.chevron.classList.replace(
        'pi-angle-double-right',
        'pi-angle-double-down'
      );
    } else {
      this.content.style.maxHeight = '0';
      this.content.style.overflow = 'hidden'; // Hide overflowing content
      this.chevron.classList.replace(
        'pi-angle-double-down',
        'pi-angle-double-right'
      );
    }
  }
}
