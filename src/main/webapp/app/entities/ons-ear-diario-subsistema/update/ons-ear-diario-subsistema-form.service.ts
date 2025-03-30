import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsEarDiarioSubsistema, NewOnsEarDiarioSubsistema } from '../ons-ear-diario-subsistema.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsEarDiarioSubsistema for edit and NewOnsEarDiarioSubsistemaFormGroupInput for create.
 */
type OnsEarDiarioSubsistemaFormGroupInput = IOnsEarDiarioSubsistema | PartialWithRequiredKeyOf<NewOnsEarDiarioSubsistema>;

type OnsEarDiarioSubsistemaFormDefaults = Pick<NewOnsEarDiarioSubsistema, 'id'>;

type OnsEarDiarioSubsistemaFormGroupContent = {
  id: FormControl<IOnsEarDiarioSubsistema['id'] | NewOnsEarDiarioSubsistema['id']>;
  idSubsistema: FormControl<IOnsEarDiarioSubsistema['idSubsistema']>;
  nomSubsistema: FormControl<IOnsEarDiarioSubsistema['nomSubsistema']>;
  earData: FormControl<IOnsEarDiarioSubsistema['earData']>;
  earMaxSubsistema: FormControl<IOnsEarDiarioSubsistema['earMaxSubsistema']>;
  earVerifSubsistemaMwmes: FormControl<IOnsEarDiarioSubsistema['earVerifSubsistemaMwmes']>;
  earVerifSubsistemaPercentual: FormControl<IOnsEarDiarioSubsistema['earVerifSubsistemaPercentual']>;
};

export type OnsEarDiarioSubsistemaFormGroup = FormGroup<OnsEarDiarioSubsistemaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsEarDiarioSubsistemaFormService {
  createOnsEarDiarioSubsistemaFormGroup(
    onsEarDiarioSubsistema: OnsEarDiarioSubsistemaFormGroupInput = { id: null },
  ): OnsEarDiarioSubsistemaFormGroup {
    const onsEarDiarioSubsistemaRawValue = {
      ...this.getFormDefaults(),
      ...onsEarDiarioSubsistema,
    };
    return new FormGroup<OnsEarDiarioSubsistemaFormGroupContent>({
      id: new FormControl(
        { value: onsEarDiarioSubsistemaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsEarDiarioSubsistemaRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsEarDiarioSubsistemaRawValue.nomSubsistema),
      earData: new FormControl(onsEarDiarioSubsistemaRawValue.earData),
      earMaxSubsistema: new FormControl(onsEarDiarioSubsistemaRawValue.earMaxSubsistema),
      earVerifSubsistemaMwmes: new FormControl(onsEarDiarioSubsistemaRawValue.earVerifSubsistemaMwmes),
      earVerifSubsistemaPercentual: new FormControl(onsEarDiarioSubsistemaRawValue.earVerifSubsistemaPercentual),
    });
  }

  getOnsEarDiarioSubsistema(form: OnsEarDiarioSubsistemaFormGroup): IOnsEarDiarioSubsistema | NewOnsEarDiarioSubsistema {
    return form.getRawValue() as IOnsEarDiarioSubsistema | NewOnsEarDiarioSubsistema;
  }

  resetForm(form: OnsEarDiarioSubsistemaFormGroup, onsEarDiarioSubsistema: OnsEarDiarioSubsistemaFormGroupInput): void {
    const onsEarDiarioSubsistemaRawValue = { ...this.getFormDefaults(), ...onsEarDiarioSubsistema };
    form.reset(
      {
        ...onsEarDiarioSubsistemaRawValue,
        id: { value: onsEarDiarioSubsistemaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsEarDiarioSubsistemaFormDefaults {
    return {
      id: null,
    };
  }
}
