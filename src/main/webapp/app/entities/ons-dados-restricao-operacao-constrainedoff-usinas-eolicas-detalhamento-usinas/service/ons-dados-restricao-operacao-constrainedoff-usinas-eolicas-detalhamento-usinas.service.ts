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
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';

export type PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
  Partial<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas> &
    Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas, 'id'>;

type RestOf<
  T extends
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
    | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
  RestOf<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>;

export type NewRestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
  RestOf<NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>;

export type PartialUpdateRestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas =
  RestOf<PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>;

export type EntityResponseType = HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas/_search',
  );

  create(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas);
    return this.http
      .post<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas);
    return this.http
      .put<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>(
        `${this.resourceUrl}/${this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas);
    return this.http
      .patch<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>(
        `${this.resourceUrl}/${this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[]
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
        RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[]
      >(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() =>
          scheduled([new HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[]>()], asapScheduler),
        ),
      );
  }

  getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: Pick<
      IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
      'id'
    >,
  ): number {
    return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.id;
  }

  compareOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(
    o1: Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas, 'id'> | null,
    o2: Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier(o1) ===
          this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCollectionIfMissing<
    Type extends Pick<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas, 'id'>,
  >(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection: Type[],
    ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: Type[] =
      onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToCheck.filter(isPresent);
    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.length > 0) {
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollectionIdentifiers =
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection.map(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasItem =>
            this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier(
              onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasItem,
            ),
        );
      const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToAdd =
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.filter(
          onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasItem => {
            const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier =
              this.getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasItem,
              );
            if (
              onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollectionIdentifiers.includes(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier,
              )
            ) {
              return false;
            }
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollectionIdentifiers.push(
              onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasIdentifier,
            );
            return true;
          },
        );
      return [
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasToAdd,
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection,
      ];
    }
    return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
      | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
      | PartialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  >(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: T): RestOf<T> {
    return {
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
      dinInstante: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  ): IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas {
    return {
      ...restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
      dinInstante: restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.dinInstante
        ? dayjs(restOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.dinInstante)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>,
  ): HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[]>,
  ): HttpResponse<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
