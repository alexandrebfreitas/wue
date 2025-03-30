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
import { IOnsCargaEnergiaDiaria, NewOnsCargaEnergiaDiaria } from '../ons-carga-energia-diaria.model';

export type PartialUpdateOnsCargaEnergiaDiaria = Partial<IOnsCargaEnergiaDiaria> & Pick<IOnsCargaEnergiaDiaria, 'id'>;

type RestOf<T extends IOnsCargaEnergiaDiaria | NewOnsCargaEnergiaDiaria> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsCargaEnergiaDiaria = RestOf<IOnsCargaEnergiaDiaria>;

export type NewRestOnsCargaEnergiaDiaria = RestOf<NewOnsCargaEnergiaDiaria>;

export type PartialUpdateRestOnsCargaEnergiaDiaria = RestOf<PartialUpdateOnsCargaEnergiaDiaria>;

export type EntityResponseType = HttpResponse<IOnsCargaEnergiaDiaria>;
export type EntityArrayResponseType = HttpResponse<IOnsCargaEnergiaDiaria[]>;

@Injectable({ providedIn: 'root' })
export class OnsCargaEnergiaDiariaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-carga-energia-diarias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-carga-energia-diarias/_search');

  create(onsCargaEnergiaDiaria: NewOnsCargaEnergiaDiaria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaDiaria);
    return this.http
      .post<RestOnsCargaEnergiaDiaria>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCargaEnergiaDiaria: IOnsCargaEnergiaDiaria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaDiaria);
    return this.http
      .put<RestOnsCargaEnergiaDiaria>(`${this.resourceUrl}/${this.getOnsCargaEnergiaDiariaIdentifier(onsCargaEnergiaDiaria)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCargaEnergiaDiaria: PartialUpdateOnsCargaEnergiaDiaria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCargaEnergiaDiaria);
    return this.http
      .patch<RestOnsCargaEnergiaDiaria>(`${this.resourceUrl}/${this.getOnsCargaEnergiaDiariaIdentifier(onsCargaEnergiaDiaria)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCargaEnergiaDiaria>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCargaEnergiaDiaria[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCargaEnergiaDiaria[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCargaEnergiaDiaria[]>()], asapScheduler)),
    );
  }

  getOnsCargaEnergiaDiariaIdentifier(onsCargaEnergiaDiaria: Pick<IOnsCargaEnergiaDiaria, 'id'>): number {
    return onsCargaEnergiaDiaria.id;
  }

  compareOnsCargaEnergiaDiaria(o1: Pick<IOnsCargaEnergiaDiaria, 'id'> | null, o2: Pick<IOnsCargaEnergiaDiaria, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsCargaEnergiaDiariaIdentifier(o1) === this.getOnsCargaEnergiaDiariaIdentifier(o2) : o1 === o2;
  }

  addOnsCargaEnergiaDiariaToCollectionIfMissing<Type extends Pick<IOnsCargaEnergiaDiaria, 'id'>>(
    onsCargaEnergiaDiariaCollection: Type[],
    ...onsCargaEnergiaDiariasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCargaEnergiaDiarias: Type[] = onsCargaEnergiaDiariasToCheck.filter(isPresent);
    if (onsCargaEnergiaDiarias.length > 0) {
      const onsCargaEnergiaDiariaCollectionIdentifiers = onsCargaEnergiaDiariaCollection.map(onsCargaEnergiaDiariaItem =>
        this.getOnsCargaEnergiaDiariaIdentifier(onsCargaEnergiaDiariaItem),
      );
      const onsCargaEnergiaDiariasToAdd = onsCargaEnergiaDiarias.filter(onsCargaEnergiaDiariaItem => {
        const onsCargaEnergiaDiariaIdentifier = this.getOnsCargaEnergiaDiariaIdentifier(onsCargaEnergiaDiariaItem);
        if (onsCargaEnergiaDiariaCollectionIdentifiers.includes(onsCargaEnergiaDiariaIdentifier)) {
          return false;
        }
        onsCargaEnergiaDiariaCollectionIdentifiers.push(onsCargaEnergiaDiariaIdentifier);
        return true;
      });
      return [...onsCargaEnergiaDiariasToAdd, ...onsCargaEnergiaDiariaCollection];
    }
    return onsCargaEnergiaDiariaCollection;
  }

  protected convertDateFromClient<T extends IOnsCargaEnergiaDiaria | NewOnsCargaEnergiaDiaria | PartialUpdateOnsCargaEnergiaDiaria>(
    onsCargaEnergiaDiaria: T,
  ): RestOf<T> {
    return {
      ...onsCargaEnergiaDiaria,
      dinInstante: onsCargaEnergiaDiaria.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsCargaEnergiaDiaria: RestOnsCargaEnergiaDiaria): IOnsCargaEnergiaDiaria {
    return {
      ...restOnsCargaEnergiaDiaria,
      dinInstante: restOnsCargaEnergiaDiaria.dinInstante ? dayjs(restOnsCargaEnergiaDiaria.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsCargaEnergiaDiaria>): HttpResponse<IOnsCargaEnergiaDiaria> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsCargaEnergiaDiaria[]>): HttpResponse<IOnsCargaEnergiaDiaria[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
