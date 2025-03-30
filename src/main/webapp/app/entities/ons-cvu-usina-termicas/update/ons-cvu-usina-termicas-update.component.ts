import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCvuUsinaTermicas } from '../ons-cvu-usina-termicas.model';
import { OnsCvuUsinaTermicasService } from '../service/ons-cvu-usina-termicas.service';
import { OnsCvuUsinaTermicasFormGroup, OnsCvuUsinaTermicasFormService } from './ons-cvu-usina-termicas-form.service';

@Component({
  selector: 'jhi-ons-cvu-usina-termicas-update',
  templateUrl: './ons-cvu-usina-termicas-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCvuUsinaTermicasUpdateComponent implements OnInit {
  isSaving = false;
  onsCvuUsinaTermicas: IOnsCvuUsinaTermicas | null = null;

  protected onsCvuUsinaTermicasService = inject(OnsCvuUsinaTermicasService);
  protected onsCvuUsinaTermicasFormService = inject(OnsCvuUsinaTermicasFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCvuUsinaTermicasFormGroup = this.onsCvuUsinaTermicasFormService.createOnsCvuUsinaTermicasFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCvuUsinaTermicas }) => {
      this.onsCvuUsinaTermicas = onsCvuUsinaTermicas;
      if (onsCvuUsinaTermicas) {
        this.updateForm(onsCvuUsinaTermicas);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCvuUsinaTermicas = this.onsCvuUsinaTermicasFormService.getOnsCvuUsinaTermicas(this.editForm);
    if (onsCvuUsinaTermicas.id !== null) {
      this.subscribeToSaveResponse(this.onsCvuUsinaTermicasService.update(onsCvuUsinaTermicas));
    } else {
      this.subscribeToSaveResponse(this.onsCvuUsinaTermicasService.create(onsCvuUsinaTermicas));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCvuUsinaTermicas>>): void {
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

  protected updateForm(onsCvuUsinaTermicas: IOnsCvuUsinaTermicas): void {
    this.onsCvuUsinaTermicas = onsCvuUsinaTermicas;
    this.onsCvuUsinaTermicasFormService.resetForm(this.editForm, onsCvuUsinaTermicas);
  }
}
