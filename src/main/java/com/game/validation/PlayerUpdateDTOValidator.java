package com.game.validation;

import com.game.dto.PlayerUpdateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public final class PlayerUpdateDTOValidator extends PlayerDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Objects.equals(clazz, PlayerUpdateDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!supports(target.getClass())) {
            errors.reject("", "Wrong class");
            return;
        }
        PlayerUpdateDTO playerUpdateDTO = (PlayerUpdateDTO) target;
        if(playerUpdateDTO.getName() != null){
            validateName(playerUpdateDTO.getName(), errors);
        }
        if(playerUpdateDTO.getTitle() != null){
            validateTitle(playerUpdateDTO.getTitle(), errors);
        }
        if(playerUpdateDTO.getExperience() != null){
            validateExperience(playerUpdateDTO.getExperience(), errors);
        }
        if(playerUpdateDTO.getBirthday() != null){
            validateBirthday(playerUpdateDTO.getBirthday(), errors);
        }
    }
}
