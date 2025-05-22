package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announce {
    private int id;
    private int user_id;
    private int company_id;
    private String company_name;
    private String address;
    private String content;
    private int available;
}
