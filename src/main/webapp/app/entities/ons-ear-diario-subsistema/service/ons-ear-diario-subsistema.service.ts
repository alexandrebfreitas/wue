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
import { IOnsEarDiarioSubsistema, NewOnsEarDiarioSubsistema } from '../ons-ear-diario-subsistema.model';

export type PartialUpdateOnsEarDiarioSubsistema = Partial<IOnsEarDiarioSubsistema> & Pick<IOnsEarDiarioSubsistema, 'id'>;

type RestOf<T extends IOnsEarDiarioSubsistema | NewOnsEarDiarioSubsistema> = Omit<T, 'earData'> & {
  earData?: string | null;
};

export type RestOnsEarDiarioSubsistema = RestOf<IOnsEarDiarioSubsistema>;

export type NewRestOnsEarDiarioSubsistema = RestOf<NewOnsEarDiarioSubsistema>;

export type PartialUpdateRestOnsEarDiarioSubsistema = RestOf<PartialUpdateOnsEarDiarioSubsistema>;

export type EntityResponseType = HttpResponse<IOnsEarDiarioSubsistema>;
export type EntityArrayResponseType = HttpResponse<IOnsEarDiarioSubsistema[]>;

@Injectable({ providedIn: 'root' })
export class OnsEarDiarioSubsistemaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-ear-diario-subsistemas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-ear-diario-subsistemas/_search');

  create(onsEarDiarioSubsistema: NewOnsEarDiarioSubsistema): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioSubsistema);
    return this.http
      .post<RestOnsEarDiarioSubsistema>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsEarDiarioSubsistema: IOnsEarDiarioSubsistema): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioSubsistema);
    return this.http
      .put<RestOnsEarDiarioSubsistema>(`${this.resourceUrl}/${this.getOnsEarDiarioSubsistemaIdentifier(onsEarDiarioSubsistema)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsEarDiarioSubsistema: PartialUpdateOnsEarDiarioSubsistema): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioSubsistema);
    return this.http
      .patch<RestOnsEarDiarioSubsistema>(`${this.resourceUrl}/${this.getOnsEarDiarioSubsistemaIdentifier(onsEarDiarioSubsistema)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsEarDiarioSubsistema>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsEarDiarioSubsistema[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsEarDiarioSubsistema[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsEarDiarioSubsistema[]>()], asapScheduler)),
    );
  }

  getOnsEarDiarioSubsistemaIdentifier(onsEarDiarioSubsistema: Pick<IOnsEarDiarioSubsistema, 'id'>): number {
    return onsEarDiarioSubsistema.id;
  }

  compareOnsEarDiarioSubsistema(o1: Pick<IOnsEarDiarioSubsistema, 'id'> | null, o2: Pick<IOnsEarDiarioSubsistema, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsEarDiarioSubsistemaIdentifier(o1) === this.getOnsEarDiarioSubsistemaIdentifier(o2) : o1 === o2;
  }

  addOnsEarDiarioSubsistemaToCollectionIfMissing<Type extends Pick<IOnsEarDiarioSubsistema, 'id'>>(
    onsEarDiarioSubsistemaCollection: Type[],
    ...onsEarDiarioSubsistemasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsEarDiarioSubsistemas: Type[] = onsEarDiarioSubsistemasToCheck.filter(isPresent);
    if (onsEarDiarioSubsistemas.length > 0) {
      const onsEarDiarioSubsistemaCollectionIdentifiers = onsEarDiarioSubsistemaCollection.map(onsEarDiarioSubsistemaItem =>
        this.getOnsEarDiarioSubsistemaIdentifier(onsEarDiarioSubsistemaItem),
      );
      const onsEarDiarioSubsistemasToAdd = onsEarDiarioSubsistemas.filter(onsEarDiarioSubsistemaItem => {
        const onsEarDiarioSubsistemaIdentifier = this.getOnsEarDiarioSubsistemaIdentifier(onsEarDiarioSubsistemaItem);
        if (onsEarDiarioSubsistemaCollectionIdentifiers.includes(onsEarDiarioSubsistemaIdentifier)) {
          return false;
        }
        onsEarDiarioSubsistemaCollectionIdentifiers.push(onsEarDiarioSubsistemaIdentifier);
        return true;
      });
      return [...onsEarDiarioSubsistemasToAdd, ...onsEarDiarioSubsistemaCollection];
    }
    return onsEarDiarioSubsistemaCollection;
  }

  protected convertDateFromClient<T extends IOnsEarDiarioSubsistema | NewOnsEarDiarioSubsistema | PartialUpdateOnsEarDiarioSubsistema>(
    onsEarDiarioSubsistema: T,
  ): RestOf<T> {
    return {
      ...onsEarDiarioSubsistema,
      earData: onsEarDiarioSubsistema.earData?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsEarDiarioSubsistema: RestOnsEarDiarioSubsistema): IOnsEarDiarioSubsistema {
    return {
      ...restOnsEarDiarioSubsistema,
      earData: restOnsEarDiarioSubsistema.earData ? dayjs(restOnsEarDiarioSubsistema.earData) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsEarDiarioSubsistema>): HttpResponse<IOnsEarDiarioSubsistema> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsEarDiarioSubsistema[]>): HttpResponse<IOnsEarDiarioSubsistema[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
