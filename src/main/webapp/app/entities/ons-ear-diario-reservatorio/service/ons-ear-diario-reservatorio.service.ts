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
import { IOnsEarDiarioReservatorio, NewOnsEarDiarioReservatorio } from '../ons-ear-diario-reservatorio.model';

export type PartialUpdateOnsEarDiarioReservatorio = Partial<IOnsEarDiarioReservatorio> & Pick<IOnsEarDiarioReservatorio, 'id'>;

type RestOf<T extends IOnsEarDiarioReservatorio | NewOnsEarDiarioReservatorio> = Omit<T, 'earData'> & {
  earData?: string | null;
};

export type RestOnsEarDiarioReservatorio = RestOf<IOnsEarDiarioReservatorio>;

export type NewRestOnsEarDiarioReservatorio = RestOf<NewOnsEarDiarioReservatorio>;

export type PartialUpdateRestOnsEarDiarioReservatorio = RestOf<PartialUpdateOnsEarDiarioReservatorio>;

export type EntityResponseType = HttpResponse<IOnsEarDiarioReservatorio>;
export type EntityArrayResponseType = HttpResponse<IOnsEarDiarioReservatorio[]>;

@Injectable({ providedIn: 'root' })
export class OnsEarDiarioReservatorioService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-ear-diario-reservatorios');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-ear-diario-reservatorios/_search');

  create(onsEarDiarioReservatorio: NewOnsEarDiarioReservatorio): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioReservatorio);
    return this.http
      .post<RestOnsEarDiarioReservatorio>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsEarDiarioReservatorio: IOnsEarDiarioReservatorio): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioReservatorio);
    return this.http
      .put<RestOnsEarDiarioReservatorio>(
        `${this.resourceUrl}/${this.getOnsEarDiarioReservatorioIdentifier(onsEarDiarioReservatorio)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsEarDiarioReservatorio: PartialUpdateOnsEarDiarioReservatorio): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioReservatorio);
    return this.http
      .patch<RestOnsEarDiarioReservatorio>(
        `${this.resourceUrl}/${this.getOnsEarDiarioReservatorioIdentifier(onsEarDiarioReservatorio)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsEarDiarioReservatorio>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsEarDiarioReservatorio[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsEarDiarioReservatorio[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsEarDiarioReservatorio[]>()], asapScheduler)),
    );
  }

  getOnsEarDiarioReservatorioIdentifier(onsEarDiarioReservatorio: Pick<IOnsEarDiarioReservatorio, 'id'>): number {
    return onsEarDiarioReservatorio.id;
  }

  compareOnsEarDiarioReservatorio(
    o1: Pick<IOnsEarDiarioReservatorio, 'id'> | null,
    o2: Pick<IOnsEarDiarioReservatorio, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getOnsEarDiarioReservatorioIdentifier(o1) === this.getOnsEarDiarioReservatorioIdentifier(o2) : o1 === o2;
  }

  addOnsEarDiarioReservatorioToCollectionIfMissing<Type extends Pick<IOnsEarDiarioReservatorio, 'id'>>(
    onsEarDiarioReservatorioCollection: Type[],
    ...onsEarDiarioReservatoriosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsEarDiarioReservatorios: Type[] = onsEarDiarioReservatoriosToCheck.filter(isPresent);
    if (onsEarDiarioReservatorios.length > 0) {
      const onsEarDiarioReservatorioCollectionIdentifiers = onsEarDiarioReservatorioCollection.map(onsEarDiarioReservatorioItem =>
        this.getOnsEarDiarioReservatorioIdentifier(onsEarDiarioReservatorioItem),
      );
      const onsEarDiarioReservatoriosToAdd = onsEarDiarioReservatorios.filter(onsEarDiarioReservatorioItem => {
        const onsEarDiarioReservatorioIdentifier = this.getOnsEarDiarioReservatorioIdentifier(onsEarDiarioReservatorioItem);
        if (onsEarDiarioReservatorioCollectionIdentifiers.includes(onsEarDiarioReservatorioIdentifier)) {
          return false;
        }
        onsEarDiarioReservatorioCollectionIdentifiers.push(onsEarDiarioReservatorioIdentifier);
        return true;
      });
      return [...onsEarDiarioReservatoriosToAdd, ...onsEarDiarioReservatorioCollection];
    }
    return onsEarDiarioReservatorioCollection;
  }

  protected convertDateFromClient<
    T extends IOnsEarDiarioReservatorio | NewOnsEarDiarioReservatorio | PartialUpdateOnsEarDiarioReservatorio,
  >(onsEarDiarioReservatorio: T): RestOf<T> {
    return {
      ...onsEarDiarioReservatorio,
      earData: onsEarDiarioReservatorio.earData?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsEarDiarioReservatorio: RestOnsEarDiarioReservatorio): IOnsEarDiarioReservatorio {
    return {
      ...restOnsEarDiarioReservatorio,
      earData: restOnsEarDiarioReservatorio.earData ? dayjs(restOnsEarDiarioReservatorio.earData) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsEarDiarioReservatorio>): HttpResponse<IOnsEarDiarioReservatorio> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsEarDiarioReservatorio[]>): HttpResponse<IOnsEarDiarioReservatorio[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
