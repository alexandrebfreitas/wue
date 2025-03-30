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
import { IOnsTaxasTeifaETeip, NewOnsTaxasTeifaETeip } from '../ons-taxas-teifa-e-teip.model';

export type PartialUpdateOnsTaxasTeifaETeip = Partial<IOnsTaxasTeifaETeip> & Pick<IOnsTaxasTeifaETeip, 'id'>;

type RestOf<T extends IOnsTaxasTeifaETeip | NewOnsTaxasTeifaETeip> = Omit<T, 'dinMes' | 'dinInstante'> & {
  dinMes?: string | null;
  dinInstante?: string | null;
};

export type RestOnsTaxasTeifaETeip = RestOf<IOnsTaxasTeifaETeip>;

export type NewRestOnsTaxasTeifaETeip = RestOf<NewOnsTaxasTeifaETeip>;

export type PartialUpdateRestOnsTaxasTeifaETeip = RestOf<PartialUpdateOnsTaxasTeifaETeip>;

export type EntityResponseType = HttpResponse<IOnsTaxasTeifaETeip>;
export type EntityArrayResponseType = HttpResponse<IOnsTaxasTeifaETeip[]>;

@Injectable({ providedIn: 'root' })
export class OnsTaxasTeifaETeipService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-taxas-teifa-e-teips');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-taxas-teifa-e-teips/_search');

  create(onsTaxasTeifaETeip: NewOnsTaxasTeifaETeip): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsTaxasTeifaETeip);
    return this.http
      .post<RestOnsTaxasTeifaETeip>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsTaxasTeifaETeip: IOnsTaxasTeifaETeip): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsTaxasTeifaETeip);
    return this.http
      .put<RestOnsTaxasTeifaETeip>(`${this.resourceUrl}/${this.getOnsTaxasTeifaETeipIdentifier(onsTaxasTeifaETeip)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsTaxasTeifaETeip: PartialUpdateOnsTaxasTeifaETeip): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsTaxasTeifaETeip);
    return this.http
      .patch<RestOnsTaxasTeifaETeip>(`${this.resourceUrl}/${this.getOnsTaxasTeifaETeipIdentifier(onsTaxasTeifaETeip)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsTaxasTeifaETeip>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsTaxasTeifaETeip[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsTaxasTeifaETeip[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsTaxasTeifaETeip[]>()], asapScheduler)),
    );
  }

  getOnsTaxasTeifaETeipIdentifier(onsTaxasTeifaETeip: Pick<IOnsTaxasTeifaETeip, 'id'>): number {
    return onsTaxasTeifaETeip.id;
  }

  compareOnsTaxasTeifaETeip(o1: Pick<IOnsTaxasTeifaETeip, 'id'> | null, o2: Pick<IOnsTaxasTeifaETeip, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsTaxasTeifaETeipIdentifier(o1) === this.getOnsTaxasTeifaETeipIdentifier(o2) : o1 === o2;
  }

  addOnsTaxasTeifaETeipToCollectionIfMissing<Type extends Pick<IOnsTaxasTeifaETeip, 'id'>>(
    onsTaxasTeifaETeipCollection: Type[],
    ...onsTaxasTeifaETeipsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsTaxasTeifaETeips: Type[] = onsTaxasTeifaETeipsToCheck.filter(isPresent);
    if (onsTaxasTeifaETeips.length > 0) {
      const onsTaxasTeifaETeipCollectionIdentifiers = onsTaxasTeifaETeipCollection.map(onsTaxasTeifaETeipItem =>
        this.getOnsTaxasTeifaETeipIdentifier(onsTaxasTeifaETeipItem),
      );
      const onsTaxasTeifaETeipsToAdd = onsTaxasTeifaETeips.filter(onsTaxasTeifaETeipItem => {
        const onsTaxasTeifaETeipIdentifier = this.getOnsTaxasTeifaETeipIdentifier(onsTaxasTeifaETeipItem);
        if (onsTaxasTeifaETeipCollectionIdentifiers.includes(onsTaxasTeifaETeipIdentifier)) {
          return false;
        }
        onsTaxasTeifaETeipCollectionIdentifiers.push(onsTaxasTeifaETeipIdentifier);
        return true;
      });
      return [...onsTaxasTeifaETeipsToAdd, ...onsTaxasTeifaETeipCollection];
    }
    return onsTaxasTeifaETeipCollection;
  }

  protected convertDateFromClient<T extends IOnsTaxasTeifaETeip | NewOnsTaxasTeifaETeip | PartialUpdateOnsTaxasTeifaETeip>(
    onsTaxasTeifaETeip: T,
  ): RestOf<T> {
    return {
      ...onsTaxasTeifaETeip,
      dinMes: onsTaxasTeifaETeip.dinMes?.format(DATE_FORMAT) ?? null,
      dinInstante: onsTaxasTeifaETeip.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsTaxasTeifaETeip: RestOnsTaxasTeifaETeip): IOnsTaxasTeifaETeip {
    return {
      ...restOnsTaxasTeifaETeip,
      dinMes: restOnsTaxasTeifaETeip.dinMes ? dayjs(restOnsTaxasTeifaETeip.dinMes) : undefined,
      dinInstante: restOnsTaxasTeifaETeip.dinInstante ? dayjs(restOnsTaxasTeifaETeip.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsTaxasTeifaETeip>): HttpResponse<IOnsTaxasTeifaETeip> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsTaxasTeifaETeip[]>): HttpResponse<IOnsTaxasTeifaETeip[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
