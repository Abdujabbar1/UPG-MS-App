package uz.pdp.productserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.productserver.common.ApiResponse;
import uz.pdp.productserver.dtos.CharacteristicDto;
import uz.pdp.productserver.entity.Characteristic;
import uz.pdp.productserver.entity.CharacteristicCategory;
import uz.pdp.productserver.repository.CharacteristicCategoryRepo;
import uz.pdp.productserver.repository.CharacteristicRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CharacteristicService {
    private final CharacteristicRepo characteristicRepo;
    private final CharacteristicCategoryRepo categoryRepo;

    public ApiResponse getAllCharacteristics(){
        List<Characteristic> characteristicList = characteristicRepo.findAll();
        if (characteristicList.isEmpty()) {
            return new ApiResponse("Characteristics not found!", false);
        }
        return new ApiResponse("Success!", true, characteristicList);
    }

    public ApiResponse gatCharacteristicById(Long characteristicId){
        Optional<Characteristic> characteristicOptional = characteristicRepo.findById(characteristicId);
        if (characteristicOptional.isEmpty()) {
            return new ApiResponse("Characteristic not found!", false);
        }
        return new ApiResponse("Success!", true, characteristicOptional.get());
    }

    public ApiResponse addOrUpdateCharacteristic(CharacteristicDto characteristicDto, Long id){
        if (id != null){
            try {
                Optional<Characteristic> characteristic = characteristicRepo.findById(id);
                if (characteristic.isEmpty()) {
                    return new ApiResponse("Characteristic not found!", false);
                }
                Optional<CharacteristicCategory> categoryOptional = categoryRepo.findById(characteristicDto.getCategoryId());
                if (categoryOptional.isEmpty()) {
                    return new ApiResponse("Characteristic Category not found!", false);
                }

                Characteristic newCharacteristic = characteristic.get();
                newCharacteristic.setName(characteristicDto.getName());
                newCharacteristic.setCategory(categoryOptional.get());

                characteristicRepo.save(newCharacteristic);

                return new ApiResponse("Success!", true, newCharacteristic);
            } catch (Exception e){
                return new ApiResponse("Error!", false);
            }
        }
        try {
            Optional<CharacteristicCategory> categoryOptional = categoryRepo.findById(characteristicDto.getCategoryId());
            if (categoryOptional.isEmpty()) {
                return new ApiResponse("Characteristic Category not found!", false);
            }

            Characteristic characteristic = new Characteristic();
            characteristic.setName(characteristicDto.getName());
            characteristic.setCategory(categoryOptional.get());

            characteristicRepo.save(characteristic);

            return new ApiResponse("Success!", true, characteristic);
        } catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse deleteCharacteristic(Long id){
        Optional<Characteristic> characteristicOptional = characteristicRepo.findById(id);
        if (characteristicOptional.isEmpty()) {
            return new ApiResponse("Not found!", false);
        }
        characteristicRepo.delete(characteristicOptional.get());

        return new ApiResponse("Success!", true);
    }
}
