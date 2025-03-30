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
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';

export type PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
  Partial<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas> &
    Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas, 'id'>;

type RestOf<
  T extends IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
  RestOf<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>;

export type NewRestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
  RestOf<NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>;

export type PartialUpdateRestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas =
  RestOf<PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>;

export type EntityResponseType = HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas/_search',
  );

  create(
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas);
    return this.http
      .post<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas);
    return this.http
      .put<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>(
        `${this.resourceUrl}/${this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas);
    return this.http
      .patch<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>(
        `${this.resourceUrl}/${this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[]
      >(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[]>()], asapScheduler)),
      );
  }

  getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier(
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas, 'id'>,
  ): number {
    return onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.id;
  }

  compareOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(
    o1: Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas, 'id'> | null,
    o2: Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier(o1) ===
          this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCollectionIfMissing<
    Type extends Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas, 'id'>,
  >(
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection: Type[],
    ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: Type[] =
      onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToCheck.filter(isPresent);
    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.length > 0) {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollectionIdentifiers =
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection.map(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasItem =>
            this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier(
              onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasItem,
            ),
        );
      const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToAdd =
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.filter(
          onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasItem => {
            const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier =
              this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasItem,
              );
            if (
              onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollectionIdentifiers.includes(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier,
              )
            ) {
              return false;
            }
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollectionIdentifiers.push(
              onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasIdentifier,
            );
            return true;
          },
        );
      return [
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasToAdd,
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection,
      ];
    }
    return onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
      | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
      | PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  >(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: T): RestOf<T> {
    return {
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
      dinInstante: onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  ): IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas {
    return {
      ...restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
      dinInstante: restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.dinInstante
        ? dayjs(restOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.dinInstante)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>,
  ): HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[]>,
  ): HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
