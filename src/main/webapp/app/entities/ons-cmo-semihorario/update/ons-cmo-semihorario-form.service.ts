import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsCmoSemihorario, NewOnsCmoSemihorario } from '../ons-cmo-semihorario.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCmoSemihorario for edit and NewOnsCmoSemihorarioFormGroupInput for create.
 */
type OnsCmoSemihorarioFormGroupInput = IOnsCmoSemihorario | PartialWithRequiredKeyOf<NewOnsCmoSemihorario>;

type OnsCmoSemihorarioFormDefaults = Pick<NewOnsCmoSemihorario, 'id'>;

type OnsCmoSemihorarioFormGroupContent = {
  id: FormControl<IOnsCmoSemihorario['id'] | NewOnsCmoSemihorario['id']>;
  idSubsistema: FormControl<IOnsCmoSemihorario['idSubsistema']>;
  nomSubsistema: FormControl<IOnsCmoSemihorario['nomSubsistema']>;
  dinInstante: FormControl<IOnsCmoSemihorario['dinInstante']>;
  valCmo: FormControl<IOnsCmoSemihorario['valCmo']>;
};

export type OnsCmoSemihorarioFormGroup = FormGroup<OnsCmoSemihorarioFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCmoSemihorarioFormService {
  createOnsCmoSemihorarioFormGroup(onsCmoSemihorario: OnsCmoSemihorarioFormGroupInput = { id: null }): OnsCmoSemihorarioFormGroup {
    const onsCmoSemihorarioRawValue = {
      ...this.getFormDefaults(),
      ...onsCmoSemihorario,
    };
    return new FormGroup<OnsCmoSemihorarioFormGroupContent>({
      id: new FormControl(
        { value: onsCmoSemihorarioRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsCmoSemihorarioRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsCmoSemihorarioRawValue.nomSubsistema),
      dinInstante: new FormControl(onsCmoSemihorarioRawValue.dinInstante),
      valCmo: new FormControl(onsCmoSemihorarioRawValue.valCmo),
    });
  }

  getOnsCmoSemihorario(form: OnsCmoSemihorarioFormGroup): IOnsCmoSemihorario | NewOnsCmoSemihorario {
    return form.getRawValue() as IOnsCmoSemihorario | NewOnsCmoSemihorario;
  }

  resetForm(form: OnsCmoSemihorarioFormGroup, onsCmoSemihorario: OnsCmoSemihorarioFormGroupInput): void {
    const onsCmoSemihorarioRawValue = { ...this.getFormDefaults(), ...onsCmoSemihorario };
    form.reset(
      {
        ...onsCmoSemihorarioRawValue,
        id: { value: onsCmoSemihorarioRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCmoSemihorarioFormDefaults {
    return {
      id: null,
    };
  }
}
