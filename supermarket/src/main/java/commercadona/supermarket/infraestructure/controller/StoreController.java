package commercadona.supermarket.infraestructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import commercadona.supermarket.application.usecases.StoreUseCase;
import commercadona.supermarket.infraestructure.dtos.response.StoreResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/stores")
public class StoreController {
    
       private StoreUseCase storeUseCase;

       public StoreController (StoreUseCase storeUseCase){
        this.storeUseCase = storeUseCase;
    }


    @Operation(summary = "Obtener la informacion de la tienda por el identificador")
    @GetMapping("/{storeId}")
    public StoreResponseDTO getStoreData(@PathVariable Long storeId){
        log.info("Iniciando la obtencion de datos de la tienda {}", storeId);
        var store = storeUseCase.getStoreData(storeId);
        log.info("Terminado la obtencion de datos de la tienda {}", storeId);
        return store;
    }
}
