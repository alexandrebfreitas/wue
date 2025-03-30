import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsCmoSemanal, NewOnsCmoSemanal } from '../ons-cmo-semanal.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCmoSemanal for edit and NewOnsCmoSemanalFormGroupInput for create.
 */
type OnsCmoSemanalFormGroupInput = IOnsCmoSemanal | PartialWithRequiredKeyOf<NewOnsCmoSemanal>;

type OnsCmoSemanalFormDefaults = Pick<NewOnsCmoSemanal, 'id'>;

type OnsCmoSemanalFormGroupContent = {
  id: FormControl<IOnsCmoSemanal['id'] | NewOnsCmoSemanal['id']>;
  idSubsistema: FormControl<IOnsCmoSemanal['idSubsistema']>;
  nomSubsistema: FormControl<IOnsCmoSemanal['nomSubsistema']>;
  dinInstante: FormControl<IOnsCmoSemanal['dinInstante']>;
  valCmomediasemanal: FormControl<IOnsCmoSemanal['valCmomediasemanal']>;
  valCmoleve: FormControl<IOnsCmoSemanal['valCmoleve']>;
  valCmomedia: FormControl<IOnsCmoSemanal['valCmomedia']>;
  valCmopesada: FormControl<IOnsCmoSemanal['valCmopesada']>;
};

export type OnsCmoSemanalFormGroup = FormGroup<OnsCmoSemanalFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCmoSemanalFormService {
  createOnsCmoSemanalFormGroup(onsCmoSemanal: OnsCmoSemanalFormGroupInput = { id: null }): OnsCmoSemanalFormGroup {
    const onsCmoSemanalRawValue = {
      ...this.getFormDefaults(),
      ...onsCmoSemanal,
    };
    return new FormGroup<OnsCmoSemanalFormGroupContent>({
      id: new FormControl(
        { value: onsCmoSemanalRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsCmoSemanalRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsCmoSemanalRawValue.nomSubsistema),
      dinInstante: new FormControl(onsCmoSemanalRawValue.dinInstante),
      valCmomediasemanal: new FormControl(onsCmoSemanalRawValue.valCmomediasemanal),
      valCmoleve: new FormControl(onsCmoSemanalRawValue.valCmoleve),
      valCmomedia: new FormControl(onsCmoSemanalRawValue.valCmomedia),
      valCmopesada: new FormControl(onsCmoSemanalRawValue.valCmopesada),
    });
  }

  getOnsCmoSemanal(form: OnsCmoSemanalFormGroup): IOnsCmoSemanal | NewOnsCmoSemanal {
    return form.getRawValue() as IOnsCmoSemanal | NewOnsCmoSemanal;
  }

  resetForm(form: OnsCmoSemanalFormGroup, onsCmoSemanal: OnsCmoSemanalFormGroupInput): void {
    const onsCmoSemanalRawValue = { ...this.getFormDefaults(), ...onsCmoSemanal };
    form.reset(
      {
        ...onsCmoSemanalRawValue,
        id: { value: onsCmoSemanalRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCmoSemanalFormDefaults {
    return {
      id: null,
    };
  }
}
