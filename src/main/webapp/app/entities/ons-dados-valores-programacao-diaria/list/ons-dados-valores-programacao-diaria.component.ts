import { Component, NgZone, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { Observable, Subscription, combineLatest, filter, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { SortByDirective, SortDirective, SortService, type SortState, sortStateSignal } from 'app/shared/sort';
import { FormatMediumDatePipe } from 'app/shared/date';
import { FormsModule } from '@angular/forms';
import { DEFAULT_SORT_DATA, ITEM_DELETED_EVENT, SORT } from 'app/config/navigation.constants';
import { IOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';
import { EntityArrayResponseType, OnsDadosValoresProgramacaoDiariaService } from '../service/ons-dados-valores-programacao-diaria.service';
import { OnsDadosValoresProgramacaoDiariaDeleteDialogComponent } from '../delete/ons-dados-valores-programacao-diaria-delete-dialog.component';

@Component({
  selector: 'jhi-ons-dados-valores-programacao-diaria',
  templateUrl: './ons-dados-valores-programacao-diaria.component.html',
  imports: [RouterModule, FormsModule, SharedModule, SortDirective, SortByDirective, FormatMediumDatePipe],
})
export class OnsDadosValoresProgramacaoDiariaComponent implements OnInit {
  private static readonly NOT_SORTABLE_FIELDS_AFTER_SEARCH = [
    'codExibicaousina',
    'nomUsina',
    'tipGeracao',
    'nomModalidadeoperacao',
    'idSubsistema',
    'nomSubsistema',
    'idEstado',
    'nomEstado',
  ];

  subscription: Subscription | null = null;
  onsDadosValoresProgramacaoDiarias = signal<IOnsDadosValoresProgramacaoDiaria[]>([]);
  isLoading = false;

  sortState = sortStateSignal({});
  currentSearch = '';

  public readonly router = inject(Router);
  protected readonly onsDadosValoresProgramacaoDiariaService = inject(OnsDadosValoresProgramacaoDiariaService);
  protected readonly activatedRoute = inject(ActivatedRoute);
  protected readonly sortService = inject(SortService);
  protected modalService = inject(NgbModal);
  protected ngZone = inject(NgZone);

  trackId = (item: IOnsDadosValoresProgramacaoDiaria): number =>
    this.onsDadosValoresProgramacaoDiariaService.getOnsDadosValoresProgramacaoDiariaIdentifier(item);

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
    if (query && predicate && OnsDadosValoresProgramacaoDiariaComponent.NOT_SORTABLE_FIELDS_AFTER_SEARCH.includes(predicate)) {
      this.navigateToWithComponentValues(this.getDefaultSortState());
      return;
    }
    this.navigateToWithComponentValues(this.sortState());
  }

  getDefaultSortState(): SortState {
    return this.sortService.parseSortParam(this.activatedRoute.snapshot.data[DEFAULT_SORT_DATA]);
  }

  delete(onsDadosValoresProgramacaoDiaria: IOnsDadosValoresProgramacaoDiaria): void {
    const modalRef = this.modalService.open(OnsDadosValoresProgramacaoDiariaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.onsDadosValoresProgramacaoDiaria = onsDadosValoresProgramacaoDiaria;
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
      if (predicate && OnsDadosValoresProgramacaoDiariaComponent.NOT_SORTABLE_FIELDS_AFTER_SEARCH.includes(predicate)) {
        this.sortState.set({});
      }
    }
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.onsDadosValoresProgramacaoDiarias.set(this.refineData(dataFromBody));
  }

  protected refineData(data: IOnsDadosValoresProgramacaoDiaria[]): IOnsDadosValoresProgramacaoDiaria[] {
    const { predicate, order } = this.sortState();
    return predicate && order ? data.sort(this.sortService.startSort({ predicate, order })) : data;
  }

  protected fillComponentAttributesFromResponseBody(data: IOnsDadosValoresProgramacaoDiaria[] | null): IOnsDadosValoresProgramacaoDiaria[] {
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
      return this.onsDadosValoresProgramacaoDiariaService.search(queryObject).pipe(tap(() => (this.isLoading = false)));
    }
    return this.onsDadosValoresProgramacaoDiariaService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
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
