import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosHidraulicosReservatorioBaseDiaria,
  NewOnsDadosHidraulicosReservatorioBaseDiaria,
} from '../ons-dados-hidraulicos-reservatorio-base-diaria.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosHidraulicosReservatorioBaseDiaria for edit and NewOnsDadosHidraulicosReservatorioBaseDiariaFormGroupInput for create.
 */
type OnsDadosHidraulicosReservatorioBaseDiariaFormGroupInput =
  | IOnsDadosHidraulicosReservatorioBaseDiaria
  | PartialWithRequiredKeyOf<NewOnsDadosHidraulicosReservatorioBaseDiaria>;

type OnsDadosHidraulicosReservatorioBaseDiariaFormDefaults = Pick<NewOnsDadosHidraulicosReservatorioBaseDiaria, 'id'>;

type OnsDadosHidraulicosReservatorioBaseDiariaFormGroupContent = {
  id: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['id'] | NewOnsDadosHidraulicosReservatorioBaseDiaria['id']>;
  valNivelmontante: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valNivelmontante']>;
  valNiveljusante: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valNiveljusante']>;
  valVolumeutilcon: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVolumeutilcon']>;
  valVazaoafluente: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaoafluente']>;
  valVazaoturbinada: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaoturbinada']>;
  valVazaovertida: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaovertida']>;
  valVazaooutrasestruturas: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaooutrasestruturas']>;
  valVazaodefluente: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaodefluente']>;
  valVazaotransferida: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaotransferida']>;
  valVazaonatural: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaonatural']>;
  valVazaoartificial: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaoartificial']>;
  valVazaoincremental: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaoincremental']>;
  valVazaoevaporacaoliquida: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaoevaporacaoliquida']>;
  valVazaousoconsuntivo: FormControl<IOnsDadosHidraulicosReservatorioBaseDiaria['valVazaousoconsuntivo']>;
};

export type OnsDadosHidraulicosReservatorioBaseDiariaFormGroup = FormGroup<OnsDadosHidraulicosReservatorioBaseDiariaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosHidraulicosReservatorioBaseDiariaFormService {
  createOnsDadosHidraulicosReservatorioBaseDiariaFormGroup(
    onsDadosHidraulicosReservatorioBaseDiaria: OnsDadosHidraulicosReservatorioBaseDiariaFormGroupInput = { id: null },
  ): OnsDadosHidraulicosReservatorioBaseDiariaFormGroup {
    const onsDadosHidraulicosReservatorioBaseDiariaRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosHidraulicosReservatorioBaseDiaria,
    };
    return new FormGroup<OnsDadosHidraulicosReservatorioBaseDiariaFormGroupContent>({
      id: new FormControl(
        { value: onsDadosHidraulicosReservatorioBaseDiariaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      valNivelmontante: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valNivelmontante),
      valNiveljusante: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valNiveljusante),
      valVolumeutilcon: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVolumeutilcon),
      valVazaoafluente: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaoafluente),
      valVazaoturbinada: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaoturbinada),
      valVazaovertida: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaovertida),
      valVazaooutrasestruturas: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaooutrasestruturas),
      valVazaodefluente: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaodefluente),
      valVazaotransferida: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaotransferida),
      valVazaonatural: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaonatural),
      valVazaoartificial: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaoartificial),
      valVazaoincremental: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaoincremental),
      valVazaoevaporacaoliquida: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaoevaporacaoliquida),
      valVazaousoconsuntivo: new FormControl(onsDadosHidraulicosReservatorioBaseDiariaRawValue.valVazaousoconsuntivo),
    });
  }

  getOnsDadosHidraulicosReservatorioBaseDiaria(
    form: OnsDadosHidraulicosReservatorioBaseDiariaFormGroup,
  ): IOnsDadosHidraulicosReservatorioBaseDiaria | NewOnsDadosHidraulicosReservatorioBaseDiaria {
    return form.getRawValue() as IOnsDadosHidraulicosReservatorioBaseDiaria | NewOnsDadosHidraulicosReservatorioBaseDiaria;
  }

  resetForm(
    form: OnsDadosHidraulicosReservatorioBaseDiariaFormGroup,
    onsDadosHidraulicosReservatorioBaseDiaria: OnsDadosHidraulicosReservatorioBaseDiariaFormGroupInput,
  ): void {
    const onsDadosHidraulicosReservatorioBaseDiariaRawValue = { ...this.getFormDefaults(), ...onsDadosHidraulicosReservatorioBaseDiaria };
    form.reset(
      {
        ...onsDadosHidraulicosReservatorioBaseDiariaRawValue,
        id: { value: onsDadosHidraulicosReservatorioBaseDiariaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosHidraulicosReservatorioBaseDiariaFormDefaults {
    return {
      id: null,
    };
  }
}
