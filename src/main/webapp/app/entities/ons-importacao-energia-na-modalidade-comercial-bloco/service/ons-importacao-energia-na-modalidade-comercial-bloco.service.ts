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
  IOnsImportacaoEnergiaNaModalidadeComercialBloco,
  NewOnsImportacaoEnergiaNaModalidadeComercialBloco,
} from '../ons-importacao-energia-na-modalidade-comercial-bloco.model';

export type PartialUpdateOnsImportacaoEnergiaNaModalidadeComercialBloco = Partial<IOnsImportacaoEnergiaNaModalidadeComercialBloco> &
  Pick<IOnsImportacaoEnergiaNaModalidadeComercialBloco, 'id'>;

type RestOf<T extends IOnsImportacaoEnergiaNaModalidadeComercialBloco | NewOnsImportacaoEnergiaNaModalidadeComercialBloco> = Omit<
  T,
  'dinInstante'
> & {
  dinInstante?: string | null;
};

export type RestOnsImportacaoEnergiaNaModalidadeComercialBloco = RestOf<IOnsImportacaoEnergiaNaModalidadeComercialBloco>;

export type NewRestOnsImportacaoEnergiaNaModalidadeComercialBloco = RestOf<NewOnsImportacaoEnergiaNaModalidadeComercialBloco>;

export type PartialUpdateRestOnsImportacaoEnergiaNaModalidadeComercialBloco =
  RestOf<PartialUpdateOnsImportacaoEnergiaNaModalidadeComercialBloco>;

export type EntityResponseType = HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco>;
export type EntityArrayResponseType = HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco[]>;

@Injectable({ providedIn: 'root' })
export class OnsImportacaoEnergiaNaModalidadeComercialBlocoService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-importacao-energia-na-modalidade-comercial-blocos');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-importacao-energia-na-modalidade-comercial-blocos/_search',
  );

  create(
    onsImportacaoEnergiaNaModalidadeComercialBloco: NewOnsImportacaoEnergiaNaModalidadeComercialBloco,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsImportacaoEnergiaNaModalidadeComercialBloco);
    return this.http
      .post<RestOnsImportacaoEnergiaNaModalidadeComercialBloco>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsImportacaoEnergiaNaModalidadeComercialBloco);
    return this.http
      .put<RestOnsImportacaoEnergiaNaModalidadeComercialBloco>(
        `${this.resourceUrl}/${this.getOnsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier(onsImportacaoEnergiaNaModalidadeComercialBloco)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsImportacaoEnergiaNaModalidadeComercialBloco: PartialUpdateOnsImportacaoEnergiaNaModalidadeComercialBloco,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsImportacaoEnergiaNaModalidadeComercialBloco);
    return this.http
      .patch<RestOnsImportacaoEnergiaNaModalidadeComercialBloco>(
        `${this.resourceUrl}/${this.getOnsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier(onsImportacaoEnergiaNaModalidadeComercialBloco)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsImportacaoEnergiaNaModalidadeComercialBloco>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsImportacaoEnergiaNaModalidadeComercialBloco[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsImportacaoEnergiaNaModalidadeComercialBloco[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco[]>()], asapScheduler)),
      );
  }

  getOnsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier(
    onsImportacaoEnergiaNaModalidadeComercialBloco: Pick<IOnsImportacaoEnergiaNaModalidadeComercialBloco, 'id'>,
  ): number {
    return onsImportacaoEnergiaNaModalidadeComercialBloco.id;
  }

  compareOnsImportacaoEnergiaNaModalidadeComercialBloco(
    o1: Pick<IOnsImportacaoEnergiaNaModalidadeComercialBloco, 'id'> | null,
    o2: Pick<IOnsImportacaoEnergiaNaModalidadeComercialBloco, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier(o1) ===
          this.getOnsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier(o2)
      : o1 === o2;
  }

  addOnsImportacaoEnergiaNaModalidadeComercialBlocoToCollectionIfMissing<
    Type extends Pick<IOnsImportacaoEnergiaNaModalidadeComercialBloco, 'id'>,
  >(
    onsImportacaoEnergiaNaModalidadeComercialBlocoCollection: Type[],
    ...onsImportacaoEnergiaNaModalidadeComercialBlocosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsImportacaoEnergiaNaModalidadeComercialBlocos: Type[] =
      onsImportacaoEnergiaNaModalidadeComercialBlocosToCheck.filter(isPresent);
    if (onsImportacaoEnergiaNaModalidadeComercialBlocos.length > 0) {
      const onsImportacaoEnergiaNaModalidadeComercialBlocoCollectionIdentifiers =
        onsImportacaoEnergiaNaModalidadeComercialBlocoCollection.map(onsImportacaoEnergiaNaModalidadeComercialBlocoItem =>
          this.getOnsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier(onsImportacaoEnergiaNaModalidadeComercialBlocoItem),
        );
      const onsImportacaoEnergiaNaModalidadeComercialBlocosToAdd = onsImportacaoEnergiaNaModalidadeComercialBlocos.filter(
        onsImportacaoEnergiaNaModalidadeComercialBlocoItem => {
          const onsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier = this.getOnsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier(
            onsImportacaoEnergiaNaModalidadeComercialBlocoItem,
          );
          if (
            onsImportacaoEnergiaNaModalidadeComercialBlocoCollectionIdentifiers.includes(
              onsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier,
            )
          ) {
            return false;
          }
          onsImportacaoEnergiaNaModalidadeComercialBlocoCollectionIdentifiers.push(
            onsImportacaoEnergiaNaModalidadeComercialBlocoIdentifier,
          );
          return true;
        },
      );
      return [...onsImportacaoEnergiaNaModalidadeComercialBlocosToAdd, ...onsImportacaoEnergiaNaModalidadeComercialBlocoCollection];
    }
    return onsImportacaoEnergiaNaModalidadeComercialBlocoCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsImportacaoEnergiaNaModalidadeComercialBloco
      | NewOnsImportacaoEnergiaNaModalidadeComercialBloco
      | PartialUpdateOnsImportacaoEnergiaNaModalidadeComercialBloco,
  >(onsImportacaoEnergiaNaModalidadeComercialBloco: T): RestOf<T> {
    return {
      ...onsImportacaoEnergiaNaModalidadeComercialBloco,
      dinInstante: onsImportacaoEnergiaNaModalidadeComercialBloco.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsImportacaoEnergiaNaModalidadeComercialBloco: RestOnsImportacaoEnergiaNaModalidadeComercialBloco,
  ): IOnsImportacaoEnergiaNaModalidadeComercialBloco {
    return {
      ...restOnsImportacaoEnergiaNaModalidadeComercialBloco,
      dinInstante: restOnsImportacaoEnergiaNaModalidadeComercialBloco.dinInstante
        ? dayjs(restOnsImportacaoEnergiaNaModalidadeComercialBloco.dinInstante)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsImportacaoEnergiaNaModalidadeComercialBloco>,
  ): HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsImportacaoEnergiaNaModalidadeComercialBloco[]>,
  ): HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
