import { Component, OnInit } from '@angular/core';
import { OktaAuthService } from '@okta/okta-angular';

import * as OktaSignIn from '@okta/okta-signin-widget';
import myAppConfig from '../../config/my-app-config';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  oktaSignIn: any;

  constructor(private oktaAuthService: OktaAuthService) {

    this.oktaSignIn = new  OktaSignIn({
      logo: 'assets/images/logo1.png',
      features: {
        registration: true
      },
      baseUrl: myAppConfig.oidc.issuer.split('/aouth2')[0],
      clientId: myAppConfig.oidc.clientId,
      redirectUri: myAppConfig.oidc.redirectUri,
      authParams: {
        pkce: true,
        issuer: myAppConfig.oidc.issuer,
        scopes: myAppConfig.oidc.scopes
      }
    });
   }

  ngOnInit(): void {
    this.oktaSignIn.remove();

    this.oktaSignIn.renderEl({
      el: '#okta-sign-in-widget'}, //this name should be same as div tag id in login.component.html
      (response) => {
        if(response.status === 'SUCCESS') {
          this.oktaAuthService.signInWithRedirect();
        }
      },
      (error) => { 
        throw error; 
      }  
    );
  }

}
