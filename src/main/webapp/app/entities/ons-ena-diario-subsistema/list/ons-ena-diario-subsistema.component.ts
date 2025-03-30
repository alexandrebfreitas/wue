import { Component, NgZone, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { Observable, Subscription, combineLatest, filter, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { SortByDirective, SortDirective, SortService, type SortState, sortStateSignal } from 'app/shared/sort';
import { FormsModule } from '@angular/forms';
import { DEFAULT_SORT_DATA, ITEM_DELETED_EVENT, SORT } from 'app/config/navigation.constants';
import { IOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';
import { EntityArrayResponseType, OnsEnaDiarioSubsistemaService } from '../service/ons-ena-diario-subsistema.service';
import { OnsEnaDiarioSubsistemaDeleteDialogComponent } from '../delete/ons-ena-diario-subsistema-delete-dialog.component';

@Component({
  selector: 'jhi-ons-ena-diario-subsistema',
  templateUrl: './ons-ena-diario-subsistema.component.html',
  imports: [RouterModule, FormsModule, SharedModule, SortDirective, SortByDirective],
})
export class OnsEnaDiarioSubsistemaComponent implements OnInit {
  subscription: Subscription | null = null;
  onsEnaDiarioSubsistemas = signal<IOnsEnaDiarioSubsistema[]>([]);
  isLoading = false;

  sortState = sortStateSignal({});
  currentSearch = '';

  public readonly router = inject(Router);
  protected readonly onsEnaDiarioSubsistemaService = inject(OnsEnaDiarioSubsistemaService);
  protected readonly activatedRoute = inject(ActivatedRoute);
  protected readonly sortService = inject(SortService);
  protected modalService = inject(NgbModal);
  protected ngZone = inject(NgZone);

  trackId = (item: IOnsEnaDiarioSubsistema): number => this.onsEnaDiarioSubsistemaService.getOnsEnaDiarioSubsistemaIdentifier(item);

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

  delete(onsEnaDiarioSubsistema: IOnsEnaDiarioSubsistema): void {
    const modalRef = this.modalService.open(OnsEnaDiarioSubsistemaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.onsEnaDiarioSubsistema = onsEnaDiarioSubsistema;
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
    this.onsEnaDiarioSubsistemas.set(this.refineData(dataFromBody));
  }

  protected refineData(data: IOnsEnaDiarioSubsistema[]): IOnsEnaDiarioSubsistema[] {
    const { predicate, order } = this.sortState();
    return predicate && order ? data.sort(this.sortService.startSort({ predicate, order })) : data;
  }

  protected fillComponentAttributesFromResponseBody(data: IOnsEnaDiarioSubsistema[] | null): IOnsEnaDiarioSubsistema[] {
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
      return this.onsEnaDiarioSubsistemaService.search(queryObject).pipe(tap(() => (this.isLoading = false)));
    }
    return this.onsEnaDiarioSubsistemaService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
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
