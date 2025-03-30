import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import { IOnsModalidadeOperacaoUsinas, NewOnsModalidadeOperacaoUsinas } from '../ons-modalidade-operacao-usinas.model';

export type PartialUpdateOnsModalidadeOperacaoUsinas = Partial<IOnsModalidadeOperacaoUsinas> & Pick<IOnsModalidadeOperacaoUsinas, 'id'>;

export type EntityResponseType = HttpResponse<IOnsModalidadeOperacaoUsinas>;
export type EntityArrayResponseType = HttpResponse<IOnsModalidadeOperacaoUsinas[]>;

@Injectable({ providedIn: 'root' })
export class OnsModalidadeOperacaoUsinasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-modalidade-operacao-usinas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-modalidade-operacao-usinas/_search');

  create(onsModalidadeOperacaoUsinas: NewOnsModalidadeOperacaoUsinas): Observable<EntityResponseType> {
    return this.http.post<IOnsModalidadeOperacaoUsinas>(this.resourceUrl, onsModalidadeOperacaoUsinas, { observe: 'response' });
  }

  update(onsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas): Observable<EntityResponseType> {
    return this.http.put<IOnsModalidadeOperacaoUsinas>(
      `${this.resourceUrl}/${this.getOnsModalidadeOperacaoUsinasIdentifier(onsModalidadeOperacaoUsinas)}`,
      onsModalidadeOperacaoUsinas,
      { observe: 'response' },
    );
  }

  partialUpdate(onsModalidadeOperacaoUsinas: PartialUpdateOnsModalidadeOperacaoUsinas): Observable<EntityResponseType> {
    return this.http.patch<IOnsModalidadeOperacaoUsinas>(
      `${this.resourceUrl}/${this.getOnsModalidadeOperacaoUsinasIdentifier(onsModalidadeOperacaoUsinas)}`,
      onsModalidadeOperacaoUsinas,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsModalidadeOperacaoUsinas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsModalidadeOperacaoUsinas[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOnsModalidadeOperacaoUsinas[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(catchError(() => scheduled([new HttpResponse<IOnsModalidadeOperacaoUsinas[]>()], asapScheduler)));
  }

  getOnsModalidadeOperacaoUsinasIdentifier(onsModalidadeOperacaoUsinas: Pick<IOnsModalidadeOperacaoUsinas, 'id'>): number {
    return onsModalidadeOperacaoUsinas.id;
  }

  compareOnsModalidadeOperacaoUsinas(
    o1: Pick<IOnsModalidadeOperacaoUsinas, 'id'> | null,
    o2: Pick<IOnsModalidadeOperacaoUsinas, 'id'> | null,
  ): boolean {
    return o1 && o2 ? this.getOnsModalidadeOperacaoUsinasIdentifier(o1) === this.getOnsModalidadeOperacaoUsinasIdentifier(o2) : o1 === o2;
  }

  addOnsModalidadeOperacaoUsinasToCollectionIfMissing<Type extends Pick<IOnsModalidadeOperacaoUsinas, 'id'>>(
    onsModalidadeOperacaoUsinasCollection: Type[],
    ...onsModalidadeOperacaoUsinasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsModalidadeOperacaoUsinas: Type[] = onsModalidadeOperacaoUsinasToCheck.filter(isPresent);
    if (onsModalidadeOperacaoUsinas.length > 0) {
      const onsModalidadeOperacaoUsinasCollectionIdentifiers = onsModalidadeOperacaoUsinasCollection.map(onsModalidadeOperacaoUsinasItem =>
        this.getOnsModalidadeOperacaoUsinasIdentifier(onsModalidadeOperacaoUsinasItem),
      );
      const onsModalidadeOperacaoUsinasToAdd = onsModalidadeOperacaoUsinas.filter(onsModalidadeOperacaoUsinasItem => {
        const onsModalidadeOperacaoUsinasIdentifier = this.getOnsModalidadeOperacaoUsinasIdentifier(onsModalidadeOperacaoUsinasItem);
        if (onsModalidadeOperacaoUsinasCollectionIdentifiers.includes(onsModalidadeOperacaoUsinasIdentifier)) {
          return false;
        }
        onsModalidadeOperacaoUsinasCollectionIdentifiers.push(onsModalidadeOperacaoUsinasIdentifier);
        return true;
      });
      return [...onsModalidadeOperacaoUsinasToAdd, ...onsModalidadeOperacaoUsinasCollection];
    }
    return onsModalidadeOperacaoUsinasCollection;
  }
}
