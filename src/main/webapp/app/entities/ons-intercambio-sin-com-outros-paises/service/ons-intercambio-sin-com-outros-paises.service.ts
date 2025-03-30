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
import { IOnsIntercambioSinComOutrosPaises, NewOnsIntercambioSinComOutrosPaises } from '../ons-intercambio-sin-com-outros-paises.model';

export type PartialUpdateOnsIntercambioSinComOutrosPaises = Partial<IOnsIntercambioSinComOutrosPaises> &
  Pick<IOnsIntercambioSinComOutrosPaises, 'id'>;

type RestOf<T extends IOnsIntercambioSinComOutrosPaises | NewOnsIntercambioSinComOutrosPaises> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsIntercambioSinComOutrosPaises = RestOf<IOnsIntercambioSinComOutrosPaises>;

export type NewRestOnsIntercambioSinComOutrosPaises = RestOf<NewOnsIntercambioSinComOutrosPaises>;

export type PartialUpdateRestOnsIntercambioSinComOutrosPaises = RestOf<PartialUpdateOnsIntercambioSinComOutrosPaises>;

export type EntityResponseType = HttpResponse<IOnsIntercambioSinComOutrosPaises>;
export type EntityArrayResponseType = HttpResponse<IOnsIntercambioSinComOutrosPaises[]>;

@Injectable({ providedIn: 'root' })
export class OnsIntercambioSinComOutrosPaisesService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-intercambio-sin-com-outros-paises');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-intercambio-sin-com-outros-paises/_search');

  create(onsIntercambioSinComOutrosPaises: NewOnsIntercambioSinComOutrosPaises): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIntercambioSinComOutrosPaises);
    return this.http
      .post<RestOnsIntercambioSinComOutrosPaises>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsIntercambioSinComOutrosPaises: IOnsIntercambioSinComOutrosPaises): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIntercambioSinComOutrosPaises);
    return this.http
      .put<RestOnsIntercambioSinComOutrosPaises>(
        `${this.resourceUrl}/${this.getOnsIntercambioSinComOutrosPaisesIdentifier(onsIntercambioSinComOutrosPaises)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsIntercambioSinComOutrosPaises: PartialUpdateOnsIntercambioSinComOutrosPaises): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIntercambioSinComOutrosPaises);
    return this.http
      .patch<RestOnsIntercambioSinComOutrosPaises>(
        `${this.resourceUrl}/${this.getOnsIntercambioSinComOutrosPaisesIdentifier(onsIntercambioSinComOutrosPaises)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsIntercambioSinComOutrosPaises>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsIntercambioSinComOutrosPaises[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsIntercambioSinComOutrosPaises[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsIntercambioSinComOutrosPaises[]>()], asapScheduler)),
    );
  }

  getOnsIntercambioSinComOutrosPaisesIdentifier(onsIntercambioSinComOutrosPaises: Pick<IOnsIntercambioSinComOutrosPaises, 'id'>): number {
    return onsIntercambioSinComOutrosPaises.id;
  }

  compareOnsIntercambioSinComOutrosPaises(
    o1: Pick<IOnsIntercambioSinComOutrosPaises, 'id'> | null,
    o2: Pick<IOnsIntercambioSinComOutrosPaises, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsIntercambioSinComOutrosPaisesIdentifier(o1) === this.getOnsIntercambioSinComOutrosPaisesIdentifier(o2)
      : o1 === o2;
  }

  addOnsIntercambioSinComOutrosPaisesToCollectionIfMissing<Type extends Pick<IOnsIntercambioSinComOutrosPaises, 'id'>>(
    onsIntercambioSinComOutrosPaisesCollection: Type[],
    ...onsIntercambioSinComOutrosPaisesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsIntercambioSinComOutrosPaises: Type[] = onsIntercambioSinComOutrosPaisesToCheck.filter(isPresent);
    if (onsIntercambioSinComOutrosPaises.length > 0) {
      const onsIntercambioSinComOutrosPaisesCollectionIdentifiers = onsIntercambioSinComOutrosPaisesCollection.map(
        onsIntercambioSinComOutrosPaisesItem => this.getOnsIntercambioSinComOutrosPaisesIdentifier(onsIntercambioSinComOutrosPaisesItem),
      );
      const onsIntercambioSinComOutrosPaisesToAdd = onsIntercambioSinComOutrosPaises.filter(onsIntercambioSinComOutrosPaisesItem => {
        const onsIntercambioSinComOutrosPaisesIdentifier = this.getOnsIntercambioSinComOutrosPaisesIdentifier(
          onsIntercambioSinComOutrosPaisesItem,
        );
        if (onsIntercambioSinComOutrosPaisesCollectionIdentifiers.includes(onsIntercambioSinComOutrosPaisesIdentifier)) {
          return false;
        }
        onsIntercambioSinComOutrosPaisesCollectionIdentifiers.push(onsIntercambioSinComOutrosPaisesIdentifier);
        return true;
      });
      return [...onsIntercambioSinComOutrosPaisesToAdd, ...onsIntercambioSinComOutrosPaisesCollection];
    }
    return onsIntercambioSinComOutrosPaisesCollection;
  }

  protected convertDateFromClient<
    T extends IOnsIntercambioSinComOutrosPaises | NewOnsIntercambioSinComOutrosPaises | PartialUpdateOnsIntercambioSinComOutrosPaises,
  >(onsIntercambioSinComOutrosPaises: T): RestOf<T> {
    return {
      ...onsIntercambioSinComOutrosPaises,
      dinInstante: onsIntercambioSinComOutrosPaises.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsIntercambioSinComOutrosPaises: RestOnsIntercambioSinComOutrosPaises,
  ): IOnsIntercambioSinComOutrosPaises {
    return {
      ...restOnsIntercambioSinComOutrosPaises,
      dinInstante: restOnsIntercambioSinComOutrosPaises.dinInstante ? dayjs(restOnsIntercambioSinComOutrosPaises.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsIntercambioSinComOutrosPaises>,
  ): HttpResponse<IOnsIntercambioSinComOutrosPaises> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsIntercambioSinComOutrosPaises[]>,
  ): HttpResponse<IOnsIntercambioSinComOutrosPaises[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
