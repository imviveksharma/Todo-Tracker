import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { FilterPipe } from './filter/filter.pipe';
import { ToastrModule } from 'ngx-toastr';
import { MaterialModule } from './material/material.module';
import { HomescreenComponent } from './component/HomeScreenComponents/homescreen/homescreen.component';
import { LoginComponent } from './component/HomeScreenComponents/login/login.component';
import { RegisterComponent } from './component/HomeScreenComponents/register/register.component';
import { ContactusComponent } from './component/HomeScreenComponents/contactus/contactus.component';
import { AdminComponent } from './component/AdminComp/admin/admin.component';
import { FeaturesComponent } from './component/HomeScreenComponents/features/features.component';
import { PricingComponent } from './component/HomeScreenComponents/pricing/pricing.component';
import { DashboardComponent } from './component/DashboardComp/dashboard/dashboard.component';
import { MainCompComponent } from './component/DashboardComp/main-comp/main-comp.component';
import { NotesComponent } from './component/DashboardComp/notes/notes.component';
import { TrashComponent } from './component/DashboardComp/trash/trash.component';
import { EditComponent } from './component/DashboardComp/edit/edit.component';
import { HeaderComponent } from './component/HomeScreenComponents/header/header.component';
import { ArchieveComponent } from './component/DashboardComp/archieve/archieve.component';
import { AdminDashboardComponent } from './component/AdminComp/admin-dashboard/admin-dashboard.component';
import { AddMessageComponent } from './component/AdminComp/admin-dashboard/add-message/add-message.component';
import { DisplayMessageComponent } from './component/AdminComp/admin-dashboard/display-message/display-message.component';
import { ForgetPasswordComponent } from './component/HomeScreenComponents/forget-password/forget-password.component';

@NgModule({
  declarations: [
    AppComponent,
    HomescreenComponent,
    LoginComponent,
    RegisterComponent,
    ContactusComponent,
    AdminComponent,
    PricingComponent,
    FeaturesComponent,
    DashboardComponent,
    MainCompComponent,
    NotesComponent,
    TrashComponent,
    EditComponent,
    FilterPipe,
    HeaderComponent,
    ArchieveComponent,
    AdminDashboardComponent,
    AddMessageComponent,
    DisplayMessageComponent,
    ForgetPasswordComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    LayoutModule,
    MaterialModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
