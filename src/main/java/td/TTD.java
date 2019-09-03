package td;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class TTD {
    private String DataProviderId;
    private List<TDID> Items;

    public TTD(String dataProviderId, List<TDID> items) {
        DataProviderId = dataProviderId;
        Items = items;
    }
    @JSONField(name = "DataProviderId")
    public String getDataProviderId() {
        return DataProviderId;
    }

    public void setDataProviderId(String dataProviderId) {
        DataProviderId = dataProviderId;
    }
    @JSONField(name = "Items")
    public List<TDID> getItems() {
        return Items;
    }

    public void setItems(List<TDID> items) {
        Items = items;
    }
}
