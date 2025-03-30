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
  IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
  NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
} from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';

export type PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = Partial<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq> &
  Pick<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq, 'id'>;

type RestOf<T extends IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq | NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq> = Omit<
  T,
  'dinReferencia'
> & {
  dinReferencia?: string | null;
};

export type RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = RestOf<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>;

export type NewRestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = RestOf<NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>;

export type PartialUpdateRestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq =
  RestOf<PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>;

export type EntityResponseType = HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>;
export type EntityArrayResponseType = HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[]>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-indicadores-confiabilidade-rede-basica-dreq-freqs');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-confiabilidade-rede-basica-dreq-freqs/_search',
  );

  create(
    onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq);
    return this.http
      .post<RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq);
    return this.http
      .put<RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>(
        `${this.resourceUrl}/${this.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq);
    return this.http
      .patch<RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>(
        `${this.resourceUrl}/${this.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[]>()], asapScheduler)),
      );
  }

  getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier(
    onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: Pick<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq, 'id'>,
  ): number {
    return onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.id;
  }

  compareOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(
    o1: Pick<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq, 'id'> | null,
    o2: Pick<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier(o1) ===
          this.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier(o2)
      : o1 === o2;
  }

  addOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqToCollectionIfMissing<
    Type extends Pick<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq, 'id'>,
  >(
    onsIndicadoresConfiabilidadeRedeBasicaDreqFreqCollection: Type[],
    ...onsIndicadoresConfiabilidadeRedeBasicaDreqFreqsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsIndicadoresConfiabilidadeRedeBasicaDreqFreqs: Type[] =
      onsIndicadoresConfiabilidadeRedeBasicaDreqFreqsToCheck.filter(isPresent);
    if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqs.length > 0) {
      const onsIndicadoresConfiabilidadeRedeBasicaDreqFreqCollectionIdentifiers =
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqCollection.map(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqItem =>
          this.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqItem),
        );
      const onsIndicadoresConfiabilidadeRedeBasicaDreqFreqsToAdd = onsIndicadoresConfiabilidadeRedeBasicaDreqFreqs.filter(
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqItem => {
          const onsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier = this.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier(
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqItem,
          );
          if (
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqCollectionIdentifiers.includes(
              onsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier,
            )
          ) {
            return false;
          }
          onsIndicadoresConfiabilidadeRedeBasicaDreqFreqCollectionIdentifiers.push(
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier,
          );
          return true;
        },
      );
      return [...onsIndicadoresConfiabilidadeRedeBasicaDreqFreqsToAdd, ...onsIndicadoresConfiabilidadeRedeBasicaDreqFreqCollection];
    }
    return onsIndicadoresConfiabilidadeRedeBasicaDreqFreqCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq
      | NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq
      | PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
  >(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: T): RestOf<T> {
    return {
      ...onsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
      dinReferencia: onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.dinReferencia?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq: RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
  ): IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq {
    return {
      ...restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
      dinReferencia: restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq.dinReferencia
        ? dayjs(restOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq.dinReferencia)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq>,
  ): HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[]>,
  ): HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
