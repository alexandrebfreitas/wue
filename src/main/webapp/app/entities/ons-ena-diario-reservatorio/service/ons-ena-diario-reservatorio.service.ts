import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import { IOnsEnaDiarioReservatorio, NewOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';

export type PartialUpdateOnsEnaDiarioReservatorio = Partial<IOnsEnaDiarioReservatorio> & Pick<IOnsEnaDiarioReservatorio, 'id'>;

export type EntityResponseType = HttpResponse<IOnsEnaDiarioReservatorio>;
export type EntityArrayResponseType = HttpResponse<IOnsEnaDiarioReservatorio[]>;

@Injectable({ providedIn: 'root' })
export class OnsEnaDiarioReservatorioService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-ena-diario-reservatorios');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-ena-diario-reservatorios/_search');

  create(onsEnaDiarioReservatorio: NewOnsEnaDiarioReservatorio): Observable<EntityResponseType> {
    return this.http.post<IOnsEnaDiarioReservatorio>(this.resourceUrl, onsEnaDiarioReservatorio, { observe: 'response' });
  }

  update(onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio): Observable<EntityResponseType> {
    return this.http.put<IOnsEnaDiarioReservatorio>(
      `${this.resourceUrl}/${this.getOnsEnaDiarioReservatorioIdentifier(onsEnaDiarioReservatorio)}`,
      onsEnaDiarioReservatorio,
      { observe: 'response' },
    );
  }

  partialUpdate(onsEnaDiarioReservatorio: PartialUpdateOnsEnaDiarioReservatorio): Observable<EntityResponseType> {
    return this.http.patch<IOnsEnaDiarioReservatorio>(
      `${this.resourceUrl}/${this.getOnsEnaDiarioReservatorioIdentifier(onsEnaDiarioReservatorio)}`,
      onsEnaDiarioReservatorio,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsEnaDiarioReservatorio>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsEnaDiarioReservatorio[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOnsEnaDiarioReservatorio[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(catchError(() => scheduled([new HttpResponse<IOnsEnaDiarioReservatorio[]>()], asapScheduler)));
  }

  getOnsEnaDiarioReservatorioIdentifier(onsEnaDiarioReservatorio: Pick<IOnsEnaDiarioReservatorio, 'id'>): number {
    return onsEnaDiarioReservatorio.id;
  }

  compareOnsEnaDiarioReservatorio(
    o1: Pick<IOnsEnaDiarioReservatorio, 'id'> | null,
    o2: Pick<IOnsEnaDiarioReservatorio, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getOnsEnaDiarioReservatorioIdentifier(o1) === this.getOnsEnaDiarioReservatorioIdentifier(o2) : o1 === o2;
  }

  addOnsEnaDiarioReservatorioToCollectionIfMissing<Type extends Pick<IOnsEnaDiarioReservatorio, 'id'>>(
    onsEnaDiarioReservatorioCollection: Type[],
    ...onsEnaDiarioReservatoriosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsEnaDiarioReservatorios: Type[] = onsEnaDiarioReservatoriosToCheck.filter(isPresent);
    if (onsEnaDiarioReservatorios.length > 0) {
      const onsEnaDiarioReservatorioCollectionIdentifiers = onsEnaDiarioReservatorioCollection.map(onsEnaDiarioReservatorioItem =>
        this.getOnsEnaDiarioReservatorioIdentifier(onsEnaDiarioReservatorioItem),
      );
      const onsEnaDiarioReservatoriosToAdd = onsEnaDiarioReservatorios.filter(onsEnaDiarioReservatorioItem => {
        const onsEnaDiarioReservatorioIdentifier = this.getOnsEnaDiarioReservatorioIdentifier(onsEnaDiarioReservatorioItem);
        if (onsEnaDiarioReservatorioCollectionIdentifiers.includes(onsEnaDiarioReservatorioIdentifier)) {
          return false;
        }
        onsEnaDiarioReservatorioCollectionIdentifiers.push(onsEnaDiarioReservatorioIdentifier);
        return true;
      });
      return [...onsEnaDiarioReservatoriosToAdd, ...onsEnaDiarioReservatorioCollection];
    }
    return onsEnaDiarioReservatorioCollection;
  }
}
