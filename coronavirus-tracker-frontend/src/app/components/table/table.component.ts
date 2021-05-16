import { Component, OnInit } from '@angular/core';
import {CoronavirusDataService} from '../../../coronavirus-data.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  coronavirusData: any;

  constructor(private coronavirusDataService: CoronavirusDataService) { }

  ngOnInit(): void {
    this.coronavirusData = this.coronavirusDataService.getCoronavirusData().subscribe(data => this.coronavirusData = data);
  }
}
