import { Component, NgZone, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { Observable, Subscription, combineLatest, filter, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { SortByDirective, SortDirective, SortService, type SortState, sortStateSignal } from 'app/shared/sort';
import { FormsModule } from '@angular/forms';
import { DEFAULT_SORT_DATA, ITEM_DELETED_EVENT, SORT } from 'app/config/navigation.constants';
import { IOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';
import { EntityArrayResponseType, OnsEnaDiarioReservatorioService } from '../service/ons-ena-diario-reservatorio.service';
import { OnsEnaDiarioReservatorioDeleteDialogComponent } from '../delete/ons-ena-diario-reservatorio-delete-dialog.component';

@Component({
  selector: 'jhi-ons-ena-diario-reservatorio',
  templateUrl: './ons-ena-diario-reservatorio.component.html',
  imports: [RouterModule, FormsModule, SharedModule, SortDirective, SortByDirective],
})
export class OnsEnaDiarioReservatorioComponent implements OnInit {
  subscription: Subscription | null = null;
  onsEnaDiarioReservatorios = signal<IOnsEnaDiarioReservatorio[]>([]);
  isLoading = false;

  sortState = sortStateSignal({});
  currentSearch = '';

  public readonly router = inject(Router);
  protected readonly onsEnaDiarioReservatorioService = inject(OnsEnaDiarioReservatorioService);
  protected readonly activatedRoute = inject(ActivatedRoute);
  protected readonly sortService = inject(SortService);
  protected modalService = inject(NgbModal);
  protected ngZone = inject(NgZone);

  trackId = (item: IOnsEnaDiarioReservatorio): number => this.onsEnaDiarioReservatorioService.getOnsEnaDiarioReservatorioIdentifier(item);

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
    this.navigateToWithComponentValues(this.sortState());
  }

  getDefaultSortState(): SortState {
    return this.sortService.parseSortParam(this.activatedRoute.snapshot.data[DEFAULT_SORT_DATA]);
  }

  delete(onsEnaDiarioReservatorio: IOnsEnaDiarioReservatorio): void {
    const modalRef = this.modalService.open(OnsEnaDiarioReservatorioDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.onsEnaDiarioReservatorio = onsEnaDiarioReservatorio;
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
    }
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.onsEnaDiarioReservatorios.set(this.refineData(dataFromBody));
  }

  protected refineData(data: IOnsEnaDiarioReservatorio[]): IOnsEnaDiarioReservatorio[] {
    const { predicate, order } = this.sortState();
    return predicate && order ? data.sort(this.sortService.startSort({ predicate, order })) : data;
  }

  protected fillComponentAttributesFromResponseBody(data: IOnsEnaDiarioReservatorio[] | null): IOnsEnaDiarioReservatorio[] {
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
      return this.onsEnaDiarioReservatorioService.search(queryObject).pipe(tap(() => (this.isLoading = false)));
    }
    return this.onsEnaDiarioReservatorioService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
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
