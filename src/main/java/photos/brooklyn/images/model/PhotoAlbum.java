package photos.brooklyn.images.model;

import java.util.List;
import java.util.Locale;

import photos.brooklyn.utils.LocaleSensitive;

public class PhotoAlbum implements LocaleSensitive{
    private Album albumData;
    private List<Photo> photos;
    public Album getAlbumData() {
        return albumData;
    }
    public void setAlbumData(Album albumData) {
        this.albumData = albumData;
    }
    public List<Photo> getPhotos() {
        return photos;
    }
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    public PhotoAlbum() {}
    public PhotoAlbum(Album albumData, List<Photo> photos) {
        this.albumData = albumData;
        this.photos = photos;
    }
    @Override
    public void setLocale(Locale locale) {
        albumData.setLocale(locale);
    }
}
