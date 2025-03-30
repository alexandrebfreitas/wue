import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsFatorCapacidadeGeracaoEolicaESolar,
  NewOnsFatorCapacidadeGeracaoEolicaESolar,
} from '../ons-fator-capacidade-geracao-eolica-e-solar.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsFatorCapacidadeGeracaoEolicaESolar for edit and NewOnsFatorCapacidadeGeracaoEolicaESolarFormGroupInput for create.
 */
type OnsFatorCapacidadeGeracaoEolicaESolarFormGroupInput =
  | IOnsFatorCapacidadeGeracaoEolicaESolar
  | PartialWithRequiredKeyOf<NewOnsFatorCapacidadeGeracaoEolicaESolar>;

type OnsFatorCapacidadeGeracaoEolicaESolarFormDefaults = Pick<NewOnsFatorCapacidadeGeracaoEolicaESolar, 'id'>;

type OnsFatorCapacidadeGeracaoEolicaESolarFormGroupContent = {
  id: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['id'] | NewOnsFatorCapacidadeGeracaoEolicaESolar['id']>;
  idSubsistema: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['idSubsistema']>;
  nomSubsistema: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['nomSubsistema']>;
  idEstado: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['idEstado']>;
  nomEstado: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['nomEstado']>;
  codPontoconexao: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['codPontoconexao']>;
  nomPontoconexao: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['nomPontoconexao']>;
  nomLocalizacao: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['nomLocalizacao']>;
  valLatitudesecoletora: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['valLatitudesecoletora']>;
  valLongitudesecoletora: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['valLongitudesecoletora']>;
  valLatitudepontoconexao: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['valLatitudepontoconexao']>;
  valLongitudepontoconexao: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['valLongitudepontoconexao']>;
  nomModalidadeoperacao: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['nomModalidadeoperacao']>;
  nomTipousina: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['nomTipousina']>;
  nomUsinaConjunto: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['nomUsinaConjunto']>;
  dinInstante: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['dinInstante']>;
  idOns: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['idOns']>;
  ceg: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['ceg']>;
  valGeracaoprogramada: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['valGeracaoprogramada']>;
  valGeracaoverificada: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['valGeracaoverificada']>;
  valCapacidadeinstalada: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['valCapacidadeinstalada']>;
  valFatorcapacidade: FormControl<IOnsFatorCapacidadeGeracaoEolicaESolar['valFatorcapacidade']>;
};

export type OnsFatorCapacidadeGeracaoEolicaESolarFormGroup = FormGroup<OnsFatorCapacidadeGeracaoEolicaESolarFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsFatorCapacidadeGeracaoEolicaESolarFormService {
  createOnsFatorCapacidadeGeracaoEolicaESolarFormGroup(
    onsFatorCapacidadeGeracaoEolicaESolar: OnsFatorCapacidadeGeracaoEolicaESolarFormGroupInput = { id: null },
  ): OnsFatorCapacidadeGeracaoEolicaESolarFormGroup {
    const onsFatorCapacidadeGeracaoEolicaESolarRawValue = {
      ...this.getFormDefaults(),
      ...onsFatorCapacidadeGeracaoEolicaESolar,
    };
    return new FormGroup<OnsFatorCapacidadeGeracaoEolicaESolarFormGroupContent>({
      id: new FormControl(
        { value: onsFatorCapacidadeGeracaoEolicaESolarRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.nomSubsistema),
      idEstado: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.idEstado),
      nomEstado: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.nomEstado),
      codPontoconexao: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.codPontoconexao),
      nomPontoconexao: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.nomPontoconexao),
      nomLocalizacao: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.nomLocalizacao),
      valLatitudesecoletora: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.valLatitudesecoletora),
      valLongitudesecoletora: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.valLongitudesecoletora),
      valLatitudepontoconexao: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.valLatitudepontoconexao),
      valLongitudepontoconexao: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.valLongitudepontoconexao),
      nomModalidadeoperacao: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.nomModalidadeoperacao),
      nomTipousina: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.nomTipousina),
      nomUsinaConjunto: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.nomUsinaConjunto),
      dinInstante: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.dinInstante),
      idOns: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.idOns),
      ceg: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.ceg),
      valGeracaoprogramada: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.valGeracaoprogramada),
      valGeracaoverificada: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.valGeracaoverificada),
      valCapacidadeinstalada: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.valCapacidadeinstalada),
      valFatorcapacidade: new FormControl(onsFatorCapacidadeGeracaoEolicaESolarRawValue.valFatorcapacidade),
    });
  }

  getOnsFatorCapacidadeGeracaoEolicaESolar(
    form: OnsFatorCapacidadeGeracaoEolicaESolarFormGroup,
  ): IOnsFatorCapacidadeGeracaoEolicaESolar | NewOnsFatorCapacidadeGeracaoEolicaESolar {
    return form.getRawValue() as IOnsFatorCapacidadeGeracaoEolicaESolar | NewOnsFatorCapacidadeGeracaoEolicaESolar;
  }

  resetForm(
    form: OnsFatorCapacidadeGeracaoEolicaESolarFormGroup,
    onsFatorCapacidadeGeracaoEolicaESolar: OnsFatorCapacidadeGeracaoEolicaESolarFormGroupInput,
  ): void {
    const onsFatorCapacidadeGeracaoEolicaESolarRawValue = { ...this.getFormDefaults(), ...onsFatorCapacidadeGeracaoEolicaESolar };
    form.reset(
      {
        ...onsFatorCapacidadeGeracaoEolicaESolarRawValue,
        id: { value: onsFatorCapacidadeGeracaoEolicaESolarRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsFatorCapacidadeGeracaoEolicaESolarFormDefaults {
    return {
      id: null,
    };
  }
}
