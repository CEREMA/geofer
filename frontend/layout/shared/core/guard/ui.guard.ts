import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';

import UIPolicy from '../../../../../policies/ui.policy.json';
import { UserService } from 'layout/shared/service/user.service';
import menu from '../../../../app/menu.json';

interface MenuItem {
  label: string;
  icon: string;
  routerLink?: string[];
  visible?: boolean;
  items?: MenuItem[];
}

@Injectable({
  providedIn: 'root',
})
export class UIGuard implements CanActivate {
  constructor(private user: UserService, private router: Router) {}

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
          subItem.visible = allowedRoutes.has(subItem.routerLink[0]);
          if (subItem.visible) {
            hasVisibleSubItem = true;
          }
        }
        menuItem.visible = hasVisibleSubItem;
      }
    }

    return menu;
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> {
    return this.user.getCurrentUser().pipe(
      map((data) => {
        const roleActifList = data.body.roleActifList;
        const updatedMenu = this.updateMenuVisibility(
          roleActifList,
          UIPolicy,
          menu
        );
        const currentRoute = state.url;

        for (const menuItem of updatedMenu) {
          if (
            menuItem.routerLink &&
            menuItem.routerLink[0] === currentRoute &&
            menuItem.visible
          ) {
            return true;
          }
          if (menuItem.items) {
            for (const subItem of menuItem.items) {
              if (subItem.routerLink[0] === currentRoute && subItem.visible) {
                return true;
              }
            }
          }
        }

        this.router.navigate(['/403']);
        return false;
      })
    );
  }
}
