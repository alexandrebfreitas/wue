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
import { IOnsBalancoEnergiaNosSubsistemas, NewOnsBalancoEnergiaNosSubsistemas } from '../ons-balanco-energia-nos-subsistemas.model';

export type PartialUpdateOnsBalancoEnergiaNosSubsistemas = Partial<IOnsBalancoEnergiaNosSubsistemas> &
  Pick<IOnsBalancoEnergiaNosSubsistemas, 'id'>;

type RestOf<T extends IOnsBalancoEnergiaNosSubsistemas | NewOnsBalancoEnergiaNosSubsistemas> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsBalancoEnergiaNosSubsistemas = RestOf<IOnsBalancoEnergiaNosSubsistemas>;

export type NewRestOnsBalancoEnergiaNosSubsistemas = RestOf<NewOnsBalancoEnergiaNosSubsistemas>;

export type PartialUpdateRestOnsBalancoEnergiaNosSubsistemas = RestOf<PartialUpdateOnsBalancoEnergiaNosSubsistemas>;

export type EntityResponseType = HttpResponse<IOnsBalancoEnergiaNosSubsistemas>;
export type EntityArrayResponseType = HttpResponse<IOnsBalancoEnergiaNosSubsistemas[]>;

@Injectable({ providedIn: 'root' })
export class OnsBalancoEnergiaNosSubsistemasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-balanco-energia-nos-subsistemas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-balanco-energia-nos-subsistemas/_search');

  create(onsBalancoEnergiaNosSubsistemas: NewOnsBalancoEnergiaNosSubsistemas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsBalancoEnergiaNosSubsistemas);
    return this.http
      .post<RestOnsBalancoEnergiaNosSubsistemas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsBalancoEnergiaNosSubsistemas: IOnsBalancoEnergiaNosSubsistemas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsBalancoEnergiaNosSubsistemas);
    return this.http
      .put<RestOnsBalancoEnergiaNosSubsistemas>(
        `${this.resourceUrl}/${this.getOnsBalancoEnergiaNosSubsistemasIdentifier(onsBalancoEnergiaNosSubsistemas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsBalancoEnergiaNosSubsistemas: PartialUpdateOnsBalancoEnergiaNosSubsistemas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsBalancoEnergiaNosSubsistemas);
    return this.http
      .patch<RestOnsBalancoEnergiaNosSubsistemas>(
        `${this.resourceUrl}/${this.getOnsBalancoEnergiaNosSubsistemasIdentifier(onsBalancoEnergiaNosSubsistemas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsBalancoEnergiaNosSubsistemas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsBalancoEnergiaNosSubsistemas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsBalancoEnergiaNosSubsistemas[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsBalancoEnergiaNosSubsistemas[]>()], asapScheduler)),
    );
  }

  getOnsBalancoEnergiaNosSubsistemasIdentifier(onsBalancoEnergiaNosSubsistemas: Pick<IOnsBalancoEnergiaNosSubsistemas, 'id'>): number {
    return onsBalancoEnergiaNosSubsistemas.id;
  }

  compareOnsBalancoEnergiaNosSubsistemas(
    o1: Pick<IOnsBalancoEnergiaNosSubsistemas, 'id'> | null,
    o2: Pick<IOnsBalancoEnergiaNosSubsistemas, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsBalancoEnergiaNosSubsistemasIdentifier(o1) === this.getOnsBalancoEnergiaNosSubsistemasIdentifier(o2)
      : o1 === o2;
  }

  addOnsBalancoEnergiaNosSubsistemasToCollectionIfMissing<Type extends Pick<IOnsBalancoEnergiaNosSubsistemas, 'id'>>(
    onsBalancoEnergiaNosSubsistemasCollection: Type[],
    ...onsBalancoEnergiaNosSubsistemasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsBalancoEnergiaNosSubsistemas: Type[] = onsBalancoEnergiaNosSubsistemasToCheck.filter(isPresent);
    if (onsBalancoEnergiaNosSubsistemas.length > 0) {
      const onsBalancoEnergiaNosSubsistemasCollectionIdentifiers = onsBalancoEnergiaNosSubsistemasCollection.map(
        onsBalancoEnergiaNosSubsistemasItem => this.getOnsBalancoEnergiaNosSubsistemasIdentifier(onsBalancoEnergiaNosSubsistemasItem),
      );
      const onsBalancoEnergiaNosSubsistemasToAdd = onsBalancoEnergiaNosSubsistemas.filter(onsBalancoEnergiaNosSubsistemasItem => {
        const onsBalancoEnergiaNosSubsistemasIdentifier =
          this.getOnsBalancoEnergiaNosSubsistemasIdentifier(onsBalancoEnergiaNosSubsistemasItem);
        if (onsBalancoEnergiaNosSubsistemasCollectionIdentifiers.includes(onsBalancoEnergiaNosSubsistemasIdentifier)) {
          return false;
        }
        onsBalancoEnergiaNosSubsistemasCollectionIdentifiers.push(onsBalancoEnergiaNosSubsistemasIdentifier);
        return true;
      });
      return [...onsBalancoEnergiaNosSubsistemasToAdd, ...onsBalancoEnergiaNosSubsistemasCollection];
    }
    return onsBalancoEnergiaNosSubsistemasCollection;
  }

  protected convertDateFromClient<
    T extends IOnsBalancoEnergiaNosSubsistemas | NewOnsBalancoEnergiaNosSubsistemas | PartialUpdateOnsBalancoEnergiaNosSubsistemas,
  >(onsBalancoEnergiaNosSubsistemas: T): RestOf<T> {
    return {
      ...onsBalancoEnergiaNosSubsistemas,
      dinInstante: onsBalancoEnergiaNosSubsistemas.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsBalancoEnergiaNosSubsistemas: RestOnsBalancoEnergiaNosSubsistemas,
  ): IOnsBalancoEnergiaNosSubsistemas {
    return {
      ...restOnsBalancoEnergiaNosSubsistemas,
      dinInstante: restOnsBalancoEnergiaNosSubsistemas.dinInstante ? dayjs(restOnsBalancoEnergiaNosSubsistemas.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsBalancoEnergiaNosSubsistemas>,
  ): HttpResponse<IOnsBalancoEnergiaNosSubsistemas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsBalancoEnergiaNosSubsistemas[]>,
  ): HttpResponse<IOnsBalancoEnergiaNosSubsistemas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
