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
  IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
} from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.model';

export type PartialUpdateOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
  Partial<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores> &
    Pick<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores, 'id'>;

type RestOf<
  T extends
    | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
    | NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
> = Omit<T, 'dinReferencia'> & {
  dinReferencia?: string | null;
};

export type RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
  RestOf<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>;

export type NewRestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
  RestOf<NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>;

export type PartialUpdateRestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores =
  RestOf<PartialUpdateOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>;

export type EntityResponseType = HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>;
export type EntityArrayResponseType = HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[]>;

@Injectable({ providedIn: 'root' })
export class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores',
  );
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor(
    'api/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores/_search',
  );

  create(
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores);
    return this.http
      .post<RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>(this.resourceUrl, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores);
    return this.http
      .put<RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>(
        `${this.resourceUrl}/${this.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: PartialUpdateOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores);
    return this.http
      .patch<RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>(
        `${this.resourceUrl}/${this.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores)}`,
        copy,
        { observe: 'response' },
      )
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>(`${this.resourceUrl}/${id}`, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<
        RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[]
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
        RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[]
      >(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(
        map(res => this.convertResponseArrayFromServer(res)),

        catchError(() =>
          scheduled(
            [new HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[]>()],
            asapScheduler,
          ),
        ),
      );
  }

  getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier(
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: Pick<
      IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
      'id'
    >,
  ): number {
    return onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.id;
  }

  compareOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
    o1: Pick<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores, 'id'> | null,
    o2: Pick<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores, 'id'> | null,
  ): boolean {
    return o1 && o2
      ? this.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier(o1) ===
          this.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier(o2)
      : o1 === o2;
  }

  addOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCollectionIfMissing<
    Type extends Pick<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores, 'id'>,
  >(
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection: Type[],
    ...onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCheck: (Type | null | undefined)[]
  ): Type[] {
    const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: Type[] =
      onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToCheck.filter(isPresent);
    if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.length > 0) {
      const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollectionIdentifiers =
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection.map(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresItem =>
            this.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier(
              onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresItem,
            ),
        );
      const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToAdd =
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.filter(
          onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresItem => {
            const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier =
              this.getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresItem,
              );
            if (
              onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollectionIdentifiers.includes(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier,
              )
            ) {
              return false;
            }
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollectionIdentifiers.push(
              onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresIdentifier,
            );
            return true;
          },
        );
      return [
        ...onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresToAdd,
        ...onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection,
      ];
    }
    return onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresCollection;
  }

  protected convertDateFromClient<
    T extends
      | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
      | NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
      | PartialUpdateOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  >(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: T): RestOf<T> {
    return {
      ...onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
      dinReferencia:
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.dinReferencia?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(
    restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  ): IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores {
    return {
      ...restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
      dinReferencia: restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.dinReferencia
        ? dayjs(restOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.dinReferencia)
        : undefined,
    };
  }

  protected convertResponseFromServer(
    res: HttpResponse<RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>,
  ): HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(
    res: HttpResponse<RestOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[]>,
  ): HttpResponse<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
