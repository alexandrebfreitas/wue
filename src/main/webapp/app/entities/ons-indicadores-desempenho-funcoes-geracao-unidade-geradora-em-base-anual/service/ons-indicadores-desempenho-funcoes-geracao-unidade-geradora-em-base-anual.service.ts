import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import {
  IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
  NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
} from '../ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.model';

export type PartialUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual =
  Partial<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual> &
    Pick<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual, 'id'>;

export type EntityResponseType = HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>;
export type EntityArrayResponseType = HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[]>;

@Injectable({ providedIn: 'root' })
export class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals/_search',
  );

  create(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
  ): Observable<EntityResponseType> {
    return this.http.post<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>(
      this.resourceUrl,
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
      { observe: 'response' },
    );
  }

  update(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
  ): Observable<EntityResponseType> {
    return this.http.put<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>(
      `${this.resourceUrl}/${this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual)}`,
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
      { observe: 'response' },
    );
  }

  partialUpdate(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: PartialUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
  ): Observable<EntityResponseType> {
    return this.http.patch<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>(
      `${this.resourceUrl}/${this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual)}`,
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual>(`${this.resourceUrl}/${id}`, {
      observe: 'response',
    });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[]>(this.resourceUrl, {
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
        IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[]
      >(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        catchError(() =>
          scheduled([new HttpResponse<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual[]>()], asapScheduler),
        ),
      );
  }

  getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual: Pick<
      IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual,
      'id'
    >,
  ): number {
    return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.id;
  }

  compareOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(
    o1: Pick<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual, 'id'> | null,
    o2: Pick<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier(o1) ===
          this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier(o2)
      : o1 === o2;
  }

  addOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualToCollectionIfMissing<
    Type extends Pick<IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual, 'id'>,
  >(
    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection: Type[],
    ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals: Type[] =
      onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualsToCheck.filter(isPresent);
    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals.length > 0) {
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollectionIdentifiers =
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection.map(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualItem =>
            this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier(
              onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualItem,
            ),
        );
      const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualsToAdd =
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals.filter(
          onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualItem => {
            const onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier =
              this.getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualItem,
              );
            if (
              onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollectionIdentifiers.includes(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier,
              )
            ) {
              return false;
            }
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollectionIdentifiers.push(
              onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualIdentifier,
            );
            return true;
          },
        );
      return [
        ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualsToAdd,
        ...onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection,
      ];
    }
    return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualCollection;
  }
}
