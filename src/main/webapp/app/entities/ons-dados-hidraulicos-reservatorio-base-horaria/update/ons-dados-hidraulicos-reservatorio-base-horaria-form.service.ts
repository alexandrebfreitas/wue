import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosHidraulicosReservatorioBaseHoraria,
  NewOnsDadosHidraulicosReservatorioBaseHoraria,
} from '../ons-dados-hidraulicos-reservatorio-base-horaria.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosHidraulicosReservatorioBaseHoraria for edit and NewOnsDadosHidraulicosReservatorioBaseHorariaFormGroupInput for create.
 */
type OnsDadosHidraulicosReservatorioBaseHorariaFormGroupInput =
  | IOnsDadosHidraulicosReservatorioBaseHoraria
  | PartialWithRequiredKeyOf<NewOnsDadosHidraulicosReservatorioBaseHoraria>;

type OnsDadosHidraulicosReservatorioBaseHorariaFormDefaults = Pick<NewOnsDadosHidraulicosReservatorioBaseHoraria, 'id'>;

type OnsDadosHidraulicosReservatorioBaseHorariaFormGroupContent = {
  id: FormControl<IOnsDadosHidraulicosReservatorioBaseHoraria['id'] | NewOnsDadosHidraulicosReservatorioBaseHoraria['id']>;
  valVolumeutil: FormControl<IOnsDadosHidraulicosReservatorioBaseHoraria['valVolumeutil']>;
  valVazaoafluente: FormControl<IOnsDadosHidraulicosReservatorioBaseHoraria['valVazaoafluente']>;
  valVazaodefluente: FormControl<IOnsDadosHidraulicosReservatorioBaseHoraria['valVazaodefluente']>;
  valVazaoturbinada: FormControl<IOnsDadosHidraulicosReservatorioBaseHoraria['valVazaoturbinada']>;
  valVazaovertida: FormControl<IOnsDadosHidraulicosReservatorioBaseHoraria['valVazaovertida']>;
  valVazaooutrasestruturas: FormControl<IOnsDadosHidraulicosReservatorioBaseHoraria['valVazaooutrasestruturas']>;
  valVazaotransferida: FormControl<IOnsDadosHidraulicosReservatorioBaseHoraria['valVazaotransferida']>;
  valVazaovertidanaoturbinavel: FormControl<IOnsDadosHidraulicosReservatorioBaseHoraria['valVazaovertidanaoturbinavel']>;
};

export type OnsDadosHidraulicosReservatorioBaseHorariaFormGroup = FormGroup<OnsDadosHidraulicosReservatorioBaseHorariaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosHidraulicosReservatorioBaseHorariaFormService {
  createOnsDadosHidraulicosReservatorioBaseHorariaFormGroup(
    onsDadosHidraulicosReservatorioBaseHoraria: OnsDadosHidraulicosReservatorioBaseHorariaFormGroupInput = { id: null },
  ): OnsDadosHidraulicosReservatorioBaseHorariaFormGroup {
    const onsDadosHidraulicosReservatorioBaseHorariaRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosHidraulicosReservatorioBaseHoraria,
    };
    return new FormGroup<OnsDadosHidraulicosReservatorioBaseHorariaFormGroupContent>({
      id: new FormControl(
        { value: onsDadosHidraulicosReservatorioBaseHorariaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      valVolumeutil: new FormControl(onsDadosHidraulicosReservatorioBaseHorariaRawValue.valVolumeutil),
      valVazaoafluente: new FormControl(onsDadosHidraulicosReservatorioBaseHorariaRawValue.valVazaoafluente),
      valVazaodefluente: new FormControl(onsDadosHidraulicosReservatorioBaseHorariaRawValue.valVazaodefluente),
      valVazaoturbinada: new FormControl(onsDadosHidraulicosReservatorioBaseHorariaRawValue.valVazaoturbinada),
      valVazaovertida: new FormControl(onsDadosHidraulicosReservatorioBaseHorariaRawValue.valVazaovertida),
      valVazaooutrasestruturas: new FormControl(onsDadosHidraulicosReservatorioBaseHorariaRawValue.valVazaooutrasestruturas),
      valVazaotransferida: new FormControl(onsDadosHidraulicosReservatorioBaseHorariaRawValue.valVazaotransferida),
      valVazaovertidanaoturbinavel: new FormControl(onsDadosHidraulicosReservatorioBaseHorariaRawValue.valVazaovertidanaoturbinavel),
    });
  }

  getOnsDadosHidraulicosReservatorioBaseHoraria(
    form: OnsDadosHidraulicosReservatorioBaseHorariaFormGroup,
  ): IOnsDadosHidraulicosReservatorioBaseHoraria | NewOnsDadosHidraulicosReservatorioBaseHoraria {
    return form.getRawValue() as IOnsDadosHidraulicosReservatorioBaseHoraria | NewOnsDadosHidraulicosReservatorioBaseHoraria;
  }

  resetForm(
    form: OnsDadosHidraulicosReservatorioBaseHorariaFormGroup,
    onsDadosHidraulicosReservatorioBaseHoraria: OnsDadosHidraulicosReservatorioBaseHorariaFormGroupInput,
  ): void {
    const onsDadosHidraulicosReservatorioBaseHorariaRawValue = { ...this.getFormDefaults(), ...onsDadosHidraulicosReservatorioBaseHoraria };
    form.reset(
      {
        ...onsDadosHidraulicosReservatorioBaseHorariaRawValue,
        id: { value: onsDadosHidraulicosReservatorioBaseHorariaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosHidraulicosReservatorioBaseHorariaFormDefaults {
    return {
      id: null,
    };
  }
}
