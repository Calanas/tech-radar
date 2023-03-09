import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ShowTechnologiesComponent } from './show-technologies/show-technologies.component';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [AppComponent, ShowTechnologiesComponent],
  imports: [BrowserModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
