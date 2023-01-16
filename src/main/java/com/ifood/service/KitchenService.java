package com.ifood.service;

import com.ifood.dto.KitchenDTO;
import com.ifood.mapper.Mapper;
import com.ifood.model.Kitchen;
import com.ifood.repository.KitchenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class KitchenService {

    private final KitchenRepository kitchenRepository;

    public KitchenService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public Kitchen execute(KitchenDTO kitchenDTO) {
        Kitchen kitchen = Mapper.parseObject(kitchenDTO, Kitchen.class);
        return kitchenRepository.save(kitchen);
    }

    public Page<Kitchen> listAllPaginated(Pageable pageable) {
        return kitchenRepository.findAll(pageable);
    }

    public Kitchen findById(Long id) {
        Optional<Kitchen> kitchenOptional = kitchenRepository.findById(id);
        if (kitchenOptional.isPresent()) {
            return kitchenOptional.get();
        }
        throw new ResourceNotFoundException("No records found for this given id");

    }

    public Kitchen update(Long id, KitchenDTO kitchenDTO) {
        Kitchen entity = this.findById(id);

        Mapper.copyProperties(kitchenDTO, entity);
        return kitchenRepository.save(entity);
    }

    public void delete(Long id) {
        Kitchen entity = this.findById(id);

        kitchenRepository.deleteById(entity.getId());
    }

}
