import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { DetailsComponent } from './components/details/details.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JwtModule } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';
import { AuthService } from './services/auth.service';
import { TokenInterceptor } from './interceptor/tokenInterceptor';
import { CanActivateGuard } from './guards/canActivateGuard';
import { CanActivateAdminGuard } from './guards/canActivateAdminGuard';

// Routes
const appRoutes: Routes = [
  {
    path: '', 
    component: HomeComponent 
  },
  { 
    path: 'home', 
    component: HomeComponent 
  },
  { 
    path: 'login', 
    component: LoginComponent 
  },
  { 
    path: 'logout', 
    component: LogoutComponent 
  },
  { 
    path: 'details', 
    component: DetailsComponent, 
    canActivate: [CanActivateGuard] 
  },
  { 
    path: '**', 
    component: LoginComponent 
  }
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    DetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: function  tokenGetter() {
          return localStorage.getItem(environment.TOKEN_NAME);
        }
      }
    })
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    AuthService,
    CanActivateGuard,
    CanActivateAdminGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
