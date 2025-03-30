import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsIntercambioSinComOutrosPaisesDetailComponent } from './ons-intercambio-sin-com-outros-paises-detail.component';

describe('OnsIntercambioSinComOutrosPaises Management Detail Component', () => {
  let comp: OnsIntercambioSinComOutrosPaisesDetailComponent;
  let fixture: ComponentFixture<OnsIntercambioSinComOutrosPaisesDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsIntercambioSinComOutrosPaisesDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-intercambio-sin-com-outros-paises-detail.component').then(
                  m => m.OnsIntercambioSinComOutrosPaisesDetailComponent,
                ),
              resolve: { onsIntercambioSinComOutrosPaises: () => of({ id: 8173 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsIntercambioSinComOutrosPaisesDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsIntercambioSinComOutrosPaisesDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsIntercambioSinComOutrosPaises on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsIntercambioSinComOutrosPaisesDetailComponent);

      // THEN
      expect(instance.onsIntercambioSinComOutrosPaises()).toEqual(expect.objectContaining({ id: 8173 }));
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
