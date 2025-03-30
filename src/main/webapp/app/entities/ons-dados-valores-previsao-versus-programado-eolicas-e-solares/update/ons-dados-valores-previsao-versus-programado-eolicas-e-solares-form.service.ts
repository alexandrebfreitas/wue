import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
} from '../ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares for edit and NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroupInput for create.
 */
type OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroupInput =
  | IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares
  | PartialWithRequiredKeyOf<NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares>;

type OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormDefaults = Pick<
  NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  'id'
>;

type OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroupContent = {
  id: FormControl<
    IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares['id'] | NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares['id']
  >;
  datProgramacao: FormControl<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares['datProgramacao']>;
  numPatamar: FormControl<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares['numPatamar']>;
  codUsinapdp: FormControl<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares['codUsinapdp']>;
  nomUsinapdp: FormControl<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares['nomUsinapdp']>;
  valPrevisao: FormControl<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares['valPrevisao']>;
  valProgramado: FormControl<IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares['valProgramado']>;
};

export type OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup =
  FormGroup<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormService {
  createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup(
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroupInput = {
      id: null,
    },
  ): OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup {
    const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
    };
    return new FormGroup<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroupContent>({
      id: new FormControl(
        { value: onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      datProgramacao: new FormControl(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue.datProgramacao),
      numPatamar: new FormControl(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue.numPatamar),
      codUsinapdp: new FormControl(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue.codUsinapdp),
      nomUsinapdp: new FormControl(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue.nomUsinapdp),
      valPrevisao: new FormControl(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue.valPrevisao),
      valProgramado: new FormControl(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue.valProgramado),
    });
  }

  getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
    form: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup,
  ): IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares | NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares {
    return form.getRawValue() as
      | IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares
      | NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares;
  }

  resetForm(
    form: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroup,
    onsDadosValoresPrevisaoVersusProgramadoEolicasESolares: OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormGroupInput,
  ): void {
    const onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
    };
    form.reset(
      {
        ...onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue,
        id: { value: onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresFormDefaults {
    return {
      id: null,
    };
  }
}
