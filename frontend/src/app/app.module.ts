import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ShowTechnologiesComponent } from './show-technologies/show-technologies.component';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddTechnologyComponent } from './add-technology/add-technology.component';

@NgModule({
  declarations: [
    AppComponent,
    ShowTechnologiesComponent,
    AddTechnologyComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, ReactiveFormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
