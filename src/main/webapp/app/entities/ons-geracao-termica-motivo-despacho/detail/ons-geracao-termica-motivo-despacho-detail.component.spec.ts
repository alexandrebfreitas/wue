import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsGeracaoTermicaMotivoDespachoDetailComponent } from './ons-geracao-termica-motivo-despacho-detail.component';

describe('OnsGeracaoTermicaMotivoDespacho Management Detail Component', () => {
  let comp: OnsGeracaoTermicaMotivoDespachoDetailComponent;
  let fixture: ComponentFixture<OnsGeracaoTermicaMotivoDespachoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsGeracaoTermicaMotivoDespachoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-geracao-termica-motivo-despacho-detail.component').then(
                  m => m.OnsGeracaoTermicaMotivoDespachoDetailComponent,
                ),
              resolve: { onsGeracaoTermicaMotivoDespacho: () => of({ id: 18788 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsGeracaoTermicaMotivoDespachoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsGeracaoTermicaMotivoDespachoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsGeracaoTermicaMotivoDespacho on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsGeracaoTermicaMotivoDespachoDetailComponent);

      // THEN
      expect(instance.onsGeracaoTermicaMotivoDespacho()).toEqual(expect.objectContaining({ id: 18788 }));
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
