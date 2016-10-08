package photos.brooklyn.images.model;

import com.google.gdata.data.media.mediarss.MediaContent;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.photos.PhotoEntry;

public class Photo {
    private String url;
    private String thumbnailUrl;
    private String title;
    private int width;
    private int height;
    private int thumbnailHeight;
    private int thumbnailWidth;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public static Photo newInstance(PhotoEntry e) {
        Photo p = new Photo();
        MediaThumbnail tn = e.getMediaThumbnails().get(0);
        p.thumbnailUrl = tn.getUrl();
        p.thumbnailWidth = tn.getWidth();
        p.thumbnailHeight = tn.getHeight();
        MediaContent c = e.getMediaGroup().getContents().get(0);
        p.url= c.getUrl();
        p.width = c.getWidth();
        p.height = c.getHeight();
        p.title = e.getTitle().getPlainText();
        return p;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

}
