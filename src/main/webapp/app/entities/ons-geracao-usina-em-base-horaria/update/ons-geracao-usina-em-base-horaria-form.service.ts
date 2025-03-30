import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsGeracaoUsinaEmBaseHoraria, NewOnsGeracaoUsinaEmBaseHoraria } from '../ons-geracao-usina-em-base-horaria.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsGeracaoUsinaEmBaseHoraria for edit and NewOnsGeracaoUsinaEmBaseHorariaFormGroupInput for create.
 */
type OnsGeracaoUsinaEmBaseHorariaFormGroupInput = IOnsGeracaoUsinaEmBaseHoraria | PartialWithRequiredKeyOf<NewOnsGeracaoUsinaEmBaseHoraria>;

type OnsGeracaoUsinaEmBaseHorariaFormDefaults = Pick<NewOnsGeracaoUsinaEmBaseHoraria, 'id'>;

type OnsGeracaoUsinaEmBaseHorariaFormGroupContent = {
  id: FormControl<IOnsGeracaoUsinaEmBaseHoraria['id'] | NewOnsGeracaoUsinaEmBaseHoraria['id']>;
  dinInstante: FormControl<IOnsGeracaoUsinaEmBaseHoraria['dinInstante']>;
  idSubsistema: FormControl<IOnsGeracaoUsinaEmBaseHoraria['idSubsistema']>;
  nomSubsistema: FormControl<IOnsGeracaoUsinaEmBaseHoraria['nomSubsistema']>;
  idEstado: FormControl<IOnsGeracaoUsinaEmBaseHoraria['idEstado']>;
  nomEstado: FormControl<IOnsGeracaoUsinaEmBaseHoraria['nomEstado']>;
  codModalidadeoperacao: FormControl<IOnsGeracaoUsinaEmBaseHoraria['codModalidadeoperacao']>;
  nomTipousina: FormControl<IOnsGeracaoUsinaEmBaseHoraria['nomTipousina']>;
  nomTipocombustivel: FormControl<IOnsGeracaoUsinaEmBaseHoraria['nomTipocombustivel']>;
  nomUsina: FormControl<IOnsGeracaoUsinaEmBaseHoraria['nomUsina']>;
  idOns: FormControl<IOnsGeracaoUsinaEmBaseHoraria['idOns']>;
  ceg: FormControl<IOnsGeracaoUsinaEmBaseHoraria['ceg']>;
  valGeracao: FormControl<IOnsGeracaoUsinaEmBaseHoraria['valGeracao']>;
};

export type OnsGeracaoUsinaEmBaseHorariaFormGroup = FormGroup<OnsGeracaoUsinaEmBaseHorariaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsGeracaoUsinaEmBaseHorariaFormService {
  createOnsGeracaoUsinaEmBaseHorariaFormGroup(
    onsGeracaoUsinaEmBaseHoraria: OnsGeracaoUsinaEmBaseHorariaFormGroupInput = { id: null },
  ): OnsGeracaoUsinaEmBaseHorariaFormGroup {
    const onsGeracaoUsinaEmBaseHorariaRawValue = {
      ...this.getFormDefaults(),
      ...onsGeracaoUsinaEmBaseHoraria,
    };
    return new FormGroup<OnsGeracaoUsinaEmBaseHorariaFormGroupContent>({
      id: new FormControl(
        { value: onsGeracaoUsinaEmBaseHorariaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dinInstante: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.dinInstante),
      idSubsistema: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.nomSubsistema),
      idEstado: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.idEstado),
      nomEstado: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.nomEstado),
      codModalidadeoperacao: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.codModalidadeoperacao),
      nomTipousina: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.nomTipousina),
      nomTipocombustivel: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.nomTipocombustivel),
      nomUsina: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.nomUsina),
      idOns: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.idOns),
      ceg: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.ceg),
      valGeracao: new FormControl(onsGeracaoUsinaEmBaseHorariaRawValue.valGeracao),
    });
  }

  getOnsGeracaoUsinaEmBaseHoraria(
    form: OnsGeracaoUsinaEmBaseHorariaFormGroup,
  ): IOnsGeracaoUsinaEmBaseHoraria | NewOnsGeracaoUsinaEmBaseHoraria {
    return form.getRawValue() as IOnsGeracaoUsinaEmBaseHoraria | NewOnsGeracaoUsinaEmBaseHoraria;
  }

  resetForm(form: OnsGeracaoUsinaEmBaseHorariaFormGroup, onsGeracaoUsinaEmBaseHoraria: OnsGeracaoUsinaEmBaseHorariaFormGroupInput): void {
    const onsGeracaoUsinaEmBaseHorariaRawValue = { ...this.getFormDefaults(), ...onsGeracaoUsinaEmBaseHoraria };
    form.reset(
      {
        ...onsGeracaoUsinaEmBaseHorariaRawValue,
        id: { value: onsGeracaoUsinaEmBaseHorariaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsGeracaoUsinaEmBaseHorariaFormDefaults {
    return {
      id: null,
    };
  }
}
