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
import { IOnsCurvaCargaHoraria, NewOnsCurvaCargaHoraria } from '../ons-curva-carga-horaria.model';

export type PartialUpdateOnsCurvaCargaHoraria = Partial<IOnsCurvaCargaHoraria> & Pick<IOnsCurvaCargaHoraria, 'id'>;

type RestOf<T extends IOnsCurvaCargaHoraria | NewOnsCurvaCargaHoraria> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsCurvaCargaHoraria = RestOf<IOnsCurvaCargaHoraria>;

export type NewRestOnsCurvaCargaHoraria = RestOf<NewOnsCurvaCargaHoraria>;

export type PartialUpdateRestOnsCurvaCargaHoraria = RestOf<PartialUpdateOnsCurvaCargaHoraria>;

export type EntityResponseType = HttpResponse<IOnsCurvaCargaHoraria>;
export type EntityArrayResponseType = HttpResponse<IOnsCurvaCargaHoraria[]>;

@Injectable({ providedIn: 'root' })
export class OnsCurvaCargaHorariaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-curva-carga-horarias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-curva-carga-horarias/_search');

  create(onsCurvaCargaHoraria: NewOnsCurvaCargaHoraria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCurvaCargaHoraria);
    return this.http
      .post<RestOnsCurvaCargaHoraria>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCurvaCargaHoraria: IOnsCurvaCargaHoraria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCurvaCargaHoraria);
    return this.http
      .put<RestOnsCurvaCargaHoraria>(`${this.resourceUrl}/${this.getOnsCurvaCargaHorariaIdentifier(onsCurvaCargaHoraria)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCurvaCargaHoraria: PartialUpdateOnsCurvaCargaHoraria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCurvaCargaHoraria);
    return this.http
      .patch<RestOnsCurvaCargaHoraria>(`${this.resourceUrl}/${this.getOnsCurvaCargaHorariaIdentifier(onsCurvaCargaHoraria)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCurvaCargaHoraria>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCurvaCargaHoraria[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCurvaCargaHoraria[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCurvaCargaHoraria[]>()], asapScheduler)),
    );
  }

  getOnsCurvaCargaHorariaIdentifier(onsCurvaCargaHoraria: Pick<IOnsCurvaCargaHoraria, 'id'>): number {
    return onsCurvaCargaHoraria.id;
  }

  compareOnsCurvaCargaHoraria(o1: Pick<IOnsCurvaCargaHoraria, 'id'> | null, o2: Pick<IOnsCurvaCargaHoraria, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsCurvaCargaHorariaIdentifier(o1) === this.getOnsCurvaCargaHorariaIdentifier(o2) : o1 === o2;
  }

  addOnsCurvaCargaHorariaToCollectionIfMissing<Type extends Pick<IOnsCurvaCargaHoraria, 'id'>>(
    onsCurvaCargaHorariaCollection: Type[],
    ...onsCurvaCargaHorariasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCurvaCargaHorarias: Type[] = onsCurvaCargaHorariasToCheck.filter(isPresent);
    if (onsCurvaCargaHorarias.length > 0) {
      const onsCurvaCargaHorariaCollectionIdentifiers = onsCurvaCargaHorariaCollection.map(onsCurvaCargaHorariaItem =>
        this.getOnsCurvaCargaHorariaIdentifier(onsCurvaCargaHorariaItem),
      );
      const onsCurvaCargaHorariasToAdd = onsCurvaCargaHorarias.filter(onsCurvaCargaHorariaItem => {
        const onsCurvaCargaHorariaIdentifier = this.getOnsCurvaCargaHorariaIdentifier(onsCurvaCargaHorariaItem);
        if (onsCurvaCargaHorariaCollectionIdentifiers.includes(onsCurvaCargaHorariaIdentifier)) {
          return false;
        }
        onsCurvaCargaHorariaCollectionIdentifiers.push(onsCurvaCargaHorariaIdentifier);
        return true;
      });
      return [...onsCurvaCargaHorariasToAdd, ...onsCurvaCargaHorariaCollection];
    }
    return onsCurvaCargaHorariaCollection;
  }

  protected convertDateFromClient<T extends IOnsCurvaCargaHoraria | NewOnsCurvaCargaHoraria | PartialUpdateOnsCurvaCargaHoraria>(
    onsCurvaCargaHoraria: T,
  ): RestOf<T> {
    return {
      ...onsCurvaCargaHoraria,
      dinInstante: onsCurvaCargaHoraria.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsCurvaCargaHoraria: RestOnsCurvaCargaHoraria): IOnsCurvaCargaHoraria {
    return {
      ...restOnsCurvaCargaHoraria,
      dinInstante: restOnsCurvaCargaHoraria.dinInstante ? dayjs(restOnsCurvaCargaHoraria.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsCurvaCargaHoraria>): HttpResponse<IOnsCurvaCargaHoraria> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsCurvaCargaHoraria[]>): HttpResponse<IOnsCurvaCargaHoraria[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
