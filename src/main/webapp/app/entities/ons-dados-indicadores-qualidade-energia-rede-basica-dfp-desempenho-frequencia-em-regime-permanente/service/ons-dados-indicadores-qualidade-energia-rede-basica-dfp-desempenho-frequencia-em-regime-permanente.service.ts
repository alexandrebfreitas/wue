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
import {
  IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
  NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
} from '../ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanente.model';

export type PartialUpdateOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente =
  Partial<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente> &
    Pick<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente, 'id'>;

type RestOf<
  T extends
    | IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
    | NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
> = Omit<T, 'dinReferencia'> & {
  dinReferencia?: string | null;
};

export type RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente =
  RestOf<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente>;

export type NewRestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente =
  RestOf<NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente>;

export type PartialUpdateRestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente =
  RestOf<PartialUpdateOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente>;

export type EntityResponseType = HttpResponse<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente>;
export type EntityArrayResponseType = HttpResponse<
  IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente[]
>;

@Injectable({ providedIn: 'root' })
export class OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes/_search',
  );

  create(
    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente: NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente);
    return this.http
      .post<RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente>(this.resourceUrl, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente);
    return this.http
      .put<RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente>(
        `${this.resourceUrl}/${this.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente: PartialUpdateOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente);
    return this.http
      .patch<RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente>(
        `${this.resourceUrl}/${this.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente>(`${this.resourceUrl}/${id}`, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente[]
      >(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente[]
      >(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() =>
          scheduled(
            [new HttpResponse<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente[]>()],
            asapScheduler,
          ),
        ),
      );
  }

  getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier(
    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente: Pick<
      IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
      'id'
    >,
  ): number {
    return onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.id;
  }

  compareOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente(
    o1: Pick<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente, 'id'> | null,
    o2: Pick<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier(o1) ===
          this.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteToCollectionIfMissing<
    Type extends Pick<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente, 'id'>,
  >(
    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteCollection: Type[],
    ...onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes: Type[] =
      onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentesToCheck.filter(isPresent);
    if (onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes.length > 0) {
      const onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteCollectionIdentifiers =
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteCollection.map(
          onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteItem =>
            this.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier(
              onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteItem,
            ),
        );
      const onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentesToAdd =
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes.filter(
          onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteItem => {
            const onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier =
              this.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteItem,
              );
            if (
              onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteCollectionIdentifiers.includes(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier,
              )
            ) {
              return false;
            }
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteCollectionIdentifiers.push(
              onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteIdentifier,
            );
            return true;
          },
        );
      return [
        ...onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentesToAdd,
        ...onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteCollection,
      ];
    }
    return onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
      | NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
      | PartialUpdateOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
  >(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente: T): RestOf<T> {
    return {
      ...onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
      dinReferencia:
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.dinReferencia?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente: RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
  ): IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente {
    return {
      ...restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
      dinReferencia: restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.dinReferencia
        ? dayjs(restOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.dinReferencia)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente>,
  ): HttpResponse<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente[]>,
  ): HttpResponse<IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
