package com.ifood.resource;

import com.ifood.model.Kitchen;
import com.ifood.service.KitchenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class KitchenResource {

    private final KitchenService kitchenService;

    public KitchenResource(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping(value = "/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Kitchen>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(kitchenService.listAllPaginated(pageable));
    }

    @PostMapping(value = "/kitchens", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Kitchen> create(@RequestBody Kitchen kitchen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kitchenService.execute(kitchen));
    }

    @PutMapping(value = "/kitchens/{kitchen_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Kitchen> update(@PathVariable("kitchen_id") Long id, @RequestBody Kitchen kitchen) {
        return ResponseEntity.status(HttpStatus.OK).body(kitchenService.update(id, kitchen));
    }

    @DeleteMapping(value = "/kitchens/{kitchen_id}")
    public ResponseEntity<Void> delete(@PathVariable("kitchen_id") Long id) {
        kitchenService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
