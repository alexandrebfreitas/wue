import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsDadosGrandezasFluviometricas, NewOnsDadosGrandezasFluviometricas } from '../ons-dados-grandezas-fluviometricas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosGrandezasFluviometricas for edit and NewOnsDadosGrandezasFluviometricasFormGroupInput for create.
 */
type OnsDadosGrandezasFluviometricasFormGroupInput =
  | IOnsDadosGrandezasFluviometricas
  | PartialWithRequiredKeyOf<NewOnsDadosGrandezasFluviometricas>;

type OnsDadosGrandezasFluviometricasFormDefaults = Pick<NewOnsDadosGrandezasFluviometricas, 'id'>;

type OnsDadosGrandezasFluviometricasFormGroupContent = {
  id: FormControl<IOnsDadosGrandezasFluviometricas['id'] | NewOnsDadosGrandezasFluviometricas['id']>;
  idPostofluv: FormControl<IOnsDadosGrandezasFluviometricas['idPostofluv']>;
  nomPostofluviometrico: FormControl<IOnsDadosGrandezasFluviometricas['nomPostofluviometrico']>;
  valLatitude: FormControl<IOnsDadosGrandezasFluviometricas['valLatitude']>;
  valLongitude: FormControl<IOnsDadosGrandezasFluviometricas['valLongitude']>;
  nomRio: FormControl<IOnsDadosGrandezasFluviometricas['nomRio']>;
  nomBacia: FormControl<IOnsDadosGrandezasFluviometricas['nomBacia']>;
  dinMedicao: FormControl<IOnsDadosGrandezasFluviometricas['dinMedicao']>;
  valVazaomedia: FormControl<IOnsDadosGrandezasFluviometricas['valVazaomedia']>;
  valVazaomediaincr: FormControl<IOnsDadosGrandezasFluviometricas['valVazaomediaincr']>;
};

export type OnsDadosGrandezasFluviometricasFormGroup = FormGroup<OnsDadosGrandezasFluviometricasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosGrandezasFluviometricasFormService {
  createOnsDadosGrandezasFluviometricasFormGroup(
    onsDadosGrandezasFluviometricas: OnsDadosGrandezasFluviometricasFormGroupInput = { id: null },
  ): OnsDadosGrandezasFluviometricasFormGroup {
    const onsDadosGrandezasFluviometricasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosGrandezasFluviometricas,
    };
    return new FormGroup<OnsDadosGrandezasFluviometricasFormGroupContent>({
      id: new FormControl(
        { value: onsDadosGrandezasFluviometricasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idPostofluv: new FormControl(onsDadosGrandezasFluviometricasRawValue.idPostofluv),
      nomPostofluviometrico: new FormControl(onsDadosGrandezasFluviometricasRawValue.nomPostofluviometrico),
      valLatitude: new FormControl(onsDadosGrandezasFluviometricasRawValue.valLatitude),
      valLongitude: new FormControl(onsDadosGrandezasFluviometricasRawValue.valLongitude),
      nomRio: new FormControl(onsDadosGrandezasFluviometricasRawValue.nomRio),
      nomBacia: new FormControl(onsDadosGrandezasFluviometricasRawValue.nomBacia),
      dinMedicao: new FormControl(onsDadosGrandezasFluviometricasRawValue.dinMedicao),
      valVazaomedia: new FormControl(onsDadosGrandezasFluviometricasRawValue.valVazaomedia),
      valVazaomediaincr: new FormControl(onsDadosGrandezasFluviometricasRawValue.valVazaomediaincr),
    });
  }

  getOnsDadosGrandezasFluviometricas(
    form: OnsDadosGrandezasFluviometricasFormGroup,
  ): IOnsDadosGrandezasFluviometricas | NewOnsDadosGrandezasFluviometricas {
    return form.getRawValue() as IOnsDadosGrandezasFluviometricas | NewOnsDadosGrandezasFluviometricas;
  }

  resetForm(
    form: OnsDadosGrandezasFluviometricasFormGroup,
    onsDadosGrandezasFluviometricas: OnsDadosGrandezasFluviometricasFormGroupInput,
  ): void {
    const onsDadosGrandezasFluviometricasRawValue = { ...this.getFormDefaults(), ...onsDadosGrandezasFluviometricas };
    form.reset(
      {
        ...onsDadosGrandezasFluviometricasRawValue,
        id: { value: onsDadosGrandezasFluviometricasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosGrandezasFluviometricasFormDefaults {
    return {
      id: null,
    };
  }
}
