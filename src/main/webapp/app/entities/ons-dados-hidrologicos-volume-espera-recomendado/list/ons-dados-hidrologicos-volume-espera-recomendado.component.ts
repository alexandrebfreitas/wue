import { Component, NgZone, OnInit, inject, signal } from '@angular/core';
import { ActivatedRoute, Data, ParamMap, Router, RouterModule } from '@angular/router';
import { Observable, Subscription, combineLatest, filter, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { SortByDirective, SortDirective, SortService, type SortState, sortStateSignal } from 'app/shared/sort';
import { FormatMediumDatePipe } from 'app/shared/date';
import { FormsModule } from '@angular/forms';
import { DEFAULT_SORT_DATA, ITEM_DELETED_EVENT, SORT } from 'app/config/navigation.constants';
import { IOnsDadosHidrologicosVolumeEsperaRecomendado } from '../ons-dados-hidrologicos-volume-espera-recomendado.model';
import {
  EntityArrayResponseType,
  OnsDadosHidrologicosVolumeEsperaRecomendadoService,
} from '../service/ons-dados-hidrologicos-volume-espera-recomendado.service';
import { OnsDadosHidrologicosVolumeEsperaRecomendadoDeleteDialogComponent } from '../delete/ons-dados-hidrologicos-volume-espera-recomendado-delete-dialog.component';

@Component({
  selector: 'jhi-ons-dados-hidrologicos-volume-espera-recomendado',
  templateUrl: './ons-dados-hidrologicos-volume-espera-recomendado.component.html',
  imports: [RouterModule, FormsModule, SharedModule, SortDirective, SortByDirective, FormatMediumDatePipe],
})
export class OnsDadosHidrologicosVolumeEsperaRecomendadoComponent implements OnInit {
  subscription: Subscription | null = null;
  onsDadosHidrologicosVolumeEsperaRecomendados = signal<IOnsDadosHidrologicosVolumeEsperaRecomendado[]>([]);
  isLoading = false;

  sortState = sortStateSignal({});
  currentSearch = '';

  public readonly router = inject(Router);
  protected readonly onsDadosHidrologicosVolumeEsperaRecomendadoService = inject(OnsDadosHidrologicosVolumeEsperaRecomendadoService);
  protected readonly activatedRoute = inject(ActivatedRoute);
  protected readonly sortService = inject(SortService);
  protected modalService = inject(NgbModal);
  protected ngZone = inject(NgZone);

  trackId = (item: IOnsDadosHidrologicosVolumeEsperaRecomendado): number =>
    this.onsDadosHidrologicosVolumeEsperaRecomendadoService.getOnsDadosHidrologicosVolumeEsperaRecomendadoIdentifier(item);

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

  delete(onsDadosHidrologicosVolumeEsperaRecomendado: IOnsDadosHidrologicosVolumeEsperaRecomendado): void {
    const modalRef = this.modalService.open(OnsDadosHidrologicosVolumeEsperaRecomendadoDeleteDialogComponent, {
      size: 'lg',
      backdrop: 'static',
    });
    modalRef.componentInstance.onsDadosHidrologicosVolumeEsperaRecomendado = onsDadosHidrologicosVolumeEsperaRecomendado;
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
    this.onsDadosHidrologicosVolumeEsperaRecomendados.set(this.refineData(dataFromBody));
  }

  protected refineData(data: IOnsDadosHidrologicosVolumeEsperaRecomendado[]): IOnsDadosHidrologicosVolumeEsperaRecomendado[] {
    const { predicate, order } = this.sortState();
    return predicate && order ? data.sort(this.sortService.startSort({ predicate, order })) : data;
  }

  protected fillComponentAttributesFromResponseBody(
    data: IOnsDadosHidrologicosVolumeEsperaRecomendado[] | null,
  ): IOnsDadosHidrologicosVolumeEsperaRecomendado[] {
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
      return this.onsDadosHidrologicosVolumeEsperaRecomendadoService.search(queryObject).pipe(tap(() => (this.isLoading = false)));
    }
    return this.onsDadosHidrologicosVolumeEsperaRecomendadoService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
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
