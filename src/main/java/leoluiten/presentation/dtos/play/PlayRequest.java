package leoluiten.presentation.dtos.play;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes(
        @JsonSubTypes.Type(value = PlayRpsDto.class)
)
public interface PlayRequest {
}
