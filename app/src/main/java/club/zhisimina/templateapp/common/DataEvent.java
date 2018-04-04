package club.zhisimina.templateapp.common;

/**
 * EventBus传递的数据
 */
public class DataEvent {
    /**
     * 类型
     */
    private int type;
    /**
     * 数据
     */
    private Object data;
    /**
     * 其他标识
     */
    private int whit;
    /**
     * 另加标识
     */
    private int other;
    /**
     * 其他数据
     */
    private Object otherData;
    private String message;
    public DataEvent() {
    }

    public DataEvent(int type) {
        this.type = type;
    }

    public DataEvent(int type, Object data) {
        this.type = type;
        this.data = data;
    }
    public DataEvent(int type, Object data, Object otherData) {
        this.type = type;
        this.data = data;
        this.otherData = otherData;
    }
    public DataEvent(int type, Object data, int whit) {
        this.type = type;
        this.data = data;
        this.whit = whit;
    }
    public DataEvent(int type, Object data, int whit,int other) {
        this.type = type;
        this.data = data;
        this.whit = whit;
        this.other=other;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getWhit() {
        return whit;
    }

    public void setWhit(int whit) {
        this.whit = whit;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }
}
