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
  IOnsCapacidadeTransformacaoRedeBasica,
  NewOnsCapacidadeTransformacaoRedeBasica,
} from '../ons-capacidade-transformacao-rede-basica.model';

export type PartialUpdateOnsCapacidadeTransformacaoRedeBasica = Partial<IOnsCapacidadeTransformacaoRedeBasica> &
  Pick<IOnsCapacidadeTransformacaoRedeBasica, 'id'>;

type RestOf<T extends IOnsCapacidadeTransformacaoRedeBasica | NewOnsCapacidadeTransformacaoRedeBasica> = Omit<
  T,
  'datEntradaoperacao' | 'datDesativacao'
> & {
  datEntradaoperacao?: string | null;
  datDesativacao?: string | null;
};

export type RestOnsCapacidadeTransformacaoRedeBasica = RestOf<IOnsCapacidadeTransformacaoRedeBasica>;

export type NewRestOnsCapacidadeTransformacaoRedeBasica = RestOf<NewOnsCapacidadeTransformacaoRedeBasica>;

export type PartialUpdateRestOnsCapacidadeTransformacaoRedeBasica = RestOf<PartialUpdateOnsCapacidadeTransformacaoRedeBasica>;

export type EntityResponseType = HttpResponse<IOnsCapacidadeTransformacaoRedeBasica>;
export type EntityArrayResponseType = HttpResponse<IOnsCapacidadeTransformacaoRedeBasica[]>;

@Injectable({ providedIn: 'root' })
export class OnsCapacidadeTransformacaoRedeBasicaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-capacidade-transformacao-rede-basicas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-capacidade-transformacao-rede-basicas/_search');

  create(onsCapacidadeTransformacaoRedeBasica: NewOnsCapacidadeTransformacaoRedeBasica): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCapacidadeTransformacaoRedeBasica);
    return this.http
      .post<RestOnsCapacidadeTransformacaoRedeBasica>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCapacidadeTransformacaoRedeBasica: IOnsCapacidadeTransformacaoRedeBasica): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCapacidadeTransformacaoRedeBasica);
    return this.http
      .put<RestOnsCapacidadeTransformacaoRedeBasica>(
        `${this.resourceUrl}/${this.getOnsCapacidadeTransformacaoRedeBasicaIdentifier(onsCapacidadeTransformacaoRedeBasica)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCapacidadeTransformacaoRedeBasica: PartialUpdateOnsCapacidadeTransformacaoRedeBasica): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCapacidadeTransformacaoRedeBasica);
    return this.http
      .patch<RestOnsCapacidadeTransformacaoRedeBasica>(
        `${this.resourceUrl}/${this.getOnsCapacidadeTransformacaoRedeBasicaIdentifier(onsCapacidadeTransformacaoRedeBasica)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCapacidadeTransformacaoRedeBasica>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCapacidadeTransformacaoRedeBasica[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCapacidadeTransformacaoRedeBasica[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCapacidadeTransformacaoRedeBasica[]>()], asapScheduler)),
    );
  }

  getOnsCapacidadeTransformacaoRedeBasicaIdentifier(
    onsCapacidadeTransformacaoRedeBasica: Pick<IOnsCapacidadeTransformacaoRedeBasica, 'id'>,
  ): number {
    return onsCapacidadeTransformacaoRedeBasica.id;
  }

  compareOnsCapacidadeTransformacaoRedeBasica(
    o1: Pick<IOnsCapacidadeTransformacaoRedeBasica, 'id'> | null,
    o2: Pick<IOnsCapacidadeTransformacaoRedeBasica, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsCapacidadeTransformacaoRedeBasicaIdentifier(o1) === this.getOnsCapacidadeTransformacaoRedeBasicaIdentifier(o2)
      : o1 === o2;
  }

  addOnsCapacidadeTransformacaoRedeBasicaToCollectionIfMissing<Type extends Pick<IOnsCapacidadeTransformacaoRedeBasica, 'id'>>(
    onsCapacidadeTransformacaoRedeBasicaCollection: Type[],
    ...onsCapacidadeTransformacaoRedeBasicasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCapacidadeTransformacaoRedeBasicas: Type[] = onsCapacidadeTransformacaoRedeBasicasToCheck.filter(isPresent);
    if (onsCapacidadeTransformacaoRedeBasicas.length > 0) {
      const onsCapacidadeTransformacaoRedeBasicaCollectionIdentifiers = onsCapacidadeTransformacaoRedeBasicaCollection.map(
        onsCapacidadeTransformacaoRedeBasicaItem =>
          this.getOnsCapacidadeTransformacaoRedeBasicaIdentifier(onsCapacidadeTransformacaoRedeBasicaItem),
      );
      const onsCapacidadeTransformacaoRedeBasicasToAdd = onsCapacidadeTransformacaoRedeBasicas.filter(
        onsCapacidadeTransformacaoRedeBasicaItem => {
          const onsCapacidadeTransformacaoRedeBasicaIdentifier = this.getOnsCapacidadeTransformacaoRedeBasicaIdentifier(
            onsCapacidadeTransformacaoRedeBasicaItem,
          );
          if (onsCapacidadeTransformacaoRedeBasicaCollectionIdentifiers.includes(onsCapacidadeTransformacaoRedeBasicaIdentifier)) {
            return false;
          }
          onsCapacidadeTransformacaoRedeBasicaCollectionIdentifiers.push(onsCapacidadeTransformacaoRedeBasicaIdentifier);
          return true;
        },
      );
      return [...onsCapacidadeTransformacaoRedeBasicasToAdd, ...onsCapacidadeTransformacaoRedeBasicaCollection];
    }
    return onsCapacidadeTransformacaoRedeBasicaCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsCapacidadeTransformacaoRedeBasica
      | NewOnsCapacidadeTransformacaoRedeBasica
      | PartialUpdateOnsCapacidadeTransformacaoRedeBasica,
  >(onsCapacidadeTransformacaoRedeBasica: T): RestOf<T> {
    return {
      ...onsCapacidadeTransformacaoRedeBasica,
      datEntradaoperacao: onsCapacidadeTransformacaoRedeBasica.datEntradaoperacao?.format(DATE_FORMAT) ?? null,
      datDesativacao: onsCapacidadeTransformacaoRedeBasica.datDesativacao?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsCapacidadeTransformacaoRedeBasica: RestOnsCapacidadeTransformacaoRedeBasica,
  ): IOnsCapacidadeTransformacaoRedeBasica {
    return {
      ...restOnsCapacidadeTransformacaoRedeBasica,
      datEntradaoperacao: restOnsCapacidadeTransformacaoRedeBasica.datEntradaoperacao
        ? dayjs(restOnsCapacidadeTransformacaoRedeBasica.datEntradaoperacao)
        : undefined,
      datDesativacao: restOnsCapacidadeTransformacaoRedeBasica.datDesativacao
        ? dayjs(restOnsCapacidadeTransformacaoRedeBasica.datDesativacao)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsCapacidadeTransformacaoRedeBasica>,
  ): HttpResponse<IOnsCapacidadeTransformacaoRedeBasica> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsCapacidadeTransformacaoRedeBasica[]>,
  ): HttpResponse<IOnsCapacidadeTransformacaoRedeBasica[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
