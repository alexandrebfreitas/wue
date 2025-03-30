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
import { IOnsReservatorios, NewOnsReservatorios } from '../ons-reservatorios.model';

export type PartialUpdateOnsReservatorios = Partial<IOnsReservatorios> & Pick<IOnsReservatorios, 'id'>;

type RestOf<T extends IOnsReservatorios | NewOnsReservatorios> = Omit<T, 'datEntrada'> & {
  datEntrada?: string | null;
};

export type RestOnsReservatorios = RestOf<IOnsReservatorios>;

export type NewRestOnsReservatorios = RestOf<NewOnsReservatorios>;

export type PartialUpdateRestOnsReservatorios = RestOf<PartialUpdateOnsReservatorios>;

export type EntityResponseType = HttpResponse<IOnsReservatorios>;
export type EntityArrayResponseType = HttpResponse<IOnsReservatorios[]>;

@Injectable({ providedIn: 'root' })
export class OnsReservatoriosService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-reservatorios');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-reservatorios/_search');

  create(onsReservatorios: NewOnsReservatorios): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsReservatorios);
    return this.http
      .post<RestOnsReservatorios>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsReservatorios: IOnsReservatorios): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsReservatorios);
    return this.http
      .put<RestOnsReservatorios>(`${this.resourceUrl}/${this.getOnsReservatoriosIdentifier(onsReservatorios)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsReservatorios: PartialUpdateOnsReservatorios): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsReservatorios);
    return this.http
      .patch<RestOnsReservatorios>(`${this.resourceUrl}/${this.getOnsReservatoriosIdentifier(onsReservatorios)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsReservatorios>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsReservatorios[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsReservatorios[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsReservatorios[]>()], asapScheduler)),
    );
  }

  getOnsReservatoriosIdentifier(onsReservatorios: Pick<IOnsReservatorios, 'id'>): number {
    return onsReservatorios.id;
  }

  compareOnsReservatorios(o1: Pick<IOnsReservatorios, 'id'> | null, o2: Pick<IOnsReservatorios, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsReservatoriosIdentifier(o1) === this.getOnsReservatoriosIdentifier(o2) : o1 === o2;
  }

  addOnsReservatoriosToCollectionIfMissing<Type extends Pick<IOnsReservatorios, 'id'>>(
    onsReservatoriosCollection: Type[],
    ...onsReservatoriosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsReservatorios: Type[] = onsReservatoriosToCheck.filter(isPresent);
    if (onsReservatorios.length > 0) {
      const onsReservatoriosCollectionIdentifiers = onsReservatoriosCollection.map(onsReservatoriosItem =>
        this.getOnsReservatoriosIdentifier(onsReservatoriosItem),
      );
      const onsReservatoriosToAdd = onsReservatorios.filter(onsReservatoriosItem => {
        const onsReservatoriosIdentifier = this.getOnsReservatoriosIdentifier(onsReservatoriosItem);
        if (onsReservatoriosCollectionIdentifiers.includes(onsReservatoriosIdentifier)) {
          return false;
        }
        onsReservatoriosCollectionIdentifiers.push(onsReservatoriosIdentifier);
        return true;
      });
      return [...onsReservatoriosToAdd, ...onsReservatoriosCollection];
    }
    return onsReservatoriosCollection;
  }

  protected convertDateFromClient<T extends IOnsReservatorios | NewOnsReservatorios | PartialUpdateOnsReservatorios>(
    onsReservatorios: T,
  ): RestOf<T> {
    return {
      ...onsReservatorios,
      datEntrada: onsReservatorios.datEntrada?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsReservatorios: RestOnsReservatorios): IOnsReservatorios {
    return {
      ...restOnsReservatorios,
      datEntrada: restOnsReservatorios.datEntrada ? dayjs(restOnsReservatorios.datEntrada) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsReservatorios>): HttpResponse<IOnsReservatorios> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsReservatorios[]>): HttpResponse<IOnsReservatorios[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
