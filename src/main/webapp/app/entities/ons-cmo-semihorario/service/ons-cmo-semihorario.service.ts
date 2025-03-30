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
import { IOnsCmoSemihorario, NewOnsCmoSemihorario } from '../ons-cmo-semihorario.model';

export type PartialUpdateOnsCmoSemihorario = Partial<IOnsCmoSemihorario> & Pick<IOnsCmoSemihorario, 'id'>;

type RestOf<T extends IOnsCmoSemihorario | NewOnsCmoSemihorario> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsCmoSemihorario = RestOf<IOnsCmoSemihorario>;

export type NewRestOnsCmoSemihorario = RestOf<NewOnsCmoSemihorario>;

export type PartialUpdateRestOnsCmoSemihorario = RestOf<PartialUpdateOnsCmoSemihorario>;

export type EntityResponseType = HttpResponse<IOnsCmoSemihorario>;
export type EntityArrayResponseType = HttpResponse<IOnsCmoSemihorario[]>;

@Injectable({ providedIn: 'root' })
export class OnsCmoSemihorarioService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-cmo-semihorarios');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-cmo-semihorarios/_search');

  create(onsCmoSemihorario: NewOnsCmoSemihorario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCmoSemihorario);
    return this.http
      .post<RestOnsCmoSemihorario>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCmoSemihorario: IOnsCmoSemihorario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCmoSemihorario);
    return this.http
      .put<RestOnsCmoSemihorario>(`${this.resourceUrl}/${this.getOnsCmoSemihorarioIdentifier(onsCmoSemihorario)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCmoSemihorario: PartialUpdateOnsCmoSemihorario): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCmoSemihorario);
    return this.http
      .patch<RestOnsCmoSemihorario>(`${this.resourceUrl}/${this.getOnsCmoSemihorarioIdentifier(onsCmoSemihorario)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCmoSemihorario>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCmoSemihorario[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCmoSemihorario[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCmoSemihorario[]>()], asapScheduler)),
    );
  }

  getOnsCmoSemihorarioIdentifier(onsCmoSemihorario: Pick<IOnsCmoSemihorario, 'id'>): number {
    return onsCmoSemihorario.id;
  }

  compareOnsCmoSemihorario(o1: Pick<IOnsCmoSemihorario, 'id'> | null, o2: Pick<IOnsCmoSemihorario, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsCmoSemihorarioIdentifier(o1) === this.getOnsCmoSemihorarioIdentifier(o2) : o1 === o2;
  }

  addOnsCmoSemihorarioToCollectionIfMissing<Type extends Pick<IOnsCmoSemihorario, 'id'>>(
    onsCmoSemihorarioCollection: Type[],
    ...onsCmoSemihorariosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCmoSemihorarios: Type[] = onsCmoSemihorariosToCheck.filter(isPresent);
    if (onsCmoSemihorarios.length > 0) {
      const onsCmoSemihorarioCollectionIdentifiers = onsCmoSemihorarioCollection.map(onsCmoSemihorarioItem =>
        this.getOnsCmoSemihorarioIdentifier(onsCmoSemihorarioItem),
      );
      const onsCmoSemihorariosToAdd = onsCmoSemihorarios.filter(onsCmoSemihorarioItem => {
        const onsCmoSemihorarioIdentifier = this.getOnsCmoSemihorarioIdentifier(onsCmoSemihorarioItem);
        if (onsCmoSemihorarioCollectionIdentifiers.includes(onsCmoSemihorarioIdentifier)) {
          return false;
        }
        onsCmoSemihorarioCollectionIdentifiers.push(onsCmoSemihorarioIdentifier);
        return true;
      });
      return [...onsCmoSemihorariosToAdd, ...onsCmoSemihorarioCollection];
    }
    return onsCmoSemihorarioCollection;
  }

  protected convertDateFromClient<T extends IOnsCmoSemihorario | NewOnsCmoSemihorario | PartialUpdateOnsCmoSemihorario>(
    onsCmoSemihorario: T,
  ): RestOf<T> {
    return {
      ...onsCmoSemihorario,
      dinInstante: onsCmoSemihorario.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsCmoSemihorario: RestOnsCmoSemihorario): IOnsCmoSemihorario {
    return {
      ...restOnsCmoSemihorario,
      dinInstante: restOnsCmoSemihorario.dinInstante ? dayjs(restOnsCmoSemihorario.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsCmoSemihorario>): HttpResponse<IOnsCmoSemihorario> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsCmoSemihorario[]>): HttpResponse<IOnsCmoSemihorario[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
