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
import {
  IOnsFatorCapacidadeGeracaoEolicaESolar,
  NewOnsFatorCapacidadeGeracaoEolicaESolar,
} from '../ons-fator-capacidade-geracao-eolica-e-solar.model';

export type PartialUpdateOnsFatorCapacidadeGeracaoEolicaESolar = Partial<IOnsFatorCapacidadeGeracaoEolicaESolar> &
  Pick<IOnsFatorCapacidadeGeracaoEolicaESolar, 'id'>;

type RestOf<T extends IOnsFatorCapacidadeGeracaoEolicaESolar | NewOnsFatorCapacidadeGeracaoEolicaESolar> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsFatorCapacidadeGeracaoEolicaESolar = RestOf<IOnsFatorCapacidadeGeracaoEolicaESolar>;

export type NewRestOnsFatorCapacidadeGeracaoEolicaESolar = RestOf<NewOnsFatorCapacidadeGeracaoEolicaESolar>;

export type PartialUpdateRestOnsFatorCapacidadeGeracaoEolicaESolar = RestOf<PartialUpdateOnsFatorCapacidadeGeracaoEolicaESolar>;

export type EntityResponseType = HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar>;
export type EntityArrayResponseType = HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar[]>;

@Injectable({ providedIn: 'root' })
export class OnsFatorCapacidadeGeracaoEolicaESolarService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-fator-capacidade-geracao-eolica-e-solars');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-fator-capacidade-geracao-eolica-e-solars/_search');

  create(onsFatorCapacidadeGeracaoEolicaESolar: NewOnsFatorCapacidadeGeracaoEolicaESolar): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsFatorCapacidadeGeracaoEolicaESolar);
    return this.http
      .post<RestOnsFatorCapacidadeGeracaoEolicaESolar>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsFatorCapacidadeGeracaoEolicaESolar: IOnsFatorCapacidadeGeracaoEolicaESolar): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsFatorCapacidadeGeracaoEolicaESolar);
    return this.http
      .put<RestOnsFatorCapacidadeGeracaoEolicaESolar>(
        `${this.resourceUrl}/${this.getOnsFatorCapacidadeGeracaoEolicaESolarIdentifier(onsFatorCapacidadeGeracaoEolicaESolar)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsFatorCapacidadeGeracaoEolicaESolar: PartialUpdateOnsFatorCapacidadeGeracaoEolicaESolar): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsFatorCapacidadeGeracaoEolicaESolar);
    return this.http
      .patch<RestOnsFatorCapacidadeGeracaoEolicaESolar>(
        `${this.resourceUrl}/${this.getOnsFatorCapacidadeGeracaoEolicaESolarIdentifier(onsFatorCapacidadeGeracaoEolicaESolar)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsFatorCapacidadeGeracaoEolicaESolar>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsFatorCapacidadeGeracaoEolicaESolar[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsFatorCapacidadeGeracaoEolicaESolar[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar[]>()], asapScheduler)),
      );
  }

  getOnsFatorCapacidadeGeracaoEolicaESolarIdentifier(
    onsFatorCapacidadeGeracaoEolicaESolar: Pick<IOnsFatorCapacidadeGeracaoEolicaESolar, 'id'>,
  ): number {
    return onsFatorCapacidadeGeracaoEolicaESolar.id;
  }

  compareOnsFatorCapacidadeGeracaoEolicaESolar(
    o1: Pick<IOnsFatorCapacidadeGeracaoEolicaESolar, 'id'> | null,
    o2: Pick<IOnsFatorCapacidadeGeracaoEolicaESolar, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsFatorCapacidadeGeracaoEolicaESolarIdentifier(o1) === this.getOnsFatorCapacidadeGeracaoEolicaESolarIdentifier(o2)
      : o1 === o2;
  }

  addOnsFatorCapacidadeGeracaoEolicaESolarToCollectionIfMissing<Type extends Pick<IOnsFatorCapacidadeGeracaoEolicaESolar, 'id'>>(
    onsFatorCapacidadeGeracaoEolicaESolarCollection: Type[],
    ...onsFatorCapacidadeGeracaoEolicaESolarsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsFatorCapacidadeGeracaoEolicaESolars: Type[] = onsFatorCapacidadeGeracaoEolicaESolarsToCheck.filter(isPresent);
    if (onsFatorCapacidadeGeracaoEolicaESolars.length > 0) {
      const onsFatorCapacidadeGeracaoEolicaESolarCollectionIdentifiers = onsFatorCapacidadeGeracaoEolicaESolarCollection.map(
        onsFatorCapacidadeGeracaoEolicaESolarItem =>
          this.getOnsFatorCapacidadeGeracaoEolicaESolarIdentifier(onsFatorCapacidadeGeracaoEolicaESolarItem),
      );
      const onsFatorCapacidadeGeracaoEolicaESolarsToAdd = onsFatorCapacidadeGeracaoEolicaESolars.filter(
        onsFatorCapacidadeGeracaoEolicaESolarItem => {
          const onsFatorCapacidadeGeracaoEolicaESolarIdentifier = this.getOnsFatorCapacidadeGeracaoEolicaESolarIdentifier(
            onsFatorCapacidadeGeracaoEolicaESolarItem,
          );
          if (onsFatorCapacidadeGeracaoEolicaESolarCollectionIdentifiers.includes(onsFatorCapacidadeGeracaoEolicaESolarIdentifier)) {
            return false;
          }
          onsFatorCapacidadeGeracaoEolicaESolarCollectionIdentifiers.push(onsFatorCapacidadeGeracaoEolicaESolarIdentifier);
          return true;
        },
      );
      return [...onsFatorCapacidadeGeracaoEolicaESolarsToAdd, ...onsFatorCapacidadeGeracaoEolicaESolarCollection];
    }
    return onsFatorCapacidadeGeracaoEolicaESolarCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsFatorCapacidadeGeracaoEolicaESolar
      | NewOnsFatorCapacidadeGeracaoEolicaESolar
      | PartialUpdateOnsFatorCapacidadeGeracaoEolicaESolar,
  >(onsFatorCapacidadeGeracaoEolicaESolar: T): RestOf<T> {
    return {
      ...onsFatorCapacidadeGeracaoEolicaESolar,
      dinInstante: onsFatorCapacidadeGeracaoEolicaESolar.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsFatorCapacidadeGeracaoEolicaESolar: RestOnsFatorCapacidadeGeracaoEolicaESolar,
  ): IOnsFatorCapacidadeGeracaoEolicaESolar {
    return {
      ...restOnsFatorCapacidadeGeracaoEolicaESolar,
      dinInstante: restOnsFatorCapacidadeGeracaoEolicaESolar.dinInstante
        ? dayjs(restOnsFatorCapacidadeGeracaoEolicaESolar.dinInstante)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsFatorCapacidadeGeracaoEolicaESolar>,
  ): HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsFatorCapacidadeGeracaoEolicaESolar[]>,
  ): HttpResponse<IOnsFatorCapacidadeGeracaoEolicaESolar[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
