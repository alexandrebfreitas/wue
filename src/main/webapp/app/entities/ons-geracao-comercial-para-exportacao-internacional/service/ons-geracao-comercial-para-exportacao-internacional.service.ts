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
  IOnsGeracaoComercialParaExportacaoInternacional,
  NewOnsGeracaoComercialParaExportacaoInternacional,
} from '../ons-geracao-comercial-para-exportacao-internacional.model';

export type PartialUpdateOnsGeracaoComercialParaExportacaoInternacional = Partial<IOnsGeracaoComercialParaExportacaoInternacional> &
  Pick<IOnsGeracaoComercialParaExportacaoInternacional, 'id'>;

type RestOf<T extends IOnsGeracaoComercialParaExportacaoInternacional | NewOnsGeracaoComercialParaExportacaoInternacional> = Omit<
  T,
  'dinInstante'
> & {
  dinInstante?: string | null;
};

export type RestOnsGeracaoComercialParaExportacaoInternacional = RestOf<IOnsGeracaoComercialParaExportacaoInternacional>;

export type NewRestOnsGeracaoComercialParaExportacaoInternacional = RestOf<NewOnsGeracaoComercialParaExportacaoInternacional>;

export type PartialUpdateRestOnsGeracaoComercialParaExportacaoInternacional =
  RestOf<PartialUpdateOnsGeracaoComercialParaExportacaoInternacional>;

export type EntityResponseType = HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional>;
export type EntityArrayResponseType = HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional[]>;

@Injectable({ providedIn: 'root' })
export class OnsGeracaoComercialParaExportacaoInternacionalService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-geracao-comercial-para-exportacao-internacionals');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-geracao-comercial-para-exportacao-internacionals/_search',
  );

  create(
    onsGeracaoComercialParaExportacaoInternacional: NewOnsGeracaoComercialParaExportacaoInternacional,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsGeracaoComercialParaExportacaoInternacional);
    return this.http
      .post<RestOnsGeracaoComercialParaExportacaoInternacional>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsGeracaoComercialParaExportacaoInternacional: IOnsGeracaoComercialParaExportacaoInternacional): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsGeracaoComercialParaExportacaoInternacional);
    return this.http
      .put<RestOnsGeracaoComercialParaExportacaoInternacional>(
        `${this.resourceUrl}/${this.getOnsGeracaoComercialParaExportacaoInternacionalIdentifier(onsGeracaoComercialParaExportacaoInternacional)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsGeracaoComercialParaExportacaoInternacional: PartialUpdateOnsGeracaoComercialParaExportacaoInternacional,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsGeracaoComercialParaExportacaoInternacional);
    return this.http
      .patch<RestOnsGeracaoComercialParaExportacaoInternacional>(
        `${this.resourceUrl}/${this.getOnsGeracaoComercialParaExportacaoInternacionalIdentifier(onsGeracaoComercialParaExportacaoInternacional)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsGeracaoComercialParaExportacaoInternacional>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsGeracaoComercialParaExportacaoInternacional[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsGeracaoComercialParaExportacaoInternacional[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional[]>()], asapScheduler)),
      );
  }

  getOnsGeracaoComercialParaExportacaoInternacionalIdentifier(
    onsGeracaoComercialParaExportacaoInternacional: Pick<IOnsGeracaoComercialParaExportacaoInternacional, 'id'>,
  ): number {
    return onsGeracaoComercialParaExportacaoInternacional.id;
  }

  compareOnsGeracaoComercialParaExportacaoInternacional(
    o1: Pick<IOnsGeracaoComercialParaExportacaoInternacional, 'id'> | null,
    o2: Pick<IOnsGeracaoComercialParaExportacaoInternacional, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsGeracaoComercialParaExportacaoInternacionalIdentifier(o1) ===
          this.getOnsGeracaoComercialParaExportacaoInternacionalIdentifier(o2)
      : o1 === o2;
  }

  addOnsGeracaoComercialParaExportacaoInternacionalToCollectionIfMissing<
    Type extends Pick<IOnsGeracaoComercialParaExportacaoInternacional, 'id'>,
  >(
    onsGeracaoComercialParaExportacaoInternacionalCollection: Type[],
    ...onsGeracaoComercialParaExportacaoInternacionalsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsGeracaoComercialParaExportacaoInternacionals: Type[] =
      onsGeracaoComercialParaExportacaoInternacionalsToCheck.filter(isPresent);
    if (onsGeracaoComercialParaExportacaoInternacionals.length > 0) {
      const onsGeracaoComercialParaExportacaoInternacionalCollectionIdentifiers =
        onsGeracaoComercialParaExportacaoInternacionalCollection.map(onsGeracaoComercialParaExportacaoInternacionalItem =>
          this.getOnsGeracaoComercialParaExportacaoInternacionalIdentifier(onsGeracaoComercialParaExportacaoInternacionalItem),
        );
      const onsGeracaoComercialParaExportacaoInternacionalsToAdd = onsGeracaoComercialParaExportacaoInternacionals.filter(
        onsGeracaoComercialParaExportacaoInternacionalItem => {
          const onsGeracaoComercialParaExportacaoInternacionalIdentifier = this.getOnsGeracaoComercialParaExportacaoInternacionalIdentifier(
            onsGeracaoComercialParaExportacaoInternacionalItem,
          );
          if (
            onsGeracaoComercialParaExportacaoInternacionalCollectionIdentifiers.includes(
              onsGeracaoComercialParaExportacaoInternacionalIdentifier,
            )
          ) {
            return false;
          }
          onsGeracaoComercialParaExportacaoInternacionalCollectionIdentifiers.push(
            onsGeracaoComercialParaExportacaoInternacionalIdentifier,
          );
          return true;
        },
      );
      return [...onsGeracaoComercialParaExportacaoInternacionalsToAdd, ...onsGeracaoComercialParaExportacaoInternacionalCollection];
    }
    return onsGeracaoComercialParaExportacaoInternacionalCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsGeracaoComercialParaExportacaoInternacional
      | NewOnsGeracaoComercialParaExportacaoInternacional
      | PartialUpdateOnsGeracaoComercialParaExportacaoInternacional,
  >(onsGeracaoComercialParaExportacaoInternacional: T): RestOf<T> {
    return {
      ...onsGeracaoComercialParaExportacaoInternacional,
      dinInstante: onsGeracaoComercialParaExportacaoInternacional.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsGeracaoComercialParaExportacaoInternacional: RestOnsGeracaoComercialParaExportacaoInternacional,
  ): IOnsGeracaoComercialParaExportacaoInternacional {
    return {
      ...restOnsGeracaoComercialParaExportacaoInternacional,
      dinInstante: restOnsGeracaoComercialParaExportacaoInternacional.dinInstante
        ? dayjs(restOnsGeracaoComercialParaExportacaoInternacional.dinInstante)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsGeracaoComercialParaExportacaoInternacional>,
  ): HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsGeracaoComercialParaExportacaoInternacional[]>,
  ): HttpResponse<IOnsGeracaoComercialParaExportacaoInternacional[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
