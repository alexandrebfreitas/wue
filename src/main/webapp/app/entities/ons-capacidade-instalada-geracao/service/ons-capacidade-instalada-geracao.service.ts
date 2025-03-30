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
import { IOnsCapacidadeInstaladaGeracao, NewOnsCapacidadeInstaladaGeracao } from '../ons-capacidade-instalada-geracao.model';

export type PartialUpdateOnsCapacidadeInstaladaGeracao = Partial<IOnsCapacidadeInstaladaGeracao> &
  Pick<IOnsCapacidadeInstaladaGeracao, 'id'>;

type RestOf<T extends IOnsCapacidadeInstaladaGeracao | NewOnsCapacidadeInstaladaGeracao> = Omit<
  T,
  'datEntradateste' | 'datEntradaoperacao' | 'datDesativacao'
> & {
  datEntradateste?: string | null;
  datEntradaoperacao?: string | null;
  datDesativacao?: string | null;
};

export type RestOnsCapacidadeInstaladaGeracao = RestOf<IOnsCapacidadeInstaladaGeracao>;

export type NewRestOnsCapacidadeInstaladaGeracao = RestOf<NewOnsCapacidadeInstaladaGeracao>;

export type PartialUpdateRestOnsCapacidadeInstaladaGeracao = RestOf<PartialUpdateOnsCapacidadeInstaladaGeracao>;

export type EntityResponseType = HttpResponse<IOnsCapacidadeInstaladaGeracao>;
export type EntityArrayResponseType = HttpResponse<IOnsCapacidadeInstaladaGeracao[]>;

@Injectable({ providedIn: 'root' })
export class OnsCapacidadeInstaladaGeracaoService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-capacidade-instalada-geracaos');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-capacidade-instalada-geracaos/_search');

  create(onsCapacidadeInstaladaGeracao: NewOnsCapacidadeInstaladaGeracao): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCapacidadeInstaladaGeracao);
    return this.http
      .post<RestOnsCapacidadeInstaladaGeracao>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCapacidadeInstaladaGeracao: IOnsCapacidadeInstaladaGeracao): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCapacidadeInstaladaGeracao);
    return this.http
      .put<RestOnsCapacidadeInstaladaGeracao>(
        `${this.resourceUrl}/${this.getOnsCapacidadeInstaladaGeracaoIdentifier(onsCapacidadeInstaladaGeracao)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCapacidadeInstaladaGeracao: PartialUpdateOnsCapacidadeInstaladaGeracao): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCapacidadeInstaladaGeracao);
    return this.http
      .patch<RestOnsCapacidadeInstaladaGeracao>(
        `${this.resourceUrl}/${this.getOnsCapacidadeInstaladaGeracaoIdentifier(onsCapacidadeInstaladaGeracao)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCapacidadeInstaladaGeracao>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCapacidadeInstaladaGeracao[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCapacidadeInstaladaGeracao[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCapacidadeInstaladaGeracao[]>()], asapScheduler)),
    );
  }

  getOnsCapacidadeInstaladaGeracaoIdentifier(onsCapacidadeInstaladaGeracao: Pick<IOnsCapacidadeInstaladaGeracao, 'id'>): number {
    return onsCapacidadeInstaladaGeracao.id;
  }

  compareOnsCapacidadeInstaladaGeracao(
    o1: Pick<IOnsCapacidadeInstaladaGeracao, 'id'> | null,
    o2: Pick<IOnsCapacidadeInstaladaGeracao, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsCapacidadeInstaladaGeracaoIdentifier(o1) === this.getOnsCapacidadeInstaladaGeracaoIdentifier(o2)
      : o1 === o2;
  }

  addOnsCapacidadeInstaladaGeracaoToCollectionIfMissing<Type extends Pick<IOnsCapacidadeInstaladaGeracao, 'id'>>(
    onsCapacidadeInstaladaGeracaoCollection: Type[],
    ...onsCapacidadeInstaladaGeracaosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCapacidadeInstaladaGeracaos: Type[] = onsCapacidadeInstaladaGeracaosToCheck.filter(isPresent);
    if (onsCapacidadeInstaladaGeracaos.length > 0) {
      const onsCapacidadeInstaladaGeracaoCollectionIdentifiers = onsCapacidadeInstaladaGeracaoCollection.map(
        onsCapacidadeInstaladaGeracaoItem => this.getOnsCapacidadeInstaladaGeracaoIdentifier(onsCapacidadeInstaladaGeracaoItem),
      );
      const onsCapacidadeInstaladaGeracaosToAdd = onsCapacidadeInstaladaGeracaos.filter(onsCapacidadeInstaladaGeracaoItem => {
        const onsCapacidadeInstaladaGeracaoIdentifier = this.getOnsCapacidadeInstaladaGeracaoIdentifier(onsCapacidadeInstaladaGeracaoItem);
        if (onsCapacidadeInstaladaGeracaoCollectionIdentifiers.includes(onsCapacidadeInstaladaGeracaoIdentifier)) {
          return false;
        }
        onsCapacidadeInstaladaGeracaoCollectionIdentifiers.push(onsCapacidadeInstaladaGeracaoIdentifier);
        return true;
      });
      return [...onsCapacidadeInstaladaGeracaosToAdd, ...onsCapacidadeInstaladaGeracaoCollection];
    }
    return onsCapacidadeInstaladaGeracaoCollection;
  }

  protected convertDateFromClient<
    T extends IOnsCapacidadeInstaladaGeracao | NewOnsCapacidadeInstaladaGeracao | PartialUpdateOnsCapacidadeInstaladaGeracao,
  >(onsCapacidadeInstaladaGeracao: T): RestOf<T> {
    return {
      ...onsCapacidadeInstaladaGeracao,
      datEntradateste: onsCapacidadeInstaladaGeracao.datEntradateste?.format(DATE_FORMAT) ?? null,
      datEntradaoperacao: onsCapacidadeInstaladaGeracao.datEntradaoperacao?.format(DATE_FORMAT) ?? null,
      datDesativacao: onsCapacidadeInstaladaGeracao.datDesativacao?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsCapacidadeInstaladaGeracao: RestOnsCapacidadeInstaladaGeracao): IOnsCapacidadeInstaladaGeracao {
    return {
      ...restOnsCapacidadeInstaladaGeracao,
      datEntradateste: restOnsCapacidadeInstaladaGeracao.datEntradateste
        ? dayjs(restOnsCapacidadeInstaladaGeracao.datEntradateste)
        : undefined,
      datEntradaoperacao: restOnsCapacidadeInstaladaGeracao.datEntradaoperacao
        ? dayjs(restOnsCapacidadeInstaladaGeracao.datEntradaoperacao)
        : undefined,
      datDesativacao: restOnsCapacidadeInstaladaGeracao.datDesativacao
        ? dayjs(restOnsCapacidadeInstaladaGeracao.datDesativacao)
        : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsCapacidadeInstaladaGeracao>): HttpResponse<IOnsCapacidadeInstaladaGeracao> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsCapacidadeInstaladaGeracao[]>,
  ): HttpResponse<IOnsCapacidadeInstaladaGeracao[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
