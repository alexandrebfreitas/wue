import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsCargaEnergiaMensal, NewOnsCargaEnergiaMensal } from '../ons-carga-energia-mensal.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCargaEnergiaMensal for edit and NewOnsCargaEnergiaMensalFormGroupInput for create.
 */
type OnsCargaEnergiaMensalFormGroupInput = IOnsCargaEnergiaMensal | PartialWithRequiredKeyOf<NewOnsCargaEnergiaMensal>;

type OnsCargaEnergiaMensalFormDefaults = Pick<NewOnsCargaEnergiaMensal, 'id'>;

type OnsCargaEnergiaMensalFormGroupContent = {
  id: FormControl<IOnsCargaEnergiaMensal['id'] | NewOnsCargaEnergiaMensal['id']>;
  idSubsistema: FormControl<IOnsCargaEnergiaMensal['idSubsistema']>;
  nomSubsistema: FormControl<IOnsCargaEnergiaMensal['nomSubsistema']>;
  dinInstante: FormControl<IOnsCargaEnergiaMensal['dinInstante']>;
  valCargaenergiamwmed: FormControl<IOnsCargaEnergiaMensal['valCargaenergiamwmed']>;
};

export type OnsCargaEnergiaMensalFormGroup = FormGroup<OnsCargaEnergiaMensalFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCargaEnergiaMensalFormService {
  createOnsCargaEnergiaMensalFormGroup(
    onsCargaEnergiaMensal: OnsCargaEnergiaMensalFormGroupInput = { id: null },
  ): OnsCargaEnergiaMensalFormGroup {
    const onsCargaEnergiaMensalRawValue = {
      ...this.getFormDefaults(),
      ...onsCargaEnergiaMensal,
    };
    return new FormGroup<OnsCargaEnergiaMensalFormGroupContent>({
      id: new FormControl(
        { value: onsCargaEnergiaMensalRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsCargaEnergiaMensalRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsCargaEnergiaMensalRawValue.nomSubsistema),
      dinInstante: new FormControl(onsCargaEnergiaMensalRawValue.dinInstante),
      valCargaenergiamwmed: new FormControl(onsCargaEnergiaMensalRawValue.valCargaenergiamwmed),
    });
  }

  getOnsCargaEnergiaMensal(form: OnsCargaEnergiaMensalFormGroup): IOnsCargaEnergiaMensal | NewOnsCargaEnergiaMensal {
    return form.getRawValue() as IOnsCargaEnergiaMensal | NewOnsCargaEnergiaMensal;
  }

  resetForm(form: OnsCargaEnergiaMensalFormGroup, onsCargaEnergiaMensal: OnsCargaEnergiaMensalFormGroupInput): void {
    const onsCargaEnergiaMensalRawValue = { ...this.getFormDefaults(), ...onsCargaEnergiaMensal };
    form.reset(
      {
        ...onsCargaEnergiaMensalRawValue,
        id: { value: onsCargaEnergiaMensalRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCargaEnergiaMensalFormDefaults {
    return {
      id: null,
    };
  }
}
