package bot;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Employee {
    @Setter
    @Getter
    private long id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private BigDecimal salary;
    @Setter
    @Getter
    private LocalDateTime toLocalDateTime;

    public void setCreatedDate(LocalDateTime toLocalDateTime) {
    }

}
