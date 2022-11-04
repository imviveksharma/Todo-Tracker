import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatTabsModule } from '@angular/material/tabs';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule, MatOptionModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatBadgeModule } from '@angular/material/badge';
import { NgxPaginationModule } from 'ngx-pagination';
import { MatTooltipModule } from '@angular/material/tooltip';

const materialArray:any=[MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatIconModule,
  MatListModule,
  MatCardModule,
  FormsModule,
  ReactiveFormsModule,
  HttpClientModule,
  MatFormFieldModule,
  MatInputModule,
  MatGridListModule,
  MatDialogModule,
  MatMenuModule,
  MatButtonToggleModule,
  MatTabsModule,
  MatPaginatorModule,
  MatSnackBarModule,
  MatSelectModule,
  MatOptionModule,
  MatDatepickerModule,
  MatBadgeModule,
  NgxPaginationModule,
  MatTooltipModule,
  MatNativeDateModule]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    materialArray
  ],exports:[
    materialArray
  ]
})
export class MaterialModule { }
