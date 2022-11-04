import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuardingGuard } from './Guarding/admin-guarding.guard';
import { HomescreenComponent } from './component/HomeScreenComponents/homescreen/homescreen.component';
import { LoginComponent } from './component/HomeScreenComponents/login/login.component';
import { RegisterComponent } from './component/HomeScreenComponents/register/register.component';
import { ContactusComponent } from './component/HomeScreenComponents/contactus/contactus.component';
import { AdminComponent } from './component/AdminComp/admin/admin.component';
import { FeaturesComponent } from './component/HomeScreenComponents/features/features.component';
import { PricingComponent } from './component/HomeScreenComponents/pricing/pricing.component';
import { DashboardComponent } from './component/DashboardComp/dashboard/dashboard.component';
import { NotesComponent } from './component/DashboardComp/notes/notes.component';
import { TrashComponent } from './component/DashboardComp/trash/trash.component';
import { EditComponent } from './component/DashboardComp/edit/edit.component';
import { ArchieveComponent } from './component/DashboardComp/archieve/archieve.component';
import { AdminDashboardComponent } from './component/AdminComp/admin-dashboard/admin-dashboard.component';
import { AddMessageComponent } from './component/AdminComp/admin-dashboard/add-message/add-message.component';
import { DisplayMessageComponent } from './component/AdminComp/admin-dashboard/display-message/display-message.component';
import { AuthguardGuard } from './Guarding/authguard.guard';



const routes: Routes = [

  {
    path: "",
    component: HomescreenComponent
  },


  {
    path: "dashboard",
    component: DashboardComponent,canActivate:[AuthguardGuard],
    children: [
      {
        path: "notes",
        component: NotesComponent,
      },

      {
        path: "edit",
        component: EditComponent
      }
      ,

      {
        path: "archive",
        component: ArchieveComponent
      },
      {
        path: "trash",
        component: TrashComponent
      },]
  },
  {
    path: "features",
    component: FeaturesComponent
  },
  {
    path: "pricing",
    component: PricingComponent
  },
  {
    path: "admin",
    component: AdminComponent
  },
  
    {
      path:"admin-dashboard",
      component:AdminDashboardComponent,
canActivate:[AdminGuardingGuard],
      children: [
     
    {path:"addMessage", component: AddMessageComponent},
    
      ]
    },
    {path :"deleteadmin", component : DisplayMessageComponent}
  ,
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "contact",
    component: ContactusComponent
  },
  {
    path: "home",
    component: HomescreenComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
