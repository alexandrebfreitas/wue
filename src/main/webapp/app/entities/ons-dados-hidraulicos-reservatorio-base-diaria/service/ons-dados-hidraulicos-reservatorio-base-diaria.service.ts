import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import {
  IOnsDadosHidraulicosReservatorioBaseDiaria,
  NewOnsDadosHidraulicosReservatorioBaseDiaria,
} from '../ons-dados-hidraulicos-reservatorio-base-diaria.model';

export type PartialUpdateOnsDadosHidraulicosReservatorioBaseDiaria = Partial<IOnsDadosHidraulicosReservatorioBaseDiaria> &
  Pick<IOnsDadosHidraulicosReservatorioBaseDiaria, 'id'>;

export type EntityResponseType = HttpResponse<IOnsDadosHidraulicosReservatorioBaseDiaria>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosHidraulicosReservatorioBaseDiaria[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosHidraulicosReservatorioBaseDiariaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-hidraulicos-reservatorio-base-diarias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-hidraulicos-reservatorio-base-diarias/_search');

  create(onsDadosHidraulicosReservatorioBaseDiaria: NewOnsDadosHidraulicosReservatorioBaseDiaria): Observable<EntityResponseType> {
    return this.http.post<IOnsDadosHidraulicosReservatorioBaseDiaria>(this.resourceUrl, onsDadosHidraulicosReservatorioBaseDiaria, {
      observe: 'response',
    });
  }

  update(onsDadosHidraulicosReservatorioBaseDiaria: IOnsDadosHidraulicosReservatorioBaseDiaria): Observable<EntityResponseType> {
    return this.http.put<IOnsDadosHidraulicosReservatorioBaseDiaria>(
      `${this.resourceUrl}/${this.getOnsDadosHidraulicosReservatorioBaseDiariaIdentifier(onsDadosHidraulicosReservatorioBaseDiaria)}`,
      onsDadosHidraulicosReservatorioBaseDiaria,
      { observe: 'response' },
    );
  }

  partialUpdate(
    onsDadosHidraulicosReservatorioBaseDiaria: PartialUpdateOnsDadosHidraulicosReservatorioBaseDiaria,
  ): Observable<EntityResponseType> {
    return this.http.patch<IOnsDadosHidraulicosReservatorioBaseDiaria>(
      `${this.resourceUrl}/${this.getOnsDadosHidraulicosReservatorioBaseDiariaIdentifier(onsDadosHidraulicosReservatorioBaseDiaria)}`,
      onsDadosHidraulicosReservatorioBaseDiaria,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsDadosHidraulicosReservatorioBaseDiaria>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsDadosHidraulicosReservatorioBaseDiaria[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOnsDadosHidraulicosReservatorioBaseDiaria[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(catchError(() => scheduled([new HttpResponse<IOnsDadosHidraulicosReservatorioBaseDiaria[]>()], asapScheduler)));
  }

  getOnsDadosHidraulicosReservatorioBaseDiariaIdentifier(
    onsDadosHidraulicosReservatorioBaseDiaria: Pick<IOnsDadosHidraulicosReservatorioBaseDiaria, 'id'>,
  ): number {
    return onsDadosHidraulicosReservatorioBaseDiaria.id;
  }

  compareOnsDadosHidraulicosReservatorioBaseDiaria(
    o1: Pick<IOnsDadosHidraulicosReservatorioBaseDiaria, 'id'> | null,
    o2: Pick<IOnsDadosHidraulicosReservatorioBaseDiaria, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosHidraulicosReservatorioBaseDiariaIdentifier(o1) === this.getOnsDadosHidraulicosReservatorioBaseDiariaIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosHidraulicosReservatorioBaseDiariaToCollectionIfMissing<Type extends Pick<IOnsDadosHidraulicosReservatorioBaseDiaria, 'id'>>(
    onsDadosHidraulicosReservatorioBaseDiariaCollection: Type[],
    ...onsDadosHidraulicosReservatorioBaseDiariasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosHidraulicosReservatorioBaseDiarias: Type[] = onsDadosHidraulicosReservatorioBaseDiariasToCheck.filter(isPresent);
    if (onsDadosHidraulicosReservatorioBaseDiarias.length > 0) {
      const onsDadosHidraulicosReservatorioBaseDiariaCollectionIdentifiers = onsDadosHidraulicosReservatorioBaseDiariaCollection.map(
        onsDadosHidraulicosReservatorioBaseDiariaItem =>
          this.getOnsDadosHidraulicosReservatorioBaseDiariaIdentifier(onsDadosHidraulicosReservatorioBaseDiariaItem),
      );
      const onsDadosHidraulicosReservatorioBaseDiariasToAdd = onsDadosHidraulicosReservatorioBaseDiarias.filter(
        onsDadosHidraulicosReservatorioBaseDiariaItem => {
          const onsDadosHidraulicosReservatorioBaseDiariaIdentifier = this.getOnsDadosHidraulicosReservatorioBaseDiariaIdentifier(
            onsDadosHidraulicosReservatorioBaseDiariaItem,
          );
          if (
            onsDadosHidraulicosReservatorioBaseDiariaCollectionIdentifiers.includes(onsDadosHidraulicosReservatorioBaseDiariaIdentifier)
          ) {
            return false;
          }
          onsDadosHidraulicosReservatorioBaseDiariaCollectionIdentifiers.push(onsDadosHidraulicosReservatorioBaseDiariaIdentifier);
          return true;
        },
      );
      return [...onsDadosHidraulicosReservatorioBaseDiariasToAdd, ...onsDadosHidraulicosReservatorioBaseDiariaCollection];
    }
    return onsDadosHidraulicosReservatorioBaseDiariaCollection;
  }
}
