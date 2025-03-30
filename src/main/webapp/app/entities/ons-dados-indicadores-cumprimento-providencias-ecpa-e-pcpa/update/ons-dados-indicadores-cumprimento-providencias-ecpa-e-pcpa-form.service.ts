import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
  NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
} from '../ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa for edit and NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroupInput for create.
 */
type OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroupInput =
  | IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
  | PartialWithRequiredKeyOf<NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa>;

type OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormDefaults = Pick<NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa, 'id'>;

type OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroupContent = {
  id: FormControl<
    IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['id'] | NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['id']
  >;
  dscAgregacao: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['dscAgregacao']>;
  dscCaracteristica: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['dscCaracteristica']>;
  dinReferencia: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['dinReferencia']>;
  numNprcConcluidas: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['numNprcConcluidas']>;
  numNprpProgramadas: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['numNprpProgramadas']>;
  numNpratAtrasadas: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['numNpratAtrasadas']>;
  numNpraAntecipadas: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['numNpraAntecipadas']>;
  numNprcpConcluidasPrazo: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['numNprcpConcluidasPrazo']>;
  valEcpa: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['valEcpa']>;
  valPcpa: FormControl<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa['valPcpa']>;
};

export type OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup =
  FormGroup<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormService {
  createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup(
    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroupInput = { id: null },
  ): OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup {
    const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
    };
    return new FormGroup<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroupContent>({
      id: new FormControl(
        { value: onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dscAgregacao: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.dscAgregacao),
      dscCaracteristica: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.dscCaracteristica),
      dinReferencia: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.dinReferencia),
      numNprcConcluidas: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.numNprcConcluidas),
      numNprpProgramadas: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.numNprpProgramadas),
      numNpratAtrasadas: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.numNpratAtrasadas),
      numNpraAntecipadas: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.numNpraAntecipadas),
      numNprcpConcluidasPrazo: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.numNprcpConcluidasPrazo),
      valEcpa: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.valEcpa),
      valPcpa: new FormControl(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.valPcpa),
    });
  }

  getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
    form: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup,
  ): IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa | NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa {
    return form.getRawValue() as
      | IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
      | NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa;
  }

  resetForm(
    form: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroup,
    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormGroupInput,
  ): void {
    const onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
    };
    form.reset(
      {
        ...onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue,
        id: { value: onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaFormDefaults {
    return {
      id: null,
    };
  }
}
