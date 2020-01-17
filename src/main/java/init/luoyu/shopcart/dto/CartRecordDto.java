package init.luoyu.shopcart.dto;



import lombok.Data;
import lombok.ToString;


public class CartRecordDto {

    private Integer pid;

    private String pName;

    private double price;

    private String type;

    private Integer num;

    private String image;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "CartRecordDto{" +
                "pid=" + pid +
                ", pName='" + pName + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", num=" + num +
                ", image='" + image + '\'' +
                '}';
    }
}
