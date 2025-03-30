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
import { IOnsEarDiarioBacia, NewOnsEarDiarioBacia } from '../ons-ear-diario-bacia.model';

export type PartialUpdateOnsEarDiarioBacia = Partial<IOnsEarDiarioBacia> & Pick<IOnsEarDiarioBacia, 'id'>;

type RestOf<T extends IOnsEarDiarioBacia | NewOnsEarDiarioBacia> = Omit<T, 'earData'> & {
  earData?: string | null;
};

export type RestOnsEarDiarioBacia = RestOf<IOnsEarDiarioBacia>;

export type NewRestOnsEarDiarioBacia = RestOf<NewOnsEarDiarioBacia>;

export type PartialUpdateRestOnsEarDiarioBacia = RestOf<PartialUpdateOnsEarDiarioBacia>;

export type EntityResponseType = HttpResponse<IOnsEarDiarioBacia>;
export type EntityArrayResponseType = HttpResponse<IOnsEarDiarioBacia[]>;

@Injectable({ providedIn: 'root' })
export class OnsEarDiarioBaciaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-ear-diario-bacias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-ear-diario-bacias/_search');

  create(onsEarDiarioBacia: NewOnsEarDiarioBacia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioBacia);
    return this.http
      .post<RestOnsEarDiarioBacia>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsEarDiarioBacia: IOnsEarDiarioBacia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioBacia);
    return this.http
      .put<RestOnsEarDiarioBacia>(`${this.resourceUrl}/${this.getOnsEarDiarioBaciaIdentifier(onsEarDiarioBacia)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsEarDiarioBacia: PartialUpdateOnsEarDiarioBacia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioBacia);
    return this.http
      .patch<RestOnsEarDiarioBacia>(`${this.resourceUrl}/${this.getOnsEarDiarioBaciaIdentifier(onsEarDiarioBacia)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsEarDiarioBacia>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsEarDiarioBacia[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsEarDiarioBacia[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsEarDiarioBacia[]>()], asapScheduler)),
    );
  }

  getOnsEarDiarioBaciaIdentifier(onsEarDiarioBacia: Pick<IOnsEarDiarioBacia, 'id'>): number {
    return onsEarDiarioBacia.id;
  }

  compareOnsEarDiarioBacia(o1: Pick<IOnsEarDiarioBacia, 'id'> | null, o2: Pick<IOnsEarDiarioBacia, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsEarDiarioBaciaIdentifier(o1) === this.getOnsEarDiarioBaciaIdentifier(o2) : o1 === o2;
  }

  addOnsEarDiarioBaciaToCollectionIfMissing<Type extends Pick<IOnsEarDiarioBacia, 'id'>>(
    onsEarDiarioBaciaCollection: Type[],
    ...onsEarDiarioBaciasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsEarDiarioBacias: Type[] = onsEarDiarioBaciasToCheck.filter(isPresent);
    if (onsEarDiarioBacias.length > 0) {
      const onsEarDiarioBaciaCollectionIdentifiers = onsEarDiarioBaciaCollection.map(onsEarDiarioBaciaItem =>
        this.getOnsEarDiarioBaciaIdentifier(onsEarDiarioBaciaItem),
      );
      const onsEarDiarioBaciasToAdd = onsEarDiarioBacias.filter(onsEarDiarioBaciaItem => {
        const onsEarDiarioBaciaIdentifier = this.getOnsEarDiarioBaciaIdentifier(onsEarDiarioBaciaItem);
        if (onsEarDiarioBaciaCollectionIdentifiers.includes(onsEarDiarioBaciaIdentifier)) {
          return false;
        }
        onsEarDiarioBaciaCollectionIdentifiers.push(onsEarDiarioBaciaIdentifier);
        return true;
      });
      return [...onsEarDiarioBaciasToAdd, ...onsEarDiarioBaciaCollection];
    }
    return onsEarDiarioBaciaCollection;
  }

  protected convertDateFromClient<T extends IOnsEarDiarioBacia | NewOnsEarDiarioBacia | PartialUpdateOnsEarDiarioBacia>(
    onsEarDiarioBacia: T,
  ): RestOf<T> {
    return {
      ...onsEarDiarioBacia,
      earData: onsEarDiarioBacia.earData?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsEarDiarioBacia: RestOnsEarDiarioBacia): IOnsEarDiarioBacia {
    return {
      ...restOnsEarDiarioBacia,
      earData: restOnsEarDiarioBacia.earData ? dayjs(restOnsEarDiarioBacia.earData) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsEarDiarioBacia>): HttpResponse<IOnsEarDiarioBacia> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsEarDiarioBacia[]>): HttpResponse<IOnsEarDiarioBacia[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
