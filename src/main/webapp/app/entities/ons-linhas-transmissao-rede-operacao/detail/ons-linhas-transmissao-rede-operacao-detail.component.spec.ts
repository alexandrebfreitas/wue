import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsLinhasTransmissaoRedeOperacaoDetailComponent } from './ons-linhas-transmissao-rede-operacao-detail.component';

describe('OnsLinhasTransmissaoRedeOperacao Management Detail Component', () => {
  let comp: OnsLinhasTransmissaoRedeOperacaoDetailComponent;
  let fixture: ComponentFixture<OnsLinhasTransmissaoRedeOperacaoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsLinhasTransmissaoRedeOperacaoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-linhas-transmissao-rede-operacao-detail.component').then(
                  m => m.OnsLinhasTransmissaoRedeOperacaoDetailComponent,
                ),
              resolve: { onsLinhasTransmissaoRedeOperacao: () => of({ id: 30390 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsLinhasTransmissaoRedeOperacaoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsLinhasTransmissaoRedeOperacaoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsLinhasTransmissaoRedeOperacao on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsLinhasTransmissaoRedeOperacaoDetailComponent);

      // THEN
      expect(instance.onsLinhasTransmissaoRedeOperacao()).toEqual(expect.objectContaining({ id: 30390 }));
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
