import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import {
  IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
  NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
} from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.model';

export type PartialUpdateOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas =
  Partial<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas> & Pick<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas, 'id'>;

export type EntityResponseType = HttpResponse<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas/_search',
  );

  create(
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
  ): Observable<EntityResponseType> {
    return this.http.post<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>(
      this.resourceUrl,
      onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
      { observe: 'response' },
    );
  }

  update(
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
  ): Observable<EntityResponseType> {
    return this.http.put<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>(
      `${this.resourceUrl}/${this.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas)}`,
      onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
      { observe: 'response' },
    );
  }

  partialUpdate(
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: PartialUpdateOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
  ): Observable<EntityResponseType> {
    return this.http.patch<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>(
      `${this.resourceUrl}/${this.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas)}`,
      onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[]>(this.resourceUrl, {
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
      .get<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(catchError(() => scheduled([new HttpResponse<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas[]>()], asapScheduler)));
  }

  getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier(
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: Pick<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas, 'id'>,
  ): number {
    return onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.id;
  }

  compareOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
    o1: Pick<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas, 'id'> | null,
    o2: Pick<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier(o1) ===
          this.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCollectionIfMissing<
    Type extends Pick<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas, 'id'>,
  >(
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection: Type[],
    ...onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: Type[] =
      onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToCheck.filter(isPresent);
    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.length > 0) {
      const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollectionIdentifiers =
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection.map(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasItem =>
          this.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasItem,
          ),
        );
      const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToAdd = onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.filter(
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasItem => {
          const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier =
            this.getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier(
              onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasItem,
            );
          if (
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollectionIdentifiers.includes(
              onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier,
            )
          ) {
            return false;
          }
          onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollectionIdentifiers.push(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasIdentifier,
          );
          return true;
        },
      );
      return [
        ...onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasToAdd,
        ...onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection,
      ];
    }
    return onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasCollection;
  }
}
