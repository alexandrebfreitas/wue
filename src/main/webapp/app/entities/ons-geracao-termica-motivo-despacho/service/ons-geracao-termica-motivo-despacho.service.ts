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
import { IOnsGeracaoTermicaMotivoDespacho, NewOnsGeracaoTermicaMotivoDespacho } from '../ons-geracao-termica-motivo-despacho.model';

export type PartialUpdateOnsGeracaoTermicaMotivoDespacho = Partial<IOnsGeracaoTermicaMotivoDespacho> &
  Pick<IOnsGeracaoTermicaMotivoDespacho, 'id'>;

type RestOf<T extends IOnsGeracaoTermicaMotivoDespacho | NewOnsGeracaoTermicaMotivoDespacho> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsGeracaoTermicaMotivoDespacho = RestOf<IOnsGeracaoTermicaMotivoDespacho>;

export type NewRestOnsGeracaoTermicaMotivoDespacho = RestOf<NewOnsGeracaoTermicaMotivoDespacho>;

export type PartialUpdateRestOnsGeracaoTermicaMotivoDespacho = RestOf<PartialUpdateOnsGeracaoTermicaMotivoDespacho>;

export type EntityResponseType = HttpResponse<IOnsGeracaoTermicaMotivoDespacho>;
export type EntityArrayResponseType = HttpResponse<IOnsGeracaoTermicaMotivoDespacho[]>;

@Injectable({ providedIn: 'root' })
export class OnsGeracaoTermicaMotivoDespachoService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-geracao-termica-motivo-despachos');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-geracao-termica-motivo-despachos/_search');

  create(onsGeracaoTermicaMotivoDespacho: NewOnsGeracaoTermicaMotivoDespacho): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsGeracaoTermicaMotivoDespacho);
    return this.http
      .post<RestOnsGeracaoTermicaMotivoDespacho>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsGeracaoTermicaMotivoDespacho: IOnsGeracaoTermicaMotivoDespacho): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsGeracaoTermicaMotivoDespacho);
    return this.http
      .put<RestOnsGeracaoTermicaMotivoDespacho>(
        `${this.resourceUrl}/${this.getOnsGeracaoTermicaMotivoDespachoIdentifier(onsGeracaoTermicaMotivoDespacho)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsGeracaoTermicaMotivoDespacho: PartialUpdateOnsGeracaoTermicaMotivoDespacho): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsGeracaoTermicaMotivoDespacho);
    return this.http
      .patch<RestOnsGeracaoTermicaMotivoDespacho>(
        `${this.resourceUrl}/${this.getOnsGeracaoTermicaMotivoDespachoIdentifier(onsGeracaoTermicaMotivoDespacho)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsGeracaoTermicaMotivoDespacho>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsGeracaoTermicaMotivoDespacho[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsGeracaoTermicaMotivoDespacho[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsGeracaoTermicaMotivoDespacho[]>()], asapScheduler)),
    );
  }

  getOnsGeracaoTermicaMotivoDespachoIdentifier(onsGeracaoTermicaMotivoDespacho: Pick<IOnsGeracaoTermicaMotivoDespacho, 'id'>): number {
    return onsGeracaoTermicaMotivoDespacho.id;
  }

  compareOnsGeracaoTermicaMotivoDespacho(
    o1: Pick<IOnsGeracaoTermicaMotivoDespacho, 'id'> | null,
    o2: Pick<IOnsGeracaoTermicaMotivoDespacho, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsGeracaoTermicaMotivoDespachoIdentifier(o1) === this.getOnsGeracaoTermicaMotivoDespachoIdentifier(o2)
      : o1 === o2;
  }

  addOnsGeracaoTermicaMotivoDespachoToCollectionIfMissing<Type extends Pick<IOnsGeracaoTermicaMotivoDespacho, 'id'>>(
    onsGeracaoTermicaMotivoDespachoCollection: Type[],
    ...onsGeracaoTermicaMotivoDespachosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsGeracaoTermicaMotivoDespachos: Type[] = onsGeracaoTermicaMotivoDespachosToCheck.filter(isPresent);
    if (onsGeracaoTermicaMotivoDespachos.length > 0) {
      const onsGeracaoTermicaMotivoDespachoCollectionIdentifiers = onsGeracaoTermicaMotivoDespachoCollection.map(
        onsGeracaoTermicaMotivoDespachoItem => this.getOnsGeracaoTermicaMotivoDespachoIdentifier(onsGeracaoTermicaMotivoDespachoItem),
      );
      const onsGeracaoTermicaMotivoDespachosToAdd = onsGeracaoTermicaMotivoDespachos.filter(onsGeracaoTermicaMotivoDespachoItem => {
        const onsGeracaoTermicaMotivoDespachoIdentifier =
          this.getOnsGeracaoTermicaMotivoDespachoIdentifier(onsGeracaoTermicaMotivoDespachoItem);
        if (onsGeracaoTermicaMotivoDespachoCollectionIdentifiers.includes(onsGeracaoTermicaMotivoDespachoIdentifier)) {
          return false;
        }
        onsGeracaoTermicaMotivoDespachoCollectionIdentifiers.push(onsGeracaoTermicaMotivoDespachoIdentifier);
        return true;
      });
      return [...onsGeracaoTermicaMotivoDespachosToAdd, ...onsGeracaoTermicaMotivoDespachoCollection];
    }
    return onsGeracaoTermicaMotivoDespachoCollection;
  }

  protected convertDateFromClient<
    T extends IOnsGeracaoTermicaMotivoDespacho | NewOnsGeracaoTermicaMotivoDespacho | PartialUpdateOnsGeracaoTermicaMotivoDespacho,
  >(onsGeracaoTermicaMotivoDespacho: T): RestOf<T> {
    return {
      ...onsGeracaoTermicaMotivoDespacho,
      dinInstante: onsGeracaoTermicaMotivoDespacho.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsGeracaoTermicaMotivoDespacho: RestOnsGeracaoTermicaMotivoDespacho,
  ): IOnsGeracaoTermicaMotivoDespacho {
    return {
      ...restOnsGeracaoTermicaMotivoDespacho,
      dinInstante: restOnsGeracaoTermicaMotivoDespacho.dinInstante ? dayjs(restOnsGeracaoTermicaMotivoDespacho.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsGeracaoTermicaMotivoDespacho>,
  ): HttpResponse<IOnsGeracaoTermicaMotivoDespacho> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsGeracaoTermicaMotivoDespacho[]>,
  ): HttpResponse<IOnsGeracaoTermicaMotivoDespacho[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
