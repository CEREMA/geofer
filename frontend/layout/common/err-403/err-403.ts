import { Component, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { OAuthService } from 'angular-oauth2-oidc';

@Component({
  standalone: true,
  imports: [ButtonModule],
  templateUrl: './err-403.component.html',
  styleUrls: ['./err-403.component.scss'],
})
export class Error403Component implements OnInit {
  constructor(public oAuthService: OAuthService) {}

  Logout() {
    this.oAuthService.logOut();
  }

  ngOnInit(): void {}
}
