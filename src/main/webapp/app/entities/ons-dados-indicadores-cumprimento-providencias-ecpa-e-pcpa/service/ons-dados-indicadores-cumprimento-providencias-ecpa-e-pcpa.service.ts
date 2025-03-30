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
  IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
  NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
} from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';

export type PartialUpdateOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa =
  Partial<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa> & Pick<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa, 'id'>;

type RestOf<T extends IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa | NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa> = Omit<
  T,
  'dinReferencia'
> & {
  dinReferencia?: string | null;
};

export type RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = RestOf<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>;

export type NewRestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = RestOf<NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>;

export type PartialUpdateRestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa =
  RestOf<PartialUpdateOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>;

export type EntityResponseType = HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas/_search',
  );

  create(
    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
    return this.http
      .post<RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
    return this.http
      .put<RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>(
        `${this.resourceUrl}/${this.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: PartialUpdateOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa);
    return this.http
      .patch<RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>(
        `${this.resourceUrl}/${this.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[]>()], asapScheduler)),
      );
  }

  getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier(
    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: Pick<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa, 'id'>,
  ): number {
    return onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.id;
  }

  compareOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
    o1: Pick<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa, 'id'> | null,
    o2: Pick<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier(o1) ===
          this.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaToCollectionIfMissing<
    Type extends Pick<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa, 'id'>,
  >(
    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection: Type[],
    ...onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas: Type[] =
      onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpasToCheck.filter(isPresent);
    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas.length > 0) {
      const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollectionIdentifiers =
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection.map(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaItem =>
          this.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaItem),
        );
      const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpasToAdd = onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas.filter(
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaItem => {
          const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier =
            this.getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaItem);
          if (
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollectionIdentifiers.includes(
              onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier,
            )
          ) {
            return false;
          }
          onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollectionIdentifiers.push(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaIdentifier,
          );
          return true;
        },
      );
      return [
        ...onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpasToAdd,
        ...onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection,
      ];
    }
    return onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
      | NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
      | PartialUpdateOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
  >(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: T): RestOf<T> {
    return {
      ...onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
      dinReferencia: onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.dinReferencia?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
  ): IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa {
    return {
      ...restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
      dinReferencia: restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.dinReferencia
        ? dayjs(restOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.dinReferencia)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>,
  ): HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[]>,
  ): HttpResponse<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
