package module.login.bean;


import com.stx.xhb.xbanner.entity.BaseBannerInfo;

public class LoadingImgBean implements BaseBannerInfo {


    /**
     * image : http://winpower.wljueli.com/assets/img/qrcode.png
     * url : /
     * title : test
     */

    private String image;
    private String url;
    private String title;

    @Override
    public Object getXBannerUrl() {
        return image;
    }

    @Override
    public String getXBannerTitle() {
        return title;
    }

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
