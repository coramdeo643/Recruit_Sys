package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announce {
    private int id;
    private int userId;
    private int companyId;
    private String companyName;
    private String address;
    private String content;
    private int available;
}
