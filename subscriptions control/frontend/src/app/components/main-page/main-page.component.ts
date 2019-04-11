import {Component, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {User} from "../../model/user";
import {MainPageService} from "../../service/main-page/main-page.service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  user:User;
  modalRef: BsModalRef;

  constructor(private service:MainPageService) {}


  ngOnInit() {
    this.user = new User();
  }

}
