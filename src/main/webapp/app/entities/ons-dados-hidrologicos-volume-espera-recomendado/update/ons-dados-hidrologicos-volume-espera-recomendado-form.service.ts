import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosHidrologicosVolumeEsperaRecomendado,
  NewOnsDadosHidrologicosVolumeEsperaRecomendado,
} from '../ons-dados-hidrologicos-volume-espera-recomendado.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosHidrologicosVolumeEsperaRecomendado for edit and NewOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroupInput for create.
 */
type OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroupInput =
  | IOnsDadosHidrologicosVolumeEsperaRecomendado
  | PartialWithRequiredKeyOf<NewOnsDadosHidrologicosVolumeEsperaRecomendado>;

type OnsDadosHidrologicosVolumeEsperaRecomendadoFormDefaults = Pick<NewOnsDadosHidrologicosVolumeEsperaRecomendado, 'id'>;

type OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroupContent = {
  id: FormControl<IOnsDadosHidrologicosVolumeEsperaRecomendado['id'] | NewOnsDadosHidrologicosVolumeEsperaRecomendado['id']>;
  dinInstante: FormControl<IOnsDadosHidrologicosVolumeEsperaRecomendado['dinInstante']>;
  valVolumeespera: FormControl<IOnsDadosHidrologicosVolumeEsperaRecomendado['valVolumeespera']>;
};

export type OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup = FormGroup<OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosHidrologicosVolumeEsperaRecomendadoFormService {
  createOnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup(
    onsDadosHidrologicosVolumeEsperaRecomendado: OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroupInput = { id: null },
  ): OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup {
    const onsDadosHidrologicosVolumeEsperaRecomendadoRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosHidrologicosVolumeEsperaRecomendado,
    };
    return new FormGroup<OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroupContent>({
      id: new FormControl(
        { value: onsDadosHidrologicosVolumeEsperaRecomendadoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dinInstante: new FormControl(onsDadosHidrologicosVolumeEsperaRecomendadoRawValue.dinInstante),
      valVolumeespera: new FormControl(onsDadosHidrologicosVolumeEsperaRecomendadoRawValue.valVolumeespera),
    });
  }

  getOnsDadosHidrologicosVolumeEsperaRecomendado(
    form: OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup,
  ): IOnsDadosHidrologicosVolumeEsperaRecomendado | NewOnsDadosHidrologicosVolumeEsperaRecomendado {
    return form.getRawValue() as IOnsDadosHidrologicosVolumeEsperaRecomendado | NewOnsDadosHidrologicosVolumeEsperaRecomendado;
  }

  resetForm(
    form: OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroup,
    onsDadosHidrologicosVolumeEsperaRecomendado: OnsDadosHidrologicosVolumeEsperaRecomendadoFormGroupInput,
  ): void {
    const onsDadosHidrologicosVolumeEsperaRecomendadoRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosHidrologicosVolumeEsperaRecomendado,
    };
    form.reset(
      {
        ...onsDadosHidrologicosVolumeEsperaRecomendadoRawValue,
        id: { value: onsDadosHidrologicosVolumeEsperaRecomendadoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosHidrologicosVolumeEsperaRecomendadoFormDefaults {
    return {
      id: null,
    };
  }
}
