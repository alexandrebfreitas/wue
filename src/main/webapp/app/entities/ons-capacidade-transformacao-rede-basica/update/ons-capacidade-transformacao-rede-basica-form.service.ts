import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsCapacidadeTransformacaoRedeBasica,
  NewOnsCapacidadeTransformacaoRedeBasica,
} from '../ons-capacidade-transformacao-rede-basica.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCapacidadeTransformacaoRedeBasica for edit and NewOnsCapacidadeTransformacaoRedeBasicaFormGroupInput for create.
 */
type OnsCapacidadeTransformacaoRedeBasicaFormGroupInput =
  | IOnsCapacidadeTransformacaoRedeBasica
  | PartialWithRequiredKeyOf<NewOnsCapacidadeTransformacaoRedeBasica>;

type OnsCapacidadeTransformacaoRedeBasicaFormDefaults = Pick<NewOnsCapacidadeTransformacaoRedeBasica, 'id'>;

type OnsCapacidadeTransformacaoRedeBasicaFormGroupContent = {
  id: FormControl<IOnsCapacidadeTransformacaoRedeBasica['id'] | NewOnsCapacidadeTransformacaoRedeBasica['id']>;
  idSubsistema: FormControl<IOnsCapacidadeTransformacaoRedeBasica['idSubsistema']>;
  nomSubsistema: FormControl<IOnsCapacidadeTransformacaoRedeBasica['nomSubsistema']>;
  idEstado: FormControl<IOnsCapacidadeTransformacaoRedeBasica['idEstado']>;
  nomEstado: FormControl<IOnsCapacidadeTransformacaoRedeBasica['nomEstado']>;
  nomTipotransformador: FormControl<IOnsCapacidadeTransformacaoRedeBasica['nomTipotransformador']>;
  nomAgenteproprietario: FormControl<IOnsCapacidadeTransformacaoRedeBasica['nomAgenteproprietario']>;
  nomSubestacao: FormControl<IOnsCapacidadeTransformacaoRedeBasica['nomSubestacao']>;
  nomTransformador: FormControl<IOnsCapacidadeTransformacaoRedeBasica['nomTransformador']>;
  codEquipamento: FormControl<IOnsCapacidadeTransformacaoRedeBasica['codEquipamento']>;
  datEntradaoperacao: FormControl<IOnsCapacidadeTransformacaoRedeBasica['datEntradaoperacao']>;
  datDesativacao: FormControl<IOnsCapacidadeTransformacaoRedeBasica['datDesativacao']>;
  valTensaoprimarioKv: FormControl<IOnsCapacidadeTransformacaoRedeBasica['valTensaoprimarioKv']>;
  valTensaosecundarioKv: FormControl<IOnsCapacidadeTransformacaoRedeBasica['valTensaosecundarioKv']>;
  valTensaoterciarioKv: FormControl<IOnsCapacidadeTransformacaoRedeBasica['valTensaoterciarioKv']>;
  valPotencianominalMva: FormControl<IOnsCapacidadeTransformacaoRedeBasica['valPotencianominalMva']>;
  nomTipoderede: FormControl<IOnsCapacidadeTransformacaoRedeBasica['nomTipoderede']>;
  numBarraPrimario: FormControl<IOnsCapacidadeTransformacaoRedeBasica['numBarraPrimario']>;
  numBarraSecundario: FormControl<IOnsCapacidadeTransformacaoRedeBasica['numBarraSecundario']>;
};

export type OnsCapacidadeTransformacaoRedeBasicaFormGroup = FormGroup<OnsCapacidadeTransformacaoRedeBasicaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCapacidadeTransformacaoRedeBasicaFormService {
  createOnsCapacidadeTransformacaoRedeBasicaFormGroup(
    onsCapacidadeTransformacaoRedeBasica: OnsCapacidadeTransformacaoRedeBasicaFormGroupInput = { id: null },
  ): OnsCapacidadeTransformacaoRedeBasicaFormGroup {
    const onsCapacidadeTransformacaoRedeBasicaRawValue = {
      ...this.getFormDefaults(),
      ...onsCapacidadeTransformacaoRedeBasica,
    };
    return new FormGroup<OnsCapacidadeTransformacaoRedeBasicaFormGroupContent>({
      id: new FormControl(
        { value: onsCapacidadeTransformacaoRedeBasicaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.nomSubsistema),
      idEstado: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.idEstado),
      nomEstado: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.nomEstado),
      nomTipotransformador: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.nomTipotransformador),
      nomAgenteproprietario: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.nomAgenteproprietario),
      nomSubestacao: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.nomSubestacao),
      nomTransformador: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.nomTransformador),
      codEquipamento: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.codEquipamento),
      datEntradaoperacao: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.datEntradaoperacao),
      datDesativacao: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.datDesativacao),
      valTensaoprimarioKv: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.valTensaoprimarioKv),
      valTensaosecundarioKv: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.valTensaosecundarioKv),
      valTensaoterciarioKv: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.valTensaoterciarioKv),
      valPotencianominalMva: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.valPotencianominalMva),
      nomTipoderede: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.nomTipoderede),
      numBarraPrimario: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.numBarraPrimario),
      numBarraSecundario: new FormControl(onsCapacidadeTransformacaoRedeBasicaRawValue.numBarraSecundario),
    });
  }

  getOnsCapacidadeTransformacaoRedeBasica(
    form: OnsCapacidadeTransformacaoRedeBasicaFormGroup,
  ): IOnsCapacidadeTransformacaoRedeBasica | NewOnsCapacidadeTransformacaoRedeBasica {
    return form.getRawValue() as IOnsCapacidadeTransformacaoRedeBasica | NewOnsCapacidadeTransformacaoRedeBasica;
  }

  resetForm(
    form: OnsCapacidadeTransformacaoRedeBasicaFormGroup,
    onsCapacidadeTransformacaoRedeBasica: OnsCapacidadeTransformacaoRedeBasicaFormGroupInput,
  ): void {
    const onsCapacidadeTransformacaoRedeBasicaRawValue = { ...this.getFormDefaults(), ...onsCapacidadeTransformacaoRedeBasica };
    form.reset(
      {
        ...onsCapacidadeTransformacaoRedeBasicaRawValue,
        id: { value: onsCapacidadeTransformacaoRedeBasicaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCapacidadeTransformacaoRedeBasicaFormDefaults {
    return {
      id: null,
    };
  }
}
