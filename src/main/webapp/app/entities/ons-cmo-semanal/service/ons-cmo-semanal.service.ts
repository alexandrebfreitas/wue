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
import { IOnsCmoSemanal, NewOnsCmoSemanal } from '../ons-cmo-semanal.model';

export type PartialUpdateOnsCmoSemanal = Partial<IOnsCmoSemanal> & Pick<IOnsCmoSemanal, 'id'>;

type RestOf<T extends IOnsCmoSemanal | NewOnsCmoSemanal> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsCmoSemanal = RestOf<IOnsCmoSemanal>;

export type NewRestOnsCmoSemanal = RestOf<NewOnsCmoSemanal>;

export type PartialUpdateRestOnsCmoSemanal = RestOf<PartialUpdateOnsCmoSemanal>;

export type EntityResponseType = HttpResponse<IOnsCmoSemanal>;
export type EntityArrayResponseType = HttpResponse<IOnsCmoSemanal[]>;

@Injectable({ providedIn: 'root' })
export class OnsCmoSemanalService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-cmo-semanals');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-cmo-semanals/_search');

  create(onsCmoSemanal: NewOnsCmoSemanal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCmoSemanal);
    return this.http
      .post<RestOnsCmoSemanal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCmoSemanal: IOnsCmoSemanal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCmoSemanal);
    return this.http
      .put<RestOnsCmoSemanal>(`${this.resourceUrl}/${this.getOnsCmoSemanalIdentifier(onsCmoSemanal)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCmoSemanal: PartialUpdateOnsCmoSemanal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCmoSemanal);
    return this.http
      .patch<RestOnsCmoSemanal>(`${this.resourceUrl}/${this.getOnsCmoSemanalIdentifier(onsCmoSemanal)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCmoSemanal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCmoSemanal[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCmoSemanal[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCmoSemanal[]>()], asapScheduler)),
    );
  }

  getOnsCmoSemanalIdentifier(onsCmoSemanal: Pick<IOnsCmoSemanal, 'id'>): number {
    return onsCmoSemanal.id;
  }

  compareOnsCmoSemanal(o1: Pick<IOnsCmoSemanal, 'id'> | null, o2: Pick<IOnsCmoSemanal, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsCmoSemanalIdentifier(o1) === this.getOnsCmoSemanalIdentifier(o2) : o1 === o2;
  }

  addOnsCmoSemanalToCollectionIfMissing<Type extends Pick<IOnsCmoSemanal, 'id'>>(
    onsCmoSemanalCollection: Type[],
    ...onsCmoSemanalsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCmoSemanals: Type[] = onsCmoSemanalsToCheck.filter(isPresent);
    if (onsCmoSemanals.length > 0) {
      const onsCmoSemanalCollectionIdentifiers = onsCmoSemanalCollection.map(onsCmoSemanalItem =>
        this.getOnsCmoSemanalIdentifier(onsCmoSemanalItem),
      );
      const onsCmoSemanalsToAdd = onsCmoSemanals.filter(onsCmoSemanalItem => {
        const onsCmoSemanalIdentifier = this.getOnsCmoSemanalIdentifier(onsCmoSemanalItem);
        if (onsCmoSemanalCollectionIdentifiers.includes(onsCmoSemanalIdentifier)) {
          return false;
        }
        onsCmoSemanalCollectionIdentifiers.push(onsCmoSemanalIdentifier);
        return true;
      });
      return [...onsCmoSemanalsToAdd, ...onsCmoSemanalCollection];
    }
    return onsCmoSemanalCollection;
  }

  protected convertDateFromClient<T extends IOnsCmoSemanal | NewOnsCmoSemanal | PartialUpdateOnsCmoSemanal>(onsCmoSemanal: T): RestOf<T> {
    return {
      ...onsCmoSemanal,
      dinInstante: onsCmoSemanal.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsCmoSemanal: RestOnsCmoSemanal): IOnsCmoSemanal {
    return {
      ...restOnsCmoSemanal,
      dinInstante: restOnsCmoSemanal.dinInstante ? dayjs(restOnsCmoSemanal.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsCmoSemanal>): HttpResponse<IOnsCmoSemanal> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsCmoSemanal[]>): HttpResponse<IOnsCmoSemanal[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
