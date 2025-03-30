import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, map, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import { IOnsDadosGrandezasFluviometricas, NewOnsDadosGrandezasFluviometricas } from '../ons-dados-grandezas-fluviometricas.model';

export type PartialUpdateOnsDadosGrandezasFluviometricas = Partial<IOnsDadosGrandezasFluviometricas> &
  Pick<IOnsDadosGrandezasFluviometricas, 'id'>;

type RestOf<T extends IOnsDadosGrandezasFluviometricas | NewOnsDadosGrandezasFluviometricas> = Omit<T, 'dinMedicao'> & {
  dinMedicao?: string | null;
};

export type RestOnsDadosGrandezasFluviometricas = RestOf<IOnsDadosGrandezasFluviometricas>;

export type NewRestOnsDadosGrandezasFluviometricas = RestOf<NewOnsDadosGrandezasFluviometricas>;

export type PartialUpdateRestOnsDadosGrandezasFluviometricas = RestOf<PartialUpdateOnsDadosGrandezasFluviometricas>;

export type EntityResponseType = HttpResponse<IOnsDadosGrandezasFluviometricas>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosGrandezasFluviometricas[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosGrandezasFluviometricasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-grandezas-fluviometricas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-grandezas-fluviometricas/_search');

  create(onsDadosGrandezasFluviometricas: NewOnsDadosGrandezasFluviometricas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosGrandezasFluviometricas);
    return this.http
      .post<RestOnsDadosGrandezasFluviometricas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsDadosGrandezasFluviometricas: IOnsDadosGrandezasFluviometricas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosGrandezasFluviometricas);
    return this.http
      .put<RestOnsDadosGrandezasFluviometricas>(
        `${this.resourceUrl}/${this.getOnsDadosGrandezasFluviometricasIdentifier(onsDadosGrandezasFluviometricas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsDadosGrandezasFluviometricas: PartialUpdateOnsDadosGrandezasFluviometricas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosGrandezasFluviometricas);
    return this.http
      .patch<RestOnsDadosGrandezasFluviometricas>(
        `${this.resourceUrl}/${this.getOnsDadosGrandezasFluviometricasIdentifier(onsDadosGrandezasFluviometricas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosGrandezasFluviometricas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosGrandezasFluviometricas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsDadosGrandezasFluviometricas[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsDadosGrandezasFluviometricas[]>()], asapScheduler)),
    );
  }

  getOnsDadosGrandezasFluviometricasIdentifier(onsDadosGrandezasFluviometricas: Pick<IOnsDadosGrandezasFluviometricas, 'id'>): number {
    return onsDadosGrandezasFluviometricas.id;
  }

  compareOnsDadosGrandezasFluviometricas(
    o1: Pick<IOnsDadosGrandezasFluviometricas, 'id'> | null,
    o2: Pick<IOnsDadosGrandezasFluviometricas, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosGrandezasFluviometricasIdentifier(o1) === this.getOnsDadosGrandezasFluviometricasIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosGrandezasFluviometricasToCollectionIfMissing<Type extends Pick<IOnsDadosGrandezasFluviometricas, 'id'>>(
    onsDadosGrandezasFluviometricasCollection: Type[],
    ...onsDadosGrandezasFluviometricasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosGrandezasFluviometricas: Type[] = onsDadosGrandezasFluviometricasToCheck.filter(isPresent);
    if (onsDadosGrandezasFluviometricas.length > 0) {
      const onsDadosGrandezasFluviometricasCollectionIdentifiers = onsDadosGrandezasFluviometricasCollection.map(
        onsDadosGrandezasFluviometricasItem => this.getOnsDadosGrandezasFluviometricasIdentifier(onsDadosGrandezasFluviometricasItem),
      );
      const onsDadosGrandezasFluviometricasToAdd = onsDadosGrandezasFluviometricas.filter(onsDadosGrandezasFluviometricasItem => {
        const onsDadosGrandezasFluviometricasIdentifier =
          this.getOnsDadosGrandezasFluviometricasIdentifier(onsDadosGrandezasFluviometricasItem);
        if (onsDadosGrandezasFluviometricasCollectionIdentifiers.includes(onsDadosGrandezasFluviometricasIdentifier)) {
          return false;
        }
        onsDadosGrandezasFluviometricasCollectionIdentifiers.push(onsDadosGrandezasFluviometricasIdentifier);
        return true;
      });
      return [...onsDadosGrandezasFluviometricasToAdd, ...onsDadosGrandezasFluviometricasCollection];
    }
    return onsDadosGrandezasFluviometricasCollection;
  }

  protected convertDateFromClient<
    T extends IOnsDadosGrandezasFluviometricas | NewOnsDadosGrandezasFluviometricas | PartialUpdateOnsDadosGrandezasFluviometricas,
  >(onsDadosGrandezasFluviometricas: T): RestOf<T> {
    return {
      ...onsDadosGrandezasFluviometricas,
      dinMedicao: onsDadosGrandezasFluviometricas.dinMedicao?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosGrandezasFluviometricas: RestOnsDadosGrandezasFluviometricas,
  ): IOnsDadosGrandezasFluviometricas {
    return {
      ...restOnsDadosGrandezasFluviometricas,
      dinMedicao: restOnsDadosGrandezasFluviometricas.dinMedicao ? dayjs(restOnsDadosGrandezasFluviometricas.dinMedicao) : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosGrandezasFluviometricas>,
  ): HttpResponse<IOnsDadosGrandezasFluviometricas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosGrandezasFluviometricas[]>,
  ): HttpResponse<IOnsDadosGrandezasFluviometricas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
