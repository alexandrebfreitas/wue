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
  IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
} from '../ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.model';

export type PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
  Partial<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs> &
    Pick<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs, 'id'>;

type RestOf<
  T extends
    | IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
    | NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
> = Omit<T, 'dinReferencia'> & {
  dinReferencia?: string | null;
};

export type RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
  RestOf<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>;

export type NewRestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
  RestOf<NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>;

export type PartialUpdateRestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs =
  RestOf<PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>;

export type EntityResponseType = HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>;
export type EntityArrayResponseType = HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[]>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs/_search',
  );

  create(
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs);
    return this.http
      .post<RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs);
    return this.http
      .put<RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>(
        `${this.resourceUrl}/${this.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs);
    return this.http
      .patch<RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>(
        `${this.resourceUrl}/${this.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[]
      >(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[]
      >(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() =>
          scheduled([new HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[]>()], asapScheduler),
        ),
      );
  }

  getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier(
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: Pick<
      IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
      'id'
    >,
  ): number {
    return onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.id;
  }

  compareOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(
    o1: Pick<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs, 'id'> | null,
    o2: Pick<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier(o1) ===
          this.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier(o2)
      : o1 === o2;
  }

  addOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCollectionIfMissing<
    Type extends Pick<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs, 'id'>,
  >(
    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection: Type[],
    ...onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: Type[] =
      onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToCheck.filter(isPresent);
    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.length > 0) {
      const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollectionIdentifiers =
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection.map(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsItem =>
            this.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier(
              onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsItem,
            ),
        );
      const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToAdd =
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.filter(
          onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsItem => {
            const onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier =
              this.getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsItem,
              );
            if (
              onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollectionIdentifiers.includes(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier,
              )
            ) {
              return false;
            }
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollectionIdentifiers.push(
              onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsIdentifier,
            );
            return true;
          },
        );
      return [
        ...onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsToAdd,
        ...onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection,
      ];
    }
    return onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
      | NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
      | PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  >(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: T): RestOf<T> {
    return {
      ...onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
      dinReferencia: onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.dinReferencia?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  ): IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs {
    return {
      ...restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
      dinReferencia: restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.dinReferencia
        ? dayjs(restOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.dinReferencia)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs>,
  ): HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[]>,
  ): HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
