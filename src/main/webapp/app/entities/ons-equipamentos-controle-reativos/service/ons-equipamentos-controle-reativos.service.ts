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
import { IOnsEquipamentosControleReativos, NewOnsEquipamentosControleReativos } from '../ons-equipamentos-controle-reativos.model';

export type PartialUpdateOnsEquipamentosControleReativos = Partial<IOnsEquipamentosControleReativos> &
  Pick<IOnsEquipamentosControleReativos, 'id'>;

type RestOf<T extends IOnsEquipamentosControleReativos | NewOnsEquipamentosControleReativos> = Omit<
  T,
  'datEntradaoperacao' | 'datDesativacao'
> & {
  datEntradaoperacao?: string | null;
  datDesativacao?: string | null;
};

export type RestOnsEquipamentosControleReativos = RestOf<IOnsEquipamentosControleReativos>;

export type NewRestOnsEquipamentosControleReativos = RestOf<NewOnsEquipamentosControleReativos>;

export type PartialUpdateRestOnsEquipamentosControleReativos = RestOf<PartialUpdateOnsEquipamentosControleReativos>;

export type EntityResponseType = HttpResponse<IOnsEquipamentosControleReativos>;
export type EntityArrayResponseType = HttpResponse<IOnsEquipamentosControleReativos[]>;

@Injectable({ providedIn: 'root' })
export class OnsEquipamentosControleReativosService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-equipamentos-controle-reativos');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-equipamentos-controle-reativos/_search');

  create(onsEquipamentosControleReativos: NewOnsEquipamentosControleReativos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEquipamentosControleReativos);
    return this.http
      .post<RestOnsEquipamentosControleReativos>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsEquipamentosControleReativos: IOnsEquipamentosControleReativos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEquipamentosControleReativos);
    return this.http
      .put<RestOnsEquipamentosControleReativos>(
        `${this.resourceUrl}/${this.getOnsEquipamentosControleReativosIdentifier(onsEquipamentosControleReativos)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsEquipamentosControleReativos: PartialUpdateOnsEquipamentosControleReativos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsEquipamentosControleReativos);
    return this.http
      .patch<RestOnsEquipamentosControleReativos>(
        `${this.resourceUrl}/${this.getOnsEquipamentosControleReativosIdentifier(onsEquipamentosControleReativos)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsEquipamentosControleReativos>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsEquipamentosControleReativos[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsEquipamentosControleReativos[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsEquipamentosControleReativos[]>()], asapScheduler)),
    );
  }

  getOnsEquipamentosControleReativosIdentifier(onsEquipamentosControleReativos: Pick<IOnsEquipamentosControleReativos, 'id'>): number {
    return onsEquipamentosControleReativos.id;
  }

  compareOnsEquipamentosControleReativos(
    o1: Pick<IOnsEquipamentosControleReativos, 'id'> | null,
    o2: Pick<IOnsEquipamentosControleReativos, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsEquipamentosControleReativosIdentifier(o1) === this.getOnsEquipamentosControleReativosIdentifier(o2)
      : o1 === o2;
  }

  addOnsEquipamentosControleReativosToCollectionIfMissing<Type extends Pick<IOnsEquipamentosControleReativos, 'id'>>(
    onsEquipamentosControleReativosCollection: Type[],
    ...onsEquipamentosControleReativosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsEquipamentosControleReativos: Type[] = onsEquipamentosControleReativosToCheck.filter(isPresent);
    if (onsEquipamentosControleReativos.length > 0) {
      const onsEquipamentosControleReativosCollectionIdentifiers = onsEquipamentosControleReativosCollection.map(
        onsEquipamentosControleReativosItem => this.getOnsEquipamentosControleReativosIdentifier(onsEquipamentosControleReativosItem),
      );
      const onsEquipamentosControleReativosToAdd = onsEquipamentosControleReativos.filter(onsEquipamentosControleReativosItem => {
        const onsEquipamentosControleReativosIdentifier =
          this.getOnsEquipamentosControleReativosIdentifier(onsEquipamentosControleReativosItem);
        if (onsEquipamentosControleReativosCollectionIdentifiers.includes(onsEquipamentosControleReativosIdentifier)) {
          return false;
        }
        onsEquipamentosControleReativosCollectionIdentifiers.push(onsEquipamentosControleReativosIdentifier);
        return true;
      });
      return [...onsEquipamentosControleReativosToAdd, ...onsEquipamentosControleReativosCollection];
    }
    return onsEquipamentosControleReativosCollection;
  }

  protected convertDateFromClient<
    T extends IOnsEquipamentosControleReativos | NewOnsEquipamentosControleReativos | PartialUpdateOnsEquipamentosControleReativos,
  >(onsEquipamentosControleReativos: T): RestOf<T> {
    return {
      ...onsEquipamentosControleReativos,
      datEntradaoperacao: onsEquipamentosControleReativos.datEntradaoperacao?.format(DATE_FORMAT) ?? null,
      datDesativacao: onsEquipamentosControleReativos.datDesativacao?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsEquipamentosControleReativos: RestOnsEquipamentosControleReativos,
  ): IOnsEquipamentosControleReativos {
    return {
      ...restOnsEquipamentosControleReativos,
      datEntradaoperacao: restOnsEquipamentosControleReativos.datEntradaoperacao
        ? dayjs(restOnsEquipamentosControleReativos.datEntradaoperacao)
        : undefined,
      datDesativacao: restOnsEquipamentosControleReativos.datDesativacao
        ? dayjs(restOnsEquipamentosControleReativos.datDesativacao)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsEquipamentosControleReativos>,
  ): HttpResponse<IOnsEquipamentosControleReativos> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsEquipamentosControleReativos[]>,
  ): HttpResponse<IOnsEquipamentosControleReativos[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
