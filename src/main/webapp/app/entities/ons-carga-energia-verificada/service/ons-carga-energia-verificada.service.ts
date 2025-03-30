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
import { IOnsCargaEnergiaVerificada, NewOnsCargaEnergiaVerificada } from '../ons-carga-energia-verificada.model';

export type PartialUpdateOnsCargaEnergiaVerificada = Partial<IOnsCargaEnergiaVerificada> & Pick<IOnsCargaEnergiaVerificada, 'id'>;

type RestOf<T extends IOnsCargaEnergiaVerificada | NewOnsCargaEnergiaVerificada> = Omit<T, 'datReferencia' | 'dinReferenciautc'> & {
  datReferencia?: string | null;
  dinReferenciautc?: string | null;
};

export type RestOnsCargaEnergiaVerificada = RestOf<IOnsCargaEnergiaVerificada>;

export type NewRestOnsCargaEnergiaVerificada = RestOf<NewOnsCargaEnergiaVerificada>;

export type PartialUpdateRestOnsCargaEnergiaVerificada = RestOf<PartialUpdateOnsCargaEnergiaVerificada>;

export type EntityResponseType = HttpResponse<IOnsCargaEnergiaVerificada>;
export type EntityArrayResponseType = HttpResponse<IOnsCargaEnergiaVerificada[]>;

@Injectable({ providedIn: 'root' })
export class OnsCargaEnergiaVerificadaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-carga-energia-verificadas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-carga-energia-verificadas/_search');

  create(onsCargaEnergiaVerificada: NewOnsCargaEnergiaVerificada): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaVerificada);
    return this.http
      .post<RestOnsCargaEnergiaVerificada>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCargaEnergiaVerificada: IOnsCargaEnergiaVerificada): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaVerificada);
    return this.http
      .put<RestOnsCargaEnergiaVerificada>(
        `${this.resourceUrl}/${this.getOnsCargaEnergiaVerificadaIdentifier(onsCargaEnergiaVerificada)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCargaEnergiaVerificada: PartialUpdateOnsCargaEnergiaVerificada): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaVerificada);
    return this.http
      .patch<RestOnsCargaEnergiaVerificada>(
        `${this.resourceUrl}/${this.getOnsCargaEnergiaVerificadaIdentifier(onsCargaEnergiaVerificada)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCargaEnergiaVerificada>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCargaEnergiaVerificada[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCargaEnergiaVerificada[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCargaEnergiaVerificada[]>()], asapScheduler)),
    );
  }

  getOnsCargaEnergiaVerificadaIdentifier(onsCargaEnergiaVerificada: Pick<IOnsCargaEnergiaVerificada, 'id'>): number {
    return onsCargaEnergiaVerificada.id;
  }

  compareOnsCargaEnergiaVerificada(
    o1: Pick<IOnsCargaEnergiaVerificada, 'id'> | null,
    o2: Pick<IOnsCargaEnergiaVerificada, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getOnsCargaEnergiaVerificadaIdentifier(o1) === this.getOnsCargaEnergiaVerificadaIdentifier(o2) : o1 === o2;
  }

  addOnsCargaEnergiaVerificadaToCollectionIfMissing<Type extends Pick<IOnsCargaEnergiaVerificada, 'id'>>(
    onsCargaEnergiaVerificadaCollection: Type[],
    ...onsCargaEnergiaVerificadasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCargaEnergiaVerificadas: Type[] = onsCargaEnergiaVerificadasToCheck.filter(isPresent);
    if (onsCargaEnergiaVerificadas.length > 0) {
      const onsCargaEnergiaVerificadaCollectionIdentifiers = onsCargaEnergiaVerificadaCollection.map(onsCargaEnergiaVerificadaItem =>
        this.getOnsCargaEnergiaVerificadaIdentifier(onsCargaEnergiaVerificadaItem),
      );
      const onsCargaEnergiaVerificadasToAdd = onsCargaEnergiaVerificadas.filter(onsCargaEnergiaVerificadaItem => {
        const onsCargaEnergiaVerificadaIdentifier = this.getOnsCargaEnergiaVerificadaIdentifier(onsCargaEnergiaVerificadaItem);
        if (onsCargaEnergiaVerificadaCollectionIdentifiers.includes(onsCargaEnergiaVerificadaIdentifier)) {
          return false;
        }
        onsCargaEnergiaVerificadaCollectionIdentifiers.push(onsCargaEnergiaVerificadaIdentifier);
        return true;
      });
      return [...onsCargaEnergiaVerificadasToAdd, ...onsCargaEnergiaVerificadaCollection];
    }
    return onsCargaEnergiaVerificadaCollection;
  }

  protected convertDateFromClient<
    T extends IOnsCargaEnergiaVerificada | NewOnsCargaEnergiaVerificada | PartialUpdateOnsCargaEnergiaVerificada,
  >(onsCargaEnergiaVerificada: T): RestOf<T> {
    return {
      ...onsCargaEnergiaVerificada,
      datReferencia: onsCargaEnergiaVerificada.datReferencia?.format(DATE_FORMAT) ?? null,
      dinReferenciautc: onsCargaEnergiaVerificada.dinReferenciautc?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsCargaEnergiaVerificada: RestOnsCargaEnergiaVerificada): IOnsCargaEnergiaVerificada {
    return {
      ...restOnsCargaEnergiaVerificada,
      datReferencia: restOnsCargaEnergiaVerificada.datReferencia ? dayjs(restOnsCargaEnergiaVerificada.datReferencia) : undefined,
      dinReferenciautc: restOnsCargaEnergiaVerificada.dinReferenciautc ? dayjs(restOnsCargaEnergiaVerificada.dinReferenciautc) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsCargaEnergiaVerificada>): HttpResponse<IOnsCargaEnergiaVerificada> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsCargaEnergiaVerificada[]>): HttpResponse<IOnsCargaEnergiaVerificada[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
