import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsImportacaoEnergiaNaModalidadeComercialBloco } from '../ons-importacao-energia-na-modalidade-comercial-bloco.model';
import { OnsImportacaoEnergiaNaModalidadeComercialBlocoService } from '../service/ons-importacao-energia-na-modalidade-comercial-bloco.service';
import {
  OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup,
  OnsImportacaoEnergiaNaModalidadeComercialBlocoFormService,
} from './ons-importacao-energia-na-modalidade-comercial-bloco-form.service';

@Component({
  selector: 'jhi-ons-importacao-energia-na-modalidade-comercial-bloco-update',
  templateUrl: './ons-importacao-energia-na-modalidade-comercial-bloco-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsImportacaoEnergiaNaModalidadeComercialBlocoUpdateComponent implements OnInit {
  isSaving = false;
  onsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco | null = null;

  protected onsImportacaoEnergiaNaModalidadeComercialBlocoService = inject(OnsImportacaoEnergiaNaModalidadeComercialBlocoService);
  protected onsImportacaoEnergiaNaModalidadeComercialBlocoFormService = inject(OnsImportacaoEnergiaNaModalidadeComercialBlocoFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup =
    this.onsImportacaoEnergiaNaModalidadeComercialBlocoFormService.createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsImportacaoEnergiaNaModalidadeComercialBloco }) => {
      this.onsImportacaoEnergiaNaModalidadeComercialBloco = onsImportacaoEnergiaNaModalidadeComercialBloco;
      if (onsImportacaoEnergiaNaModalidadeComercialBloco) {
        this.updateForm(onsImportacaoEnergiaNaModalidadeComercialBloco);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsImportacaoEnergiaNaModalidadeComercialBloco =
      this.onsImportacaoEnergiaNaModalidadeComercialBlocoFormService.getOnsImportacaoEnergiaNaModalidadeComercialBloco(this.editForm);
    if (onsImportacaoEnergiaNaModalidadeComercialBloco.id !== null) {
      this.subscribeToSaveResponse(
        this.onsImportacaoEnergiaNaModalidadeComercialBlocoService.update(onsImportacaoEnergiaNaModalidadeComercialBloco),
      );
    } else {
      this.subscribeToSaveResponse(
        this.onsImportacaoEnergiaNaModalidadeComercialBlocoService.create(onsImportacaoEnergiaNaModalidadeComercialBloco),
      );
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsImportacaoEnergiaNaModalidadeComercialBloco>>): void {
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

  protected updateForm(onsImportacaoEnergiaNaModalidadeComercialBloco: IOnsImportacaoEnergiaNaModalidadeComercialBloco): void {
    this.onsImportacaoEnergiaNaModalidadeComercialBloco = onsImportacaoEnergiaNaModalidadeComercialBloco;
    this.onsImportacaoEnergiaNaModalidadeComercialBlocoFormService.resetForm(this.editForm, onsImportacaoEnergiaNaModalidadeComercialBloco);
  }
}
