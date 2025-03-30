import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Search } from 'app/core/request/request.model';
import { IOnsEnaDiarioBacia, NewOnsEnaDiarioBacia } from '../ons-ena-diario-bacia.model';

export type PartialUpdateOnsEnaDiarioBacia = Partial<IOnsEnaDiarioBacia> & Pick<IOnsEnaDiarioBacia, 'id'>;

export type EntityResponseType = HttpResponse<IOnsEnaDiarioBacia>;
export type EntityArrayResponseType = HttpResponse<IOnsEnaDiarioBacia[]>;

@Injectable({ providedIn: 'root' })
export class OnsEnaDiarioBaciaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-ena-diario-bacias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-ena-diario-bacias/_search');

  create(onsEnaDiarioBacia: NewOnsEnaDiarioBacia): Observable<EntityResponseType> {
    return this.http.post<IOnsEnaDiarioBacia>(this.resourceUrl, onsEnaDiarioBacia, { observe: 'response' });
  }

  update(onsEnaDiarioBacia: IOnsEnaDiarioBacia): Observable<EntityResponseType> {
    return this.http.put<IOnsEnaDiarioBacia>(
      `${this.resourceUrl}/${this.getOnsEnaDiarioBaciaIdentifier(onsEnaDiarioBacia)}`,
      onsEnaDiarioBacia,
      { observe: 'response' },
    );
  }

  partialUpdate(onsEnaDiarioBacia: PartialUpdateOnsEnaDiarioBacia): Observable<EntityResponseType> {
    return this.http.patch<IOnsEnaDiarioBacia>(
      `${this.resourceUrl}/${this.getOnsEnaDiarioBaciaIdentifier(onsEnaDiarioBacia)}`,
      onsEnaDiarioBacia,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOnsEnaDiarioBacia>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOnsEnaDiarioBacia[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOnsEnaDiarioBacia[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(catchError(() => scheduled([new HttpResponse<IOnsEnaDiarioBacia[]>()], asapScheduler)));
  }

  getOnsEnaDiarioBaciaIdentifier(onsEnaDiarioBacia: Pick<IOnsEnaDiarioBacia, 'id'>): number {
    return onsEnaDiarioBacia.id;
  }

  compareOnsEnaDiarioBacia(o1: Pick<IOnsEnaDiarioBacia, 'id'> | null, o2: Pick<IOnsEnaDiarioBacia, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsEnaDiarioBaciaIdentifier(o1) === this.getOnsEnaDiarioBaciaIdentifier(o2) : o1 === o2;
  }

  addOnsEnaDiarioBaciaToCollectionIfMissing<Type extends Pick<IOnsEnaDiarioBacia, 'id'>>(
    onsEnaDiarioBaciaCollection: Type[],
    ...onsEnaDiarioBaciasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsEnaDiarioBacias: Type[] = onsEnaDiarioBaciasToCheck.filter(isPresent);
    if (onsEnaDiarioBacias.length > 0) {
      const onsEnaDiarioBaciaCollectionIdentifiers = onsEnaDiarioBaciaCollection.map(onsEnaDiarioBaciaItem =>
        this.getOnsEnaDiarioBaciaIdentifier(onsEnaDiarioBaciaItem),
      );
      const onsEnaDiarioBaciasToAdd = onsEnaDiarioBacias.filter(onsEnaDiarioBaciaItem => {
        const onsEnaDiarioBaciaIdentifier = this.getOnsEnaDiarioBaciaIdentifier(onsEnaDiarioBaciaItem);
        if (onsEnaDiarioBaciaCollectionIdentifiers.includes(onsEnaDiarioBaciaIdentifier)) {
          return false;
        }
        onsEnaDiarioBaciaCollectionIdentifiers.push(onsEnaDiarioBaciaIdentifier);
        return true;
      });
      return [...onsEnaDiarioBaciasToAdd, ...onsEnaDiarioBaciaCollection];
    }
    return onsEnaDiarioBaciaCollection;
  }
}
