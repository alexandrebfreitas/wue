import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import {
  IOnsEnaDiarioReeReservatorioEquivalenteEnergia,
  NewOnsEnaDiarioReeReservatorioEquivalenteEnergia,
} from '../ons-ena-diario-ree-reservatorio-equivalente-energia.model';

export type PartialUpdateOnsEnaDiarioReeReservatorioEquivalenteEnergia = Partial<IOnsEnaDiarioReeReservatorioEquivalenteEnergia> &
  Pick<IOnsEnaDiarioReeReservatorioEquivalenteEnergia, 'id'>;

export type EntityResponseType = HttpResponse<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>;
export type EntityArrayResponseType = HttpResponse<IOnsEnaDiarioReeReservatorioEquivalenteEnergia[]>;

@Injectable({ providedIn: 'root' })
export class OnsEnaDiarioReeReservatorioEquivalenteEnergiaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-ena-diario-ree-reservatorio-equivalente-energias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-ena-diario-ree-reservatorio-equivalente-energias/_search',
  );

  create(onsEnaDiarioReeReservatorioEquivalenteEnergia: NewOnsEnaDiarioReeReservatorioEquivalenteEnergia): Observable<EntityResponseType> {
    return this.http.post<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>(this.resourceUrl, onsEnaDiarioReeReservatorioEquivalenteEnergia, {
      observe: 'response',
    });
  }

  update(onsEnaDiarioReeReservatorioEquivalenteEnergia: IOnsEnaDiarioReeReservatorioEquivalenteEnergia): Observable<EntityResponseType> {
    return this.http.put<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>(
      `${this.resourceUrl}/${this.getOnsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier(onsEnaDiarioReeReservatorioEquivalenteEnergia)}`,
      onsEnaDiarioReeReservatorioEquivalenteEnergia,
      { observe: 'response' },
    );
  }

  partialUpdate(
    onsEnaDiarioReeReservatorioEquivalenteEnergia: PartialUpdateOnsEnaDiarioReeReservatorioEquivalenteEnergia,
  ): Observable<EntityResponseType> {
    return this.http.patch<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>(
      `${this.resourceUrl}/${this.getOnsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier(onsEnaDiarioReeReservatorioEquivalenteEnergia)}`,
      onsEnaDiarioReeReservatorioEquivalenteEnergia,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsEnaDiarioReeReservatorioEquivalenteEnergia>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsEnaDiarioReeReservatorioEquivalenteEnergia[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOnsEnaDiarioReeReservatorioEquivalenteEnergia[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(catchError(() => scheduled([new HttpResponse<IOnsEnaDiarioReeReservatorioEquivalenteEnergia[]>()], asapScheduler)));
  }

  getOnsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier(
    onsEnaDiarioReeReservatorioEquivalenteEnergia: Pick<IOnsEnaDiarioReeReservatorioEquivalenteEnergia, 'id'>,
  ): number {
    return onsEnaDiarioReeReservatorioEquivalenteEnergia.id;
  }

  compareOnsEnaDiarioReeReservatorioEquivalenteEnergia(
    o1: Pick<IOnsEnaDiarioReeReservatorioEquivalenteEnergia, 'id'> | null,
    o2: Pick<IOnsEnaDiarioReeReservatorioEquivalenteEnergia, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier(o1) ===
          this.getOnsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier(o2)
      : o1 === o2;
  }

  addOnsEnaDiarioReeReservatorioEquivalenteEnergiaToCollectionIfMissing<
    Type extends Pick<IOnsEnaDiarioReeReservatorioEquivalenteEnergia, 'id'>,
  >(
    onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection: Type[],
    ...onsEnaDiarioReeReservatorioEquivalenteEnergiasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsEnaDiarioReeReservatorioEquivalenteEnergias: Type[] = onsEnaDiarioReeReservatorioEquivalenteEnergiasToCheck.filter(isPresent);
    if (onsEnaDiarioReeReservatorioEquivalenteEnergias.length > 0) {
      const onsEnaDiarioReeReservatorioEquivalenteEnergiaCollectionIdentifiers =
        onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection.map(onsEnaDiarioReeReservatorioEquivalenteEnergiaItem =>
          this.getOnsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier(onsEnaDiarioReeReservatorioEquivalenteEnergiaItem),
        );
      const onsEnaDiarioReeReservatorioEquivalenteEnergiasToAdd = onsEnaDiarioReeReservatorioEquivalenteEnergias.filter(
        onsEnaDiarioReeReservatorioEquivalenteEnergiaItem => {
          const onsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier = this.getOnsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier(
            onsEnaDiarioReeReservatorioEquivalenteEnergiaItem,
          );
          if (
            onsEnaDiarioReeReservatorioEquivalenteEnergiaCollectionIdentifiers.includes(
              onsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier,
            )
          ) {
            return false;
          }
          onsEnaDiarioReeReservatorioEquivalenteEnergiaCollectionIdentifiers.push(onsEnaDiarioReeReservatorioEquivalenteEnergiaIdentifier);
          return true;
        },
      );
      return [...onsEnaDiarioReeReservatorioEquivalenteEnergiasToAdd, ...onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection];
    }
    return onsEnaDiarioReeReservatorioEquivalenteEnergiaCollection;
  }
}
