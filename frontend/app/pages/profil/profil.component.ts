import { Component, OnInit } from '@angular/core';
import { ProfilsService } from '../../services/profils.service';
import { CommonModule } from '@angular/common';
import { NgxJsonViewerModule } from 'ngx-json-viewer';
import { AutoUnsubscribe } from 'layout/auto-unsubscribe.decorator';

@Component({
  imports: [CommonModule, NgxJsonViewerModule],
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss'],
  standalone: true,
})
export class ProfilComponent implements OnInit {
  constructor(private profils: ProfilsService) {}

  myProfile: any;

  ngOnInit(): void {
    console.log('xxxx');
    this.profils.get().subscribe((res) => {
      this.myProfile = res;
    });
  }
  ngOnDestroy(): void {
    console.log('called.');
  }
}
