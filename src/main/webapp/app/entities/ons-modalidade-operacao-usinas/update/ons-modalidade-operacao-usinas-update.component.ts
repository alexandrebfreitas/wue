import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsModalidadeOperacaoUsinas } from '../ons-modalidade-operacao-usinas.model';
import { OnsModalidadeOperacaoUsinasService } from '../service/ons-modalidade-operacao-usinas.service';
import {
  OnsModalidadeOperacaoUsinasFormGroup,
  OnsModalidadeOperacaoUsinasFormService,
} from './ons-modalidade-operacao-usinas-form.service';

@Component({
  selector: 'jhi-ons-modalidade-operacao-usinas-update',
  templateUrl: './ons-modalidade-operacao-usinas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsModalidadeOperacaoUsinasUpdateComponent implements OnInit {
  isSaving = false;
  onsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas | null = null;

  protected onsModalidadeOperacaoUsinasService = inject(OnsModalidadeOperacaoUsinasService);
  protected onsModalidadeOperacaoUsinasFormService = inject(OnsModalidadeOperacaoUsinasFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsModalidadeOperacaoUsinasFormGroup = this.onsModalidadeOperacaoUsinasFormService.createOnsModalidadeOperacaoUsinasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsModalidadeOperacaoUsinas }) => {
      this.onsModalidadeOperacaoUsinas = onsModalidadeOperacaoUsinas;
      if (onsModalidadeOperacaoUsinas) {
        this.updateForm(onsModalidadeOperacaoUsinas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsModalidadeOperacaoUsinas = this.onsModalidadeOperacaoUsinasFormService.getOnsModalidadeOperacaoUsinas(this.editForm);
    if (onsModalidadeOperacaoUsinas.id !== null) {
      this.subscribeToSaveResponse(this.onsModalidadeOperacaoUsinasService.update(onsModalidadeOperacaoUsinas));
    } else {
      this.subscribeToSaveResponse(this.onsModalidadeOperacaoUsinasService.create(onsModalidadeOperacaoUsinas));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsModalidadeOperacaoUsinas>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(onsModalidadeOperacaoUsinas: IOnsModalidadeOperacaoUsinas): void {
    this.onsModalidadeOperacaoUsinas = onsModalidadeOperacaoUsinas;
    this.onsModalidadeOperacaoUsinasFormService.resetForm(this.editForm, onsModalidadeOperacaoUsinas);
  }
}
