package com.game.validation;

import com.game.dto.PlayerCreateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public final class PlayerCreateDTOValidator extends PlayerDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Objects.equals(clazz, PlayerCreateDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!supports(target.getClass())) {
            errors.reject("", "Wrong class");
            return;
        }
        PlayerCreateDTO playerCreateDTO = (PlayerCreateDTO) target;
        if (playerCreateDTO.getRace() == null || playerCreateDTO.getProfession() == null) {
            errors.reject("", "Not all parameters are specified");
            return;
        }
        super.validate(playerCreateDTO, errors);
    }
}
