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
import { IOnsIntercambiosEntreSubsistemas, NewOnsIntercambiosEntreSubsistemas } from '../ons-intercambios-entre-subsistemas.model';

export type PartialUpdateOnsIntercambiosEntreSubsistemas = Partial<IOnsIntercambiosEntreSubsistemas> &
  Pick<IOnsIntercambiosEntreSubsistemas, 'id'>;

type RestOf<T extends IOnsIntercambiosEntreSubsistemas | NewOnsIntercambiosEntreSubsistemas> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsIntercambiosEntreSubsistemas = RestOf<IOnsIntercambiosEntreSubsistemas>;

export type NewRestOnsIntercambiosEntreSubsistemas = RestOf<NewOnsIntercambiosEntreSubsistemas>;

export type PartialUpdateRestOnsIntercambiosEntreSubsistemas = RestOf<PartialUpdateOnsIntercambiosEntreSubsistemas>;

export type EntityResponseType = HttpResponse<IOnsIntercambiosEntreSubsistemas>;
export type EntityArrayResponseType = HttpResponse<IOnsIntercambiosEntreSubsistemas[]>;

@Injectable({ providedIn: 'root' })
export class OnsIntercambiosEntreSubsistemasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-intercambios-entre-subsistemas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-intercambios-entre-subsistemas/_search');

  create(onsIntercambiosEntreSubsistemas: NewOnsIntercambiosEntreSubsistemas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIntercambiosEntreSubsistemas);
    return this.http
      .post<RestOnsIntercambiosEntreSubsistemas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsIntercambiosEntreSubsistemas: IOnsIntercambiosEntreSubsistemas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIntercambiosEntreSubsistemas);
    return this.http
      .put<RestOnsIntercambiosEntreSubsistemas>(
        `${this.resourceUrl}/${this.getOnsIntercambiosEntreSubsistemasIdentifier(onsIntercambiosEntreSubsistemas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsIntercambiosEntreSubsistemas: PartialUpdateOnsIntercambiosEntreSubsistemas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsIntercambiosEntreSubsistemas);
    return this.http
      .patch<RestOnsIntercambiosEntreSubsistemas>(
        `${this.resourceUrl}/${this.getOnsIntercambiosEntreSubsistemasIdentifier(onsIntercambiosEntreSubsistemas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsIntercambiosEntreSubsistemas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsIntercambiosEntreSubsistemas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsIntercambiosEntreSubsistemas[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsIntercambiosEntreSubsistemas[]>()], asapScheduler)),
    );
  }

  getOnsIntercambiosEntreSubsistemasIdentifier(onsIntercambiosEntreSubsistemas: Pick<IOnsIntercambiosEntreSubsistemas, 'id'>): number {
    return onsIntercambiosEntreSubsistemas.id;
  }

  compareOnsIntercambiosEntreSubsistemas(
    o1: Pick<IOnsIntercambiosEntreSubsistemas, 'id'> | null,
    o2: Pick<IOnsIntercambiosEntreSubsistemas, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsIntercambiosEntreSubsistemasIdentifier(o1) === this.getOnsIntercambiosEntreSubsistemasIdentifier(o2)
      : o1 === o2;
  }

  addOnsIntercambiosEntreSubsistemasToCollectionIfMissing<Type extends Pick<IOnsIntercambiosEntreSubsistemas, 'id'>>(
    onsIntercambiosEntreSubsistemasCollection: Type[],
    ...onsIntercambiosEntreSubsistemasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsIntercambiosEntreSubsistemas: Type[] = onsIntercambiosEntreSubsistemasToCheck.filter(isPresent);
    if (onsIntercambiosEntreSubsistemas.length > 0) {
      const onsIntercambiosEntreSubsistemasCollectionIdentifiers = onsIntercambiosEntreSubsistemasCollection.map(
        onsIntercambiosEntreSubsistemasItem => this.getOnsIntercambiosEntreSubsistemasIdentifier(onsIntercambiosEntreSubsistemasItem),
      );
      const onsIntercambiosEntreSubsistemasToAdd = onsIntercambiosEntreSubsistemas.filter(onsIntercambiosEntreSubsistemasItem => {
        const onsIntercambiosEntreSubsistemasIdentifier =
          this.getOnsIntercambiosEntreSubsistemasIdentifier(onsIntercambiosEntreSubsistemasItem);
        if (onsIntercambiosEntreSubsistemasCollectionIdentifiers.includes(onsIntercambiosEntreSubsistemasIdentifier)) {
          return false;
        }
        onsIntercambiosEntreSubsistemasCollectionIdentifiers.push(onsIntercambiosEntreSubsistemasIdentifier);
        return true;
      });
      return [...onsIntercambiosEntreSubsistemasToAdd, ...onsIntercambiosEntreSubsistemasCollection];
    }
    return onsIntercambiosEntreSubsistemasCollection;
  }

  protected convertDateFromClient<
    T extends IOnsIntercambiosEntreSubsistemas | NewOnsIntercambiosEntreSubsistemas | PartialUpdateOnsIntercambiosEntreSubsistemas,
  >(onsIntercambiosEntreSubsistemas: T): RestOf<T> {
    return {
      ...onsIntercambiosEntreSubsistemas,
      dinInstante: onsIntercambiosEntreSubsistemas.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsIntercambiosEntreSubsistemas: RestOnsIntercambiosEntreSubsistemas,
  ): IOnsIntercambiosEntreSubsistemas {
    return {
      ...restOnsIntercambiosEntreSubsistemas,
      dinInstante: restOnsIntercambiosEntreSubsistemas.dinInstante ? dayjs(restOnsIntercambiosEntreSubsistemas.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsIntercambiosEntreSubsistemas>,
  ): HttpResponse<IOnsIntercambiosEntreSubsistemas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsIntercambiosEntreSubsistemas[]>,
  ): HttpResponse<IOnsIntercambiosEntreSubsistemas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
