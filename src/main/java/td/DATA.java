package td;

import com.alibaba.fastjson.annotation.JSONField;

public class DATA {
    private String TimestampUtc;
    private String Name;
    private String TtlInMinutes;

    public DATA(String timestampUtc, String name, String ttlInMinutes) {
        TimestampUtc = timestampUtc;
        Name = name;
        TtlInMinutes = ttlInMinutes;
    }
    @JSONField(name = "TimestampUtc")
    public String getTimestampUtc() {
        return TimestampUtc;
    }

    public void setTimestampUtc(String timestampUtc) {
        TimestampUtc = timestampUtc;
    }
    @JSONField(name = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    @JSONField(name = "TtlInMinutes")
    public String getTtlInMinutes() {
        return TtlInMinutes;
    }

    public void setTtlInMinutes(String ttlInMinutes) {
        TtlInMinutes = ttlInMinutes;
    }
}
