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
import {
  IOnsEarDiarioReeReservatorioEquivalenteEnergia,
  NewOnsEarDiarioReeReservatorioEquivalenteEnergia,
} from '../ons-ear-diario-ree-reservatorio-equivalente-energia.model';

export type PartialUpdateOnsEarDiarioReeReservatorioEquivalenteEnergia = Partial<IOnsEarDiarioReeReservatorioEquivalenteEnergia> &
  Pick<IOnsEarDiarioReeReservatorioEquivalenteEnergia, 'id'>;

type RestOf<T extends IOnsEarDiarioReeReservatorioEquivalenteEnergia | NewOnsEarDiarioReeReservatorioEquivalenteEnergia> = Omit<
  T,
  'earData'
> & {
  earData?: string | null;
};

export type RestOnsEarDiarioReeReservatorioEquivalenteEnergia = RestOf<IOnsEarDiarioReeReservatorioEquivalenteEnergia>;

export type NewRestOnsEarDiarioReeReservatorioEquivalenteEnergia = RestOf<NewOnsEarDiarioReeReservatorioEquivalenteEnergia>;

export type PartialUpdateRestOnsEarDiarioReeReservatorioEquivalenteEnergia =
  RestOf<PartialUpdateOnsEarDiarioReeReservatorioEquivalenteEnergia>;

export type EntityResponseType = HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia>;
export type EntityArrayResponseType = HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia[]>;

@Injectable({ providedIn: 'root' })
export class OnsEarDiarioReeReservatorioEquivalenteEnergiaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-ear-diario-ree-reservatorio-equivalente-energias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-ear-diario-ree-reservatorio-equivalente-energias/_search',
  );

  create(onsEarDiarioReeReservatorioEquivalenteEnergia: NewOnsEarDiarioReeReservatorioEquivalenteEnergia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioReeReservatorioEquivalenteEnergia);
    return this.http
      .post<RestOnsEarDiarioReeReservatorioEquivalenteEnergia>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsEarDiarioReeReservatorioEquivalenteEnergia: IOnsEarDiarioReeReservatorioEquivalenteEnergia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioReeReservatorioEquivalenteEnergia);
    return this.http
      .put<RestOnsEarDiarioReeReservatorioEquivalenteEnergia>(
        `${this.resourceUrl}/${this.getOnsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier(onsEarDiarioReeReservatorioEquivalenteEnergia)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsEarDiarioReeReservatorioEquivalenteEnergia: PartialUpdateOnsEarDiarioReeReservatorioEquivalenteEnergia,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEarDiarioReeReservatorioEquivalenteEnergia);
    return this.http
      .patch<RestOnsEarDiarioReeReservatorioEquivalenteEnergia>(
        `${this.resourceUrl}/${this.getOnsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier(onsEarDiarioReeReservatorioEquivalenteEnergia)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsEarDiarioReeReservatorioEquivalenteEnergia>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsEarDiarioReeReservatorioEquivalenteEnergia[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsEarDiarioReeReservatorioEquivalenteEnergia[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() => scheduled([new HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia[]>()], asapScheduler)),
      );
  }

  getOnsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier(
    onsEarDiarioReeReservatorioEquivalenteEnergia: Pick<IOnsEarDiarioReeReservatorioEquivalenteEnergia, 'id'>,
  ): number {
    return onsEarDiarioReeReservatorioEquivalenteEnergia.id;
  }

  compareOnsEarDiarioReeReservatorioEquivalenteEnergia(
    o1: Pick<IOnsEarDiarioReeReservatorioEquivalenteEnergia, 'id'> | null,
    o2: Pick<IOnsEarDiarioReeReservatorioEquivalenteEnergia, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier(o1) ===
          this.getOnsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier(o2)
      : o1 === o2;
  }

  addOnsEarDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing<
    Type extends Pick<IOnsEarDiarioReeReservatorioEquivalenteEnergia, 'id'>,
  >(
    onsEarDiarioReeReservatorioEquivalenteEnergiaCollection: Type[],
    ...onsEarDiarioReeReservatorioEquivalenteEnergiasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsEarDiarioReeReservatorioEquivalenteEnergias: Type[] = onsEarDiarioReeReservatorioEquivalenteEnergiasToCheck.filter(isPresent);
    if (onsEarDiarioReeReservatorioEquivalenteEnergias.length > 0) {
      const onsEarDiarioReeReservatorioEquivalenteEnergiaCollectionIdentifiers =
        onsEarDiarioReeReservatorioEquivalenteEnergiaCollection.map(onsEarDiarioReeReservatorioEquivalenteEnergiaItem =>
          this.getOnsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier(onsEarDiarioReeReservatorioEquivalenteEnergiaItem),
        );
      const onsEarDiarioReeReservatorioEquivalenteEnergiasToAdd = onsEarDiarioReeReservatorioEquivalenteEnergias.filter(
        onsEarDiarioReeReservatorioEquivalenteEnergiaItem => {
          const onsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier = this.getOnsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier(
            onsEarDiarioReeReservatorioEquivalenteEnergiaItem,
          );
          if (
            onsEarDiarioReeReservatorioEquivalenteEnergiaCollectionIdentifiers.includes(
              onsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier,
            )
          ) {
            return false;
          }
          onsEarDiarioReeReservatorioEquivalenteEnergiaCollectionIdentifiers.push(onsEarDiarioReeReservatorioEquivalenteEnergiaIdentifier);
          return true;
        },
      );
      return [...onsEarDiarioReeReservatorioEquivalenteEnergiasToAdd, ...onsEarDiarioReeReservatorioEquivalenteEnergiaCollection];
    }
    return onsEarDiarioReeReservatorioEquivalenteEnergiaCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsEarDiarioReeReservatorioEquivalenteEnergia
      | NewOnsEarDiarioReeReservatorioEquivalenteEnergia
      | PartialUpdateOnsEarDiarioReeReservatorioEquivalenteEnergia,
  >(onsEarDiarioReeReservatorioEquivalenteEnergia: T): RestOf<T> {
    return {
      ...onsEarDiarioReeReservatorioEquivalenteEnergia,
      earData: onsEarDiarioReeReservatorioEquivalenteEnergia.earData?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsEarDiarioReeReservatorioEquivalenteEnergia: RestOnsEarDiarioReeReservatorioEquivalenteEnergia,
  ): IOnsEarDiarioReeReservatorioEquivalenteEnergia {
    return {
      ...restOnsEarDiarioReeReservatorioEquivalenteEnergia,
      earData: restOnsEarDiarioReeReservatorioEquivalenteEnergia.earData
        ? dayjs(restOnsEarDiarioReeReservatorioEquivalenteEnergia.earData)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsEarDiarioReeReservatorioEquivalenteEnergia>,
  ): HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsEarDiarioReeReservatorioEquivalenteEnergia[]>,
  ): HttpResponse<IOnsEarDiarioReeReservatorioEquivalenteEnergia[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
