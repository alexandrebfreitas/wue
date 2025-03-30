import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsEnaDiarioSubsistema, NewOnsEnaDiarioSubsistema } from '../ons-ena-diario-subsistema.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsEnaDiarioSubsistema for edit and NewOnsEnaDiarioSubsistemaFormGroupInput for create.
 */
type OnsEnaDiarioSubsistemaFormGroupInput = IOnsEnaDiarioSubsistema | PartialWithRequiredKeyOf<NewOnsEnaDiarioSubsistema>;

type OnsEnaDiarioSubsistemaFormDefaults = Pick<NewOnsEnaDiarioSubsistema, 'id'>;

type OnsEnaDiarioSubsistemaFormGroupContent = {
  id: FormControl<IOnsEnaDiarioSubsistema['id'] | NewOnsEnaDiarioSubsistema['id']>;
  enaArmazenavelRegiaoPercentualmlt: FormControl<IOnsEnaDiarioSubsistema['enaArmazenavelRegiaoPercentualmlt']>;
};

export type OnsEnaDiarioSubsistemaFormGroup = FormGroup<OnsEnaDiarioSubsistemaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsEnaDiarioSubsistemaFormService {
  createOnsEnaDiarioSubsistemaFormGroup(
    onsEnaDiarioSubsistema: OnsEnaDiarioSubsistemaFormGroupInput = { id: null },
  ): OnsEnaDiarioSubsistemaFormGroup {
    const onsEnaDiarioSubsistemaRawValue = {
      ...this.getFormDefaults(),
      ...onsEnaDiarioSubsistema,
    };
    return new FormGroup<OnsEnaDiarioSubsistemaFormGroupContent>({
      id: new FormControl(
        { value: onsEnaDiarioSubsistemaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      enaArmazenavelRegiaoPercentualmlt: new FormControl(onsEnaDiarioSubsistemaRawValue.enaArmazenavelRegiaoPercentualmlt),
    });
  }

  getOnsEnaDiarioSubsistema(form: OnsEnaDiarioSubsistemaFormGroup): IOnsEnaDiarioSubsistema | NewOnsEnaDiarioSubsistema {
    return form.getRawValue() as IOnsEnaDiarioSubsistema | NewOnsEnaDiarioSubsistema;
  }

  resetForm(form: OnsEnaDiarioSubsistemaFormGroup, onsEnaDiarioSubsistema: OnsEnaDiarioSubsistemaFormGroupInput): void {
    const onsEnaDiarioSubsistemaRawValue = { ...this.getFormDefaults(), ...onsEnaDiarioSubsistema };
    form.reset(
      {
        ...onsEnaDiarioSubsistemaRawValue,
        id: { value: onsEnaDiarioSubsistemaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsEnaDiarioSubsistemaFormDefaults {
    return {
      id: null,
    };
  }
}
