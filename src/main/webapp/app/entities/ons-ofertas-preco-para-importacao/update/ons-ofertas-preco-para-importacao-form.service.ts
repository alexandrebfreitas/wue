import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsOfertasPrecoParaImportacao, NewOnsOfertasPrecoParaImportacao } from '../ons-ofertas-preco-para-importacao.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsOfertasPrecoParaImportacao for edit and NewOnsOfertasPrecoParaImportacaoFormGroupInput for create.
 */
type OnsOfertasPrecoParaImportacaoFormGroupInput =
  | IOnsOfertasPrecoParaImportacao
  | PartialWithRequiredKeyOf<NewOnsOfertasPrecoParaImportacao>;

type OnsOfertasPrecoParaImportacaoFormDefaults = Pick<NewOnsOfertasPrecoParaImportacao, 'id'>;

type OnsOfertasPrecoParaImportacaoFormGroupContent = {
  id: FormControl<IOnsOfertasPrecoParaImportacao['id'] | NewOnsOfertasPrecoParaImportacao['id']>;
  nomPais: FormControl<IOnsOfertasPrecoParaImportacao['nomPais']>;
  nomAgente: FormControl<IOnsOfertasPrecoParaImportacao['nomAgente']>;
  nomBloco: FormControl<IOnsOfertasPrecoParaImportacao['nomBloco']>;
  datIniciovalidade: FormControl<IOnsOfertasPrecoParaImportacao['datIniciovalidade']>;
  datFimvalidade: FormControl<IOnsOfertasPrecoParaImportacao['datFimvalidade']>;
  valPreco: FormControl<IOnsOfertasPrecoParaImportacao['valPreco']>;
};

export type OnsOfertasPrecoParaImportacaoFormGroup = FormGroup<OnsOfertasPrecoParaImportacaoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsOfertasPrecoParaImportacaoFormService {
  createOnsOfertasPrecoParaImportacaoFormGroup(
    onsOfertasPrecoParaImportacao: OnsOfertasPrecoParaImportacaoFormGroupInput = { id: null },
  ): OnsOfertasPrecoParaImportacaoFormGroup {
    const onsOfertasPrecoParaImportacaoRawValue = {
      ...this.getFormDefaults(),
      ...onsOfertasPrecoParaImportacao,
    };
    return new FormGroup<OnsOfertasPrecoParaImportacaoFormGroupContent>({
      id: new FormControl(
        { value: onsOfertasPrecoParaImportacaoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nomPais: new FormControl(onsOfertasPrecoParaImportacaoRawValue.nomPais),
      nomAgente: new FormControl(onsOfertasPrecoParaImportacaoRawValue.nomAgente),
      nomBloco: new FormControl(onsOfertasPrecoParaImportacaoRawValue.nomBloco),
      datIniciovalidade: new FormControl(onsOfertasPrecoParaImportacaoRawValue.datIniciovalidade),
      datFimvalidade: new FormControl(onsOfertasPrecoParaImportacaoRawValue.datFimvalidade),
      valPreco: new FormControl(onsOfertasPrecoParaImportacaoRawValue.valPreco),
    });
  }

  getOnsOfertasPrecoParaImportacao(
    form: OnsOfertasPrecoParaImportacaoFormGroup,
  ): IOnsOfertasPrecoParaImportacao | NewOnsOfertasPrecoParaImportacao {
    return form.getRawValue() as IOnsOfertasPrecoParaImportacao | NewOnsOfertasPrecoParaImportacao;
  }

  resetForm(
    form: OnsOfertasPrecoParaImportacaoFormGroup,
    onsOfertasPrecoParaImportacao: OnsOfertasPrecoParaImportacaoFormGroupInput,
  ): void {
    const onsOfertasPrecoParaImportacaoRawValue = { ...this.getFormDefaults(), ...onsOfertasPrecoParaImportacao };
    form.reset(
      {
        ...onsOfertasPrecoParaImportacaoRawValue,
        id: { value: onsOfertasPrecoParaImportacaoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsOfertasPrecoParaImportacaoFormDefaults {
    return {
      id: null,
    };
  }
}
