import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsCargaEnergiaProgramada, NewOnsCargaEnergiaProgramada } from '../ons-carga-energia-programada.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCargaEnergiaProgramada for edit and NewOnsCargaEnergiaProgramadaFormGroupInput for create.
 */
type OnsCargaEnergiaProgramadaFormGroupInput = IOnsCargaEnergiaProgramada | PartialWithRequiredKeyOf<NewOnsCargaEnergiaProgramada>;

type OnsCargaEnergiaProgramadaFormDefaults = Pick<NewOnsCargaEnergiaProgramada, 'id'>;

type OnsCargaEnergiaProgramadaFormGroupContent = {
  id: FormControl<IOnsCargaEnergiaProgramada['id'] | NewOnsCargaEnergiaProgramada['id']>;
  codAreacarga: FormControl<IOnsCargaEnergiaProgramada['codAreacarga']>;
  datReferencia: FormControl<IOnsCargaEnergiaProgramada['datReferencia']>;
  dinReferenciautc: FormControl<IOnsCargaEnergiaProgramada['dinReferenciautc']>;
  valCargaglobalprogramada: FormControl<IOnsCargaEnergiaProgramada['valCargaglobalprogramada']>;
};

export type OnsCargaEnergiaProgramadaFormGroup = FormGroup<OnsCargaEnergiaProgramadaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCargaEnergiaProgramadaFormService {
  createOnsCargaEnergiaProgramadaFormGroup(
    onsCargaEnergiaProgramada: OnsCargaEnergiaProgramadaFormGroupInput = { id: null },
  ): OnsCargaEnergiaProgramadaFormGroup {
    const onsCargaEnergiaProgramadaRawValue = {
      ...this.getFormDefaults(),
      ...onsCargaEnergiaProgramada,
    };
    return new FormGroup<OnsCargaEnergiaProgramadaFormGroupContent>({
      id: new FormControl(
        { value: onsCargaEnergiaProgramadaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codAreacarga: new FormControl(onsCargaEnergiaProgramadaRawValue.codAreacarga),
      datReferencia: new FormControl(onsCargaEnergiaProgramadaRawValue.datReferencia),
      dinReferenciautc: new FormControl(onsCargaEnergiaProgramadaRawValue.dinReferenciautc),
      valCargaglobalprogramada: new FormControl(onsCargaEnergiaProgramadaRawValue.valCargaglobalprogramada),
    });
  }

  getOnsCargaEnergiaProgramada(form: OnsCargaEnergiaProgramadaFormGroup): IOnsCargaEnergiaProgramada | NewOnsCargaEnergiaProgramada {
    return form.getRawValue() as IOnsCargaEnergiaProgramada | NewOnsCargaEnergiaProgramada;
  }

  resetForm(form: OnsCargaEnergiaProgramadaFormGroup, onsCargaEnergiaProgramada: OnsCargaEnergiaProgramadaFormGroupInput): void {
    const onsCargaEnergiaProgramadaRawValue = { ...this.getFormDefaults(), ...onsCargaEnergiaProgramada };
    form.reset(
      {
        ...onsCargaEnergiaProgramadaRawValue,
        id: { value: onsCargaEnergiaProgramadaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCargaEnergiaProgramadaFormDefaults {
    return {
      id: null,
    };
  }
}
