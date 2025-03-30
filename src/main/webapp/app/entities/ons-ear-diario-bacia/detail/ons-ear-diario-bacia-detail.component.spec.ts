import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsEarDiarioBaciaDetailComponent } from './ons-ear-diario-bacia-detail.component';

describe('OnsEarDiarioBacia Management Detail Component', () => {
  let comp: OnsEarDiarioBaciaDetailComponent;
  let fixture: ComponentFixture<OnsEarDiarioBaciaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsEarDiarioBaciaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-ear-diario-bacia-detail.component').then(m => m.OnsEarDiarioBaciaDetailComponent),
              resolve: { onsEarDiarioBacia: () => of({ id: 30574 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsEarDiarioBaciaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsEarDiarioBaciaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsEarDiarioBacia on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsEarDiarioBaciaDetailComponent);

      // THEN
      expect(instance.onsEarDiarioBacia()).toEqual(expect.objectContaining({ id: 30574 }));
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
