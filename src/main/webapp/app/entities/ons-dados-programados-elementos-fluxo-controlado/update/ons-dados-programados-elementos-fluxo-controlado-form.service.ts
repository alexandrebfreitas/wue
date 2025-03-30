import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosProgramadosElementosFluxoControlado,
  NewOnsDadosProgramadosElementosFluxoControlado,
} from '../ons-dados-programados-elementos-fluxo-controlado.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosProgramadosElementosFluxoControlado for edit and NewOnsDadosProgramadosElementosFluxoControladoFormGroupInput for create.
 */
type OnsDadosProgramadosElementosFluxoControladoFormGroupInput =
  | IOnsDadosProgramadosElementosFluxoControlado
  | PartialWithRequiredKeyOf<NewOnsDadosProgramadosElementosFluxoControlado>;

type OnsDadosProgramadosElementosFluxoControladoFormDefaults = Pick<NewOnsDadosProgramadosElementosFluxoControlado, 'id'>;

type OnsDadosProgramadosElementosFluxoControladoFormGroupContent = {
  id: FormControl<IOnsDadosProgramadosElementosFluxoControlado['id'] | NewOnsDadosProgramadosElementosFluxoControlado['id']>;
  dinProgramacaodia: FormControl<IOnsDadosProgramadosElementosFluxoControlado['dinProgramacaodia']>;
  numPatamar: FormControl<IOnsDadosProgramadosElementosFluxoControlado['numPatamar']>;
  nomElementofluxocontrolado: FormControl<IOnsDadosProgramadosElementosFluxoControlado['nomElementofluxocontrolado']>;
  dscElementofluxocontrolado: FormControl<IOnsDadosProgramadosElementosFluxoControlado['dscElementofluxocontrolado']>;
  tipTerminal: FormControl<IOnsDadosProgramadosElementosFluxoControlado['tipTerminal']>;
  codSubmercado: FormControl<IOnsDadosProgramadosElementosFluxoControlado['codSubmercado']>;
  valCarga: FormControl<IOnsDadosProgramadosElementosFluxoControlado['valCarga']>;
};

export type OnsDadosProgramadosElementosFluxoControladoFormGroup = FormGroup<OnsDadosProgramadosElementosFluxoControladoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosProgramadosElementosFluxoControladoFormService {
  createOnsDadosProgramadosElementosFluxoControladoFormGroup(
    onsDadosProgramadosElementosFluxoControlado: OnsDadosProgramadosElementosFluxoControladoFormGroupInput = { id: null },
  ): OnsDadosProgramadosElementosFluxoControladoFormGroup {
    const onsDadosProgramadosElementosFluxoControladoRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosProgramadosElementosFluxoControlado,
    };
    return new FormGroup<OnsDadosProgramadosElementosFluxoControladoFormGroupContent>({
      id: new FormControl(
        { value: onsDadosProgramadosElementosFluxoControladoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dinProgramacaodia: new FormControl(onsDadosProgramadosElementosFluxoControladoRawValue.dinProgramacaodia),
      numPatamar: new FormControl(onsDadosProgramadosElementosFluxoControladoRawValue.numPatamar),
      nomElementofluxocontrolado: new FormControl(onsDadosProgramadosElementosFluxoControladoRawValue.nomElementofluxocontrolado),
      dscElementofluxocontrolado: new FormControl(onsDadosProgramadosElementosFluxoControladoRawValue.dscElementofluxocontrolado),
      tipTerminal: new FormControl(onsDadosProgramadosElementosFluxoControladoRawValue.tipTerminal),
      codSubmercado: new FormControl(onsDadosProgramadosElementosFluxoControladoRawValue.codSubmercado),
      valCarga: new FormControl(onsDadosProgramadosElementosFluxoControladoRawValue.valCarga),
    });
  }

  getOnsDadosProgramadosElementosFluxoControlado(
    form: OnsDadosProgramadosElementosFluxoControladoFormGroup,
  ): IOnsDadosProgramadosElementosFluxoControlado | NewOnsDadosProgramadosElementosFluxoControlado {
    return form.getRawValue() as IOnsDadosProgramadosElementosFluxoControlado | NewOnsDadosProgramadosElementosFluxoControlado;
  }

  resetForm(
    form: OnsDadosProgramadosElementosFluxoControladoFormGroup,
    onsDadosProgramadosElementosFluxoControlado: OnsDadosProgramadosElementosFluxoControladoFormGroupInput,
  ): void {
    const onsDadosProgramadosElementosFluxoControladoRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosProgramadosElementosFluxoControlado,
    };
    form.reset(
      {
        ...onsDadosProgramadosElementosFluxoControladoRawValue,
        id: { value: onsDadosProgramadosElementosFluxoControladoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosProgramadosElementosFluxoControladoFormDefaults {
    return {
      id: null,
    };
  }
}
