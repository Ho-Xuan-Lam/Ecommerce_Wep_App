import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Country } from '../common/country';
import { State } from '../common/state';

@Injectable({
  providedIn: 'root'
})
export class Luv2ShopFormService {

  private countriesUrl = "http://localhost:8080/api/countries"; 
  private statesUrl = "http://localhost:8080/api/states"; 

  constructor(private httpClient: HttpClient) { }

  getCountries(): Observable<Country[]> {

    return this.httpClient.get<GetResponseCountries>(this.countriesUrl).pipe(
      map(response => response._embedded.countries)
    );
  }

  getStates(theCountryCode: string): Observable<State[]> {
    
    //search url
    const searchStateUrl = `${this.statesUrl}/search/findByCountryCode?code=${theCountryCode}`;

    return this.httpClient.get<GetResponseStates>(searchStateUrl).pipe(
      map(response => response._embedded.states)
    );

  }

  getCreditCardMonths(startMonth: number): Observable<number[]> {
    let data: number[] = [];


    //build an array for "Month" dropdown list
    // -start at current month and loop until
    for( let theMonth = startMonth; theMonth <= 12; theMonth++ ) {
      data.push(theMonth);
    }

    return of(data);
  }

  getCreditCardYears(): Observable<number[]> {
    let data: number[] = [];

    //build an array for "Year" dropdown list
    // -start at current month and loop for next 10 years 

    const starYears: number = new Date().getFullYear();
    const andYear: number = starYears + 10 ;

    for( let theYear= starYears; theYear <= andYear; theYear++ ){
      data.push(theYear);
    }

    return of(data);
  }
}
  interface GetResponseCountries {
    _embedded: {
      countries: Country[];
    }
  }

  interface GetResponseStates {
    _embedded: {
      states: State[];
    }
  }

