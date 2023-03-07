package kim.nzxy.ly.modules.system.vo;

import lombok.Data;

/**
 * @author ly-chn
 */
@Data
public class DictItemVO {
    private Long id;
    private String label;
    private String value;
    private String tagType;
    private String tips;
}
