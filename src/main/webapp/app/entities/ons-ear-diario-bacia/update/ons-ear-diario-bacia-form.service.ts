import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsEarDiarioBacia, NewOnsEarDiarioBacia } from '../ons-ear-diario-bacia.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsEarDiarioBacia for edit and NewOnsEarDiarioBaciaFormGroupInput for create.
 */
type OnsEarDiarioBaciaFormGroupInput = IOnsEarDiarioBacia | PartialWithRequiredKeyOf<NewOnsEarDiarioBacia>;

type OnsEarDiarioBaciaFormDefaults = Pick<NewOnsEarDiarioBacia, 'id'>;

type OnsEarDiarioBaciaFormGroupContent = {
  id: FormControl<IOnsEarDiarioBacia['id'] | NewOnsEarDiarioBacia['id']>;
  nomCurto: FormControl<IOnsEarDiarioBacia['nomCurto']>;
  earData: FormControl<IOnsEarDiarioBacia['earData']>;
  earMaxBacia: FormControl<IOnsEarDiarioBacia['earMaxBacia']>;
  earVerifBaciaMwmes: FormControl<IOnsEarDiarioBacia['earVerifBaciaMwmes']>;
  earVerifBaciaPercentual: FormControl<IOnsEarDiarioBacia['earVerifBaciaPercentual']>;
};

export type OnsEarDiarioBaciaFormGroup = FormGroup<OnsEarDiarioBaciaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsEarDiarioBaciaFormService {
  createOnsEarDiarioBaciaFormGroup(onsEarDiarioBacia: OnsEarDiarioBaciaFormGroupInput = { id: null }): OnsEarDiarioBaciaFormGroup {
    const onsEarDiarioBaciaRawValue = {
      ...this.getFormDefaults(),
      ...onsEarDiarioBacia,
    };
    return new FormGroup<OnsEarDiarioBaciaFormGroupContent>({
      id: new FormControl(
        { value: onsEarDiarioBaciaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nomCurto: new FormControl(onsEarDiarioBaciaRawValue.nomCurto),
      earData: new FormControl(onsEarDiarioBaciaRawValue.earData),
      earMaxBacia: new FormControl(onsEarDiarioBaciaRawValue.earMaxBacia),
      earVerifBaciaMwmes: new FormControl(onsEarDiarioBaciaRawValue.earVerifBaciaMwmes),
      earVerifBaciaPercentual: new FormControl(onsEarDiarioBaciaRawValue.earVerifBaciaPercentual),
    });
  }

  getOnsEarDiarioBacia(form: OnsEarDiarioBaciaFormGroup): IOnsEarDiarioBacia | NewOnsEarDiarioBacia {
    return form.getRawValue() as IOnsEarDiarioBacia | NewOnsEarDiarioBacia;
  }

  resetForm(form: OnsEarDiarioBaciaFormGroup, onsEarDiarioBacia: OnsEarDiarioBaciaFormGroupInput): void {
    const onsEarDiarioBaciaRawValue = { ...this.getFormDefaults(), ...onsEarDiarioBacia };
    form.reset(
      {
        ...onsEarDiarioBaciaRawValue,
        id: { value: onsEarDiarioBaciaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsEarDiarioBaciaFormDefaults {
    return {
      id: null,
    };
  }
}
