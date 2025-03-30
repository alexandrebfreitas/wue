import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsEnaDiarioBacia, NewOnsEnaDiarioBacia } from '../ons-ena-diario-bacia.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsEnaDiarioBacia for edit and NewOnsEnaDiarioBaciaFormGroupInput for create.
 */
type OnsEnaDiarioBaciaFormGroupInput = IOnsEnaDiarioBacia | PartialWithRequiredKeyOf<NewOnsEnaDiarioBacia>;

type OnsEnaDiarioBaciaFormDefaults = Pick<NewOnsEnaDiarioBacia, 'id'>;

type OnsEnaDiarioBaciaFormGroupContent = {
  id: FormControl<IOnsEnaDiarioBacia['id'] | NewOnsEnaDiarioBacia['id']>;
  enaArmazenavelBaciaPercentualmlt: FormControl<IOnsEnaDiarioBacia['enaArmazenavelBaciaPercentualmlt']>;
};

export type OnsEnaDiarioBaciaFormGroup = FormGroup<OnsEnaDiarioBaciaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsEnaDiarioBaciaFormService {
  createOnsEnaDiarioBaciaFormGroup(onsEnaDiarioBacia: OnsEnaDiarioBaciaFormGroupInput = { id: null }): OnsEnaDiarioBaciaFormGroup {
    const onsEnaDiarioBaciaRawValue = {
      ...this.getFormDefaults(),
      ...onsEnaDiarioBacia,
    };
    return new FormGroup<OnsEnaDiarioBaciaFormGroupContent>({
      id: new FormControl(
        { value: onsEnaDiarioBaciaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      enaArmazenavelBaciaPercentualmlt: new FormControl(onsEnaDiarioBaciaRawValue.enaArmazenavelBaciaPercentualmlt),
    });
  }

  getOnsEnaDiarioBacia(form: OnsEnaDiarioBaciaFormGroup): IOnsEnaDiarioBacia | NewOnsEnaDiarioBacia {
    return form.getRawValue() as IOnsEnaDiarioBacia | NewOnsEnaDiarioBacia;
  }

  resetForm(form: OnsEnaDiarioBaciaFormGroup, onsEnaDiarioBacia: OnsEnaDiarioBaciaFormGroupInput): void {
    const onsEnaDiarioBaciaRawValue = { ...this.getFormDefaults(), ...onsEnaDiarioBacia };
    form.reset(
      {
        ...onsEnaDiarioBaciaRawValue,
        id: { value: onsEnaDiarioBaciaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsEnaDiarioBaciaFormDefaults {
    return {
      id: null,
    };
  }
}
