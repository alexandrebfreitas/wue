import { Component, NgZone, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { Observable, Subscription, combineLatest, filter, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { SortByDirective, SortDirective, SortService, type SortState, sortStateSignal } from 'app/shared/sort';
import { FormatMediumDatePipe } from 'app/shared/date';
import { FormsModule } from '@angular/forms';
import { DEFAULT_SORT_DATA, ITEM_DELETED_EVENT, SORT } from 'app/config/navigation.constants';
import { IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes } from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';
import {
  EntityArrayResponseType,
  OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService,
} from '../service/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.service';
import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDeleteDialogComponent } from '../delete/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-delete-dialog.component';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.component.html',
  imports: [RouterModule, FormsModule, SharedModule, SortDirective, SortByDirective, FormatMediumDatePipe],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent implements OnInit {
  private static readonly NOT_SORTABLE_FIELDS_AFTER_SEARCH = ['dscAgregacao', 'codCaracteristica', 'dscCaracteristica', 'idPeriodicidade'];

  subscription: Subscription | null = null;
  onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = signal<
    IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[]
  >([]);
  isLoading = false;

  sortState = sortStateSignal({});
  currentSearch = '';

  public readonly router = inject(Router);
  protected readonly onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService = inject(
    OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService,
  );
  protected readonly activatedRoute = inject(ActivatedRoute);
  protected readonly sortService = inject(SortService);
  protected modalService = inject(NgbModal);
  protected ngZone = inject(NgZone);

  trackId = (item: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes): number =>
    this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier(
      item,
    );

  ngOnInit(): void {
    this.subscription = combineLatest([this.activatedRoute.queryParamMap, this.activatedRoute.data])
      .pipe(
        tap(([params, data]) => this.fillComponentAttributeFromRoute(params, data)),
        tap(() => this.load()),
      )
      .subscribe();
  }

  search(query: string): void {
    this.currentSearch = query;
    const { predicate } = this.sortState();
    if (
      query &&
      predicate &&
      OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent.NOT_SORTABLE_FIELDS_AFTER_SEARCH.includes(predicate)
    ) {
      this.navigateToWithComponentValues(this.getDefaultSortState());
      return;
    }
    this.navigateToWithComponentValues(this.sortState());
  }

  getDefaultSortState(): SortState {
    return this.sortService.parseSortParam(this.activatedRoute.snapshot.data[DEFAULT_SORT_DATA]);
  }

  delete(
    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  ): void {
    const modalRef = this.modalService.open(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDeleteDialogComponent, {
      size: 'lg',
      backdrop: 'static',
    });
    modalRef.componentInstance.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes =
      onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed
      .pipe(
        filter(reason => reason === ITEM_DELETED_EVENT),
        tap(() => this.load()),
      )
      .subscribe();
  }

  load(): void {
    this.queryBackend().subscribe({
      next: (res: EntityArrayResponseType) => {
        this.onResponseSuccess(res);
      },
    });
  }

  navigateToWithComponentValues(event: SortState): void {
    this.handleNavigation(event, this.currentSearch);
  }

  protected fillComponentAttributeFromRoute(params: ParamMap, data: Data): void {
    this.sortState.set(this.sortService.parseSortParam(params.get(SORT) ?? data[DEFAULT_SORT_DATA]));
    if (params.has('search') && params.get('search') !== '') {
      this.currentSearch = params.get('search') as string;
      const { predicate } = this.sortState();
      if (
        predicate &&
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent.NOT_SORTABLE_FIELDS_AFTER_SEARCH.includes(predicate)
      ) {
        this.sortState.set({});
      }
    }
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.set(this.refineData(dataFromBody));
  }

  protected refineData(
    data: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[],
  ): IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[] {
    const { predicate, order } = this.sortState();
    return predicate && order ? data.sort(this.sortService.startSort({ predicate, order })) : data;
  }

  protected fillComponentAttributesFromResponseBody(
    data: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[] | null,
  ): IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes[] {
    return data ?? [];
  }

  protected queryBackend(): Observable<EntityArrayResponseType> {
    const { currentSearch } = this;

    this.isLoading = true;
    const queryObject: any = {
      query: currentSearch,
      sort: this.sortService.buildSortParam(this.sortState()),
    };
    if (this.currentSearch && this.currentSearch !== '') {
      return this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService
        .search(queryObject)
        .pipe(tap(() => (this.isLoading = false)));
    }
    return this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService
      .query(queryObject)
      .pipe(tap(() => (this.isLoading = false)));
  }

  protected handleNavigation(sortState: SortState, currentSearch?: string): void {
    const queryParamsObj = {
      search: currentSearch,
      sort: this.sortService.buildSortParam(sortState),
    };

    this.ngZone.run(() => {
      this.router.navigate(['./'], {
        relativeTo: this.activatedRoute,
        queryParams: queryParamsObj,
      });
    });
  }
}
