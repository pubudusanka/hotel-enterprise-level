import { Component } from '@angular/core';
import {MatTab, MatTabGroup} from '@angular/material/tabs';
import {ProfileSettingsComponent} from './inner/profile-settings/profile-settings.component';
import {HistoryComponent} from './inner/history/history.component';

@Component({
  selector: 'app-settings',
  imports: [
    MatTabGroup,
    MatTab,
    ProfileSettingsComponent,
    HistoryComponent
  ],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.scss',
  standalone:true
})
export class SettingsComponent {

}
