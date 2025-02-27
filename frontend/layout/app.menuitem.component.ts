import {
  ChangeDetectorRef,
  Component,
  HostBinding,
  Input,
  OnDestroy,
  OnInit,
} from '@angular/core';
import { IsActiveMatchOptions, NavigationEnd, Router } from '@angular/router';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';
import { MenuService } from './app.menu.service';
import { LayoutService } from './service/app.layout.service';

@Component({
  selector: '[app-menuitem]',
  template: `
    <ng-container>
      <div
        *ngIf="root && item.visible !== false"
        class="layout-menuitem-root-text"
      >
        {{ item.label }}
      </div>
      <a
        *ngIf="(!item.routerLink || item.items) && item.visible !== false"
        [attr.href]="getLink(item)"
        (click)="itemClick($event)"
        (mouseenter)="onMouseEnter()"
        [ngClass]="item.class"
        [attr.target]="item.target"
        tabindex="0"
        pRipple
        [pTooltip]="item.label"
        [tooltipDisabled]="!(isSlim && root && !active)"
      >
        <i [ngClass]="item.icon" class="layout-menuitem-icon"></i>
        <span class="layout-menuitem-text">{{ item.label }}</span>
        <i
          class="pi pi-fw pi-angle-down layout-submenu-toggler"
          *ngIf="item.items"
        ></i>
      </a>
      <a
        *ngIf="item.routerLink && !item.items && item.visible !== false"
        (click)="itemClick($event)"
        (mouseenter)="onMouseEnter()"
        [ngClass]="item.class"
        [routerLink]="item.routerLink"
        routerLinkActive="active-route"
        [routerLinkActiveOptions]="
          item.routerLinkActiveOptions || {
            paths: 'exact',
            queryParams: 'ignored',
            matrixParams: 'ignored',
            fragment: 'ignored'
          }
        "
        [fragment]="item.fragment"
        [queryParamsHandling]="item.queryParamsHandling"
        [preserveFragment]="item.preserveFragment!"
        [skipLocationChange]="item.skipLocationChange!"
        [replaceUrl]="item.replaceUrl!"
        [state]="item.state"
        [queryParams]="item.queryParams"
        [attr.target]="item.target"
        tabindex="0"
        pRipple
        [pTooltip]="item.label"
        [tooltipDisabled]="!(isSlim && root)"
      >
        <i [ngClass]="item.icon" class="layout-menuitem-icon"></i>
        <span class="layout-menuitem-text">{{ item.label }}</span>
        <i
          class="pi pi-fw pi-angle-down layout-submenu-toggler"
          *ngIf="item.items"
        ></i>
      </a>

      <ul
        *ngIf="item.items && item.visible !== false"
        [@children]="submenuAnimation"
      >
        <ng-template ngFor let-child let-i="index" [ngForOf]="item.items">
          <li
            app-menuitem
            [item]="child"
            [index]="i"
            [parentKey]="key"
            [class]="child.badgeClass"
          ></li>
        </ng-template>
      </ul>
    </ng-container>
  `,
  animations: [
    trigger('children', [
      state(
        'collapsed',
        style({
          height: '0',
        })
      ),
      state(
        'expanded',
        style({
          height: '*',
        })
      ),
      state(
        'hidden',
        style({
          display: 'none',
        })
      ),
      state(
        'visible',
        style({
          display: 'block',
        })
      ),
      transition(
        'collapsed <=> expanded',
        animate('400ms cubic-bezier(0.86, 0, 0.07, 1)')
      ),
    ]),
  ],
})
export class AppMenuitemComponent implements OnInit, OnDestroy {
  @Input() item: any;

  @Input() index!: number;

  @Input() @HostBinding('class.layout-root-menuitem') root!: boolean;

  @Input() parentKey!: string;

  active = false;

  menuSourceSubscription: Subscription;

  menuResetSubscription: Subscription;

  key: string = '';

  constructor(
    public layoutService: LayoutService,
    private cd: ChangeDetectorRef,
    public router: Router,
    private menuService: MenuService
  ) {
    this.menuSourceSubscription = this.menuService.menuSource$.subscribe(
      (value) => {
        Promise.resolve(null).then(() => {
          if (value.routeEvent) {
            this.active =
              value.key === this.key || value.key.startsWith(this.key + '-')
                ? true
                : false;
          } else {
            if (
              value.key !== this.key &&
              !value.key.startsWith(this.key + '-')
            ) {
              this.active = false;
            }
          }
        });
      }
    );

    this.menuResetSubscription = this.menuService.resetSource$.subscribe(() => {
      this.active = false;
    });

    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((params) => {
        if (this.isSlim) {
          this.active = false;
        } else {
          if (this.item.routerLink) {
            this.updateActiveStateFromRoute();
          }
        }
      });
  }

  ngOnInit() {
    this.key = this.parentKey
      ? this.parentKey + '-' + this.index
      : String(this.index);

    if (!this.isSlim && this.item.routerLink) {
      this.updateActiveStateFromRoute();
    }
  }

  updateActiveStateFromRoute() {
    let activeRoute = this.router.isActive(
      this.item.routerLink[0],
      <IsActiveMatchOptions>this.item.routerLinkActiveOptions || {
        paths: 'exact',
        queryParams: 'ignored',
        matrixParams: 'ignored',
        fragment: 'ignored',
      }
    );

    if (activeRoute) {
      this.menuService.onMenuStateChange({ key: this.key, routeEvent: true });
    }
  }

  getLink(item: any) {
    if (item.url) {
      return item.url.startsWith('mailto') || item.url.startsWith('http')
        ? item.url
        : null;
    }
    return null;
  }

  itemClick(event: MouseEvent) {
    // avoid processing disabled items
    if (this.item.disabled) {
      event.preventDefault();
      return;
    }

    // navigate with hover
    if (this.root && this.isSlim) {
      this.layoutService.state.menuHoverActive =
        !this.layoutService.state.menuHoverActive;
    }

    // execute command
    if (this.item.command) {
      this.item.command({ originalEvent: event, item: this.item });
    }

    // If item has a URL but no routerLink, open the URL
    if (this.item.url && !this.item.routerLink) {
      if (this.item.url.startsWith('mailto')) {
        window.location.href = this.item.url;
      } else if (this.item.url.startsWith('http')) {
        window.open(this.item.url, '_blank');
      }
      event.preventDefault();
    }

    // toggle active state
    if (this.item.items) {
      this.active = !this.active;

      if (this.root && this.active && this.isSlim) {
        this.layoutService.onOverlaySubmenuOpen();
      }
    } else {
      if (this.layoutService.isMobile()) {
        this.layoutService.state.staticMenuMobileActive = false;
      }

      if (this.isSlim) {
        this.menuService.reset();
        this.layoutService.state.menuHoverActive = false;
      }
    }

    this.menuService.onMenuStateChange({ key: this.key });
  }

  onMouseEnter() {
    // activate item on hover
    if (this.root && this.isSlim && this.layoutService.isDesktop()) {
      if (this.layoutService.state.menuHoverActive) {
        this.active = true;
        this.menuService.onMenuStateChange({ key: this.key });
      }
    }
  }

  get submenuAnimation() {
    if (this.layoutService.isDesktop() && this.layoutService.isSlim())
      return this.active ? 'visible' : 'hidden';
    else return this.root ? 'expanded' : this.active ? 'expanded' : 'collapsed';
  }

  get isSlim() {
    return this.layoutService.isSlim();
  }

  get isMobile() {
    return this.layoutService.isMobile();
  }

  @HostBinding('class.active-menuitem')
  get activeClass() {
    return this.active && !this.root;
  }

  ngOnDestroy() {
    if (this.menuSourceSubscription) {
      this.menuSourceSubscription.unsubscribe();
    }

    if (this.menuResetSubscription) {
      this.menuResetSubscription.unsubscribe();
    }
  }
}
