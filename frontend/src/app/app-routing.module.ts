import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { TechnologyComponent } from './technology/technology.component';

const routes: Routes = [
  { path: '', component: AppComponent },
  { path: 'technologies', component: TechnologyComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
