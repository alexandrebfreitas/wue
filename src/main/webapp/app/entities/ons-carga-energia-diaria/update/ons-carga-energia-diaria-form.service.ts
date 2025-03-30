import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsCargaEnergiaDiaria, NewOnsCargaEnergiaDiaria } from '../ons-carga-energia-diaria.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCargaEnergiaDiaria for edit and NewOnsCargaEnergiaDiariaFormGroupInput for create.
 */
type OnsCargaEnergiaDiariaFormGroupInput = IOnsCargaEnergiaDiaria | PartialWithRequiredKeyOf<NewOnsCargaEnergiaDiaria>;

type OnsCargaEnergiaDiariaFormDefaults = Pick<NewOnsCargaEnergiaDiaria, 'id'>;

type OnsCargaEnergiaDiariaFormGroupContent = {
  id: FormControl<IOnsCargaEnergiaDiaria['id'] | NewOnsCargaEnergiaDiaria['id']>;
  idSubsistema: FormControl<IOnsCargaEnergiaDiaria['idSubsistema']>;
  nomSubsistema: FormControl<IOnsCargaEnergiaDiaria['nomSubsistema']>;
  dinInstante: FormControl<IOnsCargaEnergiaDiaria['dinInstante']>;
  valCargaenergiamwmed: FormControl<IOnsCargaEnergiaDiaria['valCargaenergiamwmed']>;
};

export type OnsCargaEnergiaDiariaFormGroup = FormGroup<OnsCargaEnergiaDiariaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCargaEnergiaDiariaFormService {
  createOnsCargaEnergiaDiariaFormGroup(
    onsCargaEnergiaDiaria: OnsCargaEnergiaDiariaFormGroupInput = { id: null },
  ): OnsCargaEnergiaDiariaFormGroup {
    const onsCargaEnergiaDiariaRawValue = {
      ...this.getFormDefaults(),
      ...onsCargaEnergiaDiaria,
    };
    return new FormGroup<OnsCargaEnergiaDiariaFormGroupContent>({
      id: new FormControl(
        { value: onsCargaEnergiaDiariaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsCargaEnergiaDiariaRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsCargaEnergiaDiariaRawValue.nomSubsistema),
      dinInstante: new FormControl(onsCargaEnergiaDiariaRawValue.dinInstante),
      valCargaenergiamwmed: new FormControl(onsCargaEnergiaDiariaRawValue.valCargaenergiamwmed),
    });
  }

  getOnsCargaEnergiaDiaria(form: OnsCargaEnergiaDiariaFormGroup): IOnsCargaEnergiaDiaria | NewOnsCargaEnergiaDiaria {
    return form.getRawValue() as IOnsCargaEnergiaDiaria | NewOnsCargaEnergiaDiaria;
  }

  resetForm(form: OnsCargaEnergiaDiariaFormGroup, onsCargaEnergiaDiaria: OnsCargaEnergiaDiariaFormGroupInput): void {
    const onsCargaEnergiaDiariaRawValue = { ...this.getFormDefaults(), ...onsCargaEnergiaDiaria };
    form.reset(
      {
        ...onsCargaEnergiaDiariaRawValue,
        id: { value: onsCargaEnergiaDiariaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCargaEnergiaDiariaFormDefaults {
    return {
      id: null,
    };
  }
}
