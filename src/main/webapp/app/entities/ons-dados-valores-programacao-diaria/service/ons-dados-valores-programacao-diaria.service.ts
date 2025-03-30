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
import { IOnsDadosValoresProgramacaoDiaria, NewOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';

export type PartialUpdateOnsDadosValoresProgramacaoDiaria = Partial<IOnsDadosValoresProgramacaoDiaria> &
  Pick<IOnsDadosValoresProgramacaoDiaria, 'id'>;

type RestOf<T extends IOnsDadosValoresProgramacaoDiaria | NewOnsDadosValoresProgramacaoDiaria> = Omit<T, 'dinProgramacaodia'> & {
  dinProgramacaodia?: string | null;
};

export type RestOnsDadosValoresProgramacaoDiaria = RestOf<IOnsDadosValoresProgramacaoDiaria>;

export type NewRestOnsDadosValoresProgramacaoDiaria = RestOf<NewOnsDadosValoresProgramacaoDiaria>;

export type PartialUpdateRestOnsDadosValoresProgramacaoDiaria = RestOf<PartialUpdateOnsDadosValoresProgramacaoDiaria>;

export type EntityResponseType = HttpResponse<IOnsDadosValoresProgramacaoDiaria>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosValoresProgramacaoDiaria[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosValoresProgramacaoDiariaService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-valores-programacao-diarias');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/ons-dados-valores-programacao-diarias/_search');

  create(onsDadosValoresProgramacaoDiaria: NewOnsDadosValoresProgramacaoDiaria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosValoresProgramacaoDiaria);
    return this.http
      .post<RestOnsDadosValoresProgramacaoDiaria>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosValoresProgramacaoDiaria);
    return this.http
      .put<RestOnsDadosValoresProgramacaoDiaria>(
        `${this.resourceUrl}/${this.getOnsDadosValoresProgramacaoDiariaIdentifier(onsDadosValoresProgramacaoDiaria)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(onsDadosValoresProgramacaoDiaria: PartialUpdateOnsDadosValoresProgramacaoDiaria): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosValoresProgramacaoDiaria);
    return this.http
      .patch<RestOnsDadosValoresProgramacaoDiaria>(
        `${this.resourceUrl}/${this.getOnsDadosValoresProgramacaoDiariaIdentifier(onsDadosValoresProgramacaoDiaria)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosValoresProgramacaoDiaria>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestOnsDadosValoresProgramacaoDiaria[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestOnsDadosValoresProgramacaoDiaria[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IOnsDadosValoresProgramacaoDiaria[]>()], asapScheduler)),
    );
  }

  getOnsDadosValoresProgramacaoDiariaIdentifier(onsDadosValoresProgramacaoDiaria: Pick<IOnsDadosValoresProgramacaoDiaria, 'id'>): number {
    return onsDadosValoresProgramacaoDiaria.id;
  }

  compareOnsDadosValoresProgramacaoDiaria(
    o1: Pick<IOnsDadosValoresProgramacaoDiaria, 'id'> | null,
    o2: Pick<IOnsDadosValoresProgramacaoDiaria, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosValoresProgramacaoDiariaIdentifier(o1) === this.getOnsDadosValoresProgramacaoDiariaIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosValoresProgramacaoDiariaToCollectionIfMissing<Type extends Pick<IOnsDadosValoresProgramacaoDiaria, 'id'>>(
    onsDadosValoresProgramacaoDiariaCollection: Type[],
    ...onsDadosValoresProgramacaoDiariasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosValoresProgramacaoDiarias: Type[] = onsDadosValoresProgramacaoDiariasToCheck.filter(isPresent);
    if (onsDadosValoresProgramacaoDiarias.length > 0) {
      const onsDadosValoresProgramacaoDiariaCollectionIdentifiers = onsDadosValoresProgramacaoDiariaCollection.map(
        onsDadosValoresProgramacaoDiariaItem => this.getOnsDadosValoresProgramacaoDiariaIdentifier(onsDadosValoresProgramacaoDiariaItem),
      );
      const onsDadosValoresProgramacaoDiariasToAdd = onsDadosValoresProgramacaoDiarias.filter(onsDadosValoresProgramacaoDiariaItem => {
        const onsDadosValoresProgramacaoDiariaIdentifier = this.getOnsDadosValoresProgramacaoDiariaIdentifier(
          onsDadosValoresProgramacaoDiariaItem,
        );
        if (onsDadosValoresProgramacaoDiariaCollectionIdentifiers.includes(onsDadosValoresProgramacaoDiariaIdentifier)) {
          return false;
        }
        onsDadosValoresProgramacaoDiariaCollectionIdentifiers.push(onsDadosValoresProgramacaoDiariaIdentifier);
        return true;
      });
      return [...onsDadosValoresProgramacaoDiariasToAdd, ...onsDadosValoresProgramacaoDiariaCollection];
    }
    return onsDadosValoresProgramacaoDiariaCollection;
  }

  protected convertDateFromClient<
    T extends IOnsDadosValoresProgramacaoDiaria | NewOnsDadosValoresProgramacaoDiaria | PartialUpdateOnsDadosValoresProgramacaoDiaria,
  >(onsDadosValoresProgramacaoDiaria: T): RestOf<T> {
    return {
      ...onsDadosValoresProgramacaoDiaria,
      dinProgramacaodia: onsDadosValoresProgramacaoDiaria.dinProgramacaodia?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosValoresProgramacaoDiaria: RestOnsDadosValoresProgramacaoDiaria,
  ): IOnsDadosValoresProgramacaoDiaria {
    return {
      ...restOnsDadosValoresProgramacaoDiaria,
      dinProgramacaodia: restOnsDadosValoresProgramacaoDiaria.dinProgramacaodia
        ? dayjs(restOnsDadosValoresProgramacaoDiaria.dinProgramacaodia)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosValoresProgramacaoDiaria>,
  ): HttpResponse<IOnsDadosValoresProgramacaoDiaria> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosValoresProgramacaoDiaria[]>,
  ): HttpResponse<IOnsDadosValoresProgramacaoDiaria[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
