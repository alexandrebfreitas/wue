import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import {
  IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
  NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
} from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';

export type PartialUpdateOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
  Partial<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024> &
    Pick<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024, 'id'>;

export type EntityResponseType = HttpResponse<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s/_search',
  );

  create(
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
  ): Observable<EntityResponseType> {
    return this.http.post<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>(
      this.resourceUrl,
      onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
      { observe: 'response' },
    );
  }

  update(
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
  ): Observable<EntityResponseType> {
    return this.http.put<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>(
      `${this.resourceUrl}/${this.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024)}`,
      onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
      { observe: 'response' },
    );
  }

  partialUpdate(
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: PartialUpdateOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
  ): Observable<EntityResponseType> {
    return this.http.patch<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>(
      `${this.resourceUrl}/${this.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024)}`,
      onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[]>(this.resourceUrl, {
      params: options,
      observe: 'response',
    });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(catchError(() => scheduled([new HttpResponse<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[]>()], asapScheduler)));
  }

  getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier(
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: Pick<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024, 'id'>,
  ): number {
    return onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.id;
  }

  compareOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(
    o1: Pick<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024, 'id'> | null,
    o2: Pick<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier(o1) ===
          this.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier(o2)
      : o1 === o2;
  }

  addOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024ToCollectionIfMissing<
    Type extends Pick<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024, 'id'>,
  >(
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection: Type[],
    ...onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024sToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s: Type[] =
      onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024sToCheck.filter(isPresent);
    if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s.length > 0) {
      const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024CollectionIdentifiers =
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection.map(
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Item =>
            this.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier(
              onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Item,
            ),
        );
      const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024sToAdd =
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s.filter(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Item => {
          const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier =
            this.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier(
              onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Item,
            );
          if (
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024CollectionIdentifiers.includes(
              onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier,
            )
          ) {
            return false;
          }
          onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024CollectionIdentifiers.push(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier,
          );
          return true;
        });
      return [
        ...onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024sToAdd,
        ...onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection,
      ];
    }
    return onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Collection;
  }
}
