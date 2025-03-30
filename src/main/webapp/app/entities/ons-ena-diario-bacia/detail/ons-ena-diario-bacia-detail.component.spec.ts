import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsEnaDiarioBaciaDetailComponent } from './ons-ena-diario-bacia-detail.component';

describe('OnsEnaDiarioBacia Management Detail Component', () => {
  let comp: OnsEnaDiarioBaciaDetailComponent;
  let fixture: ComponentFixture<OnsEnaDiarioBaciaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsEnaDiarioBaciaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-ena-diario-bacia-detail.component').then(m => m.OnsEnaDiarioBaciaDetailComponent),
              resolve: { onsEnaDiarioBacia: () => of({ id: 732 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsEnaDiarioBaciaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsEnaDiarioBaciaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsEnaDiarioBacia on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsEnaDiarioBaciaDetailComponent);

      // THEN
      expect(instance.onsEnaDiarioBacia()).toEqual(expect.objectContaining({ id: 732 }));
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
