import { Component, OnInit, Injectable } from '@angular/core';
import { LayoutService } from '../../service/app.layout.service';
import { UserService } from '../service/user.service';
import MyMenu from '../../../app/menu.json';
import UIPolicy from '../../../../policies/ui.policy.json';
import { environment } from 'environments/environment';

interface MenuItem {
  label: string;
  icon: string;
  routerLink?: string[];
  visible?: boolean;
  items?: MenuItem[];
  position?: string;
}

@Injectable()
@Component({
  selector: 'app-menu',
  templateUrl: '../../app.menu.component.html',
  styles: `
   :host {
      display: flex;
      flex-direction: column;
      height: 100%;
    }
    .layout-menu-spacer {
      flex-grow: 1;
    }
    .layout-menu-bottom {
      margin-top: auto;
    }
  `,
})
export class AppMenuComponent implements OnInit {
  model: any[] = [];
  topMenuItems: MenuItem[] = [];
  bottomMenuItems: MenuItem[] = [];
  version: string;

  separateMenuItems() {
    this.topMenuItems = this.model.filter(
      (item) => item.position === 'top' || !item.position
    );
    this.bottomMenuItems = this.model.filter(
      (item) => item.position === 'bottom'
    );
    console.log(this.bottomMenuItems);
  }

  updateMenuVisibility(roleActifList: any[], UIPolicy: any, menu: MenuItem[]) {
    const allowedRoutes = new Set();

    for (const role of roleActifList) {
      const routesForRole = UIPolicy[role] || [];
      for (const route of routesForRole) {
        if (route === '*') {
          for (const menuItem of menu) {
            if (menuItem.routerLink) {
              allowedRoutes.add(menuItem.routerLink[0]);
            }
            if (menuItem.items) {
              for (const subItem of menuItem.items) {
                allowedRoutes.add(subItem.routerLink[0]);
              }
            }
          }
        } else {
          allowedRoutes.add(route);
        }
      }
    }

    for (const menuItem of menu) {
      if (menuItem.routerLink) {
        menuItem.visible = allowedRoutes.has(menuItem.routerLink[0]);
      } else if (menuItem.items) {
        let hasVisibleSubItem = false;
        for (const subItem of menuItem.items) {
          if (subItem.label == 'xxx') subItem.visible = false;
          else {
            subItem.visible = allowedRoutes.has(subItem.routerLink[0]);
            if (subItem.visible) {
              hasVisibleSubItem = true;
            }
          }
        }
        menuItem.visible = hasVisibleSubItem;
      }
    }

    return menu;
  }

  constructor(private user: UserService, public layoutService: LayoutService) {}

  ngOnInit() {
    this.version = environment.config.version;
    const model: MenuItem[] = MyMenu;
    this.model = this.updateMenuVisibility(['ADMIN'], UIPolicy, model);
    this.separateMenuItems();
  }
}
