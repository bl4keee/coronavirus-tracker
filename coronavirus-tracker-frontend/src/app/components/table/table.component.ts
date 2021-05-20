import { Component, OnInit } from '@angular/core';
import {CoronavirusDataService} from '../../../coronavirus-data.service';
import {Record} from '../../../record';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  ELEMENT_DATA: Record[] = [];
  dataSource = new MatTableDataSource<Record>(this.ELEMENT_DATA);
  displayedColumns: string[] = ['state', 'country', 'totalNumberOfCases'];

  constructor(private coronavirusDataService: CoronavirusDataService) { }

  ngOnInit(): void {
    this.getCoronavirusData();
  }

  public getCoronavirusData(): void {
   this.coronavirusDataService.getCoronavirusData().subscribe(data => this.dataSource.data = data as Record[]);
  }
}
