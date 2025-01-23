import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxJsonViewerModule } from 'ngx-json-viewer';
import { AutoUnsubscribe } from 'layout/auto-unsubscribe.decorator';

@Component({
  imports: [CommonModule, NgxJsonViewerModule],
  templateUrl: './legal.component.html',
  styleUrls: ['./legal.component.scss'],
  standalone: true,
})
@AutoUnsubscribe
export class LegalComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
  ngOnDestroy(): void {
    console.log('called.');
  }
}
