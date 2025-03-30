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
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.model';

export type PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas =
  Partial<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas> & Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas, 'id'>;

type RestOf<T extends IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas> =
  Omit<T, 'dinInstante'> & {
    dinInstante?: string | null;
  };

export type RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = RestOf<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>;

export type NewRestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas = RestOf<NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>;

export type PartialUpdateRestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas =
  RestOf<PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>;

export type EntityResponseType = HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas/_search',
  );

  create(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
    return this.http
      .post<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
    return this.http
      .put<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>(
        `${this.resourceUrl}/${this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas);
    return this.http
      .patch<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>(
        `${this.resourceUrl}/${this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[]>()], asapScheduler)),
      );
  }

  getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas, 'id'>,
  ): number {
    return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.id;
  }

  compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
    o1: Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas, 'id'> | null,
    o2: Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier(o1) ===
          this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCollectionIfMissing<
    Type extends Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas, 'id'>,
  >(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection: Type[],
    ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: Type[] =
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToCheck.filter(isPresent);
    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.length > 0) {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollectionIdentifiers =
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection.map(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasItem =>
          this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasItem),
        );
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToAdd = onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.filter(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasItem => {
          const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier =
            this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier(
              onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasItem,
            );
          if (
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollectionIdentifiers.includes(
              onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier,
            )
          ) {
            return false;
          }
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollectionIdentifiers.push(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasIdentifier,
          );
          return true;
        },
      );
      return [
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasToAdd,
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection,
      ];
    }
    return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
      | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
      | PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
  >(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: T): RestOf<T> {
    return {
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
      dinInstante: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
  ): IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas {
    return {
      ...restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
      dinInstante: restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.dinInstante
        ? dayjs(restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.dinInstante)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>,
  ): HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[]>,
  ): HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
