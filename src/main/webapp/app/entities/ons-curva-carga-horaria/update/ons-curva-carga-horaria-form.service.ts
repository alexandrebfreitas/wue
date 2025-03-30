import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsCurvaCargaHoraria, NewOnsCurvaCargaHoraria } from '../ons-curva-carga-horaria.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCurvaCargaHoraria for edit and NewOnsCurvaCargaHorariaFormGroupInput for create.
 */
type OnsCurvaCargaHorariaFormGroupInput = IOnsCurvaCargaHoraria | PartialWithRequiredKeyOf<NewOnsCurvaCargaHoraria>;

type OnsCurvaCargaHorariaFormDefaults = Pick<NewOnsCurvaCargaHoraria, 'id'>;

type OnsCurvaCargaHorariaFormGroupContent = {
  id: FormControl<IOnsCurvaCargaHoraria['id'] | NewOnsCurvaCargaHoraria['id']>;
  idSubsistema: FormControl<IOnsCurvaCargaHoraria['idSubsistema']>;
  nomSubsistema: FormControl<IOnsCurvaCargaHoraria['nomSubsistema']>;
  dinInstante: FormControl<IOnsCurvaCargaHoraria['dinInstante']>;
  valCargaenergiahomwmed: FormControl<IOnsCurvaCargaHoraria['valCargaenergiahomwmed']>;
};

export type OnsCurvaCargaHorariaFormGroup = FormGroup<OnsCurvaCargaHorariaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCurvaCargaHorariaFormService {
  createOnsCurvaCargaHorariaFormGroup(
    onsCurvaCargaHoraria: OnsCurvaCargaHorariaFormGroupInput = { id: null },
  ): OnsCurvaCargaHorariaFormGroup {
    const onsCurvaCargaHorariaRawValue = {
      ...this.getFormDefaults(),
      ...onsCurvaCargaHoraria,
    };
    return new FormGroup<OnsCurvaCargaHorariaFormGroupContent>({
      id: new FormControl(
        { value: onsCurvaCargaHorariaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsCurvaCargaHorariaRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsCurvaCargaHorariaRawValue.nomSubsistema),
      dinInstante: new FormControl(onsCurvaCargaHorariaRawValue.dinInstante),
      valCargaenergiahomwmed: new FormControl(onsCurvaCargaHorariaRawValue.valCargaenergiahomwmed),
    });
  }

  getOnsCurvaCargaHoraria(form: OnsCurvaCargaHorariaFormGroup): IOnsCurvaCargaHoraria | NewOnsCurvaCargaHoraria {
    return form.getRawValue() as IOnsCurvaCargaHoraria | NewOnsCurvaCargaHoraria;
  }

  resetForm(form: OnsCurvaCargaHorariaFormGroup, onsCurvaCargaHoraria: OnsCurvaCargaHorariaFormGroupInput): void {
    const onsCurvaCargaHorariaRawValue = { ...this.getFormDefaults(), ...onsCurvaCargaHoraria };
    form.reset(
      {
        ...onsCurvaCargaHorariaRawValue,
        id: { value: onsCurvaCargaHorariaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCurvaCargaHorariaFormDefaults {
    return {
      id: null,
    };
  }
}
