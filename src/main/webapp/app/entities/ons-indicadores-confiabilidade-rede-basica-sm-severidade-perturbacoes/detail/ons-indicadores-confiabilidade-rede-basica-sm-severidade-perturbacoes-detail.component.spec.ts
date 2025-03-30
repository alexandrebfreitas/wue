import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent } from './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-detail.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes Management Detail Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-detail.component').then(
                  m => m.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent,
                ),
              resolve: { onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes: () => of({ id: 25854 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDetailComponent);

      // THEN
      expect(instance.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes()).toEqual(expect.objectContaining({ id: 25854 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
