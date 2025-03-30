import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import { IOnsEnaDiarioSubsistema, NewOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';

export type PartialUpdateOnsEnaDiarioSubsistema = Partial<IOnsEnaDiarioSubsistema> & Pick<IOnsEnaDiarioSubsistema, 'id'>;

export type EntityResponseType = HttpResponse<IOnsEnaDiarioSubsistema>;
export type EntityArrayResponseType = HttpResponse<IOnsEnaDiarioSubsistema[]>;

@Injectable({ providedIn: 'root' })
export class OnsEnaDiarioSubsistemaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-ena-diario-subsistemas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-ena-diario-subsistemas/_search');

  create(onsEnaDiarioSubsistema: NewOnsEnaDiarioSubsistema): Observable<EntityResponseType> {
    return this.http.post<IOnsEnaDiarioSubsistema>(this.resourceUrl, onsEnaDiarioSubsistema, { observe: 'response' });
  }

  update(onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema): Observable<EntityResponseType> {
    return this.http.put<IOnsEnaDiarioSubsistema>(
      `${this.resourceUrl}/${this.getOnsEnaDiarioSubsistemaIdentifier(onsEnaDiarioSubsistema)}`,
      onsEnaDiarioSubsistema,
      { observe: 'response' },
    );
  }

  partialUpdate(onsEnaDiarioSubsistema: PartialUpdateOnsEnaDiarioSubsistema): Observable<EntityResponseType> {
    return this.http.patch<IOnsEnaDiarioSubsistema>(
      `${this.resourceUrl}/${this.getOnsEnaDiarioSubsistemaIdentifier(onsEnaDiarioSubsistema)}`,
      onsEnaDiarioSubsistema,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsEnaDiarioSubsistema>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsEnaDiarioSubsistema[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOnsEnaDiarioSubsistema[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(catchError(() => scheduled([new HttpResponse<IOnsEnaDiarioSubsistema[]>()], asapScheduler)));
  }

  getOnsEnaDiarioSubsistemaIdentifier(onsEnaDiarioSubsistema: Pick<IOnsEnaDiarioSubsistema, 'id'>): number {
    return onsEnaDiarioSubsistema.id;
  }

  compareOnsEnaDiarioSubsistema(o1: Pick<IOnsEnaDiarioSubsistema, 'id'> | null, o2: Pick<IOnsEnaDiarioSubsistema, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsEnaDiarioSubsistemaIdentifier(o1) === this.getOnsEnaDiarioSubsistemaIdentifier(o2) : o1 === o2;
  }

  addOnsEnaDiarioSubsistemaToCollectionIfMissing<Type extends Pick<IOnsEnaDiarioSubsistema, 'id'>>(
    onsEnaDiarioSubsistemaCollection: Type[],
    ...onsEnaDiarioSubsistemasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsEnaDiarioSubsistemas: Type[] = onsEnaDiarioSubsistemasToCheck.filter(isPresent);
    if (onsEnaDiarioSubsistemas.length > 0) {
      const onsEnaDiarioSubsistemaCollectionIdentifiers = onsEnaDiarioSubsistemaCollection.map(onsEnaDiarioSubsistemaItem =>
        this.getOnsEnaDiarioSubsistemaIdentifier(onsEnaDiarioSubsistemaItem),
      );
      const onsEnaDiarioSubsistemasToAdd = onsEnaDiarioSubsistemas.filter(onsEnaDiarioSubsistemaItem => {
        const onsEnaDiarioSubsistemaIdentifier = this.getOnsEnaDiarioSubsistemaIdentifier(onsEnaDiarioSubsistemaItem);
        if (onsEnaDiarioSubsistemaCollectionIdentifiers.includes(onsEnaDiarioSubsistemaIdentifier)) {
          return false;
        }
        onsEnaDiarioSubsistemaCollectionIdentifiers.push(onsEnaDiarioSubsistemaIdentifier);
        return true;
      });
      return [...onsEnaDiarioSubsistemasToAdd, ...onsEnaDiarioSubsistemaCollection];
    }
    return onsEnaDiarioSubsistemaCollection;
  }
}
