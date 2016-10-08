package photos.brooklyn.images.model;

import java.util.List;
import java.util.Locale;

import photos.brooklyn.utils.LocaleSensitive;

public class Gallery implements LocaleSensitive{
    private GalleryMetaData metaData;
    private List<PhotoAlbum> photoAlbums;
    public List<PhotoAlbum> getPhotoAlbums() {
        return photoAlbums;
    }
    public void setPhotoAlbums(List<PhotoAlbum> photoAlbums) {
        this.photoAlbums = photoAlbums;
    }
    public GalleryMetaData getMetaData() {
        return metaData;
    }
    public void setMetaData(GalleryMetaData metaData) {
        this.metaData = metaData;
    }
    @Override
    public void setLocale(Locale locale) {
        metaData.setLocale(locale);
        for(PhotoAlbum p : photoAlbums) {
            p.setLocale(locale);
        }
    }
}
