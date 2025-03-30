import { Component, NgZone, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { Observable, Subscription, combineLatest, filter, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { SortByDirective, SortDirective, SortService, type SortState, sortStateSignal } from 'app/shared/sort';
import { FormsModule } from '@angular/forms';
import { DEFAULT_SORT_DATA, ITEM_DELETED_EVENT, SORT } from 'app/config/navigation.constants';
import { IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 } from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';
import {
  EntityArrayResponseType,
  OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service,
} from '../service/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.service';
import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DeleteDialogComponent } from '../delete/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-delete-dialog.component';

@Component({
  selector: 'jhi-ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024',
  templateUrl: './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.component.html',
  imports: [RouterModule, FormsModule, SharedModule, SortDirective, SortByDirective],
})
export class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component implements OnInit {
  private static readonly NOT_SORTABLE_FIELDS_AFTER_SEARCH = ['datPdp', 'codSubmercado', 'sglTipousina', 'codUsinapdp', 'nomUsinapdp'];

  subscription: Subscription | null = null;
  onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s = signal<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[]>([]);
  isLoading = false;

  sortState = sortStateSignal({});
  currentSearch = '';

  public readonly router = inject(Router);
  protected readonly onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service = inject(
    OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service,
  );
  protected readonly activatedRoute = inject(ActivatedRoute);
  protected readonly sortService = inject(SortService);
  protected modalService = inject(NgbModal);
  protected ngZone = inject(NgZone);

  trackId = (item: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024): number =>
    this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service.getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Identifier(
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
      OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component.NOT_SORTABLE_FIELDS_AFTER_SEARCH.includes(predicate)
    ) {
      this.navigateToWithComponentValues(this.getDefaultSortState());
      return;
    }
    this.navigateToWithComponentValues(this.sortState());
  }

  getDefaultSortState(): SortState {
    return this.sortService.parseSortParam(this.activatedRoute.snapshot.data[DEFAULT_SORT_DATA]);
  }

  delete(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024): void {
    const modalRef = this.modalService.open(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DeleteDialogComponent, {
      size: 'lg',
      backdrop: 'static',
    });
    modalRef.componentInstance.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 =
      onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024;
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
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Component.NOT_SORTABLE_FIELDS_AFTER_SEARCH.includes(predicate)
      ) {
        this.sortState.set({});
      }
    }
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s.set(this.refineData(dataFromBody));
  }

  protected refineData(
    data: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[],
  ): IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[] {
    const { predicate, order } = this.sortState();
    return predicate && order ? data.sort(this.sortService.startSort({ predicate, order })) : data;
  }

  protected fillComponentAttributesFromResponseBody(
    data: IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[] | null,
  ): IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024[] {
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
      return this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service
        .search(queryObject)
        .pipe(tap(() => (this.isLoading = false)));
    }
    return this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Service
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
