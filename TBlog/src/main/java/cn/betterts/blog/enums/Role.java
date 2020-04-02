package cn.betterts.blog.enums;


public enum Role {
//    定义两种角色
//    后面还可以增加

    ADMIN(1,"博主"),
    VISITOR(0,"访客");

    private Integer value;
    private String message;

    Role(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
