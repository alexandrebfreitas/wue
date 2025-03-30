import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosIntercambioEnergiaModalidade,
  NewOnsDadosIntercambioEnergiaModalidade,
} from '../ons-dados-intercambio-energia-modalidade.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosIntercambioEnergiaModalidade for edit and NewOnsDadosIntercambioEnergiaModalidadeFormGroupInput for create.
 */
type OnsDadosIntercambioEnergiaModalidadeFormGroupInput =
  | IOnsDadosIntercambioEnergiaModalidade
  | PartialWithRequiredKeyOf<NewOnsDadosIntercambioEnergiaModalidade>;

type OnsDadosIntercambioEnergiaModalidadeFormDefaults = Pick<NewOnsDadosIntercambioEnergiaModalidade, 'id'>;

type OnsDadosIntercambioEnergiaModalidadeFormGroupContent = {
  id: FormControl<IOnsDadosIntercambioEnergiaModalidade['id'] | NewOnsDadosIntercambioEnergiaModalidade['id']>;
  nomConversora: FormControl<IOnsDadosIntercambioEnergiaModalidade['nomConversora']>;
  dinInstante: FormControl<IOnsDadosIntercambioEnergiaModalidade['dinInstante']>;
  valModalidadecontratual: FormControl<IOnsDadosIntercambioEnergiaModalidade['valModalidadecontratual']>;
  valModalidadeemergencial: FormControl<IOnsDadosIntercambioEnergiaModalidade['valModalidadeemergencial']>;
  valModalidadeoportunidade: FormControl<IOnsDadosIntercambioEnergiaModalidade['valModalidadeoportunidade']>;
  valModalidadeteste: FormControl<IOnsDadosIntercambioEnergiaModalidade['valModalidadeteste']>;
  valModalidadeexcepcional: FormControl<IOnsDadosIntercambioEnergiaModalidade['valModalidadeexcepcional']>;
};

export type OnsDadosIntercambioEnergiaModalidadeFormGroup = FormGroup<OnsDadosIntercambioEnergiaModalidadeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosIntercambioEnergiaModalidadeFormService {
  createOnsDadosIntercambioEnergiaModalidadeFormGroup(
    onsDadosIntercambioEnergiaModalidade: OnsDadosIntercambioEnergiaModalidadeFormGroupInput = { id: null },
  ): OnsDadosIntercambioEnergiaModalidadeFormGroup {
    const onsDadosIntercambioEnergiaModalidadeRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosIntercambioEnergiaModalidade,
    };
    return new FormGroup<OnsDadosIntercambioEnergiaModalidadeFormGroupContent>({
      id: new FormControl(
        { value: onsDadosIntercambioEnergiaModalidadeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nomConversora: new FormControl(onsDadosIntercambioEnergiaModalidadeRawValue.nomConversora),
      dinInstante: new FormControl(onsDadosIntercambioEnergiaModalidadeRawValue.dinInstante),
      valModalidadecontratual: new FormControl(onsDadosIntercambioEnergiaModalidadeRawValue.valModalidadecontratual),
      valModalidadeemergencial: new FormControl(onsDadosIntercambioEnergiaModalidadeRawValue.valModalidadeemergencial),
      valModalidadeoportunidade: new FormControl(onsDadosIntercambioEnergiaModalidadeRawValue.valModalidadeoportunidade),
      valModalidadeteste: new FormControl(onsDadosIntercambioEnergiaModalidadeRawValue.valModalidadeteste),
      valModalidadeexcepcional: new FormControl(onsDadosIntercambioEnergiaModalidadeRawValue.valModalidadeexcepcional),
    });
  }

  getOnsDadosIntercambioEnergiaModalidade(
    form: OnsDadosIntercambioEnergiaModalidadeFormGroup,
  ): IOnsDadosIntercambioEnergiaModalidade | NewOnsDadosIntercambioEnergiaModalidade {
    return form.getRawValue() as IOnsDadosIntercambioEnergiaModalidade | NewOnsDadosIntercambioEnergiaModalidade;
  }

  resetForm(
    form: OnsDadosIntercambioEnergiaModalidadeFormGroup,
    onsDadosIntercambioEnergiaModalidade: OnsDadosIntercambioEnergiaModalidadeFormGroupInput,
  ): void {
    const onsDadosIntercambioEnergiaModalidadeRawValue = { ...this.getFormDefaults(), ...onsDadosIntercambioEnergiaModalidade };
    form.reset(
      {
        ...onsDadosIntercambioEnergiaModalidadeRawValue,
        id: { value: onsDadosIntercambioEnergiaModalidadeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosIntercambioEnergiaModalidadeFormDefaults {
    return {
      id: null,
    };
  }
}
