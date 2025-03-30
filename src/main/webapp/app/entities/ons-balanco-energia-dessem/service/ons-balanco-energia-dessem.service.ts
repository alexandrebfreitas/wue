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
import { IOnsBalancoEnergiaDessem, NewOnsBalancoEnergiaDessem } from '../ons-balanco-energia-dessem.model';

export type PartialUpdateOnsBalancoEnergiaDessem = Partial<IOnsBalancoEnergiaDessem> & Pick<IOnsBalancoEnergiaDessem, 'id'>;

type RestOf<T extends IOnsBalancoEnergiaDessem | NewOnsBalancoEnergiaDessem> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsBalancoEnergiaDessem = RestOf<IOnsBalancoEnergiaDessem>;

export type NewRestOnsBalancoEnergiaDessem = RestOf<NewOnsBalancoEnergiaDessem>;

export type PartialUpdateRestOnsBalancoEnergiaDessem = RestOf<PartialUpdateOnsBalancoEnergiaDessem>;

export type EntityResponseType = HttpResponse<IOnsBalancoEnergiaDessem>;
export type EntityArrayResponseType = HttpResponse<IOnsBalancoEnergiaDessem[]>;

@Injectable({ providedIn: 'root' })
export class OnsBalancoEnergiaDessemService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-balanco-energia-dessems');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-balanco-energia-dessems/_search');

  create(onsBalancoEnergiaDessem: NewOnsBalancoEnergiaDessem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsBalancoEnergiaDessem);
    return this.http
      .post<RestOnsBalancoEnergiaDessem>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsBalancoEnergiaDessem: IOnsBalancoEnergiaDessem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsBalancoEnergiaDessem);
    return this.http
      .put<RestOnsBalancoEnergiaDessem>(`${this.resourceUrl}/${this.getOnsBalancoEnergiaDessemIdentifier(onsBalancoEnergiaDessem)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsBalancoEnergiaDessem: PartialUpdateOnsBalancoEnergiaDessem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsBalancoEnergiaDessem);
    return this.http
      .patch<RestOnsBalancoEnergiaDessem>(
        `${this.resourceUrl}/${this.getOnsBalancoEnergiaDessemIdentifier(onsBalancoEnergiaDessem)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsBalancoEnergiaDessem>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsBalancoEnergiaDessem[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsBalancoEnergiaDessem[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsBalancoEnergiaDessem[]>()], asapScheduler)),
    );
  }

  getOnsBalancoEnergiaDessemIdentifier(onsBalancoEnergiaDessem: Pick<IOnsBalancoEnergiaDessem, 'id'>): number {
    return onsBalancoEnergiaDessem.id;
  }

  compareOnsBalancoEnergiaDessem(
    o1: Pick<IOnsBalancoEnergiaDessem, 'id'> | null,
    o2: Pick<IOnsBalancoEnergiaDessem, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getOnsBalancoEnergiaDessemIdentifier(o1) === this.getOnsBalancoEnergiaDessemIdentifier(o2) : o1 === o2;
  }

  addOnsBalancoEnergiaDessemToCollectionIfMissing<Type extends Pick<IOnsBalancoEnergiaDessem, 'id'>>(
    onsBalancoEnergiaDessemCollection: Type[],
    ...onsBalancoEnergiaDessemsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsBalancoEnergiaDessems: Type[] = onsBalancoEnergiaDessemsToCheck.filter(isPresent);
    if (onsBalancoEnergiaDessems.length > 0) {
      const onsBalancoEnergiaDessemCollectionIdentifiers = onsBalancoEnergiaDessemCollection.map(onsBalancoEnergiaDessemItem =>
        this.getOnsBalancoEnergiaDessemIdentifier(onsBalancoEnergiaDessemItem),
      );
      const onsBalancoEnergiaDessemsToAdd = onsBalancoEnergiaDessems.filter(onsBalancoEnergiaDessemItem => {
        const onsBalancoEnergiaDessemIdentifier = this.getOnsBalancoEnergiaDessemIdentifier(onsBalancoEnergiaDessemItem);
        if (onsBalancoEnergiaDessemCollectionIdentifiers.includes(onsBalancoEnergiaDessemIdentifier)) {
          return false;
        }
        onsBalancoEnergiaDessemCollectionIdentifiers.push(onsBalancoEnergiaDessemIdentifier);
        return true;
      });
      return [...onsBalancoEnergiaDessemsToAdd, ...onsBalancoEnergiaDessemCollection];
    }
    return onsBalancoEnergiaDessemCollection;
  }

  protected convertDateFromClient<T extends IOnsBalancoEnergiaDessem | NewOnsBalancoEnergiaDessem | PartialUpdateOnsBalancoEnergiaDessem>(
    onsBalancoEnergiaDessem: T,
  ): RestOf<T> {
    return {
      ...onsBalancoEnergiaDessem,
      dinInstante: onsBalancoEnergiaDessem.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsBalancoEnergiaDessem: RestOnsBalancoEnergiaDessem): IOnsBalancoEnergiaDessem {
    return {
      ...restOnsBalancoEnergiaDessem,
      dinInstante: restOnsBalancoEnergiaDessem.dinInstante ? dayjs(restOnsBalancoEnergiaDessem.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsBalancoEnergiaDessem>): HttpResponse<IOnsBalancoEnergiaDessem> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsBalancoEnergiaDessem[]>): HttpResponse<IOnsBalancoEnergiaDessem[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
