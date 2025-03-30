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
import { IOnsCargaEnergiaMensal, NewOnsCargaEnergiaMensal } from '../ons-carga-energia-mensal.model';

export type PartialUpdateOnsCargaEnergiaMensal = Partial<IOnsCargaEnergiaMensal> & Pick<IOnsCargaEnergiaMensal, 'id'>;

type RestOf<T extends IOnsCargaEnergiaMensal | NewOnsCargaEnergiaMensal> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsCargaEnergiaMensal = RestOf<IOnsCargaEnergiaMensal>;

export type NewRestOnsCargaEnergiaMensal = RestOf<NewOnsCargaEnergiaMensal>;

export type PartialUpdateRestOnsCargaEnergiaMensal = RestOf<PartialUpdateOnsCargaEnergiaMensal>;

export type EntityResponseType = HttpResponse<IOnsCargaEnergiaMensal>;
export type EntityArrayResponseType = HttpResponse<IOnsCargaEnergiaMensal[]>;

@Injectable({ providedIn: 'root' })
export class OnsCargaEnergiaMensalService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-carga-energia-mensals');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-carga-energia-mensals/_search');

  create(onsCargaEnergiaMensal: NewOnsCargaEnergiaMensal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaMensal);
    return this.http
      .post<RestOnsCargaEnergiaMensal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCargaEnergiaMensal: IOnsCargaEnergiaMensal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaMensal);
    return this.http
      .put<RestOnsCargaEnergiaMensal>(`${this.resourceUrl}/${this.getOnsCargaEnergiaMensalIdentifier(onsCargaEnergiaMensal)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCargaEnergiaMensal: PartialUpdateOnsCargaEnergiaMensal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaMensal);
    return this.http
      .patch<RestOnsCargaEnergiaMensal>(`${this.resourceUrl}/${this.getOnsCargaEnergiaMensalIdentifier(onsCargaEnergiaMensal)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCargaEnergiaMensal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCargaEnergiaMensal[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCargaEnergiaMensal[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCargaEnergiaMensal[]>()], asapScheduler)),
    );
  }

  getOnsCargaEnergiaMensalIdentifier(onsCargaEnergiaMensal: Pick<IOnsCargaEnergiaMensal, 'id'>): number {
    return onsCargaEnergiaMensal.id;
  }

  compareOnsCargaEnergiaMensal(o1: Pick<IOnsCargaEnergiaMensal, 'id'> | null, o2: Pick<IOnsCargaEnergiaMensal, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsCargaEnergiaMensalIdentifier(o1) === this.getOnsCargaEnergiaMensalIdentifier(o2) : o1 === o2;
  }

  addOnsCargaEnergiaMensalToCollectionIfMissing<Type extends Pick<IOnsCargaEnergiaMensal, 'id'>>(
    onsCargaEnergiaMensalCollection: Type[],
    ...onsCargaEnergiaMensalsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCargaEnergiaMensals: Type[] = onsCargaEnergiaMensalsToCheck.filter(isPresent);
    if (onsCargaEnergiaMensals.length > 0) {
      const onsCargaEnergiaMensalCollectionIdentifiers = onsCargaEnergiaMensalCollection.map(onsCargaEnergiaMensalItem =>
        this.getOnsCargaEnergiaMensalIdentifier(onsCargaEnergiaMensalItem),
      );
      const onsCargaEnergiaMensalsToAdd = onsCargaEnergiaMensals.filter(onsCargaEnergiaMensalItem => {
        const onsCargaEnergiaMensalIdentifier = this.getOnsCargaEnergiaMensalIdentifier(onsCargaEnergiaMensalItem);
        if (onsCargaEnergiaMensalCollectionIdentifiers.includes(onsCargaEnergiaMensalIdentifier)) {
          return false;
        }
        onsCargaEnergiaMensalCollectionIdentifiers.push(onsCargaEnergiaMensalIdentifier);
        return true;
      });
      return [...onsCargaEnergiaMensalsToAdd, ...onsCargaEnergiaMensalCollection];
    }
    return onsCargaEnergiaMensalCollection;
  }

  protected convertDateFromClient<T extends IOnsCargaEnergiaMensal | NewOnsCargaEnergiaMensal | PartialUpdateOnsCargaEnergiaMensal>(
    onsCargaEnergiaMensal: T,
  ): RestOf<T> {
    return {
      ...onsCargaEnergiaMensal,
      dinInstante: onsCargaEnergiaMensal.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsCargaEnergiaMensal: RestOnsCargaEnergiaMensal): IOnsCargaEnergiaMensal {
    return {
      ...restOnsCargaEnergiaMensal,
      dinInstante: restOnsCargaEnergiaMensal.dinInstante ? dayjs(restOnsCargaEnergiaMensal.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsCargaEnergiaMensal>): HttpResponse<IOnsCargaEnergiaMensal> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsCargaEnergiaMensal[]>): HttpResponse<IOnsCargaEnergiaMensal[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
