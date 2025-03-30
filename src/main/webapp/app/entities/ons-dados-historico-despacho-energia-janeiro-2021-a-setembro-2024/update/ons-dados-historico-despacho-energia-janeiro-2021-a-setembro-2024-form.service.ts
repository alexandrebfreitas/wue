import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
  NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
} from '../ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 for edit and NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroupInput for create.
 */
type OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroupInput =
  | IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
  | PartialWithRequiredKeyOf<NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024>;

type OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormDefaults = Pick<
  NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
  'id'
>;

type OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroupContent = {
  id: FormControl<
    IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024['id'] | NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024['id']
  >;
  datPdp: FormControl<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024['datPdp']>;
  codSubmercado: FormControl<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024['codSubmercado']>;
  sglTipousina: FormControl<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024['sglTipousina']>;
  codUsinapdp: FormControl<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024['codUsinapdp']>;
  nomUsinapdp: FormControl<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024['nomUsinapdp']>;
  numIntdespa: FormControl<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024['numIntdespa']>;
  valDespasup: FormControl<IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024['valDespasup']>;
};

export type OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup =
  FormGroup<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormService {
  createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup(
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroupInput = {
      id: null,
    },
  ): OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup {
    const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue = {
      ...this.getFormDefaults(),
      ...onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
    };
    return new FormGroup<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroupContent>({
      id: new FormControl(
        { value: onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      datPdp: new FormControl(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue.datPdp),
      codSubmercado: new FormControl(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue.codSubmercado),
      sglTipousina: new FormControl(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue.sglTipousina),
      codUsinapdp: new FormControl(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue.codUsinapdp),
      nomUsinapdp: new FormControl(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue.nomUsinapdp),
      numIntdespa: new FormControl(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue.numIntdespa),
      valDespasup: new FormControl(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue.valDespasup),
    });
  }

  getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(
    form: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup,
  ): IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 | NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 {
    return form.getRawValue() as
      | IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
      | NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024;
  }

  resetForm(
    form: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroup,
    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormGroupInput,
  ): void {
    const onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue = {
      ...this.getFormDefaults(),
      ...onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
    };
    form.reset(
      {
        ...onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue,
        id: { value: onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024RawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024FormDefaults {
    return {
      id: null,
    };
  }
}
