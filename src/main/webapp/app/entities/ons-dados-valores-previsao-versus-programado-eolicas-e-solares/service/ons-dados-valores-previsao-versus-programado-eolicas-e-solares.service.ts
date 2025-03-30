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
  IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
} from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';

export type PartialUpdateOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
  Partial<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares> & Pick<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares, 'id'>;

type RestOf<T extends IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares | NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares> =
  Omit<T, 'datProgramacao'> & {
    datProgramacao?: string | null;
  };

export type RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = RestOf<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>;

export type NewRestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
  RestOf<NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>;

export type PartialUpdateRestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares =
  RestOf<PartialUpdateOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>;

export type EntityResponseType = HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-valores-previsao-versus-programado-eolicas-e-solares',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-valores-previsao-versus-programado-eolicas-e-solares/_search',
  );

  create(
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
    return this.http
      .post<RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
    return this.http
      .put<RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>(
        `${this.resourceUrl}/${this.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: PartialUpdateOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares);
    return this.http
      .patch<RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>(
        `${this.resourceUrl}/${this.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[]>()], asapScheduler)),
      );
  }

  getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier(
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: Pick<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares, 'id'>,
  ): number {
    return onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.id;
  }

  compareOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
    o1: Pick<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares, 'id'> | null,
    o2: Pick<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier(o1) ===
          this.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCollectionIfMissing<
    Type extends Pick<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares, 'id'>,
  >(
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection: Type[],
    ...onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: Type[] =
      onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToCheck.filter(isPresent);
    if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.length > 0) {
      const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollectionIdentifiers =
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection.map(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresItem =>
          this.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresItem,
          ),
        );
      const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToAdd = onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.filter(
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresItem => {
          const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier =
            this.getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier(
              onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresItem,
            );
          if (
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollectionIdentifiers.includes(
              onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier,
            )
          ) {
            return false;
          }
          onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollectionIdentifiers.push(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresIdentifier,
          );
          return true;
        },
      );
      return [
        ...onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresToAdd,
        ...onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection,
      ];
    }
    return onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares
      | NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares
      | PartialUpdateOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  >(onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: T): RestOf<T> {
    return {
      ...onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
      datProgramacao: onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.datProgramacao?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares: RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  ): IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares {
    return {
      ...restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
      datProgramacao: restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares.datProgramacao
        ? dayjs(restOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares.datProgramacao)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>,
  ): HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[]>,
  ): HttpResponse<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
