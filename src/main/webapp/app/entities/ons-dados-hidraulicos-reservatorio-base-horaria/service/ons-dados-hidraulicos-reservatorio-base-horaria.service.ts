import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import {
  IOnsDadosHidraulicosReservatorioBaseHoraria,
  NewOnsDadosHidraulicosReservatorioBaseHoraria,
} from '../ons-dados-hidraulicos-reservatorio-base-horaria.model';

export type PartialUpdateOnsDadosHidraulicosReservatorioBaseHoraria = Partial<IOnsDadosHidraulicosReservatorioBaseHoraria> &
  Pick<IOnsDadosHidraulicosReservatorioBaseHoraria, 'id'>;

export type EntityResponseType = HttpResponse<IOnsDadosHidraulicosReservatorioBaseHoraria>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosHidraulicosReservatorioBaseHoraria[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosHidraulicosReservatorioBaseHorariaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-hidraulicos-reservatorio-base-horarias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-hidraulicos-reservatorio-base-horarias/_search',
  );

  create(onsDadosHidraulicosReservatorioBaseHoraria: NewOnsDadosHidraulicosReservatorioBaseHoraria): Observable<EntityResponseType> {
    return this.http.post<IOnsDadosHidraulicosReservatorioBaseHoraria>(this.resourceUrl, onsDadosHidraulicosReservatorioBaseHoraria, {
      observe: 'response',
    });
  }

  update(onsDadosHidraulicosReservatorioBaseHoraria: IOnsDadosHidraulicosReservatorioBaseHoraria): Observable<EntityResponseType> {
    return this.http.put<IOnsDadosHidraulicosReservatorioBaseHoraria>(
      `${this.resourceUrl}/${this.getOnsDadosHidraulicosReservatorioBaseHorariaIdentifier(onsDadosHidraulicosReservatorioBaseHoraria)}`,
      onsDadosHidraulicosReservatorioBaseHoraria,
      { observe: 'response' },
    );
  }

  partialUpdate(
    onsDadosHidraulicosReservatorioBaseHoraria: PartialUpdateOnsDadosHidraulicosReservatorioBaseHoraria,
  ): Observable<EntityResponseType> {
    return this.http.patch<IOnsDadosHidraulicosReservatorioBaseHoraria>(
      `${this.resourceUrl}/${this.getOnsDadosHidraulicosReservatorioBaseHorariaIdentifier(onsDadosHidraulicosReservatorioBaseHoraria)}`,
      onsDadosHidraulicosReservatorioBaseHoraria,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsDadosHidraulicosReservatorioBaseHoraria>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsDadosHidraulicosReservatorioBaseHoraria[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOnsDadosHidraulicosReservatorioBaseHoraria[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(catchError(() => scheduled([new HttpResponse<IOnsDadosHidraulicosReservatorioBaseHoraria[]>()], asapScheduler)));
  }

  getOnsDadosHidraulicosReservatorioBaseHorariaIdentifier(
    onsDadosHidraulicosReservatorioBaseHoraria: Pick<IOnsDadosHidraulicosReservatorioBaseHoraria, 'id'>,
  ): number {
    return onsDadosHidraulicosReservatorioBaseHoraria.id;
  }

  compareOnsDadosHidraulicosReservatorioBaseHoraria(
    o1: Pick<IOnsDadosHidraulicosReservatorioBaseHoraria, 'id'> | null,
    o2: Pick<IOnsDadosHidraulicosReservatorioBaseHoraria, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosHidraulicosReservatorioBaseHorariaIdentifier(o1) ===
          this.getOnsDadosHidraulicosReservatorioBaseHorariaIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosHidraulicosReservatorioBaseHorariaToCollectionIfMissing<Type extends Pick<IOnsDadosHidraulicosReservatorioBaseHoraria, 'id'>>(
    onsDadosHidraulicosReservatorioBaseHorariaCollection: Type[],
    ...onsDadosHidraulicosReservatorioBaseHorariasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosHidraulicosReservatorioBaseHorarias: Type[] = onsDadosHidraulicosReservatorioBaseHorariasToCheck.filter(isPresent);
    if (onsDadosHidraulicosReservatorioBaseHorarias.length > 0) {
      const onsDadosHidraulicosReservatorioBaseHorariaCollectionIdentifiers = onsDadosHidraulicosReservatorioBaseHorariaCollection.map(
        onsDadosHidraulicosReservatorioBaseHorariaItem =>
          this.getOnsDadosHidraulicosReservatorioBaseHorariaIdentifier(onsDadosHidraulicosReservatorioBaseHorariaItem),
      );
      const onsDadosHidraulicosReservatorioBaseHorariasToAdd = onsDadosHidraulicosReservatorioBaseHorarias.filter(
        onsDadosHidraulicosReservatorioBaseHorariaItem => {
          const onsDadosHidraulicosReservatorioBaseHorariaIdentifier = this.getOnsDadosHidraulicosReservatorioBaseHorariaIdentifier(
            onsDadosHidraulicosReservatorioBaseHorariaItem,
          );
          if (
            onsDadosHidraulicosReservatorioBaseHorariaCollectionIdentifiers.includes(onsDadosHidraulicosReservatorioBaseHorariaIdentifier)
          ) {
            return false;
          }
          onsDadosHidraulicosReservatorioBaseHorariaCollectionIdentifiers.push(onsDadosHidraulicosReservatorioBaseHorariaIdentifier);
          return true;
        },
      );
      return [...onsDadosHidraulicosReservatorioBaseHorariasToAdd, ...onsDadosHidraulicosReservatorioBaseHorariaCollection];
    }
    return onsDadosHidraulicosReservatorioBaseHorariaCollection;
  }
}
