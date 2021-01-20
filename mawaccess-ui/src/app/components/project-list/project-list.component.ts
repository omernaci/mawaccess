import { Component, OnInit } from '@angular/core';

import { Observable } from "rxjs";
import { MawaccessService } from "../../services/mawaccess.service";
import { Project } from "../../models/project";
import { Router } from '@angular/router';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects: Observable<Project[]>;

  constructor(private mawAccessService: MawaccessService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.projects = this.mawAccessService.getProjectList();
  }

  projectDetails(id: number){
    this.router.navigate(['details', id]);
  }

  runProject(id: number){
    this.mawAccessService.runProject(id).subscribe(response => {
      console.log(response);
    }, error => {
      console.error(error);
    });
  }

}
