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
import { IOnsCargaEnergiaProgramada, NewOnsCargaEnergiaProgramada } from '../ons-carga-energia-programada.model';

export type PartialUpdateOnsCargaEnergiaProgramada = Partial<IOnsCargaEnergiaProgramada> & Pick<IOnsCargaEnergiaProgramada, 'id'>;

type RestOf<T extends IOnsCargaEnergiaProgramada | NewOnsCargaEnergiaProgramada> = Omit<T, 'datReferencia' | 'dinReferenciautc'> & {
  datReferencia?: string | null;
  dinReferenciautc?: string | null;
};

export type RestOnsCargaEnergiaProgramada = RestOf<IOnsCargaEnergiaProgramada>;

export type NewRestOnsCargaEnergiaProgramada = RestOf<NewOnsCargaEnergiaProgramada>;

export type PartialUpdateRestOnsCargaEnergiaProgramada = RestOf<PartialUpdateOnsCargaEnergiaProgramada>;

export type EntityResponseType = HttpResponse<IOnsCargaEnergiaProgramada>;
export type EntityArrayResponseType = HttpResponse<IOnsCargaEnergiaProgramada[]>;

@Injectable({ providedIn: 'root' })
export class OnsCargaEnergiaProgramadaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-carga-energia-programadas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-carga-energia-programadas/_search');

  create(onsCargaEnergiaProgramada: NewOnsCargaEnergiaProgramada): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaProgramada);
    return this.http
      .post<RestOnsCargaEnergiaProgramada>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCargaEnergiaProgramada: IOnsCargaEnergiaProgramada): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaProgramada);
    return this.http
      .put<RestOnsCargaEnergiaProgramada>(
        `${this.resourceUrl}/${this.getOnsCargaEnergiaProgramadaIdentifier(onsCargaEnergiaProgramada)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCargaEnergiaProgramada: PartialUpdateOnsCargaEnergiaProgramada): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaProgramada);
    return this.http
      .patch<RestOnsCargaEnergiaProgramada>(
        `${this.resourceUrl}/${this.getOnsCargaEnergiaProgramadaIdentifier(onsCargaEnergiaProgramada)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCargaEnergiaProgramada>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCargaEnergiaProgramada[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCargaEnergiaProgramada[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCargaEnergiaProgramada[]>()], asapScheduler)),
    );
  }

  getOnsCargaEnergiaProgramadaIdentifier(onsCargaEnergiaProgramada: Pick<IOnsCargaEnergiaProgramada, 'id'>): number {
    return onsCargaEnergiaProgramada.id;
  }

  compareOnsCargaEnergiaProgramada(
    o1: Pick<IOnsCargaEnergiaProgramada, 'id'> | null,
    o2: Pick<IOnsCargaEnergiaProgramada, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getOnsCargaEnergiaProgramadaIdentifier(o1) === this.getOnsCargaEnergiaProgramadaIdentifier(o2) : o1 === o2;
  }

  addOnsCargaEnergiaProgramadaToCollectionIfMissing<Type extends Pick<IOnsCargaEnergiaProgramada, 'id'>>(
    onsCargaEnergiaProgramadaCollection: Type[],
    ...onsCargaEnergiaProgramadasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCargaEnergiaProgramadas: Type[] = onsCargaEnergiaProgramadasToCheck.filter(isPresent);
    if (onsCargaEnergiaProgramadas.length > 0) {
      const onsCargaEnergiaProgramadaCollectionIdentifiers = onsCargaEnergiaProgramadaCollection.map(onsCargaEnergiaProgramadaItem =>
        this.getOnsCargaEnergiaProgramadaIdentifier(onsCargaEnergiaProgramadaItem),
      );
      const onsCargaEnergiaProgramadasToAdd = onsCargaEnergiaProgramadas.filter(onsCargaEnergiaProgramadaItem => {
        const onsCargaEnergiaProgramadaIdentifier = this.getOnsCargaEnergiaProgramadaIdentifier(onsCargaEnergiaProgramadaItem);
        if (onsCargaEnergiaProgramadaCollectionIdentifiers.includes(onsCargaEnergiaProgramadaIdentifier)) {
          return false;
        }
        onsCargaEnergiaProgramadaCollectionIdentifiers.push(onsCargaEnergiaProgramadaIdentifier);
        return true;
      });
      return [...onsCargaEnergiaProgramadasToAdd, ...onsCargaEnergiaProgramadaCollection];
    }
    return onsCargaEnergiaProgramadaCollection;
  }

  protected convertDateFromClient<
    T extends IOnsCargaEnergiaProgramada | NewOnsCargaEnergiaProgramada | PartialUpdateOnsCargaEnergiaProgramada,
  >(onsCargaEnergiaProgramada: T): RestOf<T> {
    return {
      ...onsCargaEnergiaProgramada,
      datReferencia: onsCargaEnergiaProgramada.datReferencia?.format(DATE_FORMAT) ?? null,
      dinReferenciautc: onsCargaEnergiaProgramada.dinReferenciautc?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsCargaEnergiaProgramada: RestOnsCargaEnergiaProgramada): IOnsCargaEnergiaProgramada {
    return {
      ...restOnsCargaEnergiaProgramada,
      datReferencia: restOnsCargaEnergiaProgramada.datReferencia ? dayjs(restOnsCargaEnergiaProgramada.datReferencia) : undefined,
      dinReferenciautc: restOnsCargaEnergiaProgramada.dinReferenciautc ? dayjs(restOnsCargaEnergiaProgramada.dinReferenciautc) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsCargaEnergiaProgramada>): HttpResponse<IOnsCargaEnergiaProgramada> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsCargaEnergiaProgramada[]>): HttpResponse<IOnsCargaEnergiaProgramada[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
