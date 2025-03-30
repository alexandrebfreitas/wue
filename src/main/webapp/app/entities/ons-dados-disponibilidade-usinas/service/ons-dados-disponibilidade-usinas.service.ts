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
import { IOnsDadosDisponibilidadeUsinas, NewOnsDadosDisponibilidadeUsinas } from '../ons-dados-disponibilidade-usinas.model';

export type PartialUpdateOnsDadosDisponibilidadeUsinas = Partial<IOnsDadosDisponibilidadeUsinas> &
  Pick<IOnsDadosDisponibilidadeUsinas, 'id'>;

type RestOf<T extends IOnsDadosDisponibilidadeUsinas | NewOnsDadosDisponibilidadeUsinas> = Omit<T, 'dinInstante'> & {
  dinInstante?: string | null;
};

export type RestOnsDadosDisponibilidadeUsinas = RestOf<IOnsDadosDisponibilidadeUsinas>;

export type NewRestOnsDadosDisponibilidadeUsinas = RestOf<NewOnsDadosDisponibilidadeUsinas>;

export type PartialUpdateRestOnsDadosDisponibilidadeUsinas = RestOf<PartialUpdateOnsDadosDisponibilidadeUsinas>;

export type EntityResponseType = HttpResponse<IOnsDadosDisponibilidadeUsinas>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosDisponibilidadeUsinas[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosDisponibilidadeUsinasService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-disponibilidade-usinas');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-disponibilidade-usinas/_search');

  create(onsDadosDisponibilidadeUsinas: NewOnsDadosDisponibilidadeUsinas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosDisponibilidadeUsinas);
    return this.http
      .post<RestOnsDadosDisponibilidadeUsinas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsDadosDisponibilidadeUsinas: IOnsDadosDisponibilidadeUsinas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosDisponibilidadeUsinas);
    return this.http
      .put<RestOnsDadosDisponibilidadeUsinas>(
        `${this.resourceUrl}/${this.getOnsDadosDisponibilidadeUsinasIdentifier(onsDadosDisponibilidadeUsinas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsDadosDisponibilidadeUsinas: PartialUpdateOnsDadosDisponibilidadeUsinas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosDisponibilidadeUsinas);
    return this.http
      .patch<RestOnsDadosDisponibilidadeUsinas>(
        `${this.resourceUrl}/${this.getOnsDadosDisponibilidadeUsinasIdentifier(onsDadosDisponibilidadeUsinas)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosDisponibilidadeUsinas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosDisponibilidadeUsinas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsDadosDisponibilidadeUsinas[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsDadosDisponibilidadeUsinas[]>()], asapScheduler)),
    );
  }

  getOnsDadosDisponibilidadeUsinasIdentifier(onsDadosDisponibilidadeUsinas: Pick<IOnsDadosDisponibilidadeUsinas, 'id'>): number {
    return onsDadosDisponibilidadeUsinas.id;
  }

  compareOnsDadosDisponibilidadeUsinas(
    o1: Pick<IOnsDadosDisponibilidadeUsinas, 'id'> | null,
    o2: Pick<IOnsDadosDisponibilidadeUsinas, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosDisponibilidadeUsinasIdentifier(o1) === this.getOnsDadosDisponibilidadeUsinasIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosDisponibilidadeUsinasToCollectionIfMissing<Type extends Pick<IOnsDadosDisponibilidadeUsinas, 'id'>>(
    onsDadosDisponibilidadeUsinasCollection: Type[],
    ...onsDadosDisponibilidadeUsinasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosDisponibilidadeUsinas: Type[] = onsDadosDisponibilidadeUsinasToCheck.filter(isPresent);
    if (onsDadosDisponibilidadeUsinas.length > 0) {
      const onsDadosDisponibilidadeUsinasCollectionIdentifiers = onsDadosDisponibilidadeUsinasCollection.map(
        onsDadosDisponibilidadeUsinasItem => this.getOnsDadosDisponibilidadeUsinasIdentifier(onsDadosDisponibilidadeUsinasItem),
      );
      const onsDadosDisponibilidadeUsinasToAdd = onsDadosDisponibilidadeUsinas.filter(onsDadosDisponibilidadeUsinasItem => {
        const onsDadosDisponibilidadeUsinasIdentifier = this.getOnsDadosDisponibilidadeUsinasIdentifier(onsDadosDisponibilidadeUsinasItem);
        if (onsDadosDisponibilidadeUsinasCollectionIdentifiers.includes(onsDadosDisponibilidadeUsinasIdentifier)) {
          return false;
        }
        onsDadosDisponibilidadeUsinasCollectionIdentifiers.push(onsDadosDisponibilidadeUsinasIdentifier);
        return true;
      });
      return [...onsDadosDisponibilidadeUsinasToAdd, ...onsDadosDisponibilidadeUsinasCollection];
    }
    return onsDadosDisponibilidadeUsinasCollection;
  }

  protected convertDateFromClient<
    T extends IOnsDadosDisponibilidadeUsinas | NewOnsDadosDisponibilidadeUsinas | PartialUpdateOnsDadosDisponibilidadeUsinas,
  >(onsDadosDisponibilidadeUsinas: T): RestOf<T> {
    return {
      ...onsDadosDisponibilidadeUsinas,
      dinInstante: onsDadosDisponibilidadeUsinas.dinInstante?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restOnsDadosDisponibilidadeUsinas: RestOnsDadosDisponibilidadeUsinas): IOnsDadosDisponibilidadeUsinas {
    return {
      ...restOnsDadosDisponibilidadeUsinas,
      dinInstante: restOnsDadosDisponibilidadeUsinas.dinInstante ? dayjs(restOnsDadosDisponibilidadeUsinas.dinInstante) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestOnsDadosDisponibilidadeUsinas>): HttpResponse<IOnsDadosDisponibilidadeUsinas> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosDisponibilidadeUsinas[]>,
  ): HttpResponse<IOnsDadosDisponibilidadeUsinas[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
