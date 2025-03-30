import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent } from './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-detail.component';

describe('OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 Management Detail Component', () => {
  let comp: OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent;
  let fixture: ComponentFixture<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-detail.component').then(
                  m => m.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent,
                ),
              resolve: { onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024: () => of({ id: 6420 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024DetailComponent);

      // THEN
      expect(instance.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024()).toEqual(expect.objectContaining({ id: 6420 }));
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
