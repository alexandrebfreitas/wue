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
  IOnsDadosIntercambioEnergiaModalidade,
  NewOnsDadosIntercambioEnergiaModalidade,
} from '../ons-dados-intercambio-energia-modalidade.model';

export type PartialUpdateOnsDadosIntercambioEnergiaModalidade = Partial<IOnsDadosIntercambioEnergiaModalidade> &
  Pick<IOnsDadosIntercambioEnergiaModalidade, 'id'>;

type RestOf<T extends IOnsDadosIntercambioEnergiaModalidade | NewOnsDadosIntercambioEnergiaModalidade> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsDadosIntercambioEnergiaModalidade = RestOf<IOnsDadosIntercambioEnergiaModalidade>;

export type NewRestOnsDadosIntercambioEnergiaModalidade = RestOf<NewOnsDadosIntercambioEnergiaModalidade>;

export type PartialUpdateRestOnsDadosIntercambioEnergiaModalidade = RestOf<PartialUpdateOnsDadosIntercambioEnergiaModalidade>;

export type EntityResponseType = HttpResponse<IOnsDadosIntercambioEnergiaModalidade>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosIntercambioEnergiaModalidade[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosIntercambioEnergiaModalidadeService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-intercambio-energia-modalidades');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-intercambio-energia-modalidades/_search');

  create(onsDadosIntercambioEnergiaModalidade: NewOnsDadosIntercambioEnergiaModalidade): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIntercambioEnergiaModalidade);
    return this.http
      .post<RestOnsDadosIntercambioEnergiaModalidade>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsDadosIntercambioEnergiaModalidade: IOnsDadosIntercambioEnergiaModalidade): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIntercambioEnergiaModalidade);
    return this.http
      .put<RestOnsDadosIntercambioEnergiaModalidade>(
        `${this.resourceUrl}/${this.getOnsDadosIntercambioEnergiaModalidadeIdentifier(onsDadosIntercambioEnergiaModalidade)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsDadosIntercambioEnergiaModalidade: PartialUpdateOnsDadosIntercambioEnergiaModalidade): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIntercambioEnergiaModalidade);
    return this.http
      .patch<RestOnsDadosIntercambioEnergiaModalidade>(
        `${this.resourceUrl}/${this.getOnsDadosIntercambioEnergiaModalidadeIdentifier(onsDadosIntercambioEnergiaModalidade)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosIntercambioEnergiaModalidade>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosIntercambioEnergiaModalidade[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsDadosIntercambioEnergiaModalidade[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsDadosIntercambioEnergiaModalidade[]>()], asapScheduler)),
    );
  }

  getOnsDadosIntercambioEnergiaModalidadeIdentifier(
    onsDadosIntercambioEnergiaModalidade: Pick<IOnsDadosIntercambioEnergiaModalidade, 'id'>,
  ): number {
    return onsDadosIntercambioEnergiaModalidade.id;
  }

  compareOnsDadosIntercambioEnergiaModalidade(
    o1: Pick<IOnsDadosIntercambioEnergiaModalidade, 'id'> | null,
    o2: Pick<IOnsDadosIntercambioEnergiaModalidade, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosIntercambioEnergiaModalidadeIdentifier(o1) === this.getOnsDadosIntercambioEnergiaModalidadeIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosIntercambioEnergiaModalidadeToCollectionIfMissing<Type extends Pick<IOnsDadosIntercambioEnergiaModalidade, 'id'>>(
    onsDadosIntercambioEnergiaModalidadeCollection: Type[],
    ...onsDadosIntercambioEnergiaModalidadesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosIntercambioEnergiaModalidades: Type[] = onsDadosIntercambioEnergiaModalidadesToCheck.filter(isPresent);
    if (onsDadosIntercambioEnergiaModalidades.length > 0) {
      const onsDadosIntercambioEnergiaModalidadeCollectionIdentifiers = onsDadosIntercambioEnergiaModalidadeCollection.map(
        onsDadosIntercambioEnergiaModalidadeItem =>
          this.getOnsDadosIntercambioEnergiaModalidadeIdentifier(onsDadosIntercambioEnergiaModalidadeItem),
      );
      const onsDadosIntercambioEnergiaModalidadesToAdd = onsDadosIntercambioEnergiaModalidades.filter(
        onsDadosIntercambioEnergiaModalidadeItem => {
          const onsDadosIntercambioEnergiaModalidadeIdentifier = this.getOnsDadosIntercambioEnergiaModalidadeIdentifier(
            onsDadosIntercambioEnergiaModalidadeItem,
          );
          if (onsDadosIntercambioEnergiaModalidadeCollectionIdentifiers.includes(onsDadosIntercambioEnergiaModalidadeIdentifier)) {
            return false;
          }
          onsDadosIntercambioEnergiaModalidadeCollectionIdentifiers.push(onsDadosIntercambioEnergiaModalidadeIdentifier);
          return true;
        },
      );
      return [...onsDadosIntercambioEnergiaModalidadesToAdd, ...onsDadosIntercambioEnergiaModalidadeCollection];
    }
    return onsDadosIntercambioEnergiaModalidadeCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosIntercambioEnergiaModalidade
      | NewOnsDadosIntercambioEnergiaModalidade
      | PartialUpdateOnsDadosIntercambioEnergiaModalidade,
  >(onsDadosIntercambioEnergiaModalidade: T): RestOf<T> {
    return {
      ...onsDadosIntercambioEnergiaModalidade,
      dinInstante: onsDadosIntercambioEnergiaModalidade.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosIntercambioEnergiaModalidade: RestOnsDadosIntercambioEnergiaModalidade,
  ): IOnsDadosIntercambioEnergiaModalidade {
    return {
      ...restOnsDadosIntercambioEnergiaModalidade,
      dinInstante: restOnsDadosIntercambioEnergiaModalidade.dinInstante
        ? dayjs(restOnsDadosIntercambioEnergiaModalidade.dinInstante)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosIntercambioEnergiaModalidade>,
  ): HttpResponse<IOnsDadosIntercambioEnergiaModalidade> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosIntercambioEnergiaModalidade[]>,
  ): HttpResponse<IOnsDadosIntercambioEnergiaModalidade[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
