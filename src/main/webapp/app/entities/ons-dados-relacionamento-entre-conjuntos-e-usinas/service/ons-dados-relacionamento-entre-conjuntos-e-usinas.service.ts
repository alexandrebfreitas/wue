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
  IOnsDadosRelacionamentoEntreConjuntosEUsinas,
  NewOnsDadosRelacionamentoEntreConjuntosEUsinas,
} from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.model';

export type PartialUpdateOnsDadosRelacionamentoEntreConjuntosEUsinas = Partial<IOnsDadosRelacionamentoEntreConjuntosEUsinas> &
  Pick<IOnsDadosRelacionamentoEntreConjuntosEUsinas, 'id'>;

type RestOf<T extends IOnsDadosRelacionamentoEntreConjuntosEUsinas | NewOnsDadosRelacionamentoEntreConjuntosEUsinas> = Omit<
  T,
  'datIniciorelacionamento' | 'datFimrelacionamento'
> & {
  datIniciorelacionamento?: string | null;
  datFimrelacionamento?: string | null;
};

export type RestOnsDadosRelacionamentoEntreConjuntosEUsinas = RestOf<IOnsDadosRelacionamentoEntreConjuntosEUsinas>;

export type NewRestOnsDadosRelacionamentoEntreConjuntosEUsinas = RestOf<NewOnsDadosRelacionamentoEntreConjuntosEUsinas>;

export type PartialUpdateRestOnsDadosRelacionamentoEntreConjuntosEUsinas = RestOf<PartialUpdateOnsDadosRelacionamentoEntreConjuntosEUsinas>;

export type EntityResponseType = HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRelacionamentoEntreConjuntosEUsinasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-relacionamento-entre-conjuntos-e-usinas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-relacionamento-entre-conjuntos-e-usinas/_search',
  );

  create(onsDadosRelacionamentoEntreConjuntosEUsinas: NewOnsDadosRelacionamentoEntreConjuntosEUsinas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRelacionamentoEntreConjuntosEUsinas);
    return this.http
      .post<RestOnsDadosRelacionamentoEntreConjuntosEUsinas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsDadosRelacionamentoEntreConjuntosEUsinas: IOnsDadosRelacionamentoEntreConjuntosEUsinas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRelacionamentoEntreConjuntosEUsinas);
    return this.http
      .put<RestOnsDadosRelacionamentoEntreConjuntosEUsinas>(
        `${this.resourceUrl}/${this.getOnsDadosRelacionamentoEntreConjuntosEUsinasIdentifier(onsDadosRelacionamentoEntreConjuntosEUsinas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosRelacionamentoEntreConjuntosEUsinas: PartialUpdateOnsDadosRelacionamentoEntreConjuntosEUsinas,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosRelacionamentoEntreConjuntosEUsinas);
    return this.http
      .patch<RestOnsDadosRelacionamentoEntreConjuntosEUsinas>(
        `${this.resourceUrl}/${this.getOnsDadosRelacionamentoEntreConjuntosEUsinasIdentifier(onsDadosRelacionamentoEntreConjuntosEUsinas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosRelacionamentoEntreConjuntosEUsinas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosRelacionamentoEntreConjuntosEUsinas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosRelacionamentoEntreConjuntosEUsinas[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas[]>()], asapScheduler)),
      );
  }

  getOnsDadosRelacionamentoEntreConjuntosEUsinasIdentifier(
    onsDadosRelacionamentoEntreConjuntosEUsinas: Pick<IOnsDadosRelacionamentoEntreConjuntosEUsinas, 'id'>,
  ): number {
    return onsDadosRelacionamentoEntreConjuntosEUsinas.id;
  }

  compareOnsDadosRelacionamentoEntreConjuntosEUsinas(
    o1: Pick<IOnsDadosRelacionamentoEntreConjuntosEUsinas, 'id'> | null,
    o2: Pick<IOnsDadosRelacionamentoEntreConjuntosEUsinas, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosRelacionamentoEntreConjuntosEUsinasIdentifier(o1) ===
          this.getOnsDadosRelacionamentoEntreConjuntosEUsinasIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosRelacionamentoEntreConjuntosEUsinasToCollectionIfMissing<
    Type extends Pick<IOnsDadosRelacionamentoEntreConjuntosEUsinas, 'id'>,
  >(
    onsDadosRelacionamentoEntreConjuntosEUsinasCollection: Type[],
    ...onsDadosRelacionamentoEntreConjuntosEUsinasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosRelacionamentoEntreConjuntosEUsinas: Type[] = onsDadosRelacionamentoEntreConjuntosEUsinasToCheck.filter(isPresent);
    if (onsDadosRelacionamentoEntreConjuntosEUsinas.length > 0) {
      const onsDadosRelacionamentoEntreConjuntosEUsinasCollectionIdentifiers = onsDadosRelacionamentoEntreConjuntosEUsinasCollection.map(
        onsDadosRelacionamentoEntreConjuntosEUsinasItem =>
          this.getOnsDadosRelacionamentoEntreConjuntosEUsinasIdentifier(onsDadosRelacionamentoEntreConjuntosEUsinasItem),
      );
      const onsDadosRelacionamentoEntreConjuntosEUsinasToAdd = onsDadosRelacionamentoEntreConjuntosEUsinas.filter(
        onsDadosRelacionamentoEntreConjuntosEUsinasItem => {
          const onsDadosRelacionamentoEntreConjuntosEUsinasIdentifier = this.getOnsDadosRelacionamentoEntreConjuntosEUsinasIdentifier(
            onsDadosRelacionamentoEntreConjuntosEUsinasItem,
          );
          if (
            onsDadosRelacionamentoEntreConjuntosEUsinasCollectionIdentifiers.includes(onsDadosRelacionamentoEntreConjuntosEUsinasIdentifier)
          ) {
            return false;
          }
          onsDadosRelacionamentoEntreConjuntosEUsinasCollectionIdentifiers.push(onsDadosRelacionamentoEntreConjuntosEUsinasIdentifier);
          return true;
        },
      );
      return [...onsDadosRelacionamentoEntreConjuntosEUsinasToAdd, ...onsDadosRelacionamentoEntreConjuntosEUsinasCollection];
    }
    return onsDadosRelacionamentoEntreConjuntosEUsinasCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosRelacionamentoEntreConjuntosEUsinas
      | NewOnsDadosRelacionamentoEntreConjuntosEUsinas
      | PartialUpdateOnsDadosRelacionamentoEntreConjuntosEUsinas,
  >(onsDadosRelacionamentoEntreConjuntosEUsinas: T): RestOf<T> {
    return {
      ...onsDadosRelacionamentoEntreConjuntosEUsinas,
      datIniciorelacionamento: onsDadosRelacionamentoEntreConjuntosEUsinas.datIniciorelacionamento?.format(DATE_FORMAT) ?? null,
      datFimrelacionamento: onsDadosRelacionamentoEntreConjuntosEUsinas.datFimrelacionamento?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosRelacionamentoEntreConjuntosEUsinas: RestOnsDadosRelacionamentoEntreConjuntosEUsinas,
  ): IOnsDadosRelacionamentoEntreConjuntosEUsinas {
    return {
      ...restOnsDadosRelacionamentoEntreConjuntosEUsinas,
      datIniciorelacionamento: restOnsDadosRelacionamentoEntreConjuntosEUsinas.datIniciorelacionamento
        ? dayjs(restOnsDadosRelacionamentoEntreConjuntosEUsinas.datIniciorelacionamento)
        : undefined,
      datFimrelacionamento: restOnsDadosRelacionamentoEntreConjuntosEUsinas.datFimrelacionamento
        ? dayjs(restOnsDadosRelacionamentoEntreConjuntosEUsinas.datFimrelacionamento)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosRelacionamentoEntreConjuntosEUsinas>,
  ): HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosRelacionamentoEntreConjuntosEUsinas[]>,
  ): HttpResponse<IOnsDadosRelacionamentoEntreConjuntosEUsinas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
