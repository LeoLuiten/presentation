package leoluiten.presentation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private Long id;

    private String code;

    private String name;

    private String description;

    private String rules;
}
