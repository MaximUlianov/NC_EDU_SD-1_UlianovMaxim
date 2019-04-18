import {Component, OnInit} from '@angular/core';
import {MainPageService} from "../../service/main-page/main-page.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private service:MainPageService,
              private spinner:Ng4LoadingSpinnerService) {}


  ngOnInit() {
  }

}
