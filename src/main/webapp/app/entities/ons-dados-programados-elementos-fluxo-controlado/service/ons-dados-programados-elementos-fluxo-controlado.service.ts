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
  IOnsDadosProgramadosElementosFluxoControlado,
  NewOnsDadosProgramadosElementosFluxoControlado,
} from '../ons-dados-programados-elementos-fluxo-controlado.model';

export type PartialUpdateOnsDadosProgramadosElementosFluxoControlado = Partial<IOnsDadosProgramadosElementosFluxoControlado> &
  Pick<IOnsDadosProgramadosElementosFluxoControlado, 'id'>;

type RestOf<T extends IOnsDadosProgramadosElementosFluxoControlado | NewOnsDadosProgramadosElementosFluxoControlado> = Omit<
  T,
  'dinProgramacaodia'
> & {
  dinProgramacaodia?: string | null;
};

export type RestOnsDadosProgramadosElementosFluxoControlado = RestOf<IOnsDadosProgramadosElementosFluxoControlado>;

export type NewRestOnsDadosProgramadosElementosFluxoControlado = RestOf<NewOnsDadosProgramadosElementosFluxoControlado>;

export type PartialUpdateRestOnsDadosProgramadosElementosFluxoControlado = RestOf<PartialUpdateOnsDadosProgramadosElementosFluxoControlado>;

export type EntityResponseType = HttpResponse<IOnsDadosProgramadosElementosFluxoControlado>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosProgramadosElementosFluxoControlado[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosProgramadosElementosFluxoControladoService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-programados-elementos-fluxo-controlados');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-programados-elementos-fluxo-controlados/_search',
  );

  create(onsDadosProgramadosElementosFluxoControlado: NewOnsDadosProgramadosElementosFluxoControlado): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosProgramadosElementosFluxoControlado);
    return this.http
      .post<RestOnsDadosProgramadosElementosFluxoControlado>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsDadosProgramadosElementosFluxoControlado: IOnsDadosProgramadosElementosFluxoControlado): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosProgramadosElementosFluxoControlado);
    return this.http
      .put<RestOnsDadosProgramadosElementosFluxoControlado>(
        `${this.resourceUrl}/${this.getOnsDadosProgramadosElementosFluxoControladoIdentifier(onsDadosProgramadosElementosFluxoControlado)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosProgramadosElementosFluxoControlado: PartialUpdateOnsDadosProgramadosElementosFluxoControlado,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosProgramadosElementosFluxoControlado);
    return this.http
      .patch<RestOnsDadosProgramadosElementosFluxoControlado>(
        `${this.resourceUrl}/${this.getOnsDadosProgramadosElementosFluxoControladoIdentifier(onsDadosProgramadosElementosFluxoControlado)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosProgramadosElementosFluxoControlado>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosProgramadosElementosFluxoControlado[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosProgramadosElementosFluxoControlado[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsDadosProgramadosElementosFluxoControlado[]>()], asapScheduler)),
      );
  }

  getOnsDadosProgramadosElementosFluxoControladoIdentifier(
    onsDadosProgramadosElementosFluxoControlado: Pick<IOnsDadosProgramadosElementosFluxoControlado, 'id'>,
  ): number {
    return onsDadosProgramadosElementosFluxoControlado.id;
  }

  compareOnsDadosProgramadosElementosFluxoControlado(
    o1: Pick<IOnsDadosProgramadosElementosFluxoControlado, 'id'> | null,
    o2: Pick<IOnsDadosProgramadosElementosFluxoControlado, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosProgramadosElementosFluxoControladoIdentifier(o1) ===
          this.getOnsDadosProgramadosElementosFluxoControladoIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosProgramadosElementosFluxoControladoToCollectionIfMissing<
    Type extends Pick<IOnsDadosProgramadosElementosFluxoControlado, 'id'>,
  >(
    onsDadosProgramadosElementosFluxoControladoCollection: Type[],
    ...onsDadosProgramadosElementosFluxoControladosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosProgramadosElementosFluxoControlados: Type[] = onsDadosProgramadosElementosFluxoControladosToCheck.filter(isPresent);
    if (onsDadosProgramadosElementosFluxoControlados.length > 0) {
      const onsDadosProgramadosElementosFluxoControladoCollectionIdentifiers = onsDadosProgramadosElementosFluxoControladoCollection.map(
        onsDadosProgramadosElementosFluxoControladoItem =>
          this.getOnsDadosProgramadosElementosFluxoControladoIdentifier(onsDadosProgramadosElementosFluxoControladoItem),
      );
      const onsDadosProgramadosElementosFluxoControladosToAdd = onsDadosProgramadosElementosFluxoControlados.filter(
        onsDadosProgramadosElementosFluxoControladoItem => {
          const onsDadosProgramadosElementosFluxoControladoIdentifier = this.getOnsDadosProgramadosElementosFluxoControladoIdentifier(
            onsDadosProgramadosElementosFluxoControladoItem,
          );
          if (
            onsDadosProgramadosElementosFluxoControladoCollectionIdentifiers.includes(onsDadosProgramadosElementosFluxoControladoIdentifier)
          ) {
            return false;
          }
          onsDadosProgramadosElementosFluxoControladoCollectionIdentifiers.push(onsDadosProgramadosElementosFluxoControladoIdentifier);
          return true;
        },
      );
      return [...onsDadosProgramadosElementosFluxoControladosToAdd, ...onsDadosProgramadosElementosFluxoControladoCollection];
    }
    return onsDadosProgramadosElementosFluxoControladoCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosProgramadosElementosFluxoControlado
      | NewOnsDadosProgramadosElementosFluxoControlado
      | PartialUpdateOnsDadosProgramadosElementosFluxoControlado,
  >(onsDadosProgramadosElementosFluxoControlado: T): RestOf<T> {
    return {
      ...onsDadosProgramadosElementosFluxoControlado,
      dinProgramacaodia: onsDadosProgramadosElementosFluxoControlado.dinProgramacaodia?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosProgramadosElementosFluxoControlado: RestOnsDadosProgramadosElementosFluxoControlado,
  ): IOnsDadosProgramadosElementosFluxoControlado {
    return {
      ...restOnsDadosProgramadosElementosFluxoControlado,
      dinProgramacaodia: restOnsDadosProgramadosElementosFluxoControlado.dinProgramacaodia
        ? dayjs(restOnsDadosProgramadosElementosFluxoControlado.dinProgramacaodia)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosProgramadosElementosFluxoControlado>,
  ): HttpResponse<IOnsDadosProgramadosElementosFluxoControlado> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosProgramadosElementosFluxoControlado[]>,
  ): HttpResponse<IOnsDadosProgramadosElementosFluxoControlado[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
