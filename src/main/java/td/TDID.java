package td;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class TDID {
    private String TDID;
    private List<DATA> Data;

    public TDID(String TDID, List<DATA> data) {
        this.TDID = TDID;
        Data = data;
    }
//    @JSONField(name = "TDID")
    public String getTDID() {
        return TDID;
    }

    public void setTDID(String TDID) {
        this.TDID = TDID;
    }
//    @JSONField(name = "Data")
    public List<DATA> getData() {
        return Data;
    }

    public void setData(List<DATA> data) {
        Data = data;
    }
}
