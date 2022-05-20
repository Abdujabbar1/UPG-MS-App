package uz.pdp.productserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.productserver.common.ApiResponse;
import uz.pdp.productserver.dtos.CharacteristicDto;
import uz.pdp.productserver.service.CharacteristicService;

@RestController
@RequestMapping("/api/characteristic")
@RequiredArgsConstructor
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    @GetMapping
    public HttpEntity<?> getAllCharacteristic() {
        ApiResponse allCharacteristic = characteristicService.getAllCharacteristics();
        return ResponseEntity.status(allCharacteristic.isStatus() ? 200 : 404).body(allCharacteristic);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCharacteristicById(@PathVariable Long id) {
        ApiResponse characteristicById = characteristicService.gatCharacteristicById(id);
        return ResponseEntity.status(characteristicById.isStatus() ? 200 : 404).body(characteristicById);
    }

    @PostMapping
    public HttpEntity<?> addCharacteristic(@RequestBody CharacteristicDto characteristicDto,
                                           Long id) {
        ApiResponse apiResponse = characteristicService.addOrUpdateCharacteristic(characteristicDto, id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateCharacteristic(@RequestBody CharacteristicDto characteristicDto,
                                              @PathVariable Long id) {
        ApiResponse apiResponse = characteristicService.addOrUpdateCharacteristic(characteristicDto, id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCharacteristic(@PathVariable Long id) {
        ApiResponse apiResponse = characteristicService.deleteCharacteristic(id);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 404).body(apiResponse);
    }


}
