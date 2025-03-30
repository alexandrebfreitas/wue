import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import {
  IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
  NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
} from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.model';

export type PartialUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal =
  Partial<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal> &
    Pick<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal, 'id'>;

export type EntityResponseType = HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>;
export type EntityArrayResponseType = HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[]>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals/_search',
  );

  create(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
  ): Observable<EntityResponseType> {
    return this.http.post<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>(
      this.resourceUrl,
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
      { observe: 'response' },
    );
  }

  update(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
  ): Observable<EntityResponseType> {
    return this.http.put<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>(
      `${this.resourceUrl}/${this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal)}`,
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
      { observe: 'response' },
    );
  }

  partialUpdate(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: PartialUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
  ): Observable<EntityResponseType> {
    return this.http.patch<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>(
      `${this.resourceUrl}/${this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal)}`,
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal>(`${this.resourceUrl}/${id}`, {
      observe: 'response',
    });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[]>(this.resourceUrl, {
      params: options,
      observe: 'response',
    });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[]
      >(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        catchError(() =>
          scheduled([new HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal[]>()], asapScheduler),
        ),
      );
  }

  getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal: Pick<
      IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
      'id'
    >,
  ): number {
    return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.id;
  }

  compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(
    o1: Pick<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal, 'id'> | null,
    o2: Pick<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier(o1) ===
          this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier(o2)
      : o1 === o2;
  }

  addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalToCollectionIfMissing<
    Type extends Pick<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal, 'id'>,
  >(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection: Type[],
    ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals: Type[] =
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalsToCheck.filter(isPresent);
    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals.length > 0) {
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollectionIdentifiers =
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection.map(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalItem =>
            this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier(
              onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalItem,
            ),
        );
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalsToAdd =
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals.filter(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalItem => {
            const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier =
              this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalItem,
              );
            if (
              onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollectionIdentifiers.includes(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier,
              )
            ) {
              return false;
            }
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollectionIdentifiers.push(
              onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalIdentifier,
            );
            return true;
          },
        );
      return [
        ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalsToAdd,
        ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection,
      ];
    }
    return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalCollection;
  }
}
