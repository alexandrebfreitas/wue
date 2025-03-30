import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IOnsCmoSemihorario } from '../ons-cmo-semihorario.model';
import { OnsCmoSemihorarioService } from '../service/ons-cmo-semihorario.service';
import { OnsCmoSemihorarioFormGroup, OnsCmoSemihorarioFormService } from './ons-cmo-semihorario-form.service';

@Component({
  selector: 'jhi-ons-cmo-semihorario-update',
  templateUrl: './ons-cmo-semihorario-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class OnsCmoSemihorarioUpdateComponent implements OnInit {
  isSaving = false;
  onsCmoSemihorario: IOnsCmoSemihorario | null = null;

  protected onsCmoSemihorarioService = inject(OnsCmoSemihorarioService);
  protected onsCmoSemihorarioFormService = inject(OnsCmoSemihorarioFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: OnsCmoSemihorarioFormGroup = this.onsCmoSemihorarioFormService.createOnsCmoSemihorarioFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ onsCmoSemihorario }) => {
      this.onsCmoSemihorario = onsCmoSemihorario;
      if (onsCmoSemihorario) {
        this.updateForm(onsCmoSemihorario);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const onsCmoSemihorario = this.onsCmoSemihorarioFormService.getOnsCmoSemihorario(this.editForm);
    if (onsCmoSemihorario.id !== null) {
      this.subscribeToSaveResponse(this.onsCmoSemihorarioService.update(onsCmoSemihorario));
    } else {
      this.subscribeToSaveResponse(this.onsCmoSemihorarioService.create(onsCmoSemihorario));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOnsCmoSemihorario>>): void {
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

  protected updateForm(onsCmoSemihorario: IOnsCmoSemihorario): void {
    this.onsCmoSemihorario = onsCmoSemihorario;
    this.onsCmoSemihorarioFormService.resetForm(this.editForm, onsCmoSemihorario);
  }
}
