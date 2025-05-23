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

    public Announce(String company_name, String address, String content) {
        this.companyName = company_name;
        this.address = address;
        this.content = content;
    }
}
