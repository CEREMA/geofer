import { Component, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { OAuthService } from 'angular-oauth2-oidc';
import { Router } from '@angular/router';
import { HealthCheckService } from 'layout/service/healthCheck.service';
import { DOCUMENT } from '@angular/common';
import { Inject } from '@angular/core';

@Component({
  standalone: true,
  imports: [ButtonModule],
  templateUrl: './err-500.component.html',
  styleUrls: ['./err-500.component.scss'],
})
export class Error500Component implements OnInit {
  constructor(
    public health: HealthCheckService,
    public router: Router,
    public oAuthService: OAuthService
  ) {}

  Logout() {
    this.oAuthService.logOut();
  }

  reloadPage() {
    this.health.areBothAlive().subscribe((status) => {
      if (status) {
        window.location.href = '/';
      } else setTimeout(() => this.reloadPage(), 1000);
    });
  }

  ngOnInit(): void {
    this.reloadPage();
  }
}
