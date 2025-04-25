package engine.engine.Interaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EngineModel {
    private int x;
    private int y;
    private int nex;
    private int ney;
    String from;
    String to;
    String fromSquare;
    String toSquare;
}
