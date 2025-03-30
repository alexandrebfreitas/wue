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
import { IOnsLinhasTransmissaoRedeOperacao, NewOnsLinhasTransmissaoRedeOperacao } from '../ons-linhas-transmissao-rede-operacao.model';

export type PartialUpdateOnsLinhasTransmissaoRedeOperacao = Partial<IOnsLinhasTransmissaoRedeOperacao> &
  Pick<IOnsLinhasTransmissaoRedeOperacao, 'id'>;

type RestOf<T extends IOnsLinhasTransmissaoRedeOperacao | NewOnsLinhasTransmissaoRedeOperacao> = Omit<
  T,
  'datEntradaoperacao' | 'datDesativacao' | 'datPrevista'
> & {
  datEntradaoperacao?: string | null;
  datDesativacao?: string | null;
  datPrevista?: string | null;
};

export type RestOnsLinhasTransmissaoRedeOperacao = RestOf<IOnsLinhasTransmissaoRedeOperacao>;

export type NewRestOnsLinhasTransmissaoRedeOperacao = RestOf<NewOnsLinhasTransmissaoRedeOperacao>;

export type PartialUpdateRestOnsLinhasTransmissaoRedeOperacao = RestOf<PartialUpdateOnsLinhasTransmissaoRedeOperacao>;

export type EntityResponseType = HttpResponse<IOnsLinhasTransmissaoRedeOperacao>;
export type EntityArrayResponseType = HttpResponse<IOnsLinhasTransmissaoRedeOperacao[]>;

@Injectable({ providedIn: 'root' })
export class OnsLinhasTransmissaoRedeOperacaoService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-linhas-transmissao-rede-operacaos');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-linhas-transmissao-rede-operacaos/_search');

  create(onsLinhasTransmissaoRedeOperacao: NewOnsLinhasTransmissaoRedeOperacao): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsLinhasTransmissaoRedeOperacao);
    return this.http
      .post<RestOnsLinhasTransmissaoRedeOperacao>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsLinhasTransmissaoRedeOperacao: IOnsLinhasTransmissaoRedeOperacao): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsLinhasTransmissaoRedeOperacao);
    return this.http
      .put<RestOnsLinhasTransmissaoRedeOperacao>(
        `${this.resourceUrl}/${this.getOnsLinhasTransmissaoRedeOperacaoIdentifier(onsLinhasTransmissaoRedeOperacao)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsLinhasTransmissaoRedeOperacao: PartialUpdateOnsLinhasTransmissaoRedeOperacao): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsLinhasTransmissaoRedeOperacao);
    return this.http
      .patch<RestOnsLinhasTransmissaoRedeOperacao>(
        `${this.resourceUrl}/${this.getOnsLinhasTransmissaoRedeOperacaoIdentifier(onsLinhasTransmissaoRedeOperacao)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsLinhasTransmissaoRedeOperacao>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsLinhasTransmissaoRedeOperacao[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsLinhasTransmissaoRedeOperacao[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsLinhasTransmissaoRedeOperacao[]>()], asapScheduler)),
    );
  }

  getOnsLinhasTransmissaoRedeOperacaoIdentifier(onsLinhasTransmissaoRedeOperacao: Pick<IOnsLinhasTransmissaoRedeOperacao, 'id'>): number {
    return onsLinhasTransmissaoRedeOperacao.id;
  }

  compareOnsLinhasTransmissaoRedeOperacao(
    o1: Pick<IOnsLinhasTransmissaoRedeOperacao, 'id'> | null,
    o2: Pick<IOnsLinhasTransmissaoRedeOperacao, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsLinhasTransmissaoRedeOperacaoIdentifier(o1) === this.getOnsLinhasTransmissaoRedeOperacaoIdentifier(o2)
      : o1 === o2;
  }

  addOnsLinhasTransmissaoRedeOperacaoToCollectionIfMissing<Type extends Pick<IOnsLinhasTransmissaoRedeOperacao, 'id'>>(
    onsLinhasTransmissaoRedeOperacaoCollection: Type[],
    ...onsLinhasTransmissaoRedeOperacaosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsLinhasTransmissaoRedeOperacaos: Type[] = onsLinhasTransmissaoRedeOperacaosToCheck.filter(isPresent);
    if (onsLinhasTransmissaoRedeOperacaos.length > 0) {
      const onsLinhasTransmissaoRedeOperacaoCollectionIdentifiers = onsLinhasTransmissaoRedeOperacaoCollection.map(
        onsLinhasTransmissaoRedeOperacaoItem => this.getOnsLinhasTransmissaoRedeOperacaoIdentifier(onsLinhasTransmissaoRedeOperacaoItem),
      );
      const onsLinhasTransmissaoRedeOperacaosToAdd = onsLinhasTransmissaoRedeOperacaos.filter(onsLinhasTransmissaoRedeOperacaoItem => {
        const onsLinhasTransmissaoRedeOperacaoIdentifier = this.getOnsLinhasTransmissaoRedeOperacaoIdentifier(
          onsLinhasTransmissaoRedeOperacaoItem,
        );
        if (onsLinhasTransmissaoRedeOperacaoCollectionIdentifiers.includes(onsLinhasTransmissaoRedeOperacaoIdentifier)) {
          return false;
        }
        onsLinhasTransmissaoRedeOperacaoCollectionIdentifiers.push(onsLinhasTransmissaoRedeOperacaoIdentifier);
        return true;
      });
      return [...onsLinhasTransmissaoRedeOperacaosToAdd, ...onsLinhasTransmissaoRedeOperacaoCollection];
    }
    return onsLinhasTransmissaoRedeOperacaoCollection;
  }

  protected convertDateFromClient<
    T extends IOnsLinhasTransmissaoRedeOperacao | NewOnsLinhasTransmissaoRedeOperacao | PartialUpdateOnsLinhasTransmissaoRedeOperacao,
  >(onsLinhasTransmissaoRedeOperacao: T): RestOf<T> {
    return {
      ...onsLinhasTransmissaoRedeOperacao,
      datEntradaoperacao: onsLinhasTransmissaoRedeOperacao.datEntradaoperacao?.format(DATE_FORMAT) ?? null,
      datDesativacao: onsLinhasTransmissaoRedeOperacao.datDesativacao?.format(DATE_FORMAT) ?? null,
      datPrevista: onsLinhasTransmissaoRedeOperacao.datPrevista?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsLinhasTransmissaoRedeOperacao: RestOnsLinhasTransmissaoRedeOperacao,
  ): IOnsLinhasTransmissaoRedeOperacao {
    return {
      ...restOnsLinhasTransmissaoRedeOperacao,
      datEntradaoperacao: restOnsLinhasTransmissaoRedeOperacao.datEntradaoperacao
        ? dayjs(restOnsLinhasTransmissaoRedeOperacao.datEntradaoperacao)
        : undefined,
      datDesativacao: restOnsLinhasTransmissaoRedeOperacao.datDesativacao
        ? dayjs(restOnsLinhasTransmissaoRedeOperacao.datDesativacao)
        : undefined,
      datPrevista: restOnsLinhasTransmissaoRedeOperacao.datPrevista ? dayjs(restOnsLinhasTransmissaoRedeOperacao.datPrevista) : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsLinhasTransmissaoRedeOperacao>,
  ): HttpResponse<IOnsLinhasTransmissaoRedeOperacao> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsLinhasTransmissaoRedeOperacao[]>,
  ): HttpResponse<IOnsLinhasTransmissaoRedeOperacao[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
