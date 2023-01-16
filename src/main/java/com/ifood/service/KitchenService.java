package com.ifood.service;

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

    public Kitchen execute(Kitchen kitchen) {
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

    public Kitchen update(Long id, Kitchen kitchen) {
        Kitchen entity = this.findById(id);
        BeanUtils.copyProperties(kitchen, entity, "id");

        return kitchenRepository.save(entity);
    }

    public void delete(Long id) {
        Kitchen entity = this.findById(id);

        kitchenRepository.deleteById(entity.getId());
    }

}
