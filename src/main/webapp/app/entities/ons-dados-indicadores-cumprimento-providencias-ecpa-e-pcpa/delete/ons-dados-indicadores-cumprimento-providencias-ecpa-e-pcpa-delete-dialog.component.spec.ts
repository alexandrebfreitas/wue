jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService } from '../service/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.service';

import { OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDeleteDialogComponent } from './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa-delete-dialog.component';

describe('OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa Management Delete Component', () => {
  let comp: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDeleteDialogComponent>;
  let service: OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
