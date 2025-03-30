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
import { IOnsCvuUsinaTermicas, NewOnsCvuUsinaTermicas } from '../ons-cvu-usina-termicas.model';

export type PartialUpdateOnsCvuUsinaTermicas = Partial<IOnsCvuUsinaTermicas> & Pick<IOnsCvuUsinaTermicas, 'id'>;

type RestOf<T extends IOnsCvuUsinaTermicas | NewOnsCvuUsinaTermicas> = Omit<T, 'datIniciosemana' | 'datFimsemana'> & {
  datIniciosemana?: string | null;
  datFimsemana?: string | null;
};

export type RestOnsCvuUsinaTermicas = RestOf<IOnsCvuUsinaTermicas>;

export type NewRestOnsCvuUsinaTermicas = RestOf<NewOnsCvuUsinaTermicas>;

export type PartialUpdateRestOnsCvuUsinaTermicas = RestOf<PartialUpdateOnsCvuUsinaTermicas>;

export type EntityResponseType = HttpResponse<IOnsCvuUsinaTermicas>;
export type EntityArrayResponseType = HttpResponse<IOnsCvuUsinaTermicas[]>;

@Injectable({ providedIn: 'root' })
export class OnsCvuUsinaTermicasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-cvu-usina-termicas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-cvu-usina-termicas/_search');

  create(onsCvuUsinaTermicas: NewOnsCvuUsinaTermicas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCvuUsinaTermicas);
    return this.http
      .post<RestOnsCvuUsinaTermicas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsCvuUsinaTermicas: IOnsCvuUsinaTermicas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCvuUsinaTermicas);
    return this.http
      .put<RestOnsCvuUsinaTermicas>(`${this.resourceUrl}/${this.getOnsCvuUsinaTermicasIdentifier(onsCvuUsinaTermicas)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsCvuUsinaTermicas: PartialUpdateOnsCvuUsinaTermicas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsCvuUsinaTermicas);
    return this.http
      .patch<RestOnsCvuUsinaTermicas>(`${this.resourceUrl}/${this.getOnsCvuUsinaTermicasIdentifier(onsCvuUsinaTermicas)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsCvuUsinaTermicas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsCvuUsinaTermicas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsCvuUsinaTermicas[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsCvuUsinaTermicas[]>()], asapScheduler)),
    );
  }

  getOnsCvuUsinaTermicasIdentifier(onsCvuUsinaTermicas: Pick<IOnsCvuUsinaTermicas, 'id'>): number {
    return onsCvuUsinaTermicas.id;
  }

  compareOnsCvuUsinaTermicas(o1: Pick<IOnsCvuUsinaTermicas, 'id'> | null, o2: Pick<IOnsCvuUsinaTermicas, 'id'> | null): boolean {
    return o1 && o2 ? this.getOnsCvuUsinaTermicasIdentifier(o1) === this.getOnsCvuUsinaTermicasIdentifier(o2) : o1 === o2;
  }

  addOnsCvuUsinaTermicasToCollectionIfMissing<Type extends Pick<IOnsCvuUsinaTermicas, 'id'>>(
    onsCvuUsinaTermicasCollection: Type[],
    ...onsCvuUsinaTermicasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsCvuUsinaTermicas: Type[] = onsCvuUsinaTermicasToCheck.filter(isPresent);
    if (onsCvuUsinaTermicas.length > 0) {
      const onsCvuUsinaTermicasCollectionIdentifiers = onsCvuUsinaTermicasCollection.map(onsCvuUsinaTermicasItem =>
        this.getOnsCvuUsinaTermicasIdentifier(onsCvuUsinaTermicasItem),
      );
      const onsCvuUsinaTermicasToAdd = onsCvuUsinaTermicas.filter(onsCvuUsinaTermicasItem => {
        const onsCvuUsinaTermicasIdentifier = this.getOnsCvuUsinaTermicasIdentifier(onsCvuUsinaTermicasItem);
        if (onsCvuUsinaTermicasCollectionIdentifiers.includes(onsCvuUsinaTermicasIdentifier)) {
          return false;
        }
        onsCvuUsinaTermicasCollectionIdentifiers.push(onsCvuUsinaTermicasIdentifier);
        return true;
      });
      return [...onsCvuUsinaTermicasToAdd, ...onsCvuUsinaTermicasCollection];
    }
    return onsCvuUsinaTermicasCollection;
  }

  protected convertDateFromClient<T extends IOnsCvuUsinaTermicas | NewOnsCvuUsinaTermicas | PartialUpdateOnsCvuUsinaTermicas>(
    onsCvuUsinaTermicas: T,
  ): RestOf<T> {
    return {
      ...onsCvuUsinaTermicas,
      datIniciosemana: onsCvuUsinaTermicas.datIniciosemana?.format(DATE_FORMAT) ?? null,
      datFimsemana: onsCvuUsinaTermicas.datFimsemana?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsCvuUsinaTermicas: RestOnsCvuUsinaTermicas): IOnsCvuUsinaTermicas {
    return {
      ...restOnsCvuUsinaTermicas,
      datIniciosemana: restOnsCvuUsinaTermicas.datIniciosemana ? dayjs(restOnsCvuUsinaTermicas.datIniciosemana) : undefined,
      datFimsemana: restOnsCvuUsinaTermicas.datFimsemana ? dayjs(restOnsCvuUsinaTermicas.datFimsemana) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsCvuUsinaTermicas>): HttpResponse<IOnsCvuUsinaTermicas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestOnsCvuUsinaTermicas[]>): HttpResponse<IOnsCvuUsinaTermicas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
