package dataHandle;

public class TableData {
    private String locationId; //课程在课表位置
    private String rowspan;   //夸几节
    private String content;   //内容

    public TableData(String locationId, String rowspan, String content) {
        this.locationId = locationId;
        this.rowspan = rowspan;
        this.content = content;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getRowspan() {
        return rowspan;
    }

    public void setRowspan(String rowspan) {
        this.rowspan = rowspan;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
