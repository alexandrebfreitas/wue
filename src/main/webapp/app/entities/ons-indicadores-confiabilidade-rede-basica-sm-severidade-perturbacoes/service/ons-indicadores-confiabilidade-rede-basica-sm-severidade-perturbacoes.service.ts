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
  IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
} from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';

export type PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
  Partial<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes> &
    Pick<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes, 'id'>;

type RestOf<
  T extends
    | IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
    | NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
> = Omit<T, 'dinReferencia'> & {
  dinReferencia?: string | null;
};

export type RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
  RestOf<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>;

export type NewRestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
  RestOf<NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>;

export type PartialUpdateRestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
  RestOf<PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>;

export type EntityResponseType = HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>;
export type EntityArrayResponseType = HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[]>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes/_search',
  );

  create(
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes);
    return this.http
      .post<RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes);
    return this.http
      .put<RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>(
        `${this.resourceUrl}/${this.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes);
    return this.http
      .patch<RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>(
        `${this.resourceUrl}/${this.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[]
      >(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[]>()], asapScheduler)),
      );
  }

  getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier(
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: Pick<
      IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
      'id'
    >,
  ): number {
    return onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.id;
  }

  compareOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(
    o1: Pick<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes, 'id'> | null,
    o2: Pick<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier(o1) ===
          this.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier(o2)
      : o1 === o2;
  }

  addOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCollectionIfMissing<
    Type extends Pick<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes, 'id'>,
  >(
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection: Type[],
    ...onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: Type[] =
      onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToCheck.filter(isPresent);
    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.length > 0) {
      const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollectionIdentifiers =
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection.map(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesItem =>
            this.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier(
              onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesItem,
            ),
        );
      const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToAdd =
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.filter(
          onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesItem => {
            const onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier =
              this.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesItem,
              );
            if (
              onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollectionIdentifiers.includes(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier,
              )
            ) {
              return false;
            }
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollectionIdentifiers.push(
              onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier,
            );
            return true;
          },
        );
      return [
        ...onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesToAdd,
        ...onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection,
      ];
    }
    return onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
      | NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
      | PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  >(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: T): RestOf<T> {
    return {
      ...onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
      dinReferencia: onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.dinReferencia?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  ): IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes {
    return {
      ...restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
      dinReferencia: restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.dinReferencia
        ? dayjs(restOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.dinReferencia)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes>,
  ): HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[]>,
  ): HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
