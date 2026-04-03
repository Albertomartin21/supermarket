package commercadona.supermarket.infraestructure.dtos.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoggerResponseDTO {
    private String user;
    private String msg;
}
