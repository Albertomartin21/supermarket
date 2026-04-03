package commercadona.supermarket.infraestructure.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import commercadona.supermarket.application.usecases.WorkerUseCase;
import commercadona.supermarket.infraestructure.dtos.request.WorkerRequestDTO;
import commercadona.supermarket.infraestructure.dtos.response.WorkerResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/workers")
public class WorkerController {
    
    private WorkerUseCase workerUseCase;

       public WorkerController(WorkerUseCase workerUseCase){
        this.workerUseCase = workerUseCase;
    }

    @Operation(summary = "Obtener la lista de todos los trabajadores")
    @GetMapping
    public List<WorkerResponseDTO> getListWorkers(){
        log.info("Iniciando la consulta de trabajadores");
        var listWorker = workerUseCase.getListWorkers();
        log.info("Terminado la consulta de trabajadores");
        return listWorker;
    }

    @Operation(summary = "Obtener la lista de todos los trabajadores de una tienda")
    @GetMapping("/store/{idStore}")
    public List<WorkerResponseDTO> getListWorkersByIdStore(@PathVariable Long idStore){
        log.info("Iniciando la consulta de trabajadores de la tienda " + idStore);
        var listWorker = workerUseCase.getListWorkersByIdStore(idStore);
        log.info("Terminado la consulta de trabajadores" + idStore);
        return listWorker;
    }

    @Operation(summary = "Obtener el trabajador por su dni")
    @GetMapping("/{dni}")
    public WorkerResponseDTO getWorkerById(@PathVariable String dni) {
        log.info("Buscando trabajador con dni {}", dni);
        var worker = workerUseCase.getWorkerByDni(dni);
        log.info("Encontrado trabajador con dni {}", dni);
        return worker;
    }

    @Operation(summary = "Obtener el trabajador por su nombre y apellidos")
    @GetMapping("/search")
    public List<WorkerResponseDTO> getWorkersByName(@RequestParam String name, @RequestParam String lastNames) {
        log.info("Buscando trabajador con nombre {} y apellidos {}", name, lastNames);
        var workers = workerUseCase.getWorkersByName(name, lastNames);
        log.info("Encontrados {} trabajadores", workers.size());
        return workers;
    }

    @Operation(summary = "Crear trabajador")
    @PostMapping
    public WorkerResponseDTO createWorker(@RequestBody WorkerRequestDTO workerDTO) {
        log.info("Creando trabajador {}", workerDTO);
        return workerUseCase.createWorker(workerDTO);
    }

    @Operation(summary = "Editar trabajador")
    @PutMapping("/{dni}")
    public WorkerResponseDTO updateWorker(@PathVariable String dni, @RequestBody WorkerRequestDTO workerDTO) {
        log.info("Editando trabajador con dni {}", dni);
        return workerUseCase.updateWorker(dni, workerDTO);
    }

    @Operation(summary = "Borrar trabajador")
    @DeleteMapping("/{dni}")
    public WorkerResponseDTO deleteWorker(@PathVariable String dni) {
        log.info("Borrando trabajador con dni {}", dni);
        return workerUseCase.deleteWorker(dni);
    }

    @Operation(summary = "Asignar seccion a trabajador")
    @PostMapping("/{dni}/assign")
    public WorkerResponseDTO assignWorker(@PathVariable String dni, @RequestParam String section, @RequestParam int hours) {
        log.info("Asignando al trabajador con dni {} la seccion %s %s horas", dni, section, hours);
        return workerUseCase.assignWorker(dni, section, hours);
    }

    @Operation(summary = "Desasignar seccion a trabajador")
    @PostMapping("/{dni}/unassign")
    public WorkerResponseDTO unassignWorker(@PathVariable String dni, @RequestParam String section) {
        log.info("Desasignando al trabajador con dni {} la seccion %s", dni, section);
        return workerUseCase.unassignWorker(dni, section);
    }

}
