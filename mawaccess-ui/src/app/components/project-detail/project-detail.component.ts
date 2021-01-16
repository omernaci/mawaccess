import { Component, OnInit } from '@angular/core';

import { Observable } from "rxjs";
import { MawaccessService } from "../../services/mawaccess.service";
import { Project } from "../../models/project";
import { Router, ActivatedRoute } from '@angular/router';
import { ProjectListComponent } from '../project-list/project-list.component';

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.css']
})
export class ProjectDetailComponent implements OnInit {

  id: number;
  project: Project;

  constructor(private route: ActivatedRoute,private router: Router,
    private mawAccessService: MawaccessService) { }

  ngOnInit(): void {
    this.project = new Project();

    this.id = this.route.snapshot.params['id'];
    
    this.mawAccessService.getProject(this.id)
      .subscribe(data => {
        console.log(data)
        this.project = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['projects']);
  }

}
