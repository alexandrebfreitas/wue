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
  IOnsDescontinuadoPrecipitacaoDiariaObservada,
  NewOnsDescontinuadoPrecipitacaoDiariaObservada,
} from '../ons-descontinuado-precipitacao-diaria-observada.model';

export type PartialUpdateOnsDescontinuadoPrecipitacaoDiariaObservada = Partial<IOnsDescontinuadoPrecipitacaoDiariaObservada> &
  Pick<IOnsDescontinuadoPrecipitacaoDiariaObservada, 'id'>;

type RestOf<T extends IOnsDescontinuadoPrecipitacaoDiariaObservada | NewOnsDescontinuadoPrecipitacaoDiariaObservada> = Omit<
  T,
  'datObservada'
> & {
  datObservada?: string | null;
};

export type RestOnsDescontinuadoPrecipitacaoDiariaObservada = RestOf<IOnsDescontinuadoPrecipitacaoDiariaObservada>;

export type NewRestOnsDescontinuadoPrecipitacaoDiariaObservada = RestOf<NewOnsDescontinuadoPrecipitacaoDiariaObservada>;

export type PartialUpdateRestOnsDescontinuadoPrecipitacaoDiariaObservada = RestOf<PartialUpdateOnsDescontinuadoPrecipitacaoDiariaObservada>;

export type EntityResponseType = HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada>;
export type EntityArrayResponseType = HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada[]>;

@Injectable({ providedIn: 'root' })
export class OnsDescontinuadoPrecipitacaoDiariaObservadaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-descontinuado-precipitacao-diaria-observadas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-descontinuado-precipitacao-diaria-observadas/_search',
  );

  create(onsDescontinuadoPrecipitacaoDiariaObservada: NewOnsDescontinuadoPrecipitacaoDiariaObservada): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDescontinuadoPrecipitacaoDiariaObservada);
    return this.http
      .post<RestOnsDescontinuadoPrecipitacaoDiariaObservada>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsDescontinuadoPrecipitacaoDiariaObservada: IOnsDescontinuadoPrecipitacaoDiariaObservada): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDescontinuadoPrecipitacaoDiariaObservada);
    return this.http
      .put<RestOnsDescontinuadoPrecipitacaoDiariaObservada>(
        `${this.resourceUrl}/${this.getOnsDescontinuadoPrecipitacaoDiariaObservadaIdentifier(onsDescontinuadoPrecipitacaoDiariaObservada)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDescontinuadoPrecipitacaoDiariaObservada: PartialUpdateOnsDescontinuadoPrecipitacaoDiariaObservada,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDescontinuadoPrecipitacaoDiariaObservada);
    return this.http
      .patch<RestOnsDescontinuadoPrecipitacaoDiariaObservada>(
        `${this.resourceUrl}/${this.getOnsDescontinuadoPrecipitacaoDiariaObservadaIdentifier(onsDescontinuadoPrecipitacaoDiariaObservada)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDescontinuadoPrecipitacaoDiariaObservada>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDescontinuadoPrecipitacaoDiariaObservada[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDescontinuadoPrecipitacaoDiariaObservada[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada[]>()], asapScheduler)),
      );
  }

  getOnsDescontinuadoPrecipitacaoDiariaObservadaIdentifier(
    onsDescontinuadoPrecipitacaoDiariaObservada: Pick<IOnsDescontinuadoPrecipitacaoDiariaObservada, 'id'>,
  ): number {
    return onsDescontinuadoPrecipitacaoDiariaObservada.id;
  }

  compareOnsDescontinuadoPrecipitacaoDiariaObservada(
    o1: Pick<IOnsDescontinuadoPrecipitacaoDiariaObservada, 'id'> | null,
    o2: Pick<IOnsDescontinuadoPrecipitacaoDiariaObservada, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDescontinuadoPrecipitacaoDiariaObservadaIdentifier(o1) ===
          this.getOnsDescontinuadoPrecipitacaoDiariaObservadaIdentifier(o2)
      : o1 === o2;
  }

  addOnsDescontinuadoPrecipitacaoDiariaObservadaToCollectionIfMissing<
    Type extends Pick<IOnsDescontinuadoPrecipitacaoDiariaObservada, 'id'>,
  >(
    onsDescontinuadoPrecipitacaoDiariaObservadaCollection: Type[],
    ...onsDescontinuadoPrecipitacaoDiariaObservadasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDescontinuadoPrecipitacaoDiariaObservadas: Type[] = onsDescontinuadoPrecipitacaoDiariaObservadasToCheck.filter(isPresent);
    if (onsDescontinuadoPrecipitacaoDiariaObservadas.length > 0) {
      const onsDescontinuadoPrecipitacaoDiariaObservadaCollectionIdentifiers = onsDescontinuadoPrecipitacaoDiariaObservadaCollection.map(
        onsDescontinuadoPrecipitacaoDiariaObservadaItem =>
          this.getOnsDescontinuadoPrecipitacaoDiariaObservadaIdentifier(onsDescontinuadoPrecipitacaoDiariaObservadaItem),
      );
      const onsDescontinuadoPrecipitacaoDiariaObservadasToAdd = onsDescontinuadoPrecipitacaoDiariaObservadas.filter(
        onsDescontinuadoPrecipitacaoDiariaObservadaItem => {
          const onsDescontinuadoPrecipitacaoDiariaObservadaIdentifier = this.getOnsDescontinuadoPrecipitacaoDiariaObservadaIdentifier(
            onsDescontinuadoPrecipitacaoDiariaObservadaItem,
          );
          if (
            onsDescontinuadoPrecipitacaoDiariaObservadaCollectionIdentifiers.includes(onsDescontinuadoPrecipitacaoDiariaObservadaIdentifier)
          ) {
            return false;
          }
          onsDescontinuadoPrecipitacaoDiariaObservadaCollectionIdentifiers.push(onsDescontinuadoPrecipitacaoDiariaObservadaIdentifier);
          return true;
        },
      );
      return [...onsDescontinuadoPrecipitacaoDiariaObservadasToAdd, ...onsDescontinuadoPrecipitacaoDiariaObservadaCollection];
    }
    return onsDescontinuadoPrecipitacaoDiariaObservadaCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDescontinuadoPrecipitacaoDiariaObservada
      | NewOnsDescontinuadoPrecipitacaoDiariaObservada
      | PartialUpdateOnsDescontinuadoPrecipitacaoDiariaObservada,
  >(onsDescontinuadoPrecipitacaoDiariaObservada: T): RestOf<T> {
    return {
      ...onsDescontinuadoPrecipitacaoDiariaObservada,
      datObservada: onsDescontinuadoPrecipitacaoDiariaObservada.datObservada?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDescontinuadoPrecipitacaoDiariaObservada: RestOnsDescontinuadoPrecipitacaoDiariaObservada,
  ): IOnsDescontinuadoPrecipitacaoDiariaObservada {
    return {
      ...restOnsDescontinuadoPrecipitacaoDiariaObservada,
      datObservada: restOnsDescontinuadoPrecipitacaoDiariaObservada.datObservada
        ? dayjs(restOnsDescontinuadoPrecipitacaoDiariaObservada.datObservada)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDescontinuadoPrecipitacaoDiariaObservada>,
  ): HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDescontinuadoPrecipitacaoDiariaObservada[]>,
  ): HttpResponse<IOnsDescontinuadoPrecipitacaoDiariaObservada[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
