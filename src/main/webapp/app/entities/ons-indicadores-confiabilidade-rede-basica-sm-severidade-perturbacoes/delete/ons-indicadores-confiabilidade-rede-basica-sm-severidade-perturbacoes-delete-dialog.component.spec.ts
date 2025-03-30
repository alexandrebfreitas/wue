jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDeleteDialogComponent } from './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes-delete-dialog.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes Management Delete Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDeleteDialogComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDeleteDialogComponent>;
  let service: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService);
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
