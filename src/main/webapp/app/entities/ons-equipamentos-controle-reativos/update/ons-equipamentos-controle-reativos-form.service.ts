import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsEquipamentosControleReativos, NewOnsEquipamentosControleReativos } from '../ons-equipamentos-controle-reativos.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsEquipamentosControleReativos for edit and NewOnsEquipamentosControleReativosFormGroupInput for create.
 */
type OnsEquipamentosControleReativosFormGroupInput =
  | IOnsEquipamentosControleReativos
  | PartialWithRequiredKeyOf<NewOnsEquipamentosControleReativos>;

type OnsEquipamentosControleReativosFormDefaults = Pick<NewOnsEquipamentosControleReativos, 'id'>;

type OnsEquipamentosControleReativosFormGroupContent = {
  id: FormControl<IOnsEquipamentosControleReativos['id'] | NewOnsEquipamentosControleReativos['id']>;
  idSubsistema: FormControl<IOnsEquipamentosControleReativos['idSubsistema']>;
  nomSubsistema: FormControl<IOnsEquipamentosControleReativos['nomSubsistema']>;
  idEstado: FormControl<IOnsEquipamentosControleReativos['idEstado']>;
  nomEstado: FormControl<IOnsEquipamentosControleReativos['nomEstado']>;
  nomSubestacao: FormControl<IOnsEquipamentosControleReativos['nomSubestacao']>;
  nomAgenteProprietario: FormControl<IOnsEquipamentosControleReativos['nomAgenteProprietario']>;
  nomTipoderede: FormControl<IOnsEquipamentosControleReativos['nomTipoderede']>;
  nomTipoequipamento: FormControl<IOnsEquipamentosControleReativos['nomTipoequipamento']>;
  nomEquipamento: FormControl<IOnsEquipamentosControleReativos['nomEquipamento']>;
  valPotreativanominalMvar: FormControl<IOnsEquipamentosControleReativos['valPotreativanominalMvar']>;
  valLimiteinferiorMvar: FormControl<IOnsEquipamentosControleReativos['valLimiteinferiorMvar']>;
  valLimitesuperiorMvar: FormControl<IOnsEquipamentosControleReativos['valLimitesuperiorMvar']>;
  datEntradaoperacao: FormControl<IOnsEquipamentosControleReativos['datEntradaoperacao']>;
  datDesativacao: FormControl<IOnsEquipamentosControleReativos['datDesativacao']>;
  codEquipamento: FormControl<IOnsEquipamentosControleReativos['codEquipamento']>;
};

export type OnsEquipamentosControleReativosFormGroup = FormGroup<OnsEquipamentosControleReativosFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsEquipamentosControleReativosFormService {
  createOnsEquipamentosControleReativosFormGroup(
    onsEquipamentosControleReativos: OnsEquipamentosControleReativosFormGroupInput = { id: null },
  ): OnsEquipamentosControleReativosFormGroup {
    const onsEquipamentosControleReativosRawValue = {
      ...this.getFormDefaults(),
      ...onsEquipamentosControleReativos,
    };
    return new FormGroup<OnsEquipamentosControleReativosFormGroupContent>({
      id: new FormControl(
        { value: onsEquipamentosControleReativosRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsEquipamentosControleReativosRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsEquipamentosControleReativosRawValue.nomSubsistema),
      idEstado: new FormControl(onsEquipamentosControleReativosRawValue.idEstado),
      nomEstado: new FormControl(onsEquipamentosControleReativosRawValue.nomEstado),
      nomSubestacao: new FormControl(onsEquipamentosControleReativosRawValue.nomSubestacao),
      nomAgenteProprietario: new FormControl(onsEquipamentosControleReativosRawValue.nomAgenteProprietario),
      nomTipoderede: new FormControl(onsEquipamentosControleReativosRawValue.nomTipoderede),
      nomTipoequipamento: new FormControl(onsEquipamentosControleReativosRawValue.nomTipoequipamento),
      nomEquipamento: new FormControl(onsEquipamentosControleReativosRawValue.nomEquipamento),
      valPotreativanominalMvar: new FormControl(onsEquipamentosControleReativosRawValue.valPotreativanominalMvar),
      valLimiteinferiorMvar: new FormControl(onsEquipamentosControleReativosRawValue.valLimiteinferiorMvar),
      valLimitesuperiorMvar: new FormControl(onsEquipamentosControleReativosRawValue.valLimitesuperiorMvar),
      datEntradaoperacao: new FormControl(onsEquipamentosControleReativosRawValue.datEntradaoperacao),
      datDesativacao: new FormControl(onsEquipamentosControleReativosRawValue.datDesativacao),
      codEquipamento: new FormControl(onsEquipamentosControleReativosRawValue.codEquipamento),
    });
  }

  getOnsEquipamentosControleReativos(
    form: OnsEquipamentosControleReativosFormGroup,
  ): IOnsEquipamentosControleReativos | NewOnsEquipamentosControleReativos {
    return form.getRawValue() as IOnsEquipamentosControleReativos | NewOnsEquipamentosControleReativos;
  }

  resetForm(
    form: OnsEquipamentosControleReativosFormGroup,
    onsEquipamentosControleReativos: OnsEquipamentosControleReativosFormGroupInput,
  ): void {
    const onsEquipamentosControleReativosRawValue = { ...this.getFormDefaults(), ...onsEquipamentosControleReativos };
    form.reset(
      {
        ...onsEquipamentosControleReativosRawValue,
        id: { value: onsEquipamentosControleReativosRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsEquipamentosControleReativosFormDefaults {
    return {
      id: null,
    };
  }
}
