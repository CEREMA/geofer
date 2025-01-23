import { Component, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { OAuthService } from 'angular-oauth2-oidc';

@Component({
  standalone: true,
  imports: [ButtonModule],
  templateUrl: './err-404.component.html',
  styleUrls: ['./err-404.component.scss'],
})
export class Error404Component implements OnInit {
  constructor(public oAuthService: OAuthService) {}

  Logout() {
    this.oAuthService.logOut();
  }

  ngOnInit(): void {}
}
