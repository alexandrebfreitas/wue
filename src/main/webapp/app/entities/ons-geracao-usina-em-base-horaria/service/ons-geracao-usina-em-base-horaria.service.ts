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
import { IOnsGeracaoUsinaEmBaseHoraria, NewOnsGeracaoUsinaEmBaseHoraria } from '../ons-geracao-usina-em-base-horaria.model';

export type PartialUpdateOnsGeracaoUsinaEmBaseHoraria = Partial<IOnsGeracaoUsinaEmBaseHoraria> & Pick<IOnsGeracaoUsinaEmBaseHoraria, 'id'>;

type RestOf<T extends IOnsGeracaoUsinaEmBaseHoraria | NewOnsGeracaoUsinaEmBaseHoraria> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsGeracaoUsinaEmBaseHoraria = RestOf<IOnsGeracaoUsinaEmBaseHoraria>;

export type NewRestOnsGeracaoUsinaEmBaseHoraria = RestOf<NewOnsGeracaoUsinaEmBaseHoraria>;

export type PartialUpdateRestOnsGeracaoUsinaEmBaseHoraria = RestOf<PartialUpdateOnsGeracaoUsinaEmBaseHoraria>;

export type EntityResponseType = HttpResponse<IOnsGeracaoUsinaEmBaseHoraria>;
export type EntityArrayResponseType = HttpResponse<IOnsGeracaoUsinaEmBaseHoraria[]>;

@Injectable({ providedIn: 'root' })
export class OnsGeracaoUsinaEmBaseHorariaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-geracao-usina-em-base-horarias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-geracao-usina-em-base-horarias/_search');

  create(onsGeracaoUsinaEmBaseHoraria: NewOnsGeracaoUsinaEmBaseHoraria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsGeracaoUsinaEmBaseHoraria);
    return this.http
      .post<RestOnsGeracaoUsinaEmBaseHoraria>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsGeracaoUsinaEmBaseHoraria: IOnsGeracaoUsinaEmBaseHoraria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsGeracaoUsinaEmBaseHoraria);
    return this.http
      .put<RestOnsGeracaoUsinaEmBaseHoraria>(
        `${this.resourceUrl}/${this.getOnsGeracaoUsinaEmBaseHorariaIdentifier(onsGeracaoUsinaEmBaseHoraria)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsGeracaoUsinaEmBaseHoraria: PartialUpdateOnsGeracaoUsinaEmBaseHoraria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsGeracaoUsinaEmBaseHoraria);
    return this.http
      .patch<RestOnsGeracaoUsinaEmBaseHoraria>(
        `${this.resourceUrl}/${this.getOnsGeracaoUsinaEmBaseHorariaIdentifier(onsGeracaoUsinaEmBaseHoraria)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsGeracaoUsinaEmBaseHoraria>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsGeracaoUsinaEmBaseHoraria[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsGeracaoUsinaEmBaseHoraria[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsGeracaoUsinaEmBaseHoraria[]>()], asapScheduler)),
    );
  }

  getOnsGeracaoUsinaEmBaseHorariaIdentifier(onsGeracaoUsinaEmBaseHoraria: Pick<IOnsGeracaoUsinaEmBaseHoraria, 'id'>): number {
    return onsGeracaoUsinaEmBaseHoraria.id;
  }

  compareOnsGeracaoUsinaEmBaseHoraria(
    o1: Pick<IOnsGeracaoUsinaEmBaseHoraria, 'id'> | null,
    o2: Pick<IOnsGeracaoUsinaEmBaseHoraria, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getOnsGeracaoUsinaEmBaseHorariaIdentifier(o1) === this.getOnsGeracaoUsinaEmBaseHorariaIdentifier(o2) : o1 === o2;
  }

  addOnsGeracaoUsinaEmBaseHorariaToCollectionIfMissing<Type extends Pick<IOnsGeracaoUsinaEmBaseHoraria, 'id'>>(
    onsGeracaoUsinaEmBaseHorariaCollection: Type[],
    ...onsGeracaoUsinaEmBaseHorariasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsGeracaoUsinaEmBaseHorarias: Type[] = onsGeracaoUsinaEmBaseHorariasToCheck.filter(isPresent);
    if (onsGeracaoUsinaEmBaseHorarias.length > 0) {
      const onsGeracaoUsinaEmBaseHorariaCollectionIdentifiers = onsGeracaoUsinaEmBaseHorariaCollection.map(
        onsGeracaoUsinaEmBaseHorariaItem => this.getOnsGeracaoUsinaEmBaseHorariaIdentifier(onsGeracaoUsinaEmBaseHorariaItem),
      );
      const onsGeracaoUsinaEmBaseHorariasToAdd = onsGeracaoUsinaEmBaseHorarias.filter(onsGeracaoUsinaEmBaseHorariaItem => {
        const onsGeracaoUsinaEmBaseHorariaIdentifier = this.getOnsGeracaoUsinaEmBaseHorariaIdentifier(onsGeracaoUsinaEmBaseHorariaItem);
        if (onsGeracaoUsinaEmBaseHorariaCollectionIdentifiers.includes(onsGeracaoUsinaEmBaseHorariaIdentifier)) {
          return false;
        }
        onsGeracaoUsinaEmBaseHorariaCollectionIdentifiers.push(onsGeracaoUsinaEmBaseHorariaIdentifier);
        return true;
      });
      return [...onsGeracaoUsinaEmBaseHorariasToAdd, ...onsGeracaoUsinaEmBaseHorariaCollection];
    }
    return onsGeracaoUsinaEmBaseHorariaCollection;
  }

  protected convertDateFromClient<
    T extends IOnsGeracaoUsinaEmBaseHoraria | NewOnsGeracaoUsinaEmBaseHoraria | PartialUpdateOnsGeracaoUsinaEmBaseHoraria,
  >(onsGeracaoUsinaEmBaseHoraria: T): RestOf<T> {
    return {
      ...onsGeracaoUsinaEmBaseHoraria,
      dinInstante: onsGeracaoUsinaEmBaseHoraria.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsGeracaoUsinaEmBaseHoraria: RestOnsGeracaoUsinaEmBaseHoraria): IOnsGeracaoUsinaEmBaseHoraria {
    return {
      ...restOnsGeracaoUsinaEmBaseHoraria,
      dinInstante: restOnsGeracaoUsinaEmBaseHoraria.dinInstante ? dayjs(restOnsGeracaoUsinaEmBaseHoraria.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsGeracaoUsinaEmBaseHoraria>): HttpResponse<IOnsGeracaoUsinaEmBaseHoraria> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsGeracaoUsinaEmBaseHoraria[]>,
  ): HttpResponse<IOnsGeracaoUsinaEmBaseHoraria[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
