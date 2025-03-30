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
  IOnsDadosHidrologicosVolumeEsperaRecomendado,
  NewOnsDadosHidrologicosVolumeEsperaRecomendado,
} from '../ons-dados-hidrologicos-volume-espera-recomendado.model';

export type PartialUpdateOnsDadosHidrologicosVolumeEsperaRecomendado = Partial<IOnsDadosHidrologicosVolumeEsperaRecomendado> &
  Pick<IOnsDadosHidrologicosVolumeEsperaRecomendado, 'id'>;

type RestOf<T extends IOnsDadosHidrologicosVolumeEsperaRecomendado | NewOnsDadosHidrologicosVolumeEsperaRecomendado> = Omit<
  T,
  'dinInstante'
> & {
  dinInstante?: string | null;
};

export type RestOnsDadosHidrologicosVolumeEsperaRecomendado = RestOf<IOnsDadosHidrologicosVolumeEsperaRecomendado>;

export type NewRestOnsDadosHidrologicosVolumeEsperaRecomendado = RestOf<NewOnsDadosHidrologicosVolumeEsperaRecomendado>;

export type PartialUpdateRestOnsDadosHidrologicosVolumeEsperaRecomendado = RestOf<PartialUpdateOnsDadosHidrologicosVolumeEsperaRecomendado>;

export type EntityResponseType = HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosHidrologicosVolumeEsperaRecomendadoService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-hidrologicos-volume-espera-recomendados');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-hidrologicos-volume-espera-recomendados/_search',
  );

  create(onsDadosHidrologicosVolumeEsperaRecomendado: NewOnsDadosHidrologicosVolumeEsperaRecomendado): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosHidrologicosVolumeEsperaRecomendado);
    return this.http
      .post<RestOnsDadosHidrologicosVolumeEsperaRecomendado>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsDadosHidrologicosVolumeEsperaRecomendado: IOnsDadosHidrologicosVolumeEsperaRecomendado): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosHidrologicosVolumeEsperaRecomendado);
    return this.http
      .put<RestOnsDadosHidrologicosVolumeEsperaRecomendado>(
        `${this.resourceUrl}/${this.getOnsDadosHidrologicosVolumeEsperaRecomendadoIdentifier(onsDadosHidrologicosVolumeEsperaRecomendado)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosHidrologicosVolumeEsperaRecomendado: PartialUpdateOnsDadosHidrologicosVolumeEsperaRecomendado,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosHidrologicosVolumeEsperaRecomendado);
    return this.http
      .patch<RestOnsDadosHidrologicosVolumeEsperaRecomendado>(
        `${this.resourceUrl}/${this.getOnsDadosHidrologicosVolumeEsperaRecomendadoIdentifier(onsDadosHidrologicosVolumeEsperaRecomendado)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosHidrologicosVolumeEsperaRecomendado>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosHidrologicosVolumeEsperaRecomendado[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosHidrologicosVolumeEsperaRecomendado[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado[]>()], asapScheduler)),
      );
  }

  getOnsDadosHidrologicosVolumeEsperaRecomendadoIdentifier(
    onsDadosHidrologicosVolumeEsperaRecomendado: Pick<IOnsDadosHidrologicosVolumeEsperaRecomendado, 'id'>,
  ): number {
    return onsDadosHidrologicosVolumeEsperaRecomendado.id;
  }

  compareOnsDadosHidrologicosVolumeEsperaRecomendado(
    o1: Pick<IOnsDadosHidrologicosVolumeEsperaRecomendado, 'id'> | null,
    o2: Pick<IOnsDadosHidrologicosVolumeEsperaRecomendado, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosHidrologicosVolumeEsperaRecomendadoIdentifier(o1) ===
          this.getOnsDadosHidrologicosVolumeEsperaRecomendadoIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosHidrologicosVolumeEsperaRecomendadoToCollectionIfMissing<
    Type extends Pick<IOnsDadosHidrologicosVolumeEsperaRecomendado, 'id'>,
  >(
    onsDadosHidrologicosVolumeEsperaRecomendadoCollection: Type[],
    ...onsDadosHidrologicosVolumeEsperaRecomendadosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosHidrologicosVolumeEsperaRecomendados: Type[] = onsDadosHidrologicosVolumeEsperaRecomendadosToCheck.filter(isPresent);
    if (onsDadosHidrologicosVolumeEsperaRecomendados.length > 0) {
      const onsDadosHidrologicosVolumeEsperaRecomendadoCollectionIdentifiers = onsDadosHidrologicosVolumeEsperaRecomendadoCollection.map(
        onsDadosHidrologicosVolumeEsperaRecomendadoItem =>
          this.getOnsDadosHidrologicosVolumeEsperaRecomendadoIdentifier(onsDadosHidrologicosVolumeEsperaRecomendadoItem),
      );
      const onsDadosHidrologicosVolumeEsperaRecomendadosToAdd = onsDadosHidrologicosVolumeEsperaRecomendados.filter(
        onsDadosHidrologicosVolumeEsperaRecomendadoItem => {
          const onsDadosHidrologicosVolumeEsperaRecomendadoIdentifier = this.getOnsDadosHidrologicosVolumeEsperaRecomendadoIdentifier(
            onsDadosHidrologicosVolumeEsperaRecomendadoItem,
          );
          if (
            onsDadosHidrologicosVolumeEsperaRecomendadoCollectionIdentifiers.includes(onsDadosHidrologicosVolumeEsperaRecomendadoIdentifier)
          ) {
            return false;
          }
          onsDadosHidrologicosVolumeEsperaRecomendadoCollectionIdentifiers.push(onsDadosHidrologicosVolumeEsperaRecomendadoIdentifier);
          return true;
        },
      );
      return [...onsDadosHidrologicosVolumeEsperaRecomendadosToAdd, ...onsDadosHidrologicosVolumeEsperaRecomendadoCollection];
    }
    return onsDadosHidrologicosVolumeEsperaRecomendadoCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosHidrologicosVolumeEsperaRecomendado
      | NewOnsDadosHidrologicosVolumeEsperaRecomendado
      | PartialUpdateOnsDadosHidrologicosVolumeEsperaRecomendado,
  >(onsDadosHidrologicosVolumeEsperaRecomendado: T): RestOf<T> {
    return {
      ...onsDadosHidrologicosVolumeEsperaRecomendado,
      dinInstante: onsDadosHidrologicosVolumeEsperaRecomendado.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosHidrologicosVolumeEsperaRecomendado: RestOnsDadosHidrologicosVolumeEsperaRecomendado,
  ): IOnsDadosHidrologicosVolumeEsperaRecomendado {
    return {
      ...restOnsDadosHidrologicosVolumeEsperaRecomendado,
      dinInstante: restOnsDadosHidrologicosVolumeEsperaRecomendado.dinInstante
        ? dayjs(restOnsDadosHidrologicosVolumeEsperaRecomendado.dinInstante)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosHidrologicosVolumeEsperaRecomendado>,
  ): HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosHidrologicosVolumeEsperaRecomendado[]>,
  ): HttpResponse<IOnsDadosHidrologicosVolumeEsperaRecomendado[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
