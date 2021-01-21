package com.restful.gestaodepedidos.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.restful.gestaodepedidos.domain.Request;
import com.restful.gestaodepedidos.domain.enums.RequestState;
import com.restful.gestaodepedidos.domain.model.PageModel;
import com.restful.gestaodepedidos.domain.model.PageRequestModel;
import com.restful.gestaodepedidos.exception.NotFoundException;
import com.restful.gestaodepedidos.repository.RequestRepository;

@Service
public class RequestService {
	
	@Autowired private RequestRepository requestRepository;
	
	public Request save(Request request) {
		
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		Request saveRequest = requestRepository.save(request);
		return saveRequest;
	}
	
	public Request update(Request update) {
		
		Request updateRequest = requestRepository.save(update);
		return updateRequest;
	}
	
	public Request getById(Long id) {
		
		Optional<Request> result = requestRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Não existe Request com id = " + id));
		}
	
//	public List<Request> listAll(){
//		
//		List<Request> listRequest = requestRepository.findAll();
//		return listRequest;
//	}
	
public PageModel<Request> listAllByOnLazyModel(PageRequestModel pr){
		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize()); // cria a paginação
		Page<Request> page = requestRepository.findAll(pageable); // inseri os dados numa pagina.
		
		PageModel<Request> pm = new PageModel<>(
				(int)page.getTotalElements(),
				page.getSize(), page.getTotalPages(),
				page.getContent());
		
		return pm;
	}
	
//	//http://localhost:8080/users/1/requests-list /// Método Opcional
//	public List<Request> listAllByOwnerId(Long ownerId){
//		
//		List<Request> listOwner = requestRepository.findAllByOwnerId(ownerId);
//		return listOwner;
//	}
	
	//http://localhost:8080/users/1/requests
	public PageModel<Request> listAllByOwnerIdOnLazyModel(Long ownerId, PageRequestModel pr){
		
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize()); // cria paginação
		Page<Request> page = requestRepository.findAllByOwnerId(ownerId, pageable);// inserir a paginação
		
		PageModel<Request> pm = new PageModel<>(
				(int)page.getTotalElements(),
				page.getSize(),
				page.getTotalPages(),
				page.getContent());
		
		return pm;
	}

}
