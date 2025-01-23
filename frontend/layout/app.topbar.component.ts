import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LayoutService } from './service/app.layout.service';
import { User } from './shared/entities/user.model';
import { UserService } from './shared/service/user.service';
import { MainComponent } from './main/main.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-topbar',
  templateUrl: './app.topbar.component.html',
})
export class AppTopBarComponent implements OnInit {
  menu: MenuItem[] = [];
  userActif!: User;

  @ViewChild('searchinput') searchInput!: ElementRef;

  @ViewChild('menubutton') menuButton!: ElementRef;

  searchActive: boolean = false;

  constructor(
    private router: Router,
    public layoutService: LayoutService,
    public userService: UserService,
    public appMain: MainComponent
  ) {}

  ngOnInit(): void {
    this.userActif = this.userService.currentUser;
  }

  onMenuButtonClick() {
    this.layoutService.onMenuToggle();
  }

  activateSearch() {
    this.searchActive = true;
    setTimeout(() => {
      this.searchInput.nativeElement.focus();
    }, 100);
  }

  deactivateSearch() {
    this.searchActive = false;
  }

  goToProfile() {
    this.router.navigate(['/pages/profile']);
  }

  removeTab(event: MouseEvent, item: MenuItem, index: number) {
    this.layoutService.onTabClose(item, index);
    event.preventDefault();
  }

  get layoutTheme(): string {
    return this.layoutService.config.layoutTheme;
  }

  get colorScheme(): string {
    return this.layoutService.config.colorScheme;
  }

  get logo(): string {
    return 'assets/images/logo_title.png';
  }

  get tabs(): MenuItem[] {
    return this.layoutService.tabs;
  }
}
