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
  IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
} from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';

export type PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
  Partial<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes> &
    Pick<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes, 'id'>;

type RestOf<
  T extends
    | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
    | NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
> = Omit<T, 'dinReferencia'> & {
  dinReferencia?: string | null;
};

export type RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
  RestOf<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>;

export type NewRestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
  RestOf<NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>;

export type PartialUpdateRestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes =
  RestOf<PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>;

export type EntityResponseType = HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>;
export type EntityArrayResponseType = HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[]>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes/_search',
  );

  create(
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes);
    return this.http
      .post<RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes);
    return this.http
      .put<RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>(
        `${this.resourceUrl}/${this.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes);
    return this.http
      .patch<RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>(
        `${this.resourceUrl}/${this.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>(`${this.resourceUrl}/${id}`, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[]
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
        RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[]
      >(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() =>
          scheduled([new HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[]>()], asapScheduler),
        ),
      );
  }

  getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier(
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: Pick<
      IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
      'id'
    >,
  ): number {
    return onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.id;
  }

  compareOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(
    o1: Pick<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes, 'id'> | null,
    o2: Pick<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier(o1) ===
          this.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier(o2)
      : o1 === o2;
  }

  addOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCollectionIfMissing<
    Type extends Pick<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes, 'id'>,
  >(
    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection: Type[],
    ...onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: Type[] =
      onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToCheck.filter(isPresent);
    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.length > 0) {
      const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollectionIdentifiers =
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection.map(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesItem =>
            this.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier(
              onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesItem,
            ),
        );
      const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToAdd =
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.filter(
          onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesItem => {
            const onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier =
              this.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesItem,
              );
            if (
              onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollectionIdentifiers.includes(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier,
              )
            ) {
              return false;
            }
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollectionIdentifiers.push(
              onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier,
            );
            return true;
          },
        );
      return [
        ...onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesToAdd,
        ...onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection,
      ];
    }
    return onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
      | NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
      | PartialUpdateOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  >(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: T): RestOf<T> {
    return {
      ...onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
      dinReferencia: onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.dinReferencia?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes: RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  ): IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes {
    return {
      ...restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
      dinReferencia: restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.dinReferencia
        ? dayjs(restOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.dinReferencia)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes>,
  ): HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[]>,
  ): HttpResponse<IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
