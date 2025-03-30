import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsModalidadeOperacaoUsinas, NewOnsModalidadeOperacaoUsinas } from '../ons-modalidade-operacao-usinas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsModalidadeOperacaoUsinas for edit and NewOnsModalidadeOperacaoUsinasFormGroupInput for create.
 */
type OnsModalidadeOperacaoUsinasFormGroupInput = IOnsModalidadeOperacaoUsinas | PartialWithRequiredKeyOf<NewOnsModalidadeOperacaoUsinas>;

type OnsModalidadeOperacaoUsinasFormDefaults = Pick<NewOnsModalidadeOperacaoUsinas, 'id'>;

type OnsModalidadeOperacaoUsinasFormGroupContent = {
  id: FormControl<IOnsModalidadeOperacaoUsinas['id'] | NewOnsModalidadeOperacaoUsinas['id']>;
  nomUsina: FormControl<IOnsModalidadeOperacaoUsinas['nomUsina']>;
  ceg: FormControl<IOnsModalidadeOperacaoUsinas['ceg']>;
  nomModalidadeoperacao: FormControl<IOnsModalidadeOperacaoUsinas['nomModalidadeoperacao']>;
  valPotenciaautorizada: FormControl<IOnsModalidadeOperacaoUsinas['valPotenciaautorizada']>;
  sglCentrooperacao: FormControl<IOnsModalidadeOperacaoUsinas['sglCentrooperacao']>;
  nomPontoconexao: FormControl<IOnsModalidadeOperacaoUsinas['nomPontoconexao']>;
  idEstado: FormControl<IOnsModalidadeOperacaoUsinas['idEstado']>;
  nomEstado: FormControl<IOnsModalidadeOperacaoUsinas['nomEstado']>;
  stsAneel: FormControl<IOnsModalidadeOperacaoUsinas['stsAneel']>;
};

export type OnsModalidadeOperacaoUsinasFormGroup = FormGroup<OnsModalidadeOperacaoUsinasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsModalidadeOperacaoUsinasFormService {
  createOnsModalidadeOperacaoUsinasFormGroup(
    onsModalidadeOperacaoUsinas: OnsModalidadeOperacaoUsinasFormGroupInput = { id: null },
  ): OnsModalidadeOperacaoUsinasFormGroup {
    const onsModalidadeOperacaoUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsModalidadeOperacaoUsinas,
    };
    return new FormGroup<OnsModalidadeOperacaoUsinasFormGroupContent>({
      id: new FormControl(
        { value: onsModalidadeOperacaoUsinasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nomUsina: new FormControl(onsModalidadeOperacaoUsinasRawValue.nomUsina),
      ceg: new FormControl(onsModalidadeOperacaoUsinasRawValue.ceg),
      nomModalidadeoperacao: new FormControl(onsModalidadeOperacaoUsinasRawValue.nomModalidadeoperacao),
      valPotenciaautorizada: new FormControl(onsModalidadeOperacaoUsinasRawValue.valPotenciaautorizada),
      sglCentrooperacao: new FormControl(onsModalidadeOperacaoUsinasRawValue.sglCentrooperacao),
      nomPontoconexao: new FormControl(onsModalidadeOperacaoUsinasRawValue.nomPontoconexao),
      idEstado: new FormControl(onsModalidadeOperacaoUsinasRawValue.idEstado),
      nomEstado: new FormControl(onsModalidadeOperacaoUsinasRawValue.nomEstado),
      stsAneel: new FormControl(onsModalidadeOperacaoUsinasRawValue.stsAneel),
    });
  }

  getOnsModalidadeOperacaoUsinas(
    form: OnsModalidadeOperacaoUsinasFormGroup,
  ): IOnsModalidadeOperacaoUsinas | NewOnsModalidadeOperacaoUsinas {
    return form.getRawValue() as IOnsModalidadeOperacaoUsinas | NewOnsModalidadeOperacaoUsinas;
  }

  resetForm(form: OnsModalidadeOperacaoUsinasFormGroup, onsModalidadeOperacaoUsinas: OnsModalidadeOperacaoUsinasFormGroupInput): void {
    const onsModalidadeOperacaoUsinasRawValue = { ...this.getFormDefaults(), ...onsModalidadeOperacaoUsinas };
    form.reset(
      {
        ...onsModalidadeOperacaoUsinasRawValue,
        id: { value: onsModalidadeOperacaoUsinasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsModalidadeOperacaoUsinasFormDefaults {
    return {
      id: null,
    };
  }
}
