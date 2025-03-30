import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsCapacidadeInstaladaGeracao, NewOnsCapacidadeInstaladaGeracao } from '../ons-capacidade-instalada-geracao.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCapacidadeInstaladaGeracao for edit and NewOnsCapacidadeInstaladaGeracaoFormGroupInput for create.
 */
type OnsCapacidadeInstaladaGeracaoFormGroupInput =
  | IOnsCapacidadeInstaladaGeracao
  | PartialWithRequiredKeyOf<NewOnsCapacidadeInstaladaGeracao>;

type OnsCapacidadeInstaladaGeracaoFormDefaults = Pick<NewOnsCapacidadeInstaladaGeracao, 'id'>;

type OnsCapacidadeInstaladaGeracaoFormGroupContent = {
  id: FormControl<IOnsCapacidadeInstaladaGeracao['id'] | NewOnsCapacidadeInstaladaGeracao['id']>;
  idSubsistema: FormControl<IOnsCapacidadeInstaladaGeracao['idSubsistema']>;
  nomSubsistema: FormControl<IOnsCapacidadeInstaladaGeracao['nomSubsistema']>;
  idEstado: FormControl<IOnsCapacidadeInstaladaGeracao['idEstado']>;
  nomEstado: FormControl<IOnsCapacidadeInstaladaGeracao['nomEstado']>;
  nomModalidadeoperacao: FormControl<IOnsCapacidadeInstaladaGeracao['nomModalidadeoperacao']>;
  nomAgenteproprietario: FormControl<IOnsCapacidadeInstaladaGeracao['nomAgenteproprietario']>;
  nomTipousina: FormControl<IOnsCapacidadeInstaladaGeracao['nomTipousina']>;
  nomUsina: FormControl<IOnsCapacidadeInstaladaGeracao['nomUsina']>;
  ceg: FormControl<IOnsCapacidadeInstaladaGeracao['ceg']>;
  nomUnidadegeradora: FormControl<IOnsCapacidadeInstaladaGeracao['nomUnidadegeradora']>;
  codEquipamento: FormControl<IOnsCapacidadeInstaladaGeracao['codEquipamento']>;
  numUnidadegeradora: FormControl<IOnsCapacidadeInstaladaGeracao['numUnidadegeradora']>;
  nomCombustivel: FormControl<IOnsCapacidadeInstaladaGeracao['nomCombustivel']>;
  datEntradateste: FormControl<IOnsCapacidadeInstaladaGeracao['datEntradateste']>;
  datEntradaoperacao: FormControl<IOnsCapacidadeInstaladaGeracao['datEntradaoperacao']>;
  datDesativacao: FormControl<IOnsCapacidadeInstaladaGeracao['datDesativacao']>;
  valPotenciaefetiva: FormControl<IOnsCapacidadeInstaladaGeracao['valPotenciaefetiva']>;
};

export type OnsCapacidadeInstaladaGeracaoFormGroup = FormGroup<OnsCapacidadeInstaladaGeracaoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCapacidadeInstaladaGeracaoFormService {
  createOnsCapacidadeInstaladaGeracaoFormGroup(
    onsCapacidadeInstaladaGeracao: OnsCapacidadeInstaladaGeracaoFormGroupInput = { id: null },
  ): OnsCapacidadeInstaladaGeracaoFormGroup {
    const onsCapacidadeInstaladaGeracaoRawValue = {
      ...this.getFormDefaults(),
      ...onsCapacidadeInstaladaGeracao,
    };
    return new FormGroup<OnsCapacidadeInstaladaGeracaoFormGroupContent>({
      id: new FormControl(
        { value: onsCapacidadeInstaladaGeracaoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.nomSubsistema),
      idEstado: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.idEstado),
      nomEstado: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.nomEstado),
      nomModalidadeoperacao: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.nomModalidadeoperacao),
      nomAgenteproprietario: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.nomAgenteproprietario),
      nomTipousina: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.nomTipousina),
      nomUsina: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.nomUsina),
      ceg: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.ceg),
      nomUnidadegeradora: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.nomUnidadegeradora),
      codEquipamento: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.codEquipamento),
      numUnidadegeradora: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.numUnidadegeradora),
      nomCombustivel: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.nomCombustivel),
      datEntradateste: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.datEntradateste),
      datEntradaoperacao: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.datEntradaoperacao),
      datDesativacao: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.datDesativacao),
      valPotenciaefetiva: new FormControl(onsCapacidadeInstaladaGeracaoRawValue.valPotenciaefetiva),
    });
  }

  getOnsCapacidadeInstaladaGeracao(
    form: OnsCapacidadeInstaladaGeracaoFormGroup,
  ): IOnsCapacidadeInstaladaGeracao | NewOnsCapacidadeInstaladaGeracao {
    return form.getRawValue() as IOnsCapacidadeInstaladaGeracao | NewOnsCapacidadeInstaladaGeracao;
  }

  resetForm(
    form: OnsCapacidadeInstaladaGeracaoFormGroup,
    onsCapacidadeInstaladaGeracao: OnsCapacidadeInstaladaGeracaoFormGroupInput,
  ): void {
    const onsCapacidadeInstaladaGeracaoRawValue = { ...this.getFormDefaults(), ...onsCapacidadeInstaladaGeracao };
    form.reset(
      {
        ...onsCapacidadeInstaladaGeracaoRawValue,
        id: { value: onsCapacidadeInstaladaGeracaoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCapacidadeInstaladaGeracaoFormDefaults {
    return {
      id: null,
    };
  }
}
