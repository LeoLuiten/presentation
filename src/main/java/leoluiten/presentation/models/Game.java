package leoluiten.presentation.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    private Long id;

    private String game;

    private String player;

    private String createdDate;

    private String status;
}
