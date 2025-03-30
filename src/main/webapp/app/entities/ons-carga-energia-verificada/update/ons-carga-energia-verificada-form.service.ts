import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsCargaEnergiaVerificada, NewOnsCargaEnergiaVerificada } from '../ons-carga-energia-verificada.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCargaEnergiaVerificada for edit and NewOnsCargaEnergiaVerificadaFormGroupInput for create.
 */
type OnsCargaEnergiaVerificadaFormGroupInput = IOnsCargaEnergiaVerificada | PartialWithRequiredKeyOf<NewOnsCargaEnergiaVerificada>;

type OnsCargaEnergiaVerificadaFormDefaults = Pick<NewOnsCargaEnergiaVerificada, 'id'>;

type OnsCargaEnergiaVerificadaFormGroupContent = {
  id: FormControl<IOnsCargaEnergiaVerificada['id'] | NewOnsCargaEnergiaVerificada['id']>;
  codAreacarga: FormControl<IOnsCargaEnergiaVerificada['codAreacarga']>;
  datReferencia: FormControl<IOnsCargaEnergiaVerificada['datReferencia']>;
  dinReferenciautc: FormControl<IOnsCargaEnergiaVerificada['dinReferenciautc']>;
  valCargaglobal: FormControl<IOnsCargaEnergiaVerificada['valCargaglobal']>;
  valCargaglobalsmmg: FormControl<IOnsCargaEnergiaVerificada['valCargaglobalsmmg']>;
  valCargammgd: FormControl<IOnsCargaEnergiaVerificada['valCargammgd']>;
  valCargaglobalcons: FormControl<IOnsCargaEnergiaVerificada['valCargaglobalcons']>;
  valConsistencia: FormControl<IOnsCargaEnergiaVerificada['valConsistencia']>;
  valCargasupervisionada: FormControl<IOnsCargaEnergiaVerificada['valCargasupervisionada']>;
  valCarganaosupervisionada: FormControl<IOnsCargaEnergiaVerificada['valCarganaosupervisionada']>;
};

export type OnsCargaEnergiaVerificadaFormGroup = FormGroup<OnsCargaEnergiaVerificadaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCargaEnergiaVerificadaFormService {
  createOnsCargaEnergiaVerificadaFormGroup(
    onsCargaEnergiaVerificada: OnsCargaEnergiaVerificadaFormGroupInput = { id: null },
  ): OnsCargaEnergiaVerificadaFormGroup {
    const onsCargaEnergiaVerificadaRawValue = {
      ...this.getFormDefaults(),
      ...onsCargaEnergiaVerificada,
    };
    return new FormGroup<OnsCargaEnergiaVerificadaFormGroupContent>({
      id: new FormControl(
        { value: onsCargaEnergiaVerificadaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codAreacarga: new FormControl(onsCargaEnergiaVerificadaRawValue.codAreacarga),
      datReferencia: new FormControl(onsCargaEnergiaVerificadaRawValue.datReferencia),
      dinReferenciautc: new FormControl(onsCargaEnergiaVerificadaRawValue.dinReferenciautc),
      valCargaglobal: new FormControl(onsCargaEnergiaVerificadaRawValue.valCargaglobal),
      valCargaglobalsmmg: new FormControl(onsCargaEnergiaVerificadaRawValue.valCargaglobalsmmg),
      valCargammgd: new FormControl(onsCargaEnergiaVerificadaRawValue.valCargammgd),
      valCargaglobalcons: new FormControl(onsCargaEnergiaVerificadaRawValue.valCargaglobalcons),
      valConsistencia: new FormControl(onsCargaEnergiaVerificadaRawValue.valConsistencia),
      valCargasupervisionada: new FormControl(onsCargaEnergiaVerificadaRawValue.valCargasupervisionada),
      valCarganaosupervisionada: new FormControl(onsCargaEnergiaVerificadaRawValue.valCarganaosupervisionada),
    });
  }

  getOnsCargaEnergiaVerificada(form: OnsCargaEnergiaVerificadaFormGroup): IOnsCargaEnergiaVerificada | NewOnsCargaEnergiaVerificada {
    return form.getRawValue() as IOnsCargaEnergiaVerificada | NewOnsCargaEnergiaVerificada;
  }

  resetForm(form: OnsCargaEnergiaVerificadaFormGroup, onsCargaEnergiaVerificada: OnsCargaEnergiaVerificadaFormGroupInput): void {
    const onsCargaEnergiaVerificadaRawValue = { ...this.getFormDefaults(), ...onsCargaEnergiaVerificada };
    form.reset(
      {
        ...onsCargaEnergiaVerificadaRawValue,
        id: { value: onsCargaEnergiaVerificadaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCargaEnergiaVerificadaFormDefaults {
    return {
      id: null,
    };
  }
}
