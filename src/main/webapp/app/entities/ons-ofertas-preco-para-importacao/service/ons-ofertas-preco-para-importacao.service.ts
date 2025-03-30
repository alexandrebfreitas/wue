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
import { IOnsOfertasPrecoParaImportacao, NewOnsOfertasPrecoParaImportacao } from '../ons-ofertas-preco-para-importacao.model';

export type PartialUpdateOnsOfertasPrecoParaImportacao = Partial<IOnsOfertasPrecoParaImportacao> &
  Pick<IOnsOfertasPrecoParaImportacao, 'id'>;

type RestOf<T extends IOnsOfertasPrecoParaImportacao | NewOnsOfertasPrecoParaImportacao> = Omit<
  T,
  'datIniciovalidade' | 'datFimvalidade'
> & {
  datIniciovalidade?: string | null;
  datFimvalidade?: string | null;
};

export type RestOnsOfertasPrecoParaImportacao = RestOf<IOnsOfertasPrecoParaImportacao>;

export type NewRestOnsOfertasPrecoParaImportacao = RestOf<NewOnsOfertasPrecoParaImportacao>;

export type PartialUpdateRestOnsOfertasPrecoParaImportacao = RestOf<PartialUpdateOnsOfertasPrecoParaImportacao>;

export type EntityResponseType = HttpResponse<IOnsOfertasPrecoParaImportacao>;
export type EntityArrayResponseType = HttpResponse<IOnsOfertasPrecoParaImportacao[]>;

@Injectable({ providedIn: 'root' })
export class OnsOfertasPrecoParaImportacaoService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-ofertas-preco-para-importacaos');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-ofertas-preco-para-importacaos/_search');

  create(onsOfertasPrecoParaImportacao: NewOnsOfertasPrecoParaImportacao): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsOfertasPrecoParaImportacao);
    return this.http
      .post<RestOnsOfertasPrecoParaImportacao>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsOfertasPrecoParaImportacao: IOnsOfertasPrecoParaImportacao): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsOfertasPrecoParaImportacao);
    return this.http
      .put<RestOnsOfertasPrecoParaImportacao>(
        `${this.resourceUrl}/${this.getOnsOfertasPrecoParaImportacaoIdentifier(onsOfertasPrecoParaImportacao)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsOfertasPrecoParaImportacao: PartialUpdateOnsOfertasPrecoParaImportacao): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsOfertasPrecoParaImportacao);
    return this.http
      .patch<RestOnsOfertasPrecoParaImportacao>(
        `${this.resourceUrl}/${this.getOnsOfertasPrecoParaImportacaoIdentifier(onsOfertasPrecoParaImportacao)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsOfertasPrecoParaImportacao>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsOfertasPrecoParaImportacao[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsOfertasPrecoParaImportacao[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsOfertasPrecoParaImportacao[]>()], asapScheduler)),
    );
  }

  getOnsOfertasPrecoParaImportacaoIdentifier(onsOfertasPrecoParaImportacao: Pick<IOnsOfertasPrecoParaImportacao, 'id'>): number {
    return onsOfertasPrecoParaImportacao.id;
  }

  compareOnsOfertasPrecoParaImportacao(
    o1: Pick<IOnsOfertasPrecoParaImportacao, 'id'> | null,
    o2: Pick<IOnsOfertasPrecoParaImportacao, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsOfertasPrecoParaImportacaoIdentifier(o1) === this.getOnsOfertasPrecoParaImportacaoIdentifier(o2)
      : o1 === o2;
  }

  addOnsOfertasPrecoParaImportacaoToCollectionIfMissing<Type extends Pick<IOnsOfertasPrecoParaImportacao, 'id'>>(
    onsOfertasPrecoParaImportacaoCollection: Type[],
    ...onsOfertasPrecoParaImportacaosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsOfertasPrecoParaImportacaos: Type[] = onsOfertasPrecoParaImportacaosToCheck.filter(isPresent);
    if (onsOfertasPrecoParaImportacaos.length > 0) {
      const onsOfertasPrecoParaImportacaoCollectionIdentifiers = onsOfertasPrecoParaImportacaoCollection.map(
        onsOfertasPrecoParaImportacaoItem => this.getOnsOfertasPrecoParaImportacaoIdentifier(onsOfertasPrecoParaImportacaoItem),
      );
      const onsOfertasPrecoParaImportacaosToAdd = onsOfertasPrecoParaImportacaos.filter(onsOfertasPrecoParaImportacaoItem => {
        const onsOfertasPrecoParaImportacaoIdentifier = this.getOnsOfertasPrecoParaImportacaoIdentifier(onsOfertasPrecoParaImportacaoItem);
        if (onsOfertasPrecoParaImportacaoCollectionIdentifiers.includes(onsOfertasPrecoParaImportacaoIdentifier)) {
          return false;
        }
        onsOfertasPrecoParaImportacaoCollectionIdentifiers.push(onsOfertasPrecoParaImportacaoIdentifier);
        return true;
      });
      return [...onsOfertasPrecoParaImportacaosToAdd, ...onsOfertasPrecoParaImportacaoCollection];
    }
    return onsOfertasPrecoParaImportacaoCollection;
  }

  protected convertDateFromClient<
    T extends IOnsOfertasPrecoParaImportacao | NewOnsOfertasPrecoParaImportacao | PartialUpdateOnsOfertasPrecoParaImportacao,
  >(onsOfertasPrecoParaImportacao: T): RestOf<T> {
    return {
      ...onsOfertasPrecoParaImportacao,
      datIniciovalidade: onsOfertasPrecoParaImportacao.datIniciovalidade?.format(DATE_FORMAT) ?? null,
      datFimvalidade: onsOfertasPrecoParaImportacao.datFimvalidade?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsOfertasPrecoParaImportacao: RestOnsOfertasPrecoParaImportacao): IOnsOfertasPrecoParaImportacao {
    return {
      ...restOnsOfertasPrecoParaImportacao,
      datIniciovalidade: restOnsOfertasPrecoParaImportacao.datIniciovalidade
        ? dayjs(restOnsOfertasPrecoParaImportacao.datIniciovalidade)
        : undefined,
      datFimvalidade: restOnsOfertasPrecoParaImportacao.datFimvalidade
        ? dayjs(restOnsOfertasPrecoParaImportacao.datFimvalidade)
        : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsOfertasPrecoParaImportacao>): HttpResponse<IOnsOfertasPrecoParaImportacao> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsOfertasPrecoParaImportacao[]>,
  ): HttpResponse<IOnsOfertasPrecoParaImportacao[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
