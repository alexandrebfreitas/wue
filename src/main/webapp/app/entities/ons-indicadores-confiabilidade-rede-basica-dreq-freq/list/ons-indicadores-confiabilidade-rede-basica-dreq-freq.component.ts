import { Component, NgZone, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { Observable, Subscription, combineLatest, filter, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { SortByDirective, SortDirective, SortService, type SortState, sortStateSignal } from 'app/shared/sort';
import { FormatMediumDatePipe } from 'app/shared/date';
import { FormsModule } from '@angular/forms';
import { DEFAULT_SORT_DATA, ITEM_DELETED_EVENT, SORT } from 'app/config/navigation.constants';
import { IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq } from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';
import {
  EntityArrayResponseType,
  OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService,
} from '../service/ons-indicadores-confiabilidade-rede-basica-dreq-freq.service';
import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDeleteDialogComponent } from '../delete/ons-indicadores-confiabilidade-rede-basica-dreq-freq-delete-dialog.component';

@Component({
  selector: 'jhi-ons-indicadores-confiabilidade-rede-basica-dreq-freq',
  templateUrl: './ons-indicadores-confiabilidade-rede-basica-dreq-freq.component.html',
  imports: [RouterModule, FormsModule, SharedModule, SortDirective, SortByDirective, FormatMediumDatePipe],
})
export class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent implements OnInit {
  private static readonly NOT_SORTABLE_FIELDS_AFTER_SEARCH = ['dscAgregacao', 'codCaracteristica', 'dscCaracteristica', 'idPeriodicidade'];

  subscription: Subscription | null = null;
  onsIndicadoresConfiabilidadeRedeBasicaDreqFreqs = signal<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[]>([]);
  isLoading = false;

  sortState = sortStateSignal({});
  currentSearch = '';

  public readonly router = inject(Router);
  protected readonly onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService = inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService);
  protected readonly activatedRoute = inject(ActivatedRoute);
  protected readonly sortService = inject(SortService);
  protected modalService = inject(NgbModal);
  protected ngZone = inject(NgZone);

  trackId = (item: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq): number =>
    this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier(item);

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
      OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent.NOT_SORTABLE_FIELDS_AFTER_SEARCH.includes(predicate)
    ) {
      this.navigateToWithComponentValues(this.getDefaultSortState());
      return;
    }
    this.navigateToWithComponentValues(this.sortState());
  }

  getDefaultSortState(): SortState {
    return this.sortService.parseSortParam(this.activatedRoute.snapshot.data[DEFAULT_SORT_DATA]);
  }

  delete(onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq): void {
    const modalRef = this.modalService.open(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDeleteDialogComponent, {
      size: 'lg',
      backdrop: 'static',
    });
    modalRef.componentInstance.onsIndicadoresConfiabilidadeRedeBasicaDreqFreq = onsIndicadoresConfiabilidadeRedeBasicaDreqFreq;
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
      if (predicate && OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent.NOT_SORTABLE_FIELDS_AFTER_SEARCH.includes(predicate)) {
        this.sortState.set({});
      }
    }
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqs.set(this.refineData(dataFromBody));
  }

  protected refineData(data: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[]): IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[] {
    const { predicate, order } = this.sortState();
    return predicate && order ? data.sort(this.sortService.startSort({ predicate, order })) : data;
  }

  protected fillComponentAttributesFromResponseBody(
    data: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[] | null,
  ): IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq[] {
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
      return this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService.search(queryObject).pipe(tap(() => (this.isLoading = false)));
    }
    return this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
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
